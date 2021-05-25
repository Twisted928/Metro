package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.core.domain.BaseEntity;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.web.debtprotection.domian.GuaranteeScopeDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 担保函管理组合VO
 * @author 
 */
public class GuaranteeVO extends BaseEntity implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 多选删除
     */
    private List<String> ids;

    /**
     * 担保函编码
     */
    private String gtcode;

    /**
     * 客户编码
     */
    private String custCode;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 担保金额
     */
    private BigDecimal gtsum;

    /**
     * 0:Bank Guarantee,    1:Insurance
     */
    private String guaranteetype;

    /**
     * 生效时间
     */
    private Date validFrom;

    /**
     * 到期时间
     */
    private Date validTo;

    /**
     * 状态(0：无效，1：有效)
     */
    private Integer status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Integer deleted;

    /**
     * 删除卡号信息的ID集合
     */
    private List<String> scopeList;

    /**
     * 范围表
     */
    private List<GuaranteeScopeDO> guaranteeScopeDOList;

    /**
     * 附件
     */
    private List<SysBasicFile> list;

    public List<String> getScopeList() {
        return scopeList;
    }

    public void setScopeList(List<String> scopeList) {
        this.scopeList = scopeList;
    }

    public List<SysBasicFile> getList() {
        return list;
    }

    public void setList(List<SysBasicFile> list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getGtcode() {
        return gtcode;
    }

    public void setGtcode(String gtcode) {
        this.gtcode = gtcode;
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

    public String getGuaranteetype() {
        return guaranteetype;
    }

    public void setGuaranteetype(String guaranteetype) {
        this.guaranteetype = guaranteetype;
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

    public List<GuaranteeScopeDO> getGuaranteeScopeDOList() {
        return guaranteeScopeDOList;
    }

    public void setGuaranteeScopeDOList(List<GuaranteeScopeDO> guaranteeScopeDOList) {
        this.guaranteeScopeDOList = guaranteeScopeDOList;
    }
}