package com.metro.ccms.web.credit.query;

import com.metro.ccms.common.core.query.BaseQuery;

/**
 * @Author: fangyongjie
 * @Description: DCRM客户信息查询model
 * @Date: Created in 14:32 2021/1/18
 * @Modified By:
 */
public class CustomerInterfaceQuery extends BaseQuery {

    /** CRM客户编码(卡号编码) */
    private String custNo;

    /** CRM客户名称主体(卡号名称) */
    private String custName;

    /** 门店编码 */
    private String storeNo;

    /** 门店名称 */
    private String storeName;

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
