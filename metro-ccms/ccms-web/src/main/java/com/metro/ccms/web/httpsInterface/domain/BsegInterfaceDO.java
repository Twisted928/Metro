package com.metro.ccms.web.httpsInterface.domain;

import java.io.Serializable;
import java.util.Date;

/**
* @description SAP财务明细账接口表 tb_interface_bseg
* @author weiwenhui
* @date 2021/01/22 15:38
*/
public class BsegInterfaceDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 自增Id */
    private Long id;
    /** 接口编号 */
    private String ifid;
    /** 门店编码 */
    private String storeId;
    /** 顾客编号(7位CRM顾客编号) */
    private String customerId;
    /** 公司代码 */
    private String bukrs;
    /** SAP客户编码 */
    private String kunnr;
    /** 分配 */
    private String zuonr;
    /** 凭证编号 */
    private String belnr;
    /** 凭证类型 */
    private String blart;
    /** 凭证日期 */
    private Date bldat;
    /** 财年 */
    private Integer gjahr;
    /** 特别总帐标志 */
    private String umhkz;
    /** 凭证行号 */
    private Integer docln;
    /** 利润中心 */
    private String prctr;
    /** 付款参考 */
    private String kidno;
    /** 文本 */
    private String sgtxt;
    /** 付款条件 */
    private String zterm;
    /** 过账日期 */
    private Date budat;
    /** 付款日期 */
    private Date zaldt;
    /** 付款基准日期 */
    private Date zfbdt;
    /** 清帐凭证 */
    private String augbl;
    /** 科目 */
    private String hkont;
    /** 科目名称 */
    private String txt50;
    /** 本币金额 */
    private Double dmbtr;
    /** 本币货币 */
    private String lWaers;
    /** 凭证货币 */
    private String waers;
    /** 交易金额 */
    private Double wrbtr;
    /** 清账日期 */
    private Date augdt;
    /** 写入时间 */
    private Date ddate;

    private String departCode;
    private String cardName;
    private String custName;
    private String custCode;

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIfid() {
        return ifid;
    }

    public void setIfid(String ifid) {
        this.ifid = ifid;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getZuonr() {
        return zuonr;
    }

    public void setZuonr(String zuonr) {
        this.zuonr = zuonr;
    }

    public String getBelnr() {
        return belnr;
    }

    public void setBelnr(String belnr) {
        this.belnr = belnr;
    }

    public String getBlart() {
        return blart;
    }

    public void setBlart(String blart) {
        this.blart = blart;
    }

    public Date getBldat() {
        return bldat;
    }

    public void setBldat(Date bldat) {
        this.bldat = bldat;
    }

    public Integer getGjahr() {
        return gjahr;
    }

    public void setGjahr(Integer gjahr) {
        this.gjahr = gjahr;
    }

    public String getUmhkz() {
        return umhkz;
    }

    public void setUmhkz(String umhkz) {
        this.umhkz = umhkz;
    }

    public Integer getDocln() {
        return docln;
    }

    public void setDocln(Integer docln) {
        this.docln = docln;
    }

    public String getPrctr() {
        return prctr;
    }

    public void setPrctr(String prctr) {
        this.prctr = prctr;
    }

    public String getKidno() {
        return kidno;
    }

    public void setKidno(String kidno) {
        this.kidno = kidno;
    }

    public String getSgtxt() {
        return sgtxt;
    }

    public void setSgtxt(String sgtxt) {
        this.sgtxt = sgtxt;
    }

    public String getZterm() {
        return zterm;
    }

    public void setZterm(String zterm) {
        this.zterm = zterm;
    }

    public Date getBudat() {
        return budat;
    }

    public void setBudat(Date budat) {
        this.budat = budat;
    }

    public Date getZaldt() {
        return zaldt;
    }

    public void setZaldt(Date zaldt) {
        this.zaldt = zaldt;
    }

    public Date getZfbdt() {
        return zfbdt;
    }

    public void setZfbdt(Date zfbdt) {
        this.zfbdt = zfbdt;
    }

    public String getAugbl() {
        return augbl;
    }

    public void setAugbl(String augbl) {
        this.augbl = augbl;
    }

    public String getHkont() {
        return hkont;
    }

    public void setHkont(String hkont) {
        this.hkont = hkont;
    }

    public String getTxt50() {
        return txt50;
    }

    public void setTxt50(String txt50) {
        this.txt50 = txt50;
    }

    public Double getDmbtr() {
        return dmbtr;
    }

    public void setDmbtr(Double dmbtr) {
        this.dmbtr = dmbtr;
    }

    public String getlWaers() {
        return lWaers;
    }

    public void setlWaers(String lWaers) {
        this.lWaers = lWaers;
    }

    public String getWaers() {
        return waers;
    }

    public void setWaers(String waers) {
        this.waers = waers;
    }

    public Double getWrbtr() {
        return wrbtr;
    }

    public void setWrbtr(Double wrbtr) {
        this.wrbtr = wrbtr;
    }

    public Date getAugdt() {
        return augdt;
    }

    public void setAugdt(Date augdt) {
        this.augdt = augdt;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }
}
