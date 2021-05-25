package com.metro.ccms.web.httpsInterface.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: fangyongjie
 * @Description: DCRM实时应收和采购潜力金额接口对象 tb_interface_potential
 * @Date: Created in 17:37 2021/1/18
 * @Modified By:
 */
public class PotentialDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 客户编码（卡号编码） */
    private String custNo;

    /** 门店编码 */
    private String storeNo;

    /** CRM信用总额 */
    private BigDecimal creditLimit;

    /** 可用额度 */
    private BigDecimal availableLimit;

    /** 应收余额(销售-回款) */
    private BigDecimal arBalance;

    /** 可用潜力金额(总额) */
    private BigDecimal salesPotential;

    /** 写入时间 */
    private Date ddate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(BigDecimal availableLimit) {
        this.availableLimit = availableLimit;
    }

    public BigDecimal getArBalance() {
        return arBalance;
    }

    public void setArBalance(BigDecimal arBalance) {
        this.arBalance = arBalance;
    }

    public BigDecimal getSalesPotential() {
        return salesPotential;
    }

    public void setSalesPotential(BigDecimal salesPotential) {
        this.salesPotential = salesPotential;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }
}
