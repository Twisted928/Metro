package com.metro.ccms.web.monitor.mapper;

import com.metro.ccms.web.monitor.domain.MonitorDO;


/**
 * @author lw
 *  监控
 */
public interface MonitorMapper {
    /**插入监控信息
     * @param monitor
     */
    public void insertMonitor(MonitorDO monitor);

}