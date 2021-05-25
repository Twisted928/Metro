package com.metro.ccms.web.credit.domain;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;

/**
 * @Author: fangyongjie
 * @Description: 客户卡号关系表
 * @Date: Created in 16:51 2020/12/17
 * @Modified By:
 */
public class CustMembersDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 自增列 */
    private Long id;

    /** 状态(0：无效，1：有效) */
    @Excel(name = "状态(0：无效，1：有效)")
    private Integer status;

    /** 删除标记(0：正常，1：已删除) */
    @Excel(name = "删除标记(0：正常，1：已删除)")
    private Integer deleted;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item1;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item2;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item3;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item4;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item5;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item6;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item7;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item8;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item9;

    /** 电子邮件 */
    @Excel(name = "电子邮件")
    private String email;

    /** 部门编码 */
    @Excel(name = "部门编码")
    private String departCode;

    /** 门店编码 */
    @Excel(name = "门店编码")
    private String storeCode;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String custCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String custName;

    /** 卡号编码 */
    @Excel(name = "卡号编码")
    private String cardCode;

    /** 卡号名称 */
    @Excel(name = "卡号名称")
    private String cardName;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 城市街道房号 */
    @Excel(name = "城市街道房号")
    private String cityStreetRoom;

    /** 国家编码 */
    @Excel(name = "国家编码")
    private String countryCode;

    /** 国家名称 */
    @Excel(name = "国家名称")
    private String countryName;

    /** 统一社会信用代码 */
    @Excel(name = "统一社会信用代码")
    private String creditno;

    /** 客户组 */
    @Excel(name = "客户组")
    private String custGroup;

    /** 客户经理编码 */
    @Excel(name = "客户经理编码")
    private String custManagerCode;

    /** 客户经理名称 */
    @Excel(name = "客户经理名称")
    private String custManagerName;

    /** 客户潜力 */
    @Excel(name = "客户潜力")
    private String dealerPotential;

    /** 传真 */
    @Excel(name = "传真")
    private String fax;

    /** 0无     1主客户   2子客户 */
    @Excel(name = "0无     1主客户   2子客户")
    private Integer ifmain;

    /** 销售区域 */
    @Excel(name = "销售区域")
    private String salesDistrict;

    /** 信用组主表ID */
    @Excel(name = "信用组主表ID")
    private Long groupId;

    /** 锁定状态（0：未锁定;1:锁定） */
    @Excel(name = "锁定状态", readConverterExp = "0=：未锁定;1:锁定")
    private Integer custBlock;

    /** 客户锁定状态描述 */
    @Excel(name = "客户锁定状态描述")
    private String custBlockDesc;

    /** 信用冻结标记 1:冻结 0:解冻 */
    @Excel(name = "信用冻结标记 1:冻结 0:解冻")
    private Integer creditBlock;

    /** 信用冻结描述 */
    @Excel(name = "信用冻结描述")
    private String creditBlockDesc;

    /** 客户类型 */
    private String custType;

    /** 业务类型 */
    private String businessType;


    //冗余字段
    /** 客户组名称 */
    private String custGroupName;
    /** 信用组名称 */
    private String groupName;



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityStreetRoom() {
        return cityStreetRoom;
    }

    public void setCityStreetRoom(String cityStreetRoom) {
        this.cityStreetRoom = cityStreetRoom;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCreditno() {
        return creditno;
    }

    public void setCreditno(String creditno) {
        this.creditno = creditno;
    }

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getIfmain() {
        return ifmain;
    }

    public void setIfmain(Integer ifmain) {
        this.ifmain = ifmain;
    }

    public String getSalesDistrict() {
        return salesDistrict;
    }

    public void setSalesDistrict(String salesDistrict) {
        this.salesDistrict = salesDistrict;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getCustBlock() {
        return custBlock;
    }

    public void setCustBlock(Integer custBlock) {
        this.custBlock = custBlock;
    }

    public String getCustBlockDesc() {
        return custBlockDesc;
    }

    public void setCustBlockDesc(String custBlockDesc) {
        this.custBlockDesc = custBlockDesc;
    }

    public Integer getCreditBlock() {
        return creditBlock;
    }

    public void setCreditBlock(Integer creditBlock) {
        this.creditBlock = creditBlock;
    }

    public String getCreditBlockDesc() {
        return creditBlockDesc;
    }

    public void setCreditBlockDesc(String creditBlockDesc) {
        this.creditBlockDesc = creditBlockDesc;
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

    public String getCustGroupName() {
        return custGroupName;
    }

    public void setCustGroupName(String custGroupName) {
        this.custGroupName = custGroupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
