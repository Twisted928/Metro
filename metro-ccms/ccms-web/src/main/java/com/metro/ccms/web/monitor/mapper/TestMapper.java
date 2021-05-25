package com.metro.ccms.web.monitor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.metro.ccms.web.monitor.domain.MonitorDO;


/**
 * @author lw
 *  监控
 */
public interface TestMapper {
 
    public List<MonitorDO> lstJwMonitor(@Param("traceId") String traceId);

}