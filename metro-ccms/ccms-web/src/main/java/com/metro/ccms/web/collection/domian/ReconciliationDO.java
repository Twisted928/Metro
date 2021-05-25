package com.metro.ccms.web.collection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.Date;

/**
* @description 对账表
* @author weiwenhui
* @date 2020/12/14 10:47
*/
public class ReconciliationDO extends BaseEntity {
    /**
     * ID
     */
    private Long id;
    /**
     * 对账状态
     */
    private Integer status;
    /**
     * 删除标记
     */
    private Integer deleted;
    /**
     * 备用字段
     */
    private String item1;
    /**
     * 备用字段
     */
    private String item2;
    /**
     * 备用字段
     */
    private String item3;
    /**
     * 备用字段
     */
    private String item4;
    /**
     * 备用字段
     */
    private String item5;
    /**
     * 备用字段
     */
    private String item6;
    /**
     * 备用字段
     */
    private String item7;
    /**
     * 备用字段
     */
    private String item8;
    /**
     * 备用字段
     */
    private String item9;
    /**
     * 对账单号
     */
    private String applicationNo;
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
     * 对账日期
     */
    private Date ddate;
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
     * 客户编码
     */
    private String custCode;
    /**
     * 客户名称
     */
    private String custName;
    /**
     * 应收金额
     */
    private Double iar;
    /**
     * 逾期金额
     */
    private Double idue;
    /**
     * 写入日期
     */
    private Date indate;
    /**
     * 客户类型
     */
    private String custType;

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getItem6() {
        return item6;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }

    public String getItem7() {
        return item7;
    }

    public void setItem7(String item7) {
        this.item7 = item7;
    }

    public String getItem8() {
        return item8;
    }

    public void setItem8(String item8) {
        this.item8 = item8;
    }

    public String getItem9() {
        return item9;
    }

    public void setItem9(String item9) {
        this.item9 = item9;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
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

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
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

    public Double getIar() {
        return iar;
    }

    public void setIar(Double iar) {
        this.iar = iar;
    }

    public Double getIdue() {
        return idue;
    }

    public void setIdue(Double idue) {
        this.idue = idue;
    }

    @Override
    public String toString() {
        return "ReconciliationDO{" +
                "id=" + id +
                ", status=" + status +
                ", deleted=" + deleted +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", item3='" + item3 + '\'' +
                ", item4='" + item4 + '\'' +
                ", item5='" + item5 + '\'' +
                ", item6='" + item6 + '\'' +
                ", item7='" + item7 + '\'' +
                ", item8='" + item8 + '\'' +
                ", item9='" + item9 + '\'' +
                ", applicationNo='" + applicationNo + '\'' +
                ", attachitems='" + attachitems + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                ", ddate=" + ddate +
                ", departCode='" + departCode + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", cardName='" + cardName + '\'' +
                ", custCode='" + custCode + '\'' +
                ", custName='" + custName + '\'' +
                ", iar=" + iar +
                ", idue=" + idue +
                ", indate=" + indate +
                ", custType='" + custType + '\'' +
                '}';
    }
}
