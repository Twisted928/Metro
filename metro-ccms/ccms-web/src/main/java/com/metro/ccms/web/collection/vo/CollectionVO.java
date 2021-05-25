package com.metro.ccms.web.collection.vo;

import com.metro.ccms.common.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
* @description
* @author weiwenhui
* @date 2020/12/11 17:57
*/
public class CollectionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *主键ID
     */
    private Long id;
    /**
     * 催收单号
     */
    @Excel(name = "催收单号",sort = 0)
    private String applicationNo;
    /**
     * 部门编码
     */
    @Excel(name = "部门编码",sort = 1)
    private String departCode;
    /**
     * 部门名称
     */
    @Excel(name = "部门名称",sort = 2)
    private String deptName;
    /**
     * 门店编码
     */
    @Excel(name = "门店编码",sort = 3)
    private String storeCode;
    /**
     * 客户编码
     */
    @Excel(name = "客户编码",sort = 4)
    private String custCode;
    /**
     * 客户名称
     */
    @Excel(name = "客户名称",sort = 5)
    private String custName;
    /**
     * 卡号编码
     */
    @Excel(name = "卡号编码",sort = 6)
    private String cardCode;
    /**
     * 卡号名称
     */
    @Excel(name = "卡号名称",sort = 7)
    private String cardName;
    /**
     * 账期内应收
     */
    @Excel(name = "账期内应收",sort = 8)
    private Double undue;
    /**
     * 应收金额
     */
    @Excel(name = "应收金额",sort = 9)
    private Double iar;
    /**
     * 逾期金额
     */
    @Excel(name = "逾期金额",sort = 10)
    private Double idue;
    /**
     * 逾期账龄15天
     */
    @Excel(name = "逾期账龄15天",sort = 11)
    private Double idue015;
    /**
     * 逾期账龄1月
     */
    @Excel(name = "逾期账龄1月",sort = 12)
    private Double idue030;
    /**
     * 逾期账龄2月
     */
    @Excel(name = "逾期账龄2月",sort = 13)
    private Double idue060;
    /**
     * 逾期账龄3月
     */
    @Excel(name = "逾期账龄3月",sort = 14)
    private Double idue090;
    /**
     * 逾期账龄6月
     */
    @Excel(name = "逾期账龄6月",sort = 15)
    private Double idue180;
    /**
     * 逾期账龄12月
     */
    @Excel(name = "逾期账龄12月",sort = 16)
    private Double idue360;
    /**
     * 逾期账龄1-2年
     */
    @Excel(name = "逾期超一年",sort = 17)
    private Double idue361;
    /**
     * 创建人
     */
    @Excel(name = "创建人",sort = 18)
    private String createdBy;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间",width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 19)
    private Date createTime;
    /**
     * 更新人
     */
    @Excel(name = "更新人",sort = 20)
    private String updatedBy;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间",width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 21)
    private Date updateTime;
    /**
     * 状态
     */
    @Excel(name = "单据状态",sort = 22, readConverterExp = "1=催收中,2=催收成功,3=催收失败")
    private Integer status;
    /**
     * 写入日期
     */
    @Excel(name = "日期",width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 23)
    private Date ddate;
    /**
     * 删除标记(0正常 1删除)
     */
    private Integer deleted;
    /**
     * 商场地址
     */
    private String address;
    /**
     * 客户地址
     */
    private String cusaddress;
    /**
     * 联系人
     */
    private String nickName;
    /**
     * 电话
     */
    private String phonenumber;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 合计
     */
    private Double heji;
    /**
     * 逾期应还合计
     */
    private Double idueHeji;
    /**
     * 逾期91-365合计
     */
    private Double idueheji365;
    /**
     * 户名
     */
    private String accountName;
    /**
     * 开户行
     */
    private String accountBank;
    /**
     * 账户号
     */
    private String accountNo;
    /**
     * 传真
     */
    private String fax;
    /**
     * 催收函标识
     */
    private String iflag;

    public String getIflag() {
        return iflag;
    }

    public void setIflag(String iflag) {
        this.iflag = iflag;
    }

    public Double getIdueheji365() {
        return idueheji365;
    }

    public void setIdueheji365(Double idueheji365) {
        this.idueheji365 = idueheji365;
    }

    public Double getHeji() {
        return heji;
    }

    public void setHeji(Double heji) {
        this.heji = heji;
    }

    public Double getIdueHeji() {
        return idueHeji;
    }

    public void setIdueHeji(Double idueHeji) {
        this.idueHeji = idueHeji;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCusaddress() {
        return cusaddress;
    }

    public void setCusaddress(String cusaddress) {
        this.cusaddress = cusaddress;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public Double getUndue() {
        return undue;
    }

    public void setUndue(Double undue) {
        this.undue = undue;
    }

    public Double getIar() {
        return iar;
    }

    public void setIar(Double iar) {
        this.iar = iar;
    }

    public Double getIdue() {
        return idue;
    }

    public void setIdue(Double idue) {
        this.idue = idue;
    }

    public Double getIdue015() {
        return idue015;
    }

    public void setIdue015(Double idue015) {
        this.idue015 = idue015;
    }

    public Double getIdue030() {
        return idue030;
    }

    public void setIdue030(Double idue030) {
        this.idue030 = idue030;
    }

    public Double getIdue060() {
        return idue060;
    }

    public void setIdue060(Double idue060) {
        this.idue060 = idue060;
    }

    public Double getIdue090() {
        return idue090;
    }

    public void setIdue090(Double idue090) {
        this.idue090 = idue090;
    }

    public Double getIdue180() {
        return idue180;
    }

    public void setIdue180(Double idue180) {
        this.idue180 = idue180;
    }

    public Double getIdue360() {
        return idue360;
    }

    public void setIdue360(Double idue360) {
        this.idue360 = idue360;
    }

    public Double getIdue361() {
        return idue361;
    }

    public void setIdue361(Double idue361) {
        this.idue361 = idue361;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "CollectionVO{" +
                "id=" + id +
                ", applicationNo='" + applicationNo + '\'' +
                ", departCode='" + departCode + '\'' +
                ", deptName='" + deptName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", custCode='" + custCode + '\'' +
                ", custName='" + custName + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", cardName='" + cardName + '\'' +
                ", undue=" + undue +
                ", iar=" + iar +
                ", idue=" + idue +
                ", idue015=" + idue015 +
                ", idue030=" + idue030 +
                ", idue060=" + idue060 +
                ", idue090=" + idue090 +
                ", idue180=" + idue180 +
                ", idue360=" + idue360 +
                ", idue361=" + idue361 +
                ", createdBy='" + createdBy + '\'' +
                ", createTime=" + createTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", ddate=" + ddate +
                ", deleted=" + deleted +
                ", address='" + address + '\'' +
                ", cusaddress='" + cusaddress + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", heji=" + heji +
                ", idueHeji=" + idueHeji +
                ", idueheji365=" + idueheji365 +
                ", accountName='" + accountName + '\'' +
                ", accountBank='" + accountBank + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", fax='" + fax + '\'' +
                ", iflag='" + iflag + '\'' +
                '}';
    }
}
