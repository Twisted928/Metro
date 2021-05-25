package com.metro.ccms.web.monitor.handler;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metro.ccms.framework.monitor.JwMonitor;
import com.metro.ccms.framework.monitor.JwMonitorMessageConsumerHandler;
import com.metro.ccms.web.monitor.domain.MonitorDO;
import com.metro.ccms.web.monitor.service.MonitorService;
/**
 * @author lw
 * 消费监控数据
 */
@Component
public class JwMonitorMessageConsumeHandlerImpl implements JwMonitorMessageConsumerHandler {
	@Autowired
    private MonitorService monitorService;
	@Override
	public void messageConsumer(JwMonitor jwMonitor) {
		System.out.print("===========================消费数据===========================");
		MonitorDO monitorDO = new MonitorDO();
		BeanUtils.copyProperties(jwMonitor, monitorDO);
		monitorService.saveMonitor(monitorDO);
	}
 

}