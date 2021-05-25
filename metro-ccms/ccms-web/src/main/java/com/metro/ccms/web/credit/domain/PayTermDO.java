package com.metro.ccms.web.credit.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

/**
 * tb_credit_payterm
 * @author 
 */
public class PayTermDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 状态(0：无效，1：有效)
     */
    private Byte status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Byte deleted;

    /**
     * 结算方式
     */
    private String settleType;

    /**
     * 付款期限描述
     */
    private String paymentDesc;

    /**
     * 付款条件代码
     */
    private String paymentCode;

    /**
     * 参考付款条件天数
     */
    private Integer paymentDays;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getPaymentDesc() {
        return paymentDesc;
    }

    public void setPaymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Integer getPaymentDays() {
        return paymentDays;
    }

    public void setPaymentDays(Integer paymentDays) {
        this.paymentDays = paymentDays;
    }
}