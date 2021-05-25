package com.metro.ccms.web.activiti.query;


import com.metro.ccms.common.core.query.BaseQuery;

/**
 * 单据授权分页查询model
 * Created by fangyongjie
 */
public class AutherDocumentQuery extends BaseQuery {

    /**
     * 授权人姓名
     */
    private String autherUser;
    /**
     * 单据号
     */
    private String applicationNo;
    /**
     * 接收人姓名
     */
    private String receiveUser;
    /**
     * 状态 0失效 1有效
     */
    private Integer status;

    public String getAutherUser() {
        return autherUser;
    }

    public void setAutherUser(String autherUser) {
        this.autherUser = autherUser;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
