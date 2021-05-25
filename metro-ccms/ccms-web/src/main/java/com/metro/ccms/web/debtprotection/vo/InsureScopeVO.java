package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 展示对象
 * tb_sf_insure_scope
 * @author 
 */

public class InsureScopeVO extends BaseEntity implements Serializable {
    private Long id;

    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 公司名称
     */
    private String compName;

    /**
     * 保单表ID
     */
    private String policId;

    /**
     * 门店编码集合
     */
    private List<String> storeCodeList;

    /**
     * 状态(0：无效，1：有效)
     */
    private Integer status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Integer deleted;

    /**
     * 部门编码
     */
    private String departCode;

    private static final long serialVersionUID = 1L;

    public List<String> getStoreCodeList() {
        return storeCodeList;
    }

    public void setStoreCodeList(List<String> storeCodeList) {
        this.storeCodeList = storeCodeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getPolicId() {
        return policId;
    }

    public void setPolicId(String policId) {
        this.policId = policId;
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

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }
}