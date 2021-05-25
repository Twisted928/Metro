package com.metro.ccms.web.customer.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;

/**
 *<p>
 *  白名单管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/4
 * @desc
 **/
public class WhitelistmanagementQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 申请单号
     */
    private String applicationno;
    /**
     * 客户编码
     */
    private String custcode;
    /**
     * 客户名称
     */
    private String custname;
    /**
     * 生效时间
     */
    private String validfromstr;
    /**
     * 生效时间
     */
    private String validfromend;
    /**
     * 到期时间
     */
    private String validtostr;
    /**
     * 到期时间
     */
    private String validtoend;
    /**
     * 审批状态：未审批   1已提交   2审批通过   3退回
     */
    private String approvestatus;
    /**
     * 删除标记(0：被删除，1：正常)
     */
    private String deleted;
    /**
     * 有效标记(0：无效，1：有效)
     */
    private String status;
    /**
     * 申请时间
     */
    private String applytimestr;
    /**
     * 申请时间
     */
    private String applytimeend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationno() {
        return applicationno;
    }

    public void setApplicationno(String applicationno) {
        this.applicationno = applicationno;
    }

    public String getCustcode() {
        return custcode;
    }

    public void setCustcode(String custcode) {
        this.custcode = custcode;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getValidfromstr() {
        return validfromstr;
    }

    public void setValidfromstr(String validfromstr) {
        this.validfromstr = validfromstr;
    }

    public String getValidfromend() {
        return validfromend;
    }

    public void setValidfromend(String validfromend) {
        this.validfromend = validfromend;
    }

    public String getValidtostr() {
        return validtostr;
    }

    public void setValidtostr(String validtostr) {
        this.validtostr = validtostr;
    }

    public String getValidtoend() {
        return validtoend;
    }

    public void setValidtoend(String validtoend) {
        this.validtoend = validtoend;
    }

    public String getApprovestatus() {
        return approvestatus;
    }

    public void setApprovestatus(String approvestatus) {
        this.approvestatus = approvestatus;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getApplytimestr() {
        return applytimestr;
    }

    public void setApplytimestr(String applytimestr) {
        this.applytimestr = applytimestr;
    }

    public String getApplytimeend() {
        return applytimeend;
    }

    public void setApplytimeend(String applytimeend) {
        this.applytimeend = applytimeend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WhitelistmanagementQuery{" +
                "id='" + id + '\'' +
                ", applicationno='" + applicationno + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", validfromstr='" + validfromstr + '\'' +
                ", validfromend='" + validfromend + '\'' +
                ", validtostr='" + validtostr + '\'' +
                ", validtoend='" + validtoend + '\'' +
                ", approvestatus='" + approvestatus + '\'' +
                ", deleted='" + deleted + '\'' +
                ", status='" + status + '\'' +
                ", applytimestr='" + applytimestr + '\'' +
                ", applytimeend='" + applytimeend + '\'' +
                '}';
    }
}
