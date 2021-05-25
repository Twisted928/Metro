package com.metro.ccms.web.credit.vo;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.Date;

public class CustomerGroupVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 自增列 */
    private Long id;

    /** 状态(0：无效，1：有效) */
    private Integer status;

    /** 删除标记(0：正常，1：已删除) */
    private Integer deleted;

    /** 部门编码 */
    private String departCode;

    /** 门店编码 */
    private String storeCode;

    /** 客户编码 */
    private String custCode;

    /** 客户名称 */
    private String custName;

    /** 卡号编码 */
    private String cardCode;

    /** 卡号名称 */
    private String cardName;

    /** 客户组编码 */
    @Excel(name = "客户组编码",sort = 0)
    private String custGroup;

    /** 客户经理编码 */
    private String custManagerCode;

    /** 客户经理名称 */
    private String custManagerName;

    /** 0无     1主客户   2子客户 */
    private Integer ifmain;

    /** 信用组主表ID */
    private Long groupId;

    /** 客户组名称 */
    @Excel(name = "客户组名称",sort = 1)
    private String custgrName;

    /** 客户组账期 */
    @Excel(name = "客户组账期",sort = 2)
    private Integer custgrPayterm;

    /** 客户组额度 */
    @Excel(name = "客户组额度",sort = 3)
    private Double custgrLimit;

    /** 总收入 */
    @Excel(name = "总收入",sort = 4)
    private Double totalRevenue;

    /** 应收金额 */
    @Excel(name = "应收金额",sort = 5)
    private Double iar;

    /** 逾期金额 */
    @Excel(name = "逾期金额",sort = 6)
    private Double idue;

    /** 卡号数量 */
    @Excel(name = "卡号数量",sort = 7)
    private String cardNum;

    /** 门店数量 */
    @Excel(name = "门店数量",sort = 8)
    private String storeNum;

    /** 部门名称 */
    private String deptName;

    /** 额度类型 */
    private String limitType;

    /** 发布账期 */
    private Integer paymentTerm;

    /** 发布额度 */
    private Double limit;

    /** 生效时间 */
    private Date validFrom;

    /** 到期时间 */
    private Date validTo;

    /** 卡号总收入 */
    private Double totalcardRe;

    /** 卡号应收金额 */
    private Double cardiar;

    /** 卡号逾期金额 */
    private Double cardidue;

    public Integer getCustgrPayterm() {
        return custgrPayterm;
    }

    public void setCustgrPayterm(Integer custgrPayterm) {
        this.custgrPayterm = custgrPayterm;
    }

    public Integer getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(Integer paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    public String getCustManagerCode() {
        return custManagerCode;
    }

    public void setCustManagerCode(String custManagerCode) {
        this.custManagerCode = custManagerCode;
    }

    public String getCustManagerName() {
        return custManagerName;
    }

    public void setCustManagerName(String custManagerName) {
        this.custManagerName = custManagerName;
    }

    public Integer getIfmain() {
        return ifmain;
    }

    public void setIfmain(Integer ifmain) {
        this.ifmain = ifmain;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getCustgrName() {
        return custgrName;
    }

    public void setCustgrName(String custgrName) {
        this.custgrName = custgrName;
    }

    public Double getCustgrLimit() {
        return custgrLimit;
    }

    public void setCustgrLimit(Double custgrLimit) {
        this.custgrLimit = custgrLimit;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getIar() {
        return iar;
    }

    public void setIar(Double iar) {
        this.iar = iar;
    }

    public Double getIdue() {
        return idue;
    }

    public void setIdue(Double idue) {
        this.idue = idue;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Double getTotalcardRe() {
        return totalcardRe;
    }

    public void setTotalcardRe(Double totalcardRe) {
        this.totalcardRe = totalcardRe;
    }

    public Double getCardiar() {
        return cardiar;
    }

    public void setCardiar(Double cardiar) {
        this.cardiar = cardiar;
    }

    public Double getCardidue() {
        return cardidue;
    }

    public void setCardidue(Double cardidue) {
        this.cardidue = cardidue;
    }

    @Override
    public String toString() {
        return "CustomerGroupVO{" +
                "id=" + id +
                ", status=" + status +
                ", deleted=" + deleted +
                ", departCode='" + departCode + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", custCode='" + custCode + '\'' +
                ", custName='" + custName + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", cardName='" + cardName + '\'' +
                ", custGroup='" + custGroup + '\'' +
                ", custManagerCode='" + custManagerCode + '\'' +
                ", custManagerName='" + custManagerName + '\'' +
                ", ifmain=" + ifmain +
                ", groupId=" + groupId +
                ", custgrName='" + custgrName + '\'' +
                ", custgrPayterm=" + custgrPayterm +
                ", custgrLimit=" + custgrLimit +
                ", totalRevenue=" + totalRevenue +
                ", iar=" + iar +
                ", idue=" + idue +
                ", cardNum='" + cardNum + '\'' +
                ", storeNum='" + storeNum + '\'' +
                ", deptName='" + deptName + '\'' +
                ", limitType='" + limitType + '\'' +
                ", paymentTerm=" + paymentTerm +
                ", limit=" + limit +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", totalcardRe=" + totalcardRe +
                ", cardiar=" + cardiar +
                ", cardidue=" + cardidue +
                '}';
    }
}
