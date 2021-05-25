package com.metro.ccms.web.customer.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;

/**
 *<p>
 *     客户清单
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/10
 * @desc
 **/
public class CustomerlistQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 客户编码
     */
    private String custcode;

    /**
     * 客户名称
     */
    private String custname;

    /**
     * 状态(0：无效，1：有效)
     */
    private String status;

    /**
     * 删除标记(0：正常，1：已删除)
     */
    private String deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustcode() {
        return custcode;
    }

    public void setCustcode(String custcode) {
        this.custcode = custcode;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "CustomerlistQuery{" +
                "id='" + id + '\'' +
                ", custcode='" + custcode + '\'' +
                ", custname='" + custname + '\'' +
                ", status='" + status + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
