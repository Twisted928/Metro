package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 保险公司导出
 *
 * @author guangle
 * @date 2020/12/22
 * @since 1.0.0
 */
public class InsureCompanyExport implements Serializable {

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
     * 状态(0：无效，1：有效)
     */
    @Excel(name = "有效状态", readConverterExp = "0=无效,1=有效")
    private Integer status;

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

    private static final long serialVersionUID = 1L;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
