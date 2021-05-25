package com.metro.ccms.web.httpsInterface.dto;

import java.io.Serializable;

/**
 * @Author: fangyongjie
 * @Description: DCRM客户信息定时同步
 * @Date: Created in 17:16 2021/2/23
 * @Modified By:
 */
public class CustDcrmDTO implements Serializable {

    /**
     * 卡号编码
     */
    private String custNo;
    /**
     * 门店编码
     */
    private String storeNo;


    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }
}
