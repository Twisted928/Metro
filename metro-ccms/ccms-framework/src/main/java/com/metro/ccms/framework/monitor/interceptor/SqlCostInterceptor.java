package com.metro.ccms.framework.monitor.interceptor;

import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Stack;
import java.util.UUID;
import java.util.regex.Matcher;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.metro.ccms.framework.monitor.JwMonitor;
import com.metro.ccms.framework.monitor.JwMonitorContext;
import com.metro.ccms.framework.monitor.JwMonitorContextHolder;

 
/**
 * @author lw
 * Sql执行时间记录拦截器
 */
@Intercepts({
		@Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }),
		@Signature(type = StatementHandler.class, method = "update", args = { Statement.class }),
		@Signature(type = StatementHandler.class, method = "batch", args = { Statement.class }) })
public class SqlCostInterceptor implements Interceptor {
    private static final String GETTER_H="h";
    private static final String GETTER_TARGET="target";
    private static final String DELEGATE_MAPPEDSTATEMENT="delegate.mappedStatement";
    private static final String DELEGATE_BOUNDSQL="delegate.boundSql";
    
    
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object returnObj = null;
		Date startDate = new Date(), endDate = new Date();
		long startTime = System.currentTimeMillis();
		long endTime, sqlCost;
		JwMonitor jwMonitor = null;
		JwMonitorContext jwMonitorContext = JwMonitorContextHolder.get();
		Stack<JwMonitor> cacheStack = jwMonitorContext.getCacheStack();
		if (null == cacheStack || cacheStack.isEmpty()) {
			return invocation.proceed();
		}
        
		//调用正在的类
		returnObj = invocation.proceed();

		try {
			JwMonitor tempJwMonitor = cacheStack.peek();
			if(tempJwMonitor.getIfRecursion()==false) {
				return returnObj;
			}
			endTime = System.currentTimeMillis();
			sqlCost = endTime - startTime;
            
			//获取最终sql语句
			StatementHandler stmtHandler = (StatementHandler) invocation.getTarget();
			MetaObject metaStmtHandler = SystemMetaObject.forObject(stmtHandler);
			while (metaStmtHandler.hasGetter(GETTER_H)) {
				Object object = metaStmtHandler.getValue(GETTER_H);
				metaStmtHandler = SystemMetaObject.forObject(object);
			}
			while (metaStmtHandler.hasGetter(GETTER_TARGET)) {
				Object object = metaStmtHandler.getValue(GETTER_TARGET);
				metaStmtHandler = SystemMetaObject.forObject(object);
			}
			MappedStatement mappedStatement = (MappedStatement) metaStmtHandler.getValue(DELEGATE_MAPPEDSTATEMENT);
			// BoundSql就是封装myBatis最终产生的sql类
			BoundSql boundSql = (BoundSql) metaStmtHandler.getValue(DELEGATE_BOUNDSQL); 
			// 获取节点的配置
			Configuration configuration = mappedStatement.getConfiguration(); 
			String sql = showSql(configuration, boundSql);

			//组装监控信息
			startDate.setTime(startTime);
			endDate.setTime(endTime);
			Stack<JwMonitor> monitorStack = jwMonitorContext.getMonitorStack();

			jwMonitor = new JwMonitor();
			jwMonitor.setTraceId(tempJwMonitor.getTraceId());
			jwMonitor.setParentUuid(tempJwMonitor.getUuid());
			jwMonitor.setUuid(UUID.randomUUID().toString());
			jwMonitor.setClassType("dao-sql");
			jwMonitor.setCostTime(sqlCost);
			jwMonitor.setStartDate(startDate);
			jwMonitor.setEndDate(endDate);
			jwMonitor.setDdate(new Date());
			jwMonitor.setSqlMemo(sql);

			monitorStack.push(jwMonitor);
			jwMonitorContext.setMonitorStack(monitorStack);
			JwMonitorContextHolder.put(jwMonitorContext);
		} catch(Exception ex) {

		}

		return returnObj;

	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));

			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
					}
				}
			}
		}
		return sql;
	}

	private String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}
}
