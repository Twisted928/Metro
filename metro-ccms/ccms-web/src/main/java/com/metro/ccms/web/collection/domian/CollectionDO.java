package com.metro.ccms.web.collection.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
* @description
* @author weiwenhui
* @date 2020/12/11 17:55
*/
public class CollectionDO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *主键ID
     */
    private Long id;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态
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
     * 应收计算日期
     */
    private Date ddate;
    /**
     * 部门编码
     */
    private String departCode;
    /**
     * 部门名称
     */
    private String deptName;
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
     * 应收账龄15天
     */
    private Double iar015;
    /**
     * 应收账龄1月
     */
    private Double iar030;
    /**
     * 应收账龄2月
     */
    private Double iar060;
    /**
     * 应收账龄3个月
     */
    private Double iar090;
    /**
     * 应收账龄6月
     */
    private Double iar180;
    /**
     * 应收账龄12月
     */
    private Double iar360;
    /**
     * 应收账龄1-2年
     */
    private Double iar361;
    /**
     * 应收账龄4月
     */
    private Double iar120;
    /**
     * 应收账龄5月
     */
    private Double iar150;
    /**
     * 应收账龄7月
     */
    private Double iar210;
    /**
     * 应收账龄8月
     */
    private Double iar240;
    /**
     * 应收账龄9月
     */
    private Double iar270;
    /**
     * 应收账龄10月
     */
    private Double iar300;
    /**
     * 应收账龄11月
     */
    private Double iar330;
    /**
     * 应收账龄2年以上
     */
    private Double iar721;
    /**
     * 逾期金额
     */
    private Double idue;
    /**
     * 逾期账龄15天
     */
    private Double idue015;
    /**
     * 逾期账龄1月
     */
    private Double idue030;
    /**
     * 逾期账龄2月
     */
    private Double idue060;
    /**
     * 逾期账龄3月
     */
    private Double idue090;
    /**
     * 逾期账龄6月
     */
    private Double idue180;
    /**
     * 逾期账龄12月
     */
    private Double idue360;
    /**
     * 逾期账龄1-2年
     */
    private Double idue361;
    /**
     * 逾期账龄4月
     */
    private Double idue120;
    /**
     * 逾期账龄5月
     */
    private Double idue150;
    /**
     * 逾期账龄7月
     */
    private Double idue210;
    /**
     * 逾期账龄8月
     */
    private Double idue240;
    /**
     * 逾期账龄9月
     */
    private Double idue270;
    /**
     * 逾期账龄10月
     */
    private Double idue300;
    /**
     * 逾期账龄11月
     */
    private Double idue330;
    /**
     * 逾期账龄2年以上
     */
    private Double idue721;
    /**
     * 逾期内应收
     */
    private Double undue;

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

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public Double getIar015() {
        return iar015;
    }

    public void setIar015(Double iar015) {
        this.iar015 = iar015;
    }

    public Double getIar030() {
        return iar030;
    }

    public void setIar030(Double iar030) {
        this.iar030 = iar030;
    }

    public Double getIar060() {
        return iar060;
    }

    public void setIar060(Double iar060) {
        this.iar060 = iar060;
    }

    public Double getIar090() {
        return iar090;
    }

    public void setIar090(Double iar090) {
        this.iar090 = iar090;
    }

    public Double getIar180() {
        return iar180;
    }

    public void setIar180(Double iar180) {
        this.iar180 = iar180;
    }

    public Double getIar360() {
        return iar360;
    }

    public void setIar360(Double iar360) {
        this.iar360 = iar360;
    }

    public Double getIar361() {
        return iar361;
    }

    public void setIar361(Double iar361) {
        this.iar361 = iar361;
    }

    public Double getIar120() {
        return iar120;
    }

    public void setIar120(Double iar120) {
        this.iar120 = iar120;
    }

    public Double getIar150() {
        return iar150;
    }

    public void setIar150(Double iar150) {
        this.iar150 = iar150;
    }

    public Double getIar210() {
        return iar210;
    }

    public void setIar210(Double iar210) {
        this.iar210 = iar210;
    }

    public Double getIar240() {
        return iar240;
    }

    public void setIar240(Double iar240) {
        this.iar240 = iar240;
    }

    public Double getIar270() {
        return iar270;
    }

    public void setIar270(Double iar270) {
        this.iar270 = iar270;
    }

    public Double getIar300() {
        return iar300;
    }

    public void setIar300(Double iar300) {
        this.iar300 = iar300;
    }

    public Double getIar330() {
        return iar330;
    }

    public void setIar330(Double iar330) {
        this.iar330 = iar330;
    }

    public Double getIar721() {
        return iar721;
    }

    public void setIar721(Double iar721) {
        this.iar721 = iar721;
    }

    public Double getIdue() {
        return idue;
    }

    public void setIdue(Double idue) {
        this.idue = idue;
    }

    public Double getIdue015() {
        return idue015;
    }

    public void setIdue015(Double idue015) {
        this.idue015 = idue015;
    }

    public Double getIdue030() {
        return idue030;
    }

    public void setIdue030(Double idue030) {
        this.idue030 = idue030;
    }

    public Double getIdue060() {
        return idue060;
    }

    public void setIdue060(Double idue060) {
        this.idue060 = idue060;
    }

    public Double getIdue090() {
        return idue090;
    }

    public void setIdue090(Double idue090) {
        this.idue090 = idue090;
    }

    public Double getIdue180() {
        return idue180;
    }

    public void setIdue180(Double idue180) {
        this.idue180 = idue180;
    }

    public Double getIdue360() {
        return idue360;
    }

    public void setIdue360(Double idue360) {
        this.idue360 = idue360;
    }

    public Double getIdue361() {
        return idue361;
    }

    public void setIdue361(Double idue361) {
        this.idue361 = idue361;
    }

    public Double getIdue120() {
        return idue120;
    }

    public void setIdue120(Double idue120) {
        this.idue120 = idue120;
    }

    public Double getIdue150() {
        return idue150;
    }

    public void setIdue150(Double idue150) {
        this.idue150 = idue150;
    }

    public Double getIdue210() {
        return idue210;
    }

    public void setIdue210(Double idue210) {
        this.idue210 = idue210;
    }

    public Double getIdue240() {
        return idue240;
    }

    public void setIdue240(Double idue240) {
        this.idue240 = idue240;
    }

    public Double getIdue270() {
        return idue270;
    }

    public void setIdue270(Double idue270) {
        this.idue270 = idue270;
    }

    public Double getIdue300() {
        return idue300;
    }

    public void setIdue300(Double idue300) {
        this.idue300 = idue300;
    }

    public Double getIdue330() {
        return idue330;
    }

    public void setIdue330(Double idue330) {
        this.idue330 = idue330;
    }

    public Double getIdue721() {
        return idue721;
    }

    public void setIdue721(Double idue721) {
        this.idue721 = idue721;
    }

    public Double getUndue() {
        return undue;
    }

    public void setUndue(Double undue) {
        this.undue = undue;
    }

    @Override
    public String toString() {
        return "CollectionDO{" +
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
                ", iar015=" + iar015 +
                ", iar030=" + iar030 +
                ", iar060=" + iar060 +
                ", iar090=" + iar090 +
                ", iar180=" + iar180 +
                ", iar360=" + iar360 +
                ", iar361=" + iar361 +
                ", iar120=" + iar120 +
                ", iar150=" + iar150 +
                ", iar210=" + iar210 +
                ", iar240=" + iar240 +
                ", iar270=" + iar270 +
                ", iar300=" + iar300 +
                ", iar330=" + iar330 +
                ", iar721=" + iar721 +
                ", idue=" + idue +
                ", idue015=" + idue015 +
                ", idue030=" + idue030 +
                ", idue060=" + idue060 +
                ", idue090=" + idue090 +
                ", idue180=" + idue180 +
                ", idue360=" + idue360 +
                ", idue361=" + idue361 +
                ", idue120=" + idue120 +
                ", idue150=" + idue150 +
                ", idue210=" + idue210 +
                ", idue240=" + idue240 +
                ", idue270=" + idue270 +
                ", idue300=" + idue300 +
                ", idue330=" + idue330 +
                ", idue721=" + idue721 +
                ", undue=" + undue +
                '}';
    }
}
