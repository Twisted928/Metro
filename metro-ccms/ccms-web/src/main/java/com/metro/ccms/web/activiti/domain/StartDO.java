package com.metro.ccms.web.activiti.domain;

import com.metro.ccms.web.activiti.config.BusinessEnum;

import java.util.List;
import java.util.Map;

/**
 * 流程发起
 *
 * @author yuan
 */
public class StartDO {

    /**
     * 业务场景Key
     */
    private BusinessEnum businessKey;
    /**
     * 审批流程标题
     */
    private String title;
    /**
     * 页面URL
     */
    private String formUrl;
    /**
     * 部门编码
     */
    private List<String> deptCodes;
    /**
     * 业务表id或单号
     */
    private String businessId;
    /**
     * 业务流程参数
     */
    private Map<String, Object> variable;
    /**
     * 公司名称/卡号名称
     */
    private String businessName;
    /**
     * 发布人
     */
    private List<String> publishUser;
    /**
     * 发起人
     */
    private String startUser;

    public StartDO() {
    }

    public BusinessEnum getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(BusinessEnum businessKey) {
        this.businessKey = businessKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormUrl() {
        return formUrl;
    }

    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl;
    }

    public List<String> getDeptCodes() {
        return deptCodes;
    }

    public void setDeptCodes(List<String> deptCodes) {
        this.deptCodes = deptCodes;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Map<String, Object> getVariable() {
        return variable;
    }

    public void setVariable(Map<String, Object> variable) {
        this.variable = variable;
    }

    public List<String> getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(List<String> publishUser) {
        this.publishUser = publishUser;
    }

    public String getStartUser() {
        return startUser;
    }

    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StartDO{");
        sb.append("businessKey=").append(businessKey);
        sb.append(", title='").append(title).append('\'');
        sb.append(", formUrl='").append(formUrl).append('\'');
        sb.append(", deptCode='").append(deptCodes).append('\'');
        sb.append(", businessId='").append(businessId).append('\'');
        sb.append(", variable=").append(variable);
        sb.append(", publishUser=").append(publishUser);
        sb.append(", startUser='").append(startUser).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
