package com.metro.ccms.web.credit.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metro.ccms.common.core.query.BaseQuery;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 限额申请查询对象
 * @Date: Created in 17:56 2021/1/4
 * @Modified By:
 */
public class QuotaApplyQuery extends BaseQuery {

    /** 申请单号 */
    private String applicationNo;
    /** 部门 */
    private List<String> storeCodeList;
    /** 卡号编码 */
    private String cardCode;
    /** 卡号名称 */
    private String cardName;
    /** 客户类型 */
    private String ctype;
    /** 业务类型 */
    private String btype;
    /** 额度类型 */
    private String limitType;
    /** 特批类型 1准入特批 2额度特批 3合同特批*/
    private Integer specialType;
    /** 发布时间-开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date grantTimeStart;
    /** 发布时间-结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date grantTimeEnd;
    /** 生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validFrom;
    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validTo;
    /** 客户编码 */
    private String custCode;
    /** 客户名称 */
    private String custName;
    /** 是否信用组 1是 0否*/
    private Integer ifCreditgr;
    /** 审批状态：1草稿 2准入退回 3待复核 4复核驳回 5额度批复中 6批复驳回 7特批中 8待维护合同 9合同符合中
     * 10合同驳回 11待发布 12额度已发布 13发布失败 14已撤销 15超期未发布 */
    private Integer approveStatus;


    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public List<String> getStoreCodeList() {
        return storeCodeList;
    }

    public void setStoreCodeList(List<String> storeCodeList) {
        this.storeCodeList = storeCodeList;
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

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public Integer getSpecialType() {
        return specialType;
    }

    public void setSpecialType(Integer specialType) {
        this.specialType = specialType;
    }

    public Date getGrantTimeStart() {
        return grantTimeStart;
    }

    public void setGrantTimeStart(Date grantTimeStart) {
        this.grantTimeStart = grantTimeStart;
    }

    public Date getGrantTimeEnd() {
        return grantTimeEnd;
    }

    public void setGrantTimeEnd(Date grantTimeEnd) {
        this.grantTimeEnd = grantTimeEnd;
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

    public Integer getIfCreditgr() {
        return ifCreditgr;
    }

    public void setIfCreditgr(Integer ifCreditgr) {
        this.ifCreditgr = ifCreditgr;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }
}
