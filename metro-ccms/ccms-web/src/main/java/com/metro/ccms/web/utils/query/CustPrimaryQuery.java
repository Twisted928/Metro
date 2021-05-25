package com.metro.ccms.web.utils.query;

import com.metro.ccms.common.core.query.BaseQuery;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 14:45 2020/12/17
 * @Modified By:
 */
public class CustPrimaryQuery extends BaseQuery {

    /** 客户编码 */
    private String custCode;

    /** 客户名称 */
    private String custName;

    /** 统一社会信用代码 */
    private String creditnoCcms;

    /** 是否黑名单（0：否；1：是） */
    private Integer ifblack;



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

    public String getCreditnoCcms() {
        return creditnoCcms;
    }

    public void setCreditnoCcms(String creditnoCcms) {
        this.creditnoCcms = creditnoCcms;
    }

    public Integer getIfblack() {
        return ifblack;
    }

    public void setIfblack(Integer ifblack) {
        this.ifblack = ifblack;
    }
}
