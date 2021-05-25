package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 展示对象
 * tb_sf_insure_policy
 * @author 
 */
public class InsurePolicyVO extends BaseEntity implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 公司编码
     */
    private String compCode;

    /**
     * 公司名称
     */
    private String compName;

    /**
     * 保单号
     */
    private String policyno;

    /**
     * 状态(0：无效，1：有效)
     */
    private Integer status;

    /**
     * 保单主体
     */
    private String body;

    /**
     * 约定投保金额
     */
    private BigDecimal policySum;

    /**
     * 最高赔偿限额
     */
    private BigDecimal maxpaySum;

    /**
     * 赔偿比例
     */
    private BigDecimal payLv;

    /**
     * 最长付款期限
     */
    private Integer payperiod;

    /**
     * 限额闲置期
     */
    private Integer quotafree;

    /**
     * 赔款等待期
     */
    private Integer paywait;

    /**
     * 币种
     */
    private String currency;

    /**
     * 生效时间
     */
    private Date validFrom;

    /**
     * 到期时间
     */
    private Date validTo;

    /**
     * 保单表ID
     */
    private String policId;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Integer deleted;

    /**
     * 保单范围
     */
    private List<String> insureScopeDOList;

    /**
     * 校验字段 纯数字是update 非纯数字为save
     */
    private String idType;

    private static final long serialVersionUID = 1L;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setInsureScopeDOList(List<String> insureScopeDOList) {
        this.insureScopeDOList = insureScopeDOList;
    }

    public List<String> getInsureScopeDOList() {
        return insureScopeDOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getPolicyno() {
        return policyno;
    }

    public void setPolicyno(String policyno) {
        this.policyno = policyno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BigDecimal getPolicySum() {
        return policySum;
    }

    public void setPolicySum(BigDecimal policySum) {
        this.policySum = policySum;
    }

    public BigDecimal getMaxpaySum() {
        return maxpaySum;
    }

    public void setMaxpaySum(BigDecimal maxpaySum) {
        this.maxpaySum = maxpaySum;
    }

    public BigDecimal getPayLv() {
        return payLv;
    }

    public void setPayLv(BigDecimal payLv) {
        this.payLv = payLv;
    }

    public Integer getPayperiod() {
        return payperiod;
    }

    public void setPayperiod(Integer payperiod) {
        this.payperiod = payperiod;
    }

    public Integer getQuotafree() {
        return quotafree;
    }

    public void setQuotafree(Integer quotafree) {
        this.quotafree = quotafree;
    }

    public Integer getPaywait() {
        return paywait;
    }

    public void setPaywait(Integer paywait) {
        this.paywait = paywait;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getPolicId() {
        return policId;
    }

    public void setPolicId(String policId) {
        this.policId = policId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}