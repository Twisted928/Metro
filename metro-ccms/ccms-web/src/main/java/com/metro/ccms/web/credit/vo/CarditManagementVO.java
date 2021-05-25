package com.metro.ccms.web.credit.vo;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;

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
public class CarditManagementVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 申请单号
     */
    @Excel(name = "申请单号", sort = 16)
    private String applicationno;
    /**
     * 部门编码
     */
    private String departcode;
    /**
     * 部门名称
     */
    @Excel(name = "客户名称", sort = 2)
    private String departname;
    /**
     * 门店编码
     */
    @Excel(name = "门店编码", sort = 3)
    private String storecode;
    /**
     * 客户编码
     */
    @Excel(name = "客户编码", sort = 0)
    private String custcode;
    /**
     * 客户名称
     */
    @Excel(name = "客户名称", sort = 1)
    private String custname;
    /**
     * 卡号编码
     */
    @Excel(name = "卡号编码", sort = 4)
    private String cardcode;
    /**
     * 卡号名称
     */
    @Excel(name = "卡号名称", sort = 5)
    private String cardname;
    /**
     * 申请账期
     */
    @Excel(name = "申请账期", sort = 8)
    private String applypayterm;
    /**
     * 申请额度
     */
    @Excel(name = "申请额度", sort = 9)
    private String applylimit;
    /**
     * 额度类型
     */
    @Excel(name = "额度类型", sort = 6)
    private String limittype;
    /**
     * 评估分数
     */
    private String grade;
    /**
     * 客户等级
     */
    private String rank;
    /**
     * 发布账期
     */
    @Excel(name = "发布账期", sort = 10)
    private String paymentterm;
    /**
     * 发布额度
     */
    @Excel(name = "发布额度", sort = 11)
    private String limit;
    /**
     * 来源
     */
    @Excel(name = "来源", sort = 7)
    private String source;
    /**
     * 申请人姓名
     */
    @Excel(name = "申请人姓名", sort = 18)
    private String applicant;

    /**
     * 申请时间
     */
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 19)
    private Date applytime;
    /**
     * 生效时间
     */
    @Excel(name = "生效时间", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 12)
    private Date validfrom;
    /**
     * 到期时间
     */
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 13)
    private Date validto;

    /**
     * 客户组
     */
    @Excel(name = "客户组", sort = 14)
    private String custgrname;
    /**
     * 信用组名称
     */
    @Excel(name = "信用组", sort = 15)
    private String groupname;
    /**
     * 信用组账期
     */
    private String grouppayterm;
    /**
     * 信用组额度
     */
    private String grouplimit;
    /**
     * 模型名称
     */
    private String modname;
    /**
     * 评估日期
     */
    private Date appraisaldate;
    /**
     * 建议额度
     */
    private String advicelimit;
    /**
     * 建议账期
     */
    private String advicedays;
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
    @Excel(name = "状态", readConverterExp = "0=无效,1=有效",sort = 20)
    private String status;

    /**
     * 删除标记(0：被删除，1：正常)
     */
    private String deleted;
    /**
     * 是否信用组
     */
    private String ifcreditgr;
    /**
     * 审批人
     */
    private String approvedby;
    /**
     * 审批时间
     */
    private Date approvetime;
    /**
     * 审批账期
     */
    private String approvalpayterm;
    /**
     * 审批额度
     */
    private String approvallimit;
    /**
     * 审批编号
     */
    private String approvalcode;
    /**
     * 发布日期
     */
    @Excel(name = "发布日期", width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 17)
    private String releasedate;

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

    public String getStorecode() {
        return storecode;
    }

    public void setStorecode(String storecode) {
        this.storecode = storecode;
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

    public String getApplypayterm() {
        return applypayterm;
    }

    public void setApplypayterm(String applypayterm) {
        this.applypayterm = applypayterm;
    }

    public String getApplylimit() {
        return applylimit;
    }

    public void setApplylimit(String applylimit) {
        this.applylimit = applylimit;
    }

    public String getLimittype() {
        return limittype;
    }

    public void setLimittype(String limittype) {
        this.limittype = limittype;
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

    public String getPaymentterm() {
        return paymentterm;
    }

    public void setPaymentterm(String paymentterm) {
        this.paymentterm = paymentterm;
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

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
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

    public String getGrouppayterm() {
        return grouppayterm;
    }

    public void setGrouppayterm(String grouppayterm) {
        this.grouppayterm = grouppayterm;
    }

    public String getGrouplimit() {
        return grouplimit;
    }

    public void setGrouplimit(String grouplimit) {
        this.grouplimit = grouplimit;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
        this.modname = modname;
    }

    public Date getAppraisaldate() {
        return appraisaldate;
    }

    public void setAppraisaldate(Date appraisaldate) {
        this.appraisaldate = appraisaldate;
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

    public String getIfcreditgr() {
        return ifcreditgr;
    }

    public void setIfcreditgr(String ifcreditgr) {
        this.ifcreditgr = ifcreditgr;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(Date approvetime) {
        this.approvetime = approvetime;
    }

    public String getApprovalpayterm() {
        return approvalpayterm;
    }

    public void setApprovalpayterm(String approvalpayterm) {
        this.approvalpayterm = approvalpayterm;
    }

    public String getApprovallimit() {
        return approvallimit;
    }

    public void setApprovallimit(String approvallimit) {
        this.approvallimit = approvallimit;
    }

    public String getApprovalcode() {
        return approvalcode;
    }

    public void setApprovalcode(String approvalcode) {
        this.approvalcode = approvalcode;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    @Override
    public String toString() {
        return "CarditManagementVO{" +
                "id='" + id + '\'' +
                ", applicationno='" + applicationno + '\'' +
                ", departcode='" + departcode + '\'' +
                ", departname='" + departname + '\'' +
                ", storecode='" + storecode + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", cardcode='" + cardcode + '\'' +
                ", cardname='" + cardname + '\'' +
                ", applypayterm='" + applypayterm + '\'' +
                ", applylimit='" + applylimit + '\'' +
                ", limittype='" + limittype + '\'' +
                ", grade='" + grade + '\'' +
                ", rank='" + rank + '\'' +
                ", paymentterm='" + paymentterm + '\'' +
                ", limit='" + limit + '\'' +
                ", source='" + source + '\'' +
                ", applicant='" + applicant + '\'' +
                ", applytime=" + applytime +
                ", validfrom=" + validfrom +
                ", validto=" + validto +
                ", custgrname='" + custgrname + '\'' +
                ", groupname='" + groupname + '\'' +
                ", grouppayterm='" + grouppayterm + '\'' +
                ", grouplimit='" + grouplimit + '\'' +
                ", modname='" + modname + '\'' +
                ", appraisaldate=" + appraisaldate +
                ", advicelimit='" + advicelimit + '\'' +
                ", advicedays='" + advicedays + '\'' +
                ", createdby='" + createdby + '\'' +
                ", createtime=" + createtime +
                ", updatedby='" + updatedby + '\'' +
                ", updatetime=" + updatetime +
                ", status='" + status + '\'' +
                ", deleted='" + deleted + '\'' +
                ", ifcreditgr='" + ifcreditgr + '\'' +
                ", approvedby='" + approvedby + '\'' +
                ", approvetime=" + approvetime +
                ", approvalpayterm='" + approvalpayterm + '\'' +
                ", approvallimit='" + approvallimit + '\'' +
                ", approvalcode='" + approvalcode + '\'' +
                ", releasedate='" + releasedate + '\'' +
                '}';
    }
}