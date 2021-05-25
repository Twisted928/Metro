package com.metro.ccms.web.debtprotection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;
import com.metro.ccms.system.domain.SysBasicFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * tb_sf_claim
 *
 * @author
 */
public class ClaimDO extends BaseEntity implements Serializable {
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
     * 附件信息
     */
    private String attachitems;

    /**
     * 附件名称
     */
    private String attachmentName;

    /**
     * 附件地址
     */
    private String attachmentUrl;

    /**
     * 保单表ID
     */
    private Long policId;

    /**
     * 原因
     */
    private String reason;

    /**
     * 已投保表id
     */
    private String insureId;

    /**
     * 保险公司编码
     */
    private String compCode;

    /**
     * 附件
     */
    private List<SysBasicFile> sysBasicFileList;

    /**
     * 报损进度表
     */
    private List<ClaimProgressDO> claimProgressDOList;

    private static final long serialVersionUID = 1L;

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public List<SysBasicFile> getSysBasicFileList() {
        return sysBasicFileList;
    }

    public void setSysBasicFileList(List<SysBasicFile> sysBasicFileList) {
        this.sysBasicFileList = sysBasicFileList;
    }

    public List<ClaimProgressDO> getClaimProgressDOList() {
        return claimProgressDOList;
    }

    public void setClaimProgressDOList(List<ClaimProgressDO> claimProgressDOList) {
        this.claimProgressDOList = claimProgressDOList;
    }

    public String getInsureId() {
        return insureId;
    }

    public void setInsureId(String insureId) {
        this.insureId = insureId;
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

    public String getAttachitems() {
        return attachitems;
    }

    public void setAttachitems(String attachitems) {
        this.attachitems = attachitems;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public Long getPolicId() {
        return policId;
    }

    public void setPolicId(Long policId) {
        this.policId = policId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}