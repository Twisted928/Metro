package com.metro.ccms.framework.monitor.interceptor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.metro.ccms.framework.monitor.JwMonitor;
import com.metro.ccms.framework.monitor.JwMonitorContext;
import com.metro.ccms.framework.monitor.JwMonitorContextHolder;

/**
 * @author lw
 * Controller/Service/Mapper 拦截监控执行时间成本
 */
@Component
@Aspect
public class JwMonitorAspect {
    private static final String CONTROLLER="Controller";
    private static final String SERVICE="Service";
    private static final String DAO="DAO";
    private static final Set<String> ignoreUrl=new HashSet<String>() {
	private static final long serialVersionUID = 1L;

	{
		add("com.fasterxml.jackson.databind.ObjectMapper");
		add("com.metro.ccms.common.core.controller.BaseController");
	}}; 

	public JwMonitorAspect() {
	}

	@Pointcut("execution(* com..*.*Controller.*(..))")
	public void pointCutForController() {
	}

	@Pointcut("execution(* com..*.*Service.*(..))")
	public void pointCutForService() {
	}

	@Pointcut("execution(* com..*.*Mapper.*(..))")
	public void pointCutForDAO() {
	}

	/**
	 * 环绕增强：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
	 *
	 * @return
	 * @throws Throwable
	 */
	@Around("pointCutForController()")
	public Object aroundMethodForController(ProceedingJoinPoint jp) throws Throwable {
		return aroundMethod(jp,CONTROLLER);
	}

	@Around("pointCutForService()")
	public Object aroundMethodForService(ProceedingJoinPoint jp) throws Throwable {
		return aroundMethod(jp,SERVICE);
	}

	@Around("pointCutForDAO()")
	public Object aroundMethodForDAO(ProceedingJoinPoint jp) throws Throwable {
		return aroundMethod(jp,DAO);
	}
	
	private Object aroundMethod(ProceedingJoinPoint jp,String typeName) throws Throwable{
		JwMonitorContext jwMonitorContext = JwMonitorContextHolder.get();
		String methodName = jp.getSignature().getDeclaringType().getName();
		Stack<JwMonitor> cacheStack = jwMonitorContext.getCacheStack();
		Stack<JwMonitor> monitorStack = null;
		JwMonitor jwMonitor = null;
		Date startDate = new Date(), endDate = new Date();
		long startTime = System.currentTimeMillis();
		long endTime, sqlCost;
		if(ignoreUrl.contains(methodName)) {
			return jp.proceed();
		}
		if (null == cacheStack || cacheStack.isEmpty()) {
			return jp.proceed();
		}
		JwMonitor tempJwMonitor = cacheStack.peek();
		if(tempJwMonitor.getIfRecursion()==false) {
			return jp.proceed();
		}
		jwMonitor = new JwMonitor();
		jwMonitor.setTraceId(tempJwMonitor.getTraceId());
		jwMonitor.setParentUuid(tempJwMonitor.getUuid());
		jwMonitor.setUuid(UUID.randomUUID().toString());
		jwMonitor.setUri(methodName);
		jwMonitor.setClassType(typeName);
		cacheStack.push(jwMonitor);
		jwMonitorContext.setCacheStack(cacheStack);
		JwMonitorContextHolder.put(jwMonitorContext);
		Integer ifException=0;
		Object result = null;
		startTime = System.currentTimeMillis();
		try {
			result = jp.proceed();
		}catch(Exception ex) {
			//判断一下是否是由上个堆栈产生的错误
			ifException=1;
			throw ex;
		}finally {
			try {

				endTime = System.currentTimeMillis();
				sqlCost = endTime - startTime;
				Integer cacheUsed = cacheStack.pop().getCacheUsed();
				startDate.setTime(startTime);
				endDate.setTime(endTime);
				jwMonitor.setCacheUsed(cacheUsed);
				jwMonitor.setUri(methodName);
				jwMonitor.setClassType(typeName);
				jwMonitor.setCostTime(sqlCost);
				jwMonitor.setStartDate(startDate);
				jwMonitor.setEndDate(endDate);
				jwMonitor.setDdate(new Date());
				jwMonitor.setIfException(ifException);
				monitorStack = jwMonitorContext.getMonitorStack();
				monitorStack.push(jwMonitor);
				jwMonitorContext.setMonitorStack(monitorStack);
				JwMonitorContextHolder.put(jwMonitorContext);

			} catch (Exception e) {
			}
		}
		return result;
	}
}
