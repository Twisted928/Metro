package com.metro.ccms.web.debtprotection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tb_sf_invoice
 * @author 
 */
public class InvoiceDO extends BaseEntity implements Serializable {
    private Long id;

    /**
     * 状态(0：无效，1：有效)
     */
    private Integer status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Integer deleted;

    /**
     * 公司编码
     */
    private String compCode;

    /**
     * 保单号
     */
    private String policyno;

    /**
     * 保单主体
     */
    private String body;

    /**
     * 保单表ID
     */
    private String policId;

    /**
     * 汇总发票号
     */
    private String invoiceNo;

    /**
     * 买方代码
     */
    private String buyerno;

    /**
     * 汇总发票金额
     */
    private Double invoicesum;

    /**
     * 汇总发票日期
     */
    private Date invoicedate;

    /**
     * 清账类型
     */
    private String qztype;

    /**
     * 费用承担人编码
     */
    private String clientno;

    /**
     * 是否已投保（0：未头；1：已投）
     */
    private Integer baseapply;

    /**
     * 发票汇总最早发票日期
     */
    private Date btime;

    /**
     * 发票汇总最晚发票日期
     */
    private Date etime;

    /**
     * 保险账期
     */
    private Integer quotaDays;

    /**
     * 客户编码
     */
    private String custCode;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 投保金额
     */
    private BigDecimal insuresum;

    /**
     * 付款方式
     */
    private String paymode;

    private static final long serialVersionUID = 1L;

    public BigDecimal getInsuresum() {
        return insuresum;
    }

    public void setInsuresum(BigDecimal insuresum) {
        this.insuresum = insuresum;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getPolicyno() {
        return policyno;
    }

    public void setPolicyno(String policyno) {
        this.policyno = policyno;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPolicId() {
        return policId;
    }

    public void setPolicId(String policId) {
        this.policId = policId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getBuyerno() {
        return buyerno;
    }

    public void setBuyerno(String buyerno) {
        this.buyerno = buyerno;
    }

    public Double getInvoicesum() {
        return invoicesum;
    }

    public void setInvoicesum(Double invoicesum) {
        this.invoicesum = invoicesum;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getQztype() {
        return qztype;
    }

    public void setQztype(String qztype) {
        this.qztype = qztype;
    }

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
    }

    public Integer getBaseapply() {
        return baseapply;
    }

    public void setBaseapply(Integer baseapply) {
        this.baseapply = baseapply;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Integer getQuotaDays() {
        return quotaDays;
    }

    public void setQuotaDays(Integer quotaDays) {
        this.quotaDays = quotaDays;
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
}