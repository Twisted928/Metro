package com.metro.ccms.web.customer.vo;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.Date;

/**
 *<p>
 *     客户清单
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/10
 * @desc
 **/
public class CustomerlistVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 客户编码
     */
    private String custcode;

    /**
     * 客户名称
     */
    private String custname;

    /**
     * 创建人
     */
    private String createdby;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新人
     */
    private String updatedby;

    /**
     * 更新时间
     */
    private Date updatetime;
    /**
     * 状态(0：无效，1：有效)
     */
    private String status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private String deleted;

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
     * 业务类型
     */
    private String businesstype;
    /**
     * 客户类别
     */
    private String custtype;
    /**
     * 行业分类
     */
    private String industrytype;
    /**
     * 组织类型
     */
    private String organtype;
    /**
     * 是否黑名单
     */
    private String ifblack;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustcode() {
        return custcode;
    }

    public void setCustcode(String custcode) {
        this.custcode = custcode;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
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

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getCusttype() {
        return custtype;
    }

    public void setCusttype(String custtype) {
        this.custtype = custtype;
    }

    public String getIndustrytype() {
        return industrytype;
    }

    public void setIndustrytype(String industrytype) {
        this.industrytype = industrytype;
    }

    public String getOrgantype() {
        return organtype;
    }

    public void setOrgantype(String organtype) {
        this.organtype = organtype;
    }

    public String getIfblack() {
        return ifblack;
    }

    public void setIfblack(String ifblack) {
        this.ifblack = ifblack;
    }

    @Override
    public String toString() {
        return "CustomerlistVO{" +
                "id='" + id + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", createdby='" + createdby + '\'' +
                ", createtime=" + createtime +
                ", updatedby='" + updatedby + '\'' +
                ", updatetime=" + updatetime +
                ", status='" + status + '\'' +
                ", deleted='" + deleted + '\'' +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", item3='" + item3 + '\'' +
                ", item4='" + item4 + '\'' +
                ", item5='" + item5 + '\'' +
                ", item6='" + item6 + '\'' +
                ", item7='" + item7 + '\'' +
                ", item8='" + item8 + '\'' +
                ", item9='" + item9 + '\'' +
                ", businesstype='" + businesstype + '\'' +
                ", custtype='" + custtype + '\'' +
                ", industrytype='" + industrytype + '\'' +
                ", organtype='" + organtype + '\'' +
                ", ifblack='" + ifblack + '\'' +
                '}';
    }
}
