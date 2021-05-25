package com.metro.ccms.web.collection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
* @description
* @author weiwenhui
* @date 2020/12/11 17:55
*/
public class CollectionDetailDO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 删除标记
     */
    private Integer deleted;
    /**
     * 备用字段
     */
    private String item1;
    /**
     * 备用字段
     */
    private String item2;
    /**
     * 备用字段
     */
    private String item3;
    /**
     * 备用字段
     */
    private String item4;
    /**
     * 备用字段
     */
    private String item5;
    /**
     * 备用字段
     */
    private String item6;
    /**
     * 备用字段
     */
    private String item7;
    /**
     * 备用字段
     */
    private String item8;
    /**
     * 备用字段
     */
    private String item9;
    /**
     * 申请单号
     */
    private String applicationNo;
    /**
     * 部门编码
     */
    private String departCode;
    /**
     * 门店编码
     */
    private String storeCode;
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
     * 付款条件/发布账期
     */
    private String paymentTerm;
    /**
     * 应收汇日
     */
    private Date askCashDate;
    /**
     * 发票号
     */
    private String csbvcode;
    /**
     * 到期日
     */
    private Date dduedate;
    /**
     * 明细发票日期
     */
    private Date dsbvdate;
    /**
     * 本位币
     */
    private String standardCurrency;
    /**
     * 应收账款
     */
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getItem6() {
        return item6;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }

    public String getItem7() {
        return item7;
    }

    public void setItem7(String item7) {
        this.item7 = item7;
    }

    public String getItem8() {
        return item8;
    }

    public void setItem8(String item8) {
        this.item8 = item8;
    }

    public String getItem9() {
        return item9;
    }

    public void setItem9(String item9) {
        this.item9 = item9;
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

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
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

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public Date getAskCashDate() {
        return askCashDate;
    }

    public void setAskCashDate(Date askCashDate) {
        this.askCashDate = askCashDate;
    }

    public String getCsbvcode() {
        return csbvcode;
    }

    public void setCsbvcode(String csbvcode) {
        this.csbvcode = csbvcode;
    }

    public Date getDduedate() {
        return dduedate;
    }

    public void setDduedate(Date dduedate) {
        this.dduedate = dduedate;
    }

    public Date getDsbvdate() {
        return dsbvdate;
    }

    public void setDsbvdate(Date dsbvdate) {
        this.dsbvdate = dsbvdate;
    }

    public String getStandardCurrency() {
        return standardCurrency;
    }

    public void setStandardCurrency(String standardCurrency) {
        this.standardCurrency = standardCurrency;
    }

    @Override
    public String toString() {
        return "CollectionDetailDO{" +
                "id=" + id +
                ", status=" + status +
                ", deleted=" + deleted +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", item3='" + item3 + '\'' +
                ", item4='" + item4 + '\'' +
                ", item5='" + item5 + '\'' +
                ", item6='" + item6 + '\'' +
                ", item7='" + item7 + '\'' +
                ", item8='" + item8 + '\'' +
                ", item9='" + item9 + '\'' +
                ", applicationNo='" + applicationNo + '\'' +
                ", departCode='" + departCode + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", cardName='" + cardName + '\'' +
                ", custCode='" + custCode + '\'' +
                ", custName='" + custName + '\'' +
                ", paymentTerm='" + paymentTerm + '\'' +
                ", askCashDate=" + askCashDate +
                ", csbvcode='" + csbvcode + '\'' +
                ", dduedate=" + dduedate +
                ", dsbvdate=" + dsbvdate +
                ", standardCurrency='" + standardCurrency + '\'' +
                '}';
    }
}
