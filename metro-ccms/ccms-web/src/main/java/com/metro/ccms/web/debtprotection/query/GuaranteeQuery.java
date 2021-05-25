package com.metro.ccms.web.debtprotection.query;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_sf_guarantee
 * @author 
 */
public class GuaranteeQuery extends BaseEntity implements Serializable {

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
     * 担保函编码
     */
    private String gtcode;

    /**
     * 0:Bank Guarantee,    1:Insurance
     */
    private String guaranteetype;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}