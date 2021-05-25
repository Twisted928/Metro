package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 担保函导出
 *
 * @author guangle
 * @date 2020/12/22
 * @since 1.0.0
 */
public class GuaranteeExport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 担保函编码
     */
    @Excel(name = "担保函编码")
    private String gtcode;

    /**
     * 0:Bank Guarantee,    1:Insurance
     */
    @Excel(name = "担保函类型")
    private String guaranteetype;

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
     * 担保金额
     */
    @Excel(name = "担保金额")
    private BigDecimal gtsum;

    /**
     * 生效时间
     */
    @Excel(name = "生效时间", dateFormat = "yyyy-MM-dd")
    private Date validFrom;

    /**
     * 到期时间
     */
    @Excel(name = "到期时间", dateFormat = "yyyy-MM-dd")
    private Date validTo;

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

    /**
     * 状态(0：无效，1：有效)
     */
    @Excel(name = "有效状态", readConverterExp = "0=无效,1=有效")
    private Integer status;

    public String getGtcode() {
        return gtcode;
    }

    public void setGtcode(String gtcode) {
        this.gtcode = gtcode;
    }

    public String getGuaranteetype() {
        return guaranteetype;
    }

    public void setGuaranteetype(String guaranteetype) {
        this.guaranteetype = guaranteetype;
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

    public BigDecimal getGtsum() {
        return gtsum;
    }

    public void setGtsum(BigDecimal gtsum) {
        this.gtsum = gtsum;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
