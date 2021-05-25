package com.metro.ccms.web.monitor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.ccms.web.monitor.domain.MonitorDO;
import com.metro.ccms.web.monitor.mapper.MonitorMapper;
import com.metro.ccms.web.monitor.service.MonitorService;
@Service
public class MonitorServiceImpl implements MonitorService {

	@Autowired
    private MonitorMapper monitorMapper;

	@Override
	public void saveMonitor(MonitorDO monitor) {
		monitorMapper.insertMonitor(monitor);
	}

}
