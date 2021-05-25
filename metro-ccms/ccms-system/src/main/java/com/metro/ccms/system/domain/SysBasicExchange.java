package com.metro.ccms.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: fangyongjie
 * @Description: 汇率表
 * @Date: Created in 14:10 2020/12/15
 * @Modified By:
 */
public class SysBasicExchange extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 状态(0：无效，1：有效) */
    private Integer status;

    /** 币种 */
    @Excel(name = "币种",sort = 0)
    private String currency;

    /** 兑换币种 */
    @Excel(name = "兑换币种",sort = 1)
    private String currencyDh;

    /** 月度 */
    @Excel(name = "月度",sort = 2)
    private String month;

    /** 汇率 */
    @Excel(name = "汇率",sort = 3)
    private BigDecimal exchangeRate;

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

    /** 接收前台的date类型日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date monthDate;


    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(Date monthDate) {
        this.monthDate = monthDate;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyDh() {
        return currencyDh;
    }

    public void setCurrencyDh(String currencyDh) {
        this.currencyDh = currencyDh;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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
}
