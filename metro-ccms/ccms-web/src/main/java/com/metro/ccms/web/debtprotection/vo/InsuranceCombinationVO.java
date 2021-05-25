package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 组合VO
 *
 * @author guangle
 * @create 2020/12/2
 * @since 1.0.0
 */
public class InsuranceCombinationVO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 修改时删除的保单ID
     */
    private List<String> deleteId;

    /**
     * 保单表集合
     */
    private List<InsurePolicyVO> insurePolicyVOList;

    /**
     * tb_sf_insure_scope集合
     */
    private List<InsureScopeVO> insureScopeVOList;

    public List<String> getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(List<String> deleteId) {
        this.deleteId = deleteId;
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


    public List<InsurePolicyVO> getInsurePolicyVOList() {
        return insurePolicyVOList;
    }

    public void setInsurePolicyVOList(List<InsurePolicyVO> insurePolicyVOList) {
        this.insurePolicyVOList = insurePolicyVOList;
    }

    public List<InsureScopeVO> getInsureScopeVOList() {
        return insureScopeVOList;
    }

    public void setInsureScopeVOList(List<InsureScopeVO> insureScopeVOList) {
        this.insureScopeVOList = insureScopeVOList;
    }
}
