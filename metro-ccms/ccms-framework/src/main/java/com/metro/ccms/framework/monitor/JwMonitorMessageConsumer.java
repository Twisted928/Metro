package com.metro.ccms.framework.monitor;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.metro.ccms.common.utils.spring.SpringUtils;
import com.metro.ccms.framework.monitor.config.JwMonitorConfig;

/**
 * @author lw
 * 
 */
public class JwMonitorMessageConsumer extends Thread {

	@Override
	public void run() {
		BlockingQueue<Stack<JwMonitor>> queueMonitor=JwMonitorConfig.queueMonitor;
		JwMonitorMessageConsumerHandler jwMonitorMessageConsumerHandler=SpringUtils.getBean(JwMonitorMessageConsumerHandler.class);
		Stack<JwMonitor> stackMonitor=null;
		JwMonitor jwMonitor = null;
		while (true) {
			try {
				stackMonitor=queueMonitor.poll(120, TimeUnit.SECONDS);
				if(null!=stackMonitor) {
					while(stackMonitor.size()!=0) {
						jwMonitor=stackMonitor.pop();
						jwMonitorMessageConsumerHandler.messageConsumer(jwMonitor);
					}
				}
			} catch (Exception ex) {

			}

		}

	}
}