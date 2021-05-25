package com.metro.ccms.web.monitor.service;

import java.util.List;

import com.metro.ccms.web.monitor.domain.MonitorDO;

public interface TestService {
     public List<MonitorDO> lst(String traceId);
}
