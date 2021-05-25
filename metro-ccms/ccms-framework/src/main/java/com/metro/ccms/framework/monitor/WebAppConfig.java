package com.metro.ccms.framework.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.metro.ccms.framework.monitor.interceptor.JwMonitorInterceptor;
/**
 * 类说明: webMVC启动配置
 * 
 * @author liuWei
 * @version 创建时间：2017年6月29日 上午9:55:15
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
	@Autowired
	private JwMonitorInterceptor jwMonitorInterceptor;
    @SuppressWarnings("deprecation")
	@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//监控拦截器	
		registry.addInterceptor(jwMonitorInterceptor);
	}
}
