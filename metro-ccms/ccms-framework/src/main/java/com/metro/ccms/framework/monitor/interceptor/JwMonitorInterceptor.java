package com.metro.ccms.framework.monitor.interceptor;

import java.util.Date;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metro.ccms.common.utils.IpUtil;
import com.metro.ccms.framework.monitor.JwMonitor;
import com.metro.ccms.framework.monitor.JwMonitorContext;
import com.metro.ccms.framework.monitor.JwMonitorContextHolder;
import com.metro.ccms.framework.monitor.JwMonitorMessageProducer;
import com.metro.ccms.framework.monitor.config.JwMonitorConfig;

/**
 * 类说明: 监控拦截器(url 层拦截)
 * 
 * @author liuWei
 * @version 创建时间：2016年5月8日 下午8:41:13
 */
@Component
public class JwMonitorInterceptor extends HandlerInterceptorAdapter {
	private static final String URL_ERROR="/error";
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		JwMonitorContext jwMonitorContext = JwMonitorContextHolder.get();
		Stack<JwMonitor> cacheStack = jwMonitorContext.getCacheStack();
		JwMonitor jwMonitor = null;
		Date startDate = new Date();
		long startTime = System.currentTimeMillis();
		if (!(null == cacheStack || cacheStack.isEmpty())) {
			return super.preHandle(request, response, handler);
		}
		String requestUrl = request.getServletPath();
		if(URL_ERROR.equals(requestUrl)) {
			return super.preHandle(request, response, handler);
		}
		Boolean ifRecursion=null;
		Map<String,Boolean> urlAllMonitor=JwMonitorConfig.urlAllMonitor;
		ifRecursion=urlAllMonitor.get(requestUrl);
		if(null==ifRecursion) {
			ifRecursion=true;
		}
		Map<String, String[]> mappar=request.getParameterMap();
		ObjectMapper mapper = new ObjectMapper();
		String jsonPar = mapper.writeValueAsString(mappar);

		cacheStack = new Stack<JwMonitor>();
		jwMonitor = new JwMonitor();
		jwMonitor.setTraceId(UUID.randomUUID().toString());
		jwMonitor.setUuid(UUID.randomUUID().toString());
		jwMonitor.setParentUuid("");
		jwMonitor.setUri(requestUrl);
		jwMonitor.setClassType("Url");
		jwMonitor.setStartTimeInMillis(startTime);
		jwMonitor.setStartDate(startDate);
		jwMonitor.setIpAddress(IpUtil.getIpAddress(request));
		jwMonitor.setInputParameter(jsonPar.length()>=256?jsonPar.substring(0, 255):jsonPar);
		jwMonitor.setIfRecursion(ifRecursion);
		cacheStack.push(jwMonitor);
		jwMonitorContext.setCacheStack(cacheStack);
		JwMonitorContextHolder.put(jwMonitorContext);
		


		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		JwMonitorContext jwMonitorContext = JwMonitorContextHolder.get();
		long endTime= System.currentTimeMillis(),costTime;
		Date endDate=new Date();
		Stack<JwMonitor> cacheStack =null;
		JwMonitor jwMonitor=null;
		Stack<JwMonitor> monitorStack = null;
		String requestUrl = request.getServletPath();
		if(URL_ERROR.equals(requestUrl)) {
			return ;
		}
		try {
			cacheStack = jwMonitorContext.getCacheStack();
			jwMonitor=cacheStack.pop();
			costTime = endTime - jwMonitor.getStartTimeInMillis();
	        jwMonitor.setEndDate(endDate);
	        jwMonitor.setCostTime(costTime);
	        jwMonitor.setDdate(new Date());	        
			monitorStack = jwMonitorContext.getMonitorStack();
			monitorStack.push(jwMonitor);
			JwMonitorMessageProducer messageProducer = new JwMonitorMessageProducer();
			messageProducer.setStackJwMonitor(monitorStack);
			threadPoolTaskExecutor.submit(messageProducer);
		}catch(Exception e) {
			
		}finally {
			JwMonitorContextHolder.clear();
		}

	}
}
