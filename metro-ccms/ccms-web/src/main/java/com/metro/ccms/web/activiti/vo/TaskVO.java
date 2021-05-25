package com.metro.ccms.web.activiti.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ：yuanjh
 * @date ：Created in 2019/6/25
 * @description：待办
 * @modified By：
 * @version: 1.0
 */
public class TaskVO implements Serializable {

    /**
     * 任务id
     */
    private String taskId;
    /**
     * 标题
     */
    private String title;
    /**
     * 待办人
     */
    private String username;
    /**
     * 待办时间
     */
    private LocalDateTime createTime;
    /**
     * url
     */
    private String formUrl;
    /**
     * 业务表id或单号
     */
    private String businessId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 流程实例id
     */
    private String instanceId;
    /**
     * 公司名称/卡号名称
     */
    private String businessName;

    /**
     * 冗余字段 业务类型
     */
    private Integer type;
    /**
     * 冗余字段
     */
    private Date createTimeChange;


    public Date getCreateTimeChange() {
        return createTimeChange;
    }

    public void setCreateTimeChange(Date createTimeChange) {
        this.createTimeChange = createTimeChange;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getFormUrl() {
        return formUrl;
    }

    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public TaskVO(String taskId, String title, String username, LocalDateTime createTime, String formUrl, String businessId, String roleId) {
        this.taskId = taskId;
        this.title = title;
        this.username = username;
        this.createTime = createTime;
        this.formUrl = formUrl;
        this.businessId = businessId;
        this.roleId=roleId;
    }

    public TaskVO() {
    }
}
