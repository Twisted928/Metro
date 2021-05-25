package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 投标管理导出
 *
 * @author guangle
 * @date 2020/12/22
 * @since 1.0.0
 */
public class InsuranceManagementExport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司编码
     */
    @Excel(name = "公司编码")
    private String compCode;

    /**
     * 保单号
     */
    @Excel(name = "保单号")
    private String policyno;

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
     * 买方代码
     */
    @Excel(name = "买方代码")
    private String buyerno;

    /**
     * 批复状态(0：无效，1：有效)
     */
    @Excel(name = "批复状态", readConverterExp = "1=未投保,2=已投保")
    private Integer status;

    /**
     * 保单主体
     */
    @Excel(name = "保单主体")
    private String body;

    /**
     * 汇总发票号
     */
    @Excel(name = "汇总发票号")
    private String invoiceNo;

    /**
     * 汇总发票金额
     */
    @Excel(name = "汇总发票金额")
    private BigDecimal invoicesum;

    /**
     * 投保金额
     */
    @Excel(name = "投保金额")
    private BigDecimal insuresum;

    /**
     * 付款方式
     */
    @Excel(name = "付款方式")
    private String paymode;

    /**
     * 保险账期
     */
    @Excel(name = "保险账期")
    private Integer quotaDays;

    /**
     * 汇总发票日期
     */
    @Excel(name = "汇总发票日期", dateFormat = "yyyy-MM-dd")
    private Date invoicedate;

    /**
     * 发票汇总最早发票日期
     */
    @Excel(name = "发票汇总最早发票日期", dateFormat = "yyyy-MM-dd")
    private Date btime;

    /**
     * 发票汇总最晚发票日期
     */
    @Excel(name = "发票汇总最晚发票日期", dateFormat = "yyyy-MM-dd")
    private Date etime;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd")
    private Date createTime;

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

    public String getBuyerno() {
        return buyerno;
    }

    public void setBuyerno(String buyerno) {
        this.buyerno = buyerno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getInvoicesum() {
        return invoicesum;
    }

    public void setInvoicesum(BigDecimal invoicesum) {
        this.invoicesum = invoicesum;
    }

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

    public Integer getQuotaDays() {
        return quotaDays;
    }

    public void setQuotaDays(Integer quotaDays) {
        this.quotaDays = quotaDays;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
