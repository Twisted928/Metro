package com.metro.ccms.web.activiti.config;

import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：yuanjh
 * @date ：Created in 2019/6/22
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Component
public class ActivitiConfig implements ProcessEngineConfigurationConfigurer {

    @Autowired
    private ComActivitiEventListener comActivitiEventListener;

    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {

        List<ActivitiEventListener> activitiEventListener = new ArrayList<ActivitiEventListener>();

        activitiEventListener.add(comActivitiEventListener);//配置全局监听器

        processEngineConfiguration.setEventListeners(activitiEventListener);
    }
}
