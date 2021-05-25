package com.metro.ccms.web.credit.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

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
public class CreditManagementDO extends BaseEntity implements Serializable {

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
     * 模型编码
     */
    private String modcode;
    /**
     * 模型名称
     */
    private String moddesc;
    /**
     * 评估日期
     */
    private String appraisaldate;
    /**
     * 评估得分
     */
    private String grade;
    /**
     * 客户评级
     */
    private String rank;
    /**
     * 建议额度
     */
    private String advicelimit;
    /**
     * 建议账期
     */
    private String advicedays;
    /**
     * 总发布额度
     */
    private String limit;
    /**
     * 是否黑名单
     */
    private String ifblack;
    /**
     * 是否白名单
     */
    private String ifwhite;
    /**
     * 信用组名称
     */
    private String groupname;
    /**
     * 申请人
     */
    private String createdby;
    /**
     * 申请时间
     */
    private String createtime;
    /**
     * 更信人
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

    public String getModcode() {
        return modcode;
    }

    public void setModcode(String modcode) {
        this.modcode = modcode;
    }

    public String getModdesc() {
        return moddesc;
    }

    public void setModdesc(String moddesc) {
        this.moddesc = moddesc;
    }

    public String getAppraisaldate() {
        return appraisaldate;
    }

    public void setAppraisaldate(String appraisaldate) {
        this.appraisaldate = appraisaldate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAdvicelimit() {
        return advicelimit;
    }

    public void setAdvicelimit(String advicelimit) {
        this.advicelimit = advicelimit;
    }

    public String getAdvicedays() {
        return advicedays;
    }

    public void setAdvicedays(String advicedays) {
        this.advicedays = advicedays;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getIfblack() {
        return ifblack;
    }

    public void setIfblack(String ifblack) {
        this.ifblack = ifblack;
    }

    public String getIfwhite() {
        return ifwhite;
    }

    public void setIfwhite(String ifwhite) {
        this.ifwhite = ifwhite;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
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
        return "CreditManagementDO{" +
                "id='" + id + '\'' +
                ", applicationno='" + applicationno + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", industrytype='" + industrytype + '\'' +
                ", modcode='" + modcode + '\'' +
                ", moddesc='" + moddesc + '\'' +
                ", appraisaldate='" + appraisaldate + '\'' +
                ", grade='" + grade + '\'' +
                ", rank='" + rank + '\'' +
                ", advicelimit='" + advicelimit + '\'' +
                ", advicedays='" + advicedays + '\'' +
                ", limit='" + limit + '\'' +
                ", ifblack='" + ifblack + '\'' +
                ", ifwhite='" + ifwhite + '\'' +
                ", groupname='" + groupname + '\'' +
                ", createdby='" + createdby + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatedby='" + updatedby + '\'' +
                ", updatetime=" + updatetime +
                ", status='" + status + '\'' +
                ", deleted='" + deleted + '\'' +
                ", validfrom=" + validfrom +
                ", validto=" + validto +
                '}';
    }
}