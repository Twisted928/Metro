package com.metro.ccms.web.httpsInterface.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: fangyongjie
 * @Description: DCRM客户信息实时获取 tb_interface_customer
 * @Date: Created in 14:13 2021/1/18
 * @Modified By:
 */
public class CustomerInterfaceDO implements Serializable {


    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** CRM客户编码(卡号编码) */
    private String custNo;

    /** CRM客户名称主体(卡号名称) */
    private String custName;

    /** CRM客户名称后缀 */
    private String custLastName;

    /** 门店编码 */
    private String storeNo;

    /** CRM行业ID */
    private String branchId;

    /** 客户注册地址 */
    private String address;

    /** 客户创建日期 */
    private Date createDate;

    /** 客户信息最后更新时间 */
    private Date dateLastChange;

    /** 统一社会信用代码 */
    private String identificationId;

    /** 税号 */
    private String taxCode;

    /** 岗位Id */
    private String postId;

    /** 信用冻结状态 */
    private String creditBlock;

    /** 信用冻结原因 */
    private String creditBlockReason;

    /** 客户冻结状态 */
    private String custBlock;

    /** 客户冻结原因 */
    private String custBlockReason;

    /** 客户方联系人 */
    private String custContact;

    /** 客户方座机 */
    private String phone;

    /** 客户方电子邮箱 */
    private String custEmail;

    /** 写入时间 */
    private Date ddate;


    //冗余字段
    /** 门店名称 */
    private String storeName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDateLastChange() {
        return dateLastChange;
    }

    public void setDateLastChange(Date dateLastChange) {
        this.dateLastChange = dateLastChange;
    }

    public String getIdentificationId() {
        return identificationId;
    }

    public void setIdentificationId(String identificationId) {
        this.identificationId = identificationId;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getCreditBlock() {
        return creditBlock;
    }

    public void setCreditBlock(String creditBlock) {
        this.creditBlock = creditBlock;
    }

    public String getCreditBlockReason() {
        return creditBlockReason;
    }

    public void setCreditBlockReason(String creditBlockReason) {
        this.creditBlockReason = creditBlockReason;
    }

    public String getCustBlock() {
        return custBlock;
    }

    public void setCustBlock(String custBlock) {
        this.custBlock = custBlock;
    }

    public String getCustBlockReason() {
        return custBlockReason;
    }

    public void setCustBlockReason(String custBlockReason) {
        this.custBlockReason = custBlockReason;
    }

    public String getCustContact() {
        return custContact;
    }

    public void setCustContact(String custContact) {
        this.custContact = custContact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
