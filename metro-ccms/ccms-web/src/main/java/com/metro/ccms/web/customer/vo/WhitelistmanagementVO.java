package com.metro.ccms.web.customer.vo;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.web.utils.domain.ApprovalRecordDO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* @description   白名单管理
* @author JiXiang.Song
* @date
*/
public class WhitelistmanagementVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 创建人
     */
    @Excel(name = "申请人",sort = 5)
    private String createdby;
    /**
     * 创建时间
     */
    @Excel(name = "申请时间",sort = 6,dateFormat = "yyyy-MM-dd")
    private String createtime;
    /**
     * 申请单号
     */
    @Excel(name = "申请单号",sort = 0)
    private String applicationno;
    /**
     * 客户编码
     */
    @Excel(name = "客户编码",sort = 2)
    private String custcode;
    /**
     * 客户名称
     */
    @Excel(name = "客户名称",sort = 1)
    private String custname;
    /**
     * 生效时间
     */
    private Date validfrom;
    /**
     * 到期时间
     */
    private Date validto;
    /**
     * 有效期
     */
    @Excel(name = "有效期",sort = 4)
    private String validFromTo;
    /**
     * 原因
     */
    private String reason;
    /**
     * 申请人姓名
     */
    private String applicant;
    /**
     * 申请时间
     */
    private Date applytime;
    /**
     * 更新人
     */
    @Excel(name = "更新人",sort = 7)
    private String updatedby;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间",sort = 8,dateFormat = "yyyy-MM-dd")
    private Date updatetime;
    /**
     * 审批状态：1审批中 2生效 3退回 4 已撤销
     */
    @Excel(name = "申请单状态",sort = 3, readConverterExp = "1=审批中,2=生效,3=退回,4=已撤销")
    private String approvestatus;
    /**
     * 删除标记(0：被删除，1：正常)
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
     * 附件信息
     */
    private String attachitems;
    /**
     * 附件名称
     */
    private String attachmentname;
    /**
     * 附件地址
     */
    private String attachmenturl;
    /**
     * 流程实例id
     */
    private String instanceid;
    /**
     * 附件
     */
    private List<SysBasicFile> SysBasicFile;
    /**
     * 审批记录
     */
    private List<ApprovalRecordDO> approvalRecordDO;

    public List<com.metro.ccms.system.domain.SysBasicFile> getSysBasicFile() {
        return SysBasicFile;
    }

    public void setSysBasicFile(List<com.metro.ccms.system.domain.SysBasicFile> sysBasicFile) {
        SysBasicFile = sysBasicFile;
    }

    public List<ApprovalRecordDO> getApprovalRecordDO() {
        return approvalRecordDO;
    }

    public void setApprovalRecordDO(List<ApprovalRecordDO> approvalRecordDO) {
        this.approvalRecordDO = approvalRecordDO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getApprovestatus() {
        return approvestatus;
    }

    public void setApprovestatus(String approvestatus) {
        this.approvestatus = approvestatus;
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

    public String getAttachitems() {
        return attachitems;
    }

    public void setAttachitems(String attachitems) {
        this.attachitems = attachitems;
    }

    public String getAttachmentname() {
        return attachmentname;
    }

    public void setAttachmentname(String attachmentname) {
        this.attachmentname = attachmentname;
    }

    public String getAttachmenturl() {
        return attachmenturl;
    }

    public void setAttachmenturl(String attachmenturl) {
        this.attachmenturl = attachmenturl;
    }

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }

    public String getValidFromTo() {
        return validFromTo;
    }

    public void setValidFromTo(String validFromTo) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        validFromTo=sdf.format(validfrom)+"-"+sdf.format(validto);
        this.validFromTo = validFromTo;
    }

    @Override
    public String toString() {
        return "WhitelistmanagementVO{" +
                "id='" + id + '\'' +
                ", createdby='" + createdby + '\'' +
                ", createtime='" + createtime + '\'' +
                ", applicationno='" + applicationno + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", validfrom=" + validfrom +
                ", validto=" + validto +
                ", reason='" + reason + '\'' +
                ", applicant='" + applicant + '\'' +
                ", applytime=" + applytime +
                ", updatedby='" + updatedby + '\'' +
                ", updatetime=" + updatetime +
                ", approvestatus='" + approvestatus + '\'' +
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
                ", attachitems='" + attachitems + '\'' +
                ", attachmentname='" + attachmentname + '\'' +
                ", attachmenturl='" + attachmenturl + '\'' +
                ", instanceid='" + instanceid + '\'' +
                ", SysBasicFile=" + SysBasicFile +
                ", approvalRecordDO=" + approvalRecordDO +
                '}';
    }
}