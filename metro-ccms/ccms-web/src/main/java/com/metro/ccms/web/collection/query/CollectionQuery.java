package com.metro.ccms.web.collection.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;

/**
* @description
* @author weiwenhui
* @date 2020/12/11 17:56
*/
public class CollectionQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *主键ID
     */
    private Long id;
    /**
     * 单据状态
     */
    private Integer status;
    /**
     * 写入日期（应收计算日期？）
     */
    private String ddate;
    /**
     * 申请（催收）单号
     */
    private String applicationNo;
    /**
     * 部门编码
     */
    private String departCode;
    /**
     * 卡号编码
     */
    private String cardCode;
    /**
     * 卡号名称
     */
    private String cardName;
    /**
     * 客户编码
     */
    private String custCode;
    /**
     * 客户名称
     */
    private String custName;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "CollectionQuery{" +
                "id=" + id +
                ", status=" + status +
                ", ddate='" + ddate + '\'' +
                ", applicationNo='" + applicationNo + '\'' +
                ", departCode='" + departCode + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", cardName='" + cardName + '\'' +
                ", custCode='" + custCode + '\'' +
                ", custName='" + custName + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
