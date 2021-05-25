package com.metro.ccms.web.credit.vo;

import com.metro.ccms.common.annotation.Excel;

import java.util.Date;

/**
 * 付款条件导出
 *
 * @author guangle
 * @date 2021/1/7
 * @since 1.0.0
 */
public class PayTermExport {
    /**
     * 结算方式
     */
    @Excel(name = "结算方式", width = 41)
    private String settleType;

    /**
     * 付款期限描述
     */
    @Excel(name = "付款期限描述", width = 41)
    private String paymentDesc;

    /**
     * 付款条件代码
     */
    @Excel(name = "付款条件代码")
    private String paymentCode;

    /**
     * 参考付款条件天数
     */
    @Excel(name = "参考付款条件天数")
    private Integer paymentDays;

    /**
     * 状态(0：无效，1：有效)
     */
    @Excel(name = "有效状态", readConverterExp = "0=无效,1=有效")
    private Byte status;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间", dateFormat = "yyyy-MM-dd")
    private Date updateTime;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
