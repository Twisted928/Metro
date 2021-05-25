package com.metro.ccms.web.credit.query;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * tb_credit_payterm
 * @author 
 */
public class PayTermQuery extends BaseEntity implements Serializable {

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

    private static final long serialVersionUID = 1L;

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
}