package com.metro.ccms.web.credit.domain;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;

/**
 * @Author: fangyongjie
 * @Description: 客户主数据
 * @Date: Created in 14:24 2020/12/17
 * @Modified By:
 */
public class CustPrimaryDO extends BaseEntity {

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

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String custCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String custName;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String businessType;

    /** 客户类别 */
    @Excel(name = "客户类别")
    private String custType;

    /** 行业分类 */
    @Excel(name = "行业分类")
    private String industryType;

    /** 组织类型 */
    @Excel(name = "组织类型")
    private String organType;

    /** 是否黑名单（0：否；1：是） */
    @Excel(name = "是否黑名单", readConverterExp = "0=：否；1：是")
    private Integer ifblack;

    /**
     * 统一社会信用代码
     */
    private String creditnoCcms;


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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getOrganType() {
        return organType;
    }

    public void setOrganType(String organType) {
        this.organType = organType;
    }

    public Integer getIfblack() {
        return ifblack;
    }

    public void setIfblack(Integer ifblack) {
        this.ifblack = ifblack;
    }

    public String getCreditnoCcms() {
        return creditnoCcms;
    }

    public void setCreditnoCcms(String creditnoCcms) {
        this.creditnoCcms = creditnoCcms;
    }
}
