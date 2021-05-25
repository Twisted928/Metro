package com.metro.ccms.web.activiti.config;

import org.activiti.bpmn.model.BaseElement;
import org.activiti.editor.language.json.converter.BaseBpmnJsonConverter;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;

import java.util.Map;

public class CustomBpmnJsonConverter extends BpmnJsonConverter {
    public CustomBpmnJsonConverter() {
    }

    public static Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> getConvertersToJsonMap() {
        return convertersToJsonMap;
    }

    public static Map<String, Class<? extends BaseBpmnJsonConverter>> getConvertersToBpmnMap() {
        return convertersToBpmnMap;
    }
}