package com.metro.ccms.web.credit.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;
import java.util.Date;

/**
 *<p>
 *  授信管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/9
 * @desc
 **/
public class CreditManagementQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 申请单号
     */
    private String applicationno;

    /**
     * 客户编码
     */
    private String custcode;
    /**
     * 客户名称
     */
    private String custname;
    /**
     * 行业类型
     */
    private String industrytype;
    /**
     * 模型名称
     */
    private String modname;
    /**
     * 评估日期开始
     */
    private String appraisaldatestr;
    /**
     * 评估日期结束
     */
    private String appraisaldateend;
    /**
     * 客户评级
     */
    private String rank;
    /**
     * 状态(0：无效，1：有效)
     */
    private String status;

    /**
     * 删除标记(0：被删除，1：正常)
     */
    private String deleted;
    /**
     * 生效时间
     */
    private Date validfrom;
    /**
     * 到期时间
     */
    private Date validto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationno() {
        return applicationno;
    }

    public void setApplicationno(String applicationno) {
        this.applicationno = applicationno;
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

    public String getIndustrytype() {
        return industrytype;
    }

    public void setIndustrytype(String industrytype) {
        this.industrytype = industrytype;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname;
    }

    public String getAppraisaldatestr() {
        return appraisaldatestr;
    }

    public void setAppraisaldatestr(String appraisaldatestr) {
        this.appraisaldatestr = appraisaldatestr;
    }

    public String getAppraisaldateend() {
        return appraisaldateend;
    }

    public void setAppraisaldateend(String appraisaldateend) {
        this.appraisaldateend = appraisaldateend;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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

    @Override
    public String toString() {
        return "CreditManagementQuery{" +
                "id='" + id + '\'' +
                ", applicationno='" + applicationno + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", industrytype='" + industrytype + '\'' +
                ", modname='" + modname + '\'' +
                ", appraisaldatestr='" + appraisaldatestr + '\'' +
                ", appraisaldateend='" + appraisaldateend + '\'' +
                ", rank='" + rank + '\'' +
                ", status='" + status + '\'' +
                ", deleted='" + deleted + '\'' +
                ", validfrom=" + validfrom +
                ", validto=" + validto +
                '}';
    }
}
