package com.metro.ccms.web.activiti.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ：yuanjh
 * @description：任务实例
 * @modified By：
 * @version: 1.0
 */
public class TaskDO implements Serializable {

    public TaskDO(String taskId, String username, String comment, Boolean end, Map<String, Object> variable) {
        this.taskId = taskId;
        this.username = username;
        this.comment = comment;
        this.end = end;
        this.variable = variable;
    }

    /**
     * 任务ID
     */
    private String taskId;
    /**
     * 审批人ID
     */
    private String username;
    /**
     * 审批意见
     */
    private String comment;
    /**
     * 是否结束流程
     */
    private Boolean end;
    /**
     * 任务参数
     */
    private Map<String, Object> variable;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Object> getVariable() {
        return variable;
    }

    public void setVariable(Map<String, Object> variable) {
        this.variable = variable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }
}
