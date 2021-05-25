package com.metro.ccms.web.activiti.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: fangyongjie
 * @Description: 授权单据主表
 * @Date: Created in 17:23 2021/1/8
 * @Modified By:
 */
public class AutherDocumentMainDO implements Serializable {

    private Long id;
    /**
     * 授权编号
     */
    private String applicationNo;
    /**
     * 授权人id
     */
    private Long autherId;
    /**
     * 授权人姓名
     */
    private String autherUser;
    /**
     * 授权时间
     */
    private Date autherTime;


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

    public Date getAutherTime() {
        return autherTime;
    }

    public void setAutherTime(Date autherTime) {
        this.autherTime = autherTime;
    }
}
