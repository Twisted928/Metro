package com.metro.ccms.web.activiti.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metro.ccms.web.activiti.vo.TaskVO;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 授权单据表
 * Created by fangyongjie
 */
public class AutherDocumentDO implements Serializable {

    private Long id;
    /**
     * 主表id
     */
    private Long mainId;
    /**
     * 授权人id
     */
    private Long autherId;
    /**
     * 授权人姓名
     */
    private String autherUser;
    /**
     * 授权角色id
     */
    private String autherRoleId;
    /**
     * 授权角色名称
     */
    private String autherRoleName;
    /**
     * 接收人id
     */
    private Long receiveId;
    /**
     * 接收人姓名
     */
    private String receiveUser;
    /**
     * 接收人角色id
     */
    private Long receiveRoleId;
    /**
     * 接收人角色名称
     */
    private String receiveRoleName;
    /**
     * 授权单据号
     */
    private String applicationNo;
    /**
     * 授权单据类型
     */
    private Integer type;
    /**
     * 授权客户
     */
    private String autherCustomer;
    /**
     * 授权时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date autherTime;
    /**
     * 生效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    /**
     * 失效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 流程实例id
     */
    private String instanceId;
    /**
     * 任务id
     */
    private String taskId;

    private Integer status;

    //冗余字段
    /**
     * 单据授权主表单据号
     */
    private String mainNo;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMainNo() {
        return mainNo;
    }

    public void setMainNo(String mainNo) {
        this.mainNo = mainNo;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAutherId() {
        return autherId;
    }

    public void setAutherId(Long autherId) {
        this.autherId = autherId;
    }

    public String getAutherUser() {
        return autherUser;
    }

    public void setAutherUser(String autherUser) {
        this.autherUser = autherUser;
    }

    public String getAutherRoleId() {
        return autherRoleId;
    }

    public void setAutherRoleId(String autherRoleId) {
        this.autherRoleId = autherRoleId;
    }

    public String getAutherRoleName() {
        return autherRoleName;
    }

    public void setAutherRoleName(String autherRoleName) {
        this.autherRoleName = autherRoleName;
    }

    public Long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Long getReceiveRoleId() {
        return receiveRoleId;
    }

    public void setReceiveRoleId(Long receiveRoleId) {
        this.receiveRoleId = receiveRoleId;
    }

    public String getReceiveRoleName() {
        return receiveRoleName;
    }

    public void setReceiveRoleName(String receiveRoleName) {
        this.receiveRoleName = receiveRoleName;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAutherCustomer() {
        return autherCustomer;
    }

    public void setAutherCustomer(String autherCustomer) {
        this.autherCustomer = autherCustomer;
    }

    public Date getAutherTime() {
        return autherTime;
    }

    public void setAutherTime(Date autherTime) {
        this.autherTime = autherTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
}
