package com.metro.ccms.web.credit.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;
import java.util.Date;

/**
 *<p>
 *  授信管理信用卡号
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/9
 * @desc
 **/
public class CarditManagementQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 部门编码
     */
    private String departcode;
    /**
     * 部门名称
     */
    private String departname;
    /**
     * 客户编码
     */
    private String custcode;
    /**
     * 客户名称
     */
    private String custname;
    /**
     * 卡号编码
     */
    private String cardcode;
    /**
     * 卡号名称
     */
    private String cardname;
   /**
     * 额度类型
     */
    private String limittype;
    /**
     * 发布额度
     */
    private String limit;
    /**
     * 来源
     */
    private String source;

    /**
     * 生效时间
     */
    private Date validfromstr;
    /**
     * 生效时间
     */
    private Date validfromend;
    /**
     * 到期时间
     */
    private Date validtostr;
    /**
     * 到期时间
     */
    private Date validtoend;
    /**
     * 客户组
     */
    private String custgrname;
    /**
     * 信用组名称
     */
    private String groupname;
    /**
     * 模型名称
     */
    private String modname;
    /**
     * 状态(0：无效，1：有效)
     */
    private String status;

    /**
     * 删除标记(0：被删除，1：正常)
     */
    private String deleted;
    /**
     * 发布开始日期
     */
    private String releasedatestr;
    /**
     * 发布结束日期
     */
    private String releasedateend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartcode() {
        return departcode;
    }

    public void setDepartcode(String departcode) {
        this.departcode = departcode;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
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

    public String getCardcode() {
        return cardcode;
    }

    public void setCardcode(String cardcode) {
        this.cardcode = cardcode;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getLimittype() {
        return limittype;
    }

    public void setLimittype(String limittype) {
        this.limittype = limittype;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getValidfromstr() {
        return validfromstr;
    }

    public void setValidfromstr(Date validfromstr) {
        this.validfromstr = validfromstr;
    }

    public Date getValidfromend() {
        return validfromend;
    }

    public void setValidfromend(Date validfromend) {
        this.validfromend = validfromend;
    }

    public Date getValidtostr() {
        return validtostr;
    }

    public void setValidtostr(Date validtostr) {
        this.validtostr = validtostr;
    }

    public Date getValidtoend() {
        return validtoend;
    }

    public void setValidtoend(Date validtoend) {
        this.validtoend = validtoend;
    }

    public String getCustgrname() {
        return custgrname;
    }

    public void setCustgrname(String custgrname) {
        this.custgrname = custgrname;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname;
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

    public String getReleasedatestr() {
        return releasedatestr;
    }

    public void setReleasedatestr(String releasedatestr) {
        this.releasedatestr = releasedatestr;
    }

    public String getReleasedateend() {
        return releasedateend;
    }

    public void setReleasedateend(String releasedateend) {
        this.releasedateend = releasedateend;
    }

    @Override
    public String toString() {
        return "CarditManagementQuery{" +
                "id='" + id + '\'' +
                ", departcode='" + departcode + '\'' +
                ", departname='" + departname + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", cardcode='" + cardcode + '\'' +
                ", cardname='" + cardname + '\'' +
                ", limittype='" + limittype + '\'' +
                ", limit='" + limit + '\'' +
                ", source='" + source + '\'' +
                ", validfromstr=" + validfromstr +
                ", validfromend=" + validfromend +
                ", validtostr=" + validtostr +
                ", validtoend=" + validtoend +
                ", custgrname='" + custgrname + '\'' +
                ", groupname='" + groupname + '\'' +
                ", modname='" + modname + '\'' +
                ", status='" + status + '\'' +
                ", deleted='" + deleted + '\'' +
                ", releasedatestr='" + releasedatestr + '\'' +
                ", releasedateend='" + releasedateend + '\'' +
                '}';
    }
}
