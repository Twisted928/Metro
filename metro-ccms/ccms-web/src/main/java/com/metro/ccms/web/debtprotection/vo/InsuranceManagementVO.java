package com.metro.ccms.web.debtprotection.vo;

import com.metro.ccms.common.core.domain.BaseEntity;
import com.metro.ccms.web.debtprotection.domian.InsureDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 投保管理合集VO
 *
 * @author guangle
 * @date 2020/12/8
 * @since 1.0.0
 */
public class InsuranceManagementVO extends BaseEntity implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 公司编码
     */
    private String compCode;

    /**
     * 保单号
     */
    private String policyno;

    /**
     * 客户编码
     */
    private String custCode;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 买方代码
     */
    private String buyerno;

    /**
     * 批复状态(0：无效，1：有效)
     */
    private Integer status;

    /**
     * 批复说明
     */
    private String reason;

    /**
     * 保单主体
     */
    private String body;

    /**
     * 汇总发票号
     */
    private String invoiceNo;

    /**
     * 汇总发票金额
     */
    private BigDecimal invoicesum;

    /**
     * 付款方式
     */
    private String paymode;

    /**
     * 出运日期
     */
    private Date transportdate;

    /**
     * 保险账期
     */
    private Integer quotaDays;

    /**
     * 汇总发票日期
     */
    private Date invoicedate;

    /**
     * 海关10位商品代码
     */
    private String code10;

    /**
     * 商品名称
     */
    private String goodsname;

    /**
     * 费率
     */
    private BigDecimal premiumrate;

    /**
     * 保费
     */
    private BigDecimal premium;

    /**
     * 汇率
     */
    private BigDecimal dollarrate;

    /**
     * 清账类型
     */
    private String qztype;

    /**
     * 发票汇总最早发票日期
     */
    private Date btime;

    /**
     * 发票汇总最晚发票日期
     */
    private Date etime;

    /**
     * 公司名称
     */
    private String compName;

    /**
     * 保险评级
     */
    private String creditLevel;

    /**
     * 保险额度
     */
    private BigDecimal quota;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private Integer deleted;

    /**
     * 时间戳
     */
    private Date timestamp;

    /**
     * 合同编码
     */
    private String contractNo;

    /**
     * LC信用证号
     */
    private String lcno;

    /**
     * 保单表ID
     */
    private Long policId;

    /**
     * 费用承担人编码
     */
    private String clientno;


    /**
     * 投保金额
     */
    private BigDecimal insuresum;

    /**
     * 员工姓名
     */
    private String employeename;

    /**
     * 0:国内，1：海外
     */
    private Integer ifoversea;

    /**
     * 国家编码
     */
    private String countryCode;

    /**
     * 是否已投保（0：未头；1：已投）
     */
    private Integer baseapply;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 投标id集合
     */
    private List<String> idList;

    /**
     * 已投保发票表
     */
    private List<InsureDO> insureDOList;

    public List<InsureDO> getInsureDOList() {
        return insureDOList;
    }

    public void setInsureDOList(List<InsureDO> insureDOList) {
        this.insureDOList = insureDOList;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getBuyerno() {
        return buyerno;
    }

    public void setBuyerno(String buyerno) {
        this.buyerno = buyerno;
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

    public String getPolicyno() {
        return policyno;
    }

    public void setPolicyno(String policyno) {
        this.policyno = policyno;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    public BigDecimal getQuota() {
        return quota;
    }

    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }

    public Integer getQuotaDays() {
        return quotaDays;
    }

    public void setQuotaDays(Integer quotaDays) {
        this.quotaDays = quotaDays;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getLcno() {
        return lcno;
    }

    public void setLcno(String lcno) {
        this.lcno = lcno;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getPolicId() {
        return policId;
    }

    public void setPolicId(Long policId) {
        this.policId = policId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getInvoicesum() {
        return invoicesum;
    }

    public void setInvoicesum(BigDecimal invoicesum) {
        this.invoicesum = invoicesum;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getQztype() {
        return qztype;
    }

    public void setQztype(String qztype) {
        this.qztype = qztype;
    }

    public String getClientno() {
        return clientno;
    }

    public void setClientno(String clientno) {
        this.clientno = clientno;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public BigDecimal getInsuresum() {
        return insuresum;
    }

    public void setInsuresum(BigDecimal insuresum) {
        this.insuresum = insuresum;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public Date getTransportdate() {
        return transportdate;
    }

    public void setTransportdate(Date transportdate) {
        this.transportdate = transportdate;
    }

    public String getCode10() {
        return code10;
    }

    public void setCode10(String code10) {
        this.code10 = code10;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public Integer getIfoversea() {
        return ifoversea;
    }

    public void setIfoversea(Integer ifoversea) {
        this.ifoversea = ifoversea;
    }

    public BigDecimal getPremiumrate() {
        return premiumrate;
    }

    public void setPremiumrate(BigDecimal premiumrate) {
        this.premiumrate = premiumrate;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getDollarrate() {
        return dollarrate;
    }

    public void setDollarrate(BigDecimal dollarrate) {
        this.dollarrate = dollarrate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getBaseapply() {
        return baseapply;
    }

    public void setBaseapply(Integer baseapply) {
        this.baseapply = baseapply;
    }
}
