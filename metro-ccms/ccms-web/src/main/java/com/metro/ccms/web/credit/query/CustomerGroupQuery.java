package com.metro.ccms.web.credit.query;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;
import java.util.Date;

public class CustomerGroupQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 客户组编码 */
    private String custGroup;

    /** 客户组名称 */
    private String custgrName;

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup;
    }

    public String getCustgrName() {
        return custgrName;
    }

    public void setCustgrName(String custgrName) {
        this.custgrName = custgrName;
    }
}
