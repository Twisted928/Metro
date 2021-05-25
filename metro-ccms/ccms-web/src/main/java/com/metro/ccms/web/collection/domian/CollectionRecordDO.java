package com.metro.ccms.web.collection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

/**
* @description 台账表
* @author weiwenhui
* @date 2020/12/11 17:56
*/
public class CollectionRecordDO extends BaseEntity {
    /**
     * ID
     */
    private Long id;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 删除标记
     */
    private Integer deleted;
    /**
     * 主表ID
     */
    private Long mainId;
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
     * 描述
     */
    private String desc;
    /**
     * 申请单号
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
     * 部门编码
     */
    private String departCode;
    /**
     * 门店编码
     */
    private String storeCode;

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

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    @Override
    public String toString() {
        return "CollectionRecordDO{" +
                "id=" + id +
                ", status=" + status +
                ", deleted=" + deleted +
                ", mainId=" + mainId +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", item3='" + item3 + '\'' +
                ", item4='" + item4 + '\'' +
                ", item5='" + item5 + '\'' +
                ", item6='" + item6 + '\'' +
                ", item7='" + item7 + '\'' +
                ", item8='" + item8 + '\'' +
                ", item9='" + item9 + '\'' +
                ", desc='" + desc + '\'' +
                ", applicationNo='" + applicationNo + '\'' +
                ", attachitems='" + attachitems + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                ", departCode='" + departCode + '\'' +
                ", storeCode='" + storeCode + '\'' +
                '}';
    }
}
