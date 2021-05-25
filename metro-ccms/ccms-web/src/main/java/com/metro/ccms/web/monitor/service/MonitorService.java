package com.metro.ccms.web.monitor.service;

import com.metro.ccms.web.monitor.domain.MonitorDO;

/**
 * @author lw
 * 监控服务
 */
public interface MonitorService {
    /**
     * 保存监控消息
     * @param monitor
     */
    public void saveMonitor(MonitorDO monitor);
    

}