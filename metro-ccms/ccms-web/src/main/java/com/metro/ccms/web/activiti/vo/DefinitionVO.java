package com.metro.ccms.web.activiti.vo;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.Date;

/**
 * yuan
 *
 * @author yuan
 */
public class DefinitionVO {
    private String id;
    private String key;
    private String deploymentId;
    private String category;
    private String resourceName;
    private String diagramResourceName;
    private Date deploymentTime;
    private String tenantId;

    public DefinitionVO(ProcessDefinition processDefinition, Deployment deployment) {
        this.setId(processDefinition.getId());
        this.setKey(processDefinition.getKey());
        this.setDeploymentId(processDefinition.getDeploymentId());
        this.setCategory(processDefinition.getCategory());
        this.setResourceName(processDefinition.getResourceName());
        this.setDiagramResourceName(processDefinition.getDiagramResourceName());
        this.setDeploymentTime(deployment.getDeploymentTime());
        this.setTenantId(deployment.getTenantId());
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDeploymentId() {
        return this.deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDiagramResourceName() {
        return this.diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    public String toString() {
        return "DefinitionVO{id='" + this.id + '\'' + ", key='" + this.key + '\'' + ", deploymentId='" + this.deploymentId + '\'' + ", category='" + this.category + '\'' + ", resourceName='" + this.resourceName + '\'' + ", diagramResourceName='" + this.diagramResourceName + '\'' + '}';
    }
}
