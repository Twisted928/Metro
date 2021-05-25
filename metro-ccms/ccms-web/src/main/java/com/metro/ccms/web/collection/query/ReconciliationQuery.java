package com.metro.ccms.web.collection.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;
import java.util.Date;

/**
* @description 对账数据查询对象
* @author weiwenhui
* @date 2020/12/14 11:11
*/
public class ReconciliationQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 对账单号
     */
    private String applicationNo;
    /**
     * 部门编码
     */
    private String departCode;
    /**
     * 客户编码
     */
    private String custCode;
    /**
     * 客户名称
     */
    private String custName;
    /**
     * 卡号编码
     */
    private String cardCode;
    /**
     * 卡号名称
     */
    private String cardName;
    /**
     * 对账状态
     */
    private Integer status;
    /**
     * 写入日期（对账日期）
     */
    private Date ddate;
    /**
     * 删除标记（0正常 1删除）
     */
    private Integer deleted;

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

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
