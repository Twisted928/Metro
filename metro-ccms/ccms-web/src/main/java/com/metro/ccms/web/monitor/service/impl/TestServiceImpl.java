package com.metro.ccms.web.monitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.ccms.web.monitor.domain.MonitorDO;
import com.metro.ccms.web.monitor.mapper.TestMapper;
import com.metro.ccms.web.monitor.service.TestService;
@Service
public class TestServiceImpl implements TestService {
	@Autowired
    private TestMapper testMapper;
	@Override
	public List<MonitorDO> lst(String traceId) {
		return testMapper.lstJwMonitor(traceId);
	}

}
