package com.metro.ccms.web.credit.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: fangyongjie
 * @Description: 限额申请主表
 * @Date: Created in 17:30 2021/1/4
 * @Modified By:
 */
public class QuotaApplyDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 状态(0：无效，1：有效) */
    private Integer status;

    /** 删除标记(0：正常，1：已删除) */
    private Integer delete;

    /** 备用字段 */
    private String item1;

    /** 备用字段 */
    private String item2;

    /** 备用字段 */
    private String item3;

    /** 备用字段 */
    private String item4;

    /** 备用字段 */
    private String item5;

    /** 备用字段 */
    private String item6;

    /** 备用字段 */
    private String item7;

    /** 备用字段 */
    private String item8;

    /** 备用字段 */
    private String item9;

    /** 描述 */
    private String description;

    /** 申请单号 */
    @Excel(name = "申请单号",sort = 0)
    private String applicationNo;

    /** 申请人姓名 */
    @Excel(name = "申请人姓名",sort = 17)
    private String applicant;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd",sort = 18)
    private Date applyTime;

    /** 附件信息 */
    @Excel(name = "附件信息")
    private String attachitems;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String attachmentName;

    /** 附件地址 */
    @Excel(name = "附件地址")
    private String attachmentUrl;

    /** 币种 */
    @Excel(name = "币种")
    private String currency;

    /** 客户类型 */
    @Excel(name = "客户类型",sort = 9,
            readConverterExp = "NKA=全国重点客户,Related=集团关联客户,Project=项目客户,Internal=内部公司客户,Normal=普通客户")
    private String ctype;

    /** 生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效时间", width = 30, dateFormat = "yyyy-MM-dd",sort = 15)
    private Date validFrom;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd",sort = 16)
    private Date validTo;

    /** 门店名称 */
    @Excel(name = "门店名称",sort = 4)
    private String storeName;

    /** 门店编码 */
    @Excel(name = "门店编码",sort = 3)
    private String storeCode;

    /** 客户编码 */
    @Excel(name = "客户编码",sort = 1)
    private String custCode;

    /** 客户名称 */
    @Excel(name = "客户名称",sort = 2)
    private String custName;

    /** 卡号编码 */
    @Excel(name = "卡号编码",sort = 5)
    private String cardCode;

    /** 卡号名称 */
    @Excel(name = "卡号名称",sort = 6)
    private String cardName;

    /** 信用组编号 */
    @Excel(name = "信用组编号")
    private String groupCode;

    /** 申请账期 */
    @Excel(name = "申请账期",sort = 7)
    private Long applyPayterm;

    /** 申请额度 */
    @Excel(name = "申请额度",sort = 8)
    private BigDecimal applyLimit;

    /** 额度类型 */
    @Excel(name = "额度类型",sort = 10,
            readConverterExp = "One time=一次性额度,Normal=长期额度,TemporaryLimit=临时额度,Prepayment=预付款")
    private String limitType;

    /** 信用组账期 */
    @Excel(name = "信用组账期")
    private Long groupPayterm;

    /** 信用组额度 */
    @Excel(name = "信用组额度")
    private BigDecimal groupLimit;

    /** 发布额度 */
    @Excel(name = "发布额度")
    private BigDecimal limit;

    /** 付款条件/发布账期 */
    @Excel(name = "付款条件/发布账期",sort = 13)
    private Integer paymentTerm;

    /** 发布人 */
    @Excel(name = "发布人")
    private String grantedBy;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd",sort = 14)
    private Date grantTime;

    /** 发布状态 */
    @Excel(name = "发布状态")
    private String grantStatus;

    /** 是否信用组 1是 0否*/
    @Excel(name = "是否信用组",sort = 20,readConverterExp = "1=是，0=否")
    private Integer ifCreditgr;

    /** 驳回类型 */
    @Excel(name = "驳回类型")
    private String rejectType;

    /** 驳回类型描述 */
    @Excel(name = "驳回类型描述")
    private String rejectTypeDesc;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approvedBy;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveTime;

    /** 审批账期 */
    @Excel(name = "审批账期")
    private Long approvalPayterm;

    /** 审批额度 */
    @Excel(name = "审批额度")
    private BigDecimal approvalLimit;

    /** 审批状态：1草稿 2准入退回 3待复核 4复核驳回 5额度批复中 6批复驳回 7特批中 8待维护合同 9合同符合中
     * 10合同驳回 11待发布 12额度已发布 13发布失败 14已撤销 15超期未发布  */
    @Excel(name = "审批状态",sort = 12,
            readConverterExp = "1=草稿,2=准入退回,3=待复核,4=复核驳回,5=额度批复中,6=批复驳回,7=特批中,8=待维护合同," +
                    "9=合同符合中,10=合同驳回,11=待发布,12=额度已发布,13=发布失败,14=已撤,15=超期未发布")
    private Integer approveStatus;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String instanceid;

    /** 是否准入特批 1是 0否*/
    @Excel(name = "是否准入特批",sort = 21,readConverterExp = "1=是，0=否")
    private Integer ifSpecialEn;

    /** 业务类型 */
    @Excel(name = "业务类型",sort = 11,readConverterExp = "DiningDeliver=食堂配送业务,WelfareDeliver=福利配送业务,WelfareAtStore=到店购业务")
    private String btype;

    /** 是否额度特批 1是 0否*/
    @Excel(name = "是否额度特批",sort = 22,readConverterExp = "1=是，0=否")
    private Integer ifSpecialLi;

    /** 是否合同特批 1是 0否*/
    @Excel(name = "是否合同特批",sort = 23,readConverterExp = "1=是，0=否")
    private Integer ifSpecialHt;

    /** 信用组名称*/
    @Excel(name = "信用组名称",sort = 19)
    private String groupName;


    public void setPaymentTerm(Integer paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

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

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getAttachitems() {
        return attachitems;
    }

    public void setAttachitems(String attachitems) {
        this.attachitems = attachitems;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Long getApplyPayterm() {
        return applyPayterm;
    }

    public void setApplyPayterm(Long applyPayterm) {
        this.applyPayterm = applyPayterm;
    }

    public BigDecimal getApplyLimit() {
        return applyLimit;
    }

    public void setApplyLimit(BigDecimal applyLimit) {
        this.applyLimit = applyLimit;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public Long getGroupPayterm() {
        return groupPayterm;
    }

    public void setGroupPayterm(Long groupPayterm) {
        this.groupPayterm = groupPayterm;
    }

    public BigDecimal getGroupLimit() {
        return groupLimit;
    }

    public void setGroupLimit(BigDecimal groupLimit) {
        this.groupLimit = groupLimit;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public String getGrantedBy() {
        return grantedBy;
    }

    public void setGrantedBy(String grantedBy) {
        this.grantedBy = grantedBy;
    }

    public Date getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(Date grantTime) {
        this.grantTime = grantTime;
    }

    public String getGrantStatus() {
        return grantStatus;
    }

    public void setGrantStatus(String grantStatus) {
        this.grantStatus = grantStatus;
    }

    public Integer getIfCreditgr() {
        return ifCreditgr;
    }

    public void setIfCreditgr(Integer ifCreditgr) {
        this.ifCreditgr = ifCreditgr;
    }

    public String getRejectType() {
        return rejectType;
    }

    public void setRejectType(String rejectType) {
        this.rejectType = rejectType;
    }

    public String getRejectTypeDesc() {
        return rejectTypeDesc;
    }

    public void setRejectTypeDesc(String rejectTypeDesc) {
        this.rejectTypeDesc = rejectTypeDesc;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Long getApprovalPayterm() {
        return approvalPayterm;
    }

    public void setApprovalPayterm(Long approvalPayterm) {
        this.approvalPayterm = approvalPayterm;
    }

    public BigDecimal getApprovalLimit() {
        return approvalLimit;
    }

    public void setApprovalLimit(BigDecimal approvalLimit) {
        this.approvalLimit = approvalLimit;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }

    public Integer getIfSpecialEn() {
        return ifSpecialEn;
    }

    public void setIfSpecialEn(Integer ifSpecialEn) {
        this.ifSpecialEn = ifSpecialEn;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public Integer getIfSpecialLi() {
        return ifSpecialLi;
    }

    public void setIfSpecialLi(Integer ifSpecialLi) {
        this.ifSpecialLi = ifSpecialLi;
    }

    public Integer getIfSpecialHt() {
        return ifSpecialHt;
    }

    public void setIfSpecialHt(Integer ifSpecialHt) {
        this.ifSpecialHt = ifSpecialHt;
    }
}
