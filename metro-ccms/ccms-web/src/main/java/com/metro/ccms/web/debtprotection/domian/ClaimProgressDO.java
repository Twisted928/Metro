package com.metro.ccms.web.debtprotection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * tb_sf_claim_progress
 * @author 
 */
public class ClaimProgressDO extends BaseEntity implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 案件编号
     */
    private String caseno;

    /**
     * 案件进展
     */
    private String caseProgress;

    /**
     * 案件状态
     */
    private String caseStatus;


    /**
     * 主表ID
     */
    private Long mainId;

    /**
     * 状态(0：无效，1：有效)
     */
    private Byte status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Byte deleted;

    /**
     * 时间戳
     */
    private Date timestamp;

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
     * 客户编码
     */
    private String custCode;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 被保险人声明
     */
    private String declaration;

    /**
     * 汇总发票号
     */
    private String invoiceNo;

    /**
     * 报损进度表DO
     */
    private List<ClaimProgressDO> claimProgressDOList;


    private static final long serialVersionUID = 1L;

    public List<ClaimProgressDO> getClaimProgressDOList() {
        return claimProgressDOList;
    }

    public void setClaimProgressDOList(List<ClaimProgressDO> claimProgressDOList) {
        this.claimProgressDOList = claimProgressDOList;
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

    public String getCaseProgress() {
        return caseProgress;
    }

    public void setCaseProgress(String caseProgress) {
        this.caseProgress = caseProgress;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}