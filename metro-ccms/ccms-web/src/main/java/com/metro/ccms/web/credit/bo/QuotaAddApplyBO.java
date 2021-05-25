package com.metro.ccms.web.credit.bo;

import com.metro.ccms.common.annotation.Excel;

import java.io.Serializable;

/**
 * @Author: fangyongjie
 * @Description: 新增限额申请业务对象
 * @Date: Created in 18:07 2021/1/18
 * @Modified By:
 */
public class QuotaAddApplyBO implements Serializable {


    /**
     * 岗位id
     */
    private String postId;
    /**
     * 门店编码
     */
    private String storeNo;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 卡号编码
     */
    private String custNo;
    /**
     * 卡号名称
     */
    private String custName;
    /**
     * 客户类型
     */
    private String custType;
    /**
     * 客户类型名称
     */
    private String custTypeName;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 业务类型名称
     */
    private String businessTypeName;
    /**
     * 额度类型
     */
    private String limitType;
    /**
     * 额度类型名称
     */
    private String limitTypeName;
    /**
     * 客户组
     */
    private String custGroup;
    /**
     * 客户组名称
     */
    private String custGroupName;
    /**
     * 信用组主表ID
     */
    private Long groupId;
    /**
     * 信用组名称
     */
    private String groupName;
    /**
     * 统一社会信用代码
     */
    private String identificationId;
    /**
     * 客户经理编码
     */
    private String custManagerCode;
    /**
     * 客户经理名称
     */
    private String custManagerName;
    /**
     * 客户潜力
     */
    private String dealerPotential;


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    public String getCustGroupName() {
        return custGroupName;
    }

    public void setCustGroupName(String custGroupName) {
        this.custGroupName = custGroupName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCustTypeName() {
        return custTypeName;
    }

    public void setCustTypeName(String custTypeName) {
        this.custTypeName = custTypeName;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getLimitTypeName() {
        return limitTypeName;
    }

    public void setLimitTypeName(String limitTypeName) {
        this.limitTypeName = limitTypeName;
    }

    public String getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(String identificationId) {
        this.identificationId = identificationId;
    }

    public String getCustManagerCode() {
        return custManagerCode;
    }

    public void setCustManagerCode(String custManagerCode) {
        this.custManagerCode = custManagerCode;
    }

    public String getCustManagerName() {
        return custManagerName;
    }

    public void setCustManagerName(String custManagerName) {
        this.custManagerName = custManagerName;
    }

    public String getDealerPotential() {
        return dealerPotential;
    }

    public void setDealerPotential(String dealerPotential) {
        this.dealerPotential = dealerPotential;
    }
}
