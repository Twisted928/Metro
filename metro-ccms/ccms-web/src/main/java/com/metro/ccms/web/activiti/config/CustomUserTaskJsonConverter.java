package com.metro.ccms.web.activiti.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.CustomProperty;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.editor.language.json.converter.UserTaskJsonConverter;

import java.util.Map;

public class CustomUserTaskJsonConverter extends UserTaskJsonConverter {
    public CustomUserTaskJsonConverter() {
    }

    protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
        FlowElement flowElement = super.convertJsonToElement(elementNode, modelNode, shapeMap);
        UserTask userTask = (UserTask)flowElement;
        CustomProperty customProperty = new CustomProperty();
        customProperty.setName("usertask");
        customProperty.setSimpleValue(this.getPropertyValueAsString("usertask", elementNode));
        userTask.getCustomProperties().add(customProperty);
        return userTask;
    }

    protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
        super.convertElementToJson(propertiesNode, baseElement);
    }
}
