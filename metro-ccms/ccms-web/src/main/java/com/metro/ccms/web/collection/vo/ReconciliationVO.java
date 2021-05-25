package com.metro.ccms.web.collection.vo;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;
import com.metro.ccms.system.domain.SysBasicFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @description 对账显示对象
* @author weiwenhui
* @date 2020/12/14 11:25
*/
public class ReconciliationVO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *附件信息查询
     */
    private List<SysBasicFile> sysBasicFile;
    /**
     *主键ID
     */
    private Long id;
    /**
     * 对账单号
     */
    @Excel(name = "对账单号",sort = 0)
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
     * 应收金额
     */
    @Excel(name = "应收金额",sort = 8)
    private Double iar;
    /**
     * 逾期金额
     */
    @Excel(name = "逾期金额",sort = 9)
    private Double idue;
    /**
     * 对账状态
     */
    @Excel(name = "对账状态",sort = 10, readConverterExp = "0=未开始,1=对账中,2=对账完成,3=存在差异")
    private Integer status;
    /**
     * 创建人
     */
    @Excel(name = "创建人",sort = 11)
    private String createdBy;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间",width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 12)
    private Date createTime;
    /**
     * 更新人
     */
    @Excel(name = "更新人",sort = 13)
    private String updatedBy;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间",width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 14)
    private Date updateTime;
    /**
     * 写入日期(对账日期)
     */
    @Excel(name = "日期",width = 30, dateFormat = "yyyy-MM-dd", type = Excel.Type.EXPORT,sort = 16)
    private Date ddate;
    /**
     * 未逾期欠款金额(账期内)
     */
    private Double unidue;
    /**
     * 客户类型
     */
    private String custType;
    /**
     * 账户名
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


    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public Double getUnidue() {
        return unidue;
    }

    public void setUnidue(Double unidue) {
        this.unidue = unidue;
    }

    public List<SysBasicFile> getSysBasicFile() {
        return sysBasicFile;
    }

    public void setSysBasicFile(List<SysBasicFile> sysBasicFile) {
        this.sysBasicFile = sysBasicFile;
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

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public String toString() {
        return "ReconciliationVO{" +
                "sysBasicFile=" + sysBasicFile +
                ", id=" + id +
                ", applicationNo='" + applicationNo + '\'' +
                ", departCode='" + departCode + '\'' +
                ", deptName='" + deptName + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", custCode='" + custCode + '\'' +
                ", custName='" + custName + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", cardName='" + cardName + '\'' +
                ", iar=" + iar +
                ", idue=" + idue +
                ", status=" + status +
                ", createdBy='" + createdBy + '\'' +
                ", createTime=" + createTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updateTime=" + updateTime +
                ", ddate=" + ddate +
                ", unidue=" + unidue +
                ", custType='" + custType + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountBank='" + accountBank + '\'' +
                ", accountNo='" + accountNo + '\'' +
                '}';
    }
}
