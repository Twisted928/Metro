package com.metro.ccms.web.debtprotection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * tb_sf_guarantee_scope
 *
 * @author
 */
public class GuaranteeScopeDO extends BaseEntity implements Serializable {
    private Long id;

    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 客户编码
     */
    private String custCode;

    /**
     * 客户名称
     */
    private String custName;
    /**
     * 卡号编码
     */
    private String cardCode;

    /**
     * 卡号名称
     */
    private String cardName;

    /**
     * 父表ID
     */
    private String mainId;

    /**
     * 状态(0：无效，1：有效)
     */
    private Integer status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
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
}