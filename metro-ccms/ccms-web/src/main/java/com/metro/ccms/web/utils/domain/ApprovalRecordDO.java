package com.metro.ccms.web.utils.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * @Author: fangyongjie
 * @Description: 审批记录表
 * @Date: Created in 14:28 2020/12/30
 * @Modified By:
 */
public class ApprovalRecordDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    /** 单号 */
    private String applicationNo;

    /** 审批角色id */
    private Long approvalRoleId;

    /** 审批角色 */
    private String approvalRole;

    /** 审批人id */
    private String approvalUserId;

    /** 审批人 */
    private String approvalUser;

    /** 审批额度 */
    private BigDecimal creditLimit;

    /** 审批账期 */
    private Integer creditDays;

    /** 审批意见 APPROVE审批通过   REJECT 审批拒绝 */
    private String approvalOpinion;

    /** 流程实例id */
    private String instanceId;

    /** 任务id */
    private String taskId;

    /** 模块类型 */
    private Integer type;

    /** 状态(0：无效，1：有效) */
    private Integer status;

    /** 删除标记(0：正常，1：已删除) */
    private Integer deleted;

    //冗余字段
    /**
     * 是否待办 1是 0否
     */
    private Integer ifTask;

    public Integer getIfTask() {
        return ifTask;
    }

    public void setIfTask(Integer ifTask) {
        this.ifTask = ifTask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public Long getApprovalRoleId() {
        return approvalRoleId;
    }

    public void setApprovalRoleId(Long approvalRoleId) {
        this.approvalRoleId = approvalRoleId;
    }

    public String getApprovalRole() {
        return approvalRole;
    }

    public void setApprovalRole(String approvalRole) {
        this.approvalRole = approvalRole;
    }

    public String getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(String approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    public String getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Integer getCreditDays() {
        return creditDays;
    }

    public void setCreditDays(Integer creditDays) {
        this.creditDays = creditDays;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
