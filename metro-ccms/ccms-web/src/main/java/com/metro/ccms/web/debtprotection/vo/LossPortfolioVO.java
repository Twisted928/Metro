package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.core.domain.BaseEntity;
import com.metro.ccms.web.debtprotection.domian.ClaimProgressDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 报损组合VO
 *
 * @author guangle
 * @date 2020/12/10
 * @since 1.0.0
 */
public class LossPortfolioVO extends BaseEntity implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 案件编号
     */
    private String caseno;

    /**
     * 案件状态
     */
    private String caseStatus;

    /**
     * 保险公司编码
     */
    private String compCode;

    /**
     * 被保险人声明
     */
    private String declaration;

    /**
     * 客户编码
     */
    private String custCode;

    /**
     * 客户名称
     */
    private String custName;

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
    private BigDecimal invoicesum;

    /**
     * 汇总发票日期
     */
    private Date invoicedate;

    /**
     * 费用承担人编码
     */
    private String clientno;

    /**
     * 发票汇总最早发票日期
     */
    private Date btime;

    /**
     * 发票汇总最晚发票日期
     */
    private Date etime;

    /**
     * 投保金额
     */
    private BigDecimal insuresum;

    /**
     * 付款方式
     */
    private String paymode;

    /**
     * 出运日期
     */
    private Date transportdate;

    /**
     * 状态(0：无效，1：有效)
     */
    private Byte status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Byte deleted;

    /**
     * 台账信息
     */
    private List<ClaimProgressDO> claimProgressDOList;

    /**
     * 删除的ID
     */
    private List<String> ids;

    /**
     * 已投保表id
     */
    private String insureId;

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getInsureId() {
        return insureId;
    }

    public void setInsureId(String insureId) {
        this.insureId = insureId;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getInvoicesum() {
        return invoicesum;
    }

    public void setInvoicesum(BigDecimal invoicesum) {
        this.invoicesum = invoicesum;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public List<ClaimProgressDO> getClaimProgressDOList() {
        return claimProgressDOList;
    }

    public void setClaimProgressDOList(List<ClaimProgressDO> claimProgressDOList) {
        this.claimProgressDOList = claimProgressDOList;
    }
}
