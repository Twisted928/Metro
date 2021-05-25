package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户保险清单导出
 *
 * @author guangle
 * @date 2020/12/22
 * @since 1.0.0
 */
public class InsurecusExport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司编码
     */
    @Excel(name = "公司编码")
    private String compCode;

    /**
     * 公司名称
     */
    @Excel(name = "公司名称")
    private String compName;

    /**
     * 买方代码
     */
    @Excel(name = "买方代码")
    private String buyerno;

    /**
     * 客户编码
     */
    @Excel(name = "客户编码")
    private String custCode;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String custName;

    /**
     * 保单号
     */
    @Excel(name = "保单号")
    private String policyno;

    /**
     * 保险评级
     */
    @Excel(name = "保险评级")
    private String creditLevel;

    /**
     * 保险额度
     */
    @Excel(name = "保险额度")
    private BigDecimal quota;

    /**
     * 保险账期
     */
    @Excel(name = "保险账期")
    private Integer quotaDays;

    /**
     * 状态(0：无效，1：有效)
     */
    @Excel(name = "有效状态", readConverterExp = "0=无效,1=有效")
    private Integer status;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间", dateFormat = "yyyy-MM-dd")
    private Date updateTime;

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

    public String getBuyerno() {
        return buyerno;
    }

    public void setBuyerno(String buyerno) {
        this.buyerno = buyerno;
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

    public String getPolicyno() {
        return policyno;
    }

    public void setPolicyno(String policyno) {
        this.policyno = policyno;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public Integer getQuotaDays() {
        return quotaDays;
    }

    public void setQuotaDays(Integer quotaDays) {
        this.quotaDays = quotaDays;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
