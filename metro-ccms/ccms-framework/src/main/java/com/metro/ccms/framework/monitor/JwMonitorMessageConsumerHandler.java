package com.metro.ccms.framework.monitor;

/**
 * @author lw
 * 监控消息handler。回调客户端要消费的监控消息
 */
public interface JwMonitorMessageConsumerHandler {
     /**
      * 回调待消费的消息
     * @param jwMonitor 监控消息
     */
    public void messageConsumer(JwMonitor jwMonitor);
}
