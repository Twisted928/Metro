package com.metro.ccms.web.customer.domian;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
* @description   黑名单管理
* @author JiXiang.Song
* @date
*/
public class BlackListDO extends BaseEntity implements Serializable {

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
     * 生效日
     */
    private Date validfrom;

    /**
     * 到期日
     */
    private Date validto;

    /**
     * 原因
     */
    private String reason;

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

    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public Date getValidto() {
        return validto;
    }

    public void setValidto(Date validto) {
        this.validto = validto;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    @Override
    public String toString() {
        return "BlackListDO{" +
                "id='" + id + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", validfrom=" + validfrom +
                ", validto=" + validto +
                ", reason='" + reason + '\'' +
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
                '}';
    }
}