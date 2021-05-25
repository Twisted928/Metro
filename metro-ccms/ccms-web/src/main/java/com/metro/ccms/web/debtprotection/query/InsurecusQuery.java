package com.metro.ccms.web.debtprotection.query;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 保险客户清单表查询类
 *
 * @author guangle
 * @date 2020/12/7
 * @since 1.0.0
 */
public class InsurecusQuery extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 客户编码
     */
    private String custCode;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 状态(0：无效，1：有效)
     */
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
