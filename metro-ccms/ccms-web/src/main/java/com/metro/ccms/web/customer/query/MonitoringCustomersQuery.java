package com.metro.ccms.web.customer.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;

/**
 *<p>
 *  监控客户清单
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/4
 * @desc
 **/
public class MonitoringCustomersQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 客户编码
     */
    private String custcode;
    /**
     * 客户名称
     */
    private String custname;
    /**
     * 睡眠开始时间
     */
    private String validfromstr;
    /**
     * 睡眠开始时间
     */
    private String validfromend;
    /**
     * 再次交易时间
     */
    private String validtostr;
    /**
     * 再次交易时间
     */
    private String validtoend;
    /**
     * 状态(0：无效，1：有效)
     */
    private String status;
    /**
     * 删除标记(0：被删除，1：正常)
     */
    private String deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "MonitoringCustomersQuery{" +
                "id='" + id + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", validfromstr='" + validfromstr + '\'' +
                ", validfromend='" + validfromend + '\'' +
                ", validtostr='" + validtostr + '\'' +
                ", validtoend='" + validtoend + '\'' +
                ", status='" + status + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
