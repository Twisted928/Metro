package com.metro.ccms.web.utils.query;

import com.metro.ccms.common.core.query.BaseQuery;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 16:55 2020/12/17
 * @Modified By:
 */
public class CustMembersQuery extends BaseQuery {

    /** 门店编码 */
    private String storeCode;

    /** 客户编码 */
    private String custCode;

    /** 客户名称 */
    private String custName;

    /** 卡号编码 */
    private String cardCode;

    /** 卡号名称 */
    private String cardName;

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
}
