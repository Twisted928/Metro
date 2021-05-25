package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 报损管理导出
 *
 * @author guangle
 * @date 2020/12/22
 * @since 1.0.0
 */
public class ClaimExport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 案件编号
     */
    @Excel(name = "案件编号")
    private String caseno;

    /**
     * 案件状态
     */
    @Excel(name = "案件状态", readConverterExp = "1=自追,2=委托,3=关闭")
    private String caseStatus;

    /**
     * 被保险人声明
     */
    @Excel(name = "被保险人声明")
    private String declaration;

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
     * 汇总发票号
     */
    @Excel(name = "汇总发票号")
    private String invoiceNo;

    /**
     * 买方代码
     */
    @Excel(name = "买方代码")
    private String buyerno;

    /**
     * 费用承担人编码
     */
    @Excel(name = "费用承担人编码")
    private String clientno;

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
     * 出运日期
     */
    @Excel(name = "出运日期", dateFormat = "yyyy-MM-dd")
    private Date transportdate;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd")
    private Date createTime;

    public String getCaseno() {
        return caseno;
    }

    public void setCaseno(String caseno) {
        this.caseno = caseno;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
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

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
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

    public Date getTransportdate() {
        return transportdate;
    }

    public void setTransportdate(Date transportdate) {
        this.transportdate = transportdate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
