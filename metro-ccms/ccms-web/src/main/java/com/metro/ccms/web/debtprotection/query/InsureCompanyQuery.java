package com.metro.ccms.web.debtprotection.query;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 查询条件对象
 * tb_sf_insure_company
 * @author guangle
 * @create 2020/11/30
 * @since 1.0.0
 */
public class InsureCompanyQuery extends BaseEntity implements Serializable {
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
     * 状态(0：无效，1：有效)
     */
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
