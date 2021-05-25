package com.metro.ccms.framework.monitor;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.metro.ccms.framework.monitor.config.JwMonitorConfig;
/**
 * @author lw
 * 链路跟踪生产者
 */
public class JwMonitorMessageProducer extends Thread {
	private static final int MAXCOSTTIME=1000;
	private Stack<JwMonitor> stackJwMonitor;
 
	public void setStackJwMonitor(Stack<JwMonitor> stackJwMonitor) {
		this.stackJwMonitor = stackJwMonitor;
	}

	public void run() { // 覆写run()方法，作为线程 的操作主体
		BlockingQueue<Stack<JwMonitor>> queueMonitor=JwMonitorConfig.queueMonitor;
		try {
			//附加逻辑,如果此服务秒数小于1秒,则不监控。如果出现exception则监控
 			JwMonitor jwMonitor=stackJwMonitor.peek();
			Map<String,Boolean> urlAllMonitor=JwMonitorConfig.urlAllMonitor;
			Boolean ifRecursion= urlAllMonitor.get(jwMonitor.getUri());
			//需要监控
			if(jwMonitor.getCostTime()>=MAXCOSTTIME || jwMonitor.getIfException()==JwMonitor.IS_ERROR) { 
				if(null!=ifRecursion && ifRecursion==false) {
					JwMonitorConfig.urlAllMonitor.put(jwMonitor.getUri(), true);
				}
			}else { //不需要监控
				if(null==ifRecursion || ifRecursion==true) {
					JwMonitorConfig.urlAllMonitor.put(jwMonitor.getUri(), false);
				}
			} 
			
			
			queueMonitor.offer(stackJwMonitor,2,TimeUnit.SECONDS);

		} catch (InterruptedException e) {
			
		}

	}

}
