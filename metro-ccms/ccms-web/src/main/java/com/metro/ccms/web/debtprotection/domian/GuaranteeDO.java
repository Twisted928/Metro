package com.metro.ccms.web.debtprotection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tb_sf_guarantee
 *
 * @author
 */
public class GuaranteeDO extends BaseEntity implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 担保函编码
     */
    private String gtcode;

    /**
     * 0:Bank Guarantee,    1:Insurance
     */
    private String guaranteetype;

    /**
     * 客户编码
     */
    private String custCode;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 担保金额
     */
    private BigDecimal gtsum;

    /**
     * 生效时间
     */
    private Date validFrom;

    /**
     * 到期时间
     */
    private Date validTo;

    /**
     * 状态(0：无效，1：有效)
     */
    private Integer status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Integer deleted;

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
     * 部门编码
     */
    private String departCode;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 卡号编码
     */
    private String cardCode;

    /**
     * 卡号名称
     */
    private String cardName;

    /**
     * 担保函受益人
     */
    private String benefitor;

    /**
     * 是否循环 0：非循环：1循环
     */
    private Integer ifloop;

    /**
     * 部门名称
     */
    private String departName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGtcode() {
        return gtcode;
    }

    public void setGtcode(String gtcode) {
        this.gtcode = gtcode;
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

    public BigDecimal getGtsum() {
        return gtsum;
    }

    public void setGtsum(BigDecimal gtsum) {
        this.gtsum = gtsum;
    }

    public String getGuaranteetype() {
        return guaranteetype;
    }

    public void setGuaranteetype(String guaranteetype) {
        this.guaranteetype = guaranteetype;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
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

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getBenefitor() {
        return benefitor;
    }

    public void setBenefitor(String benefitor) {
        this.benefitor = benefitor;
    }

    public Integer getIfloop() {
        return ifloop;
    }

    public void setIfloop(Integer ifloop) {
        this.ifloop = ifloop;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}