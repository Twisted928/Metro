package com.metro.ccms.web.activiti.query;

import com.metro.ccms.common.core.query.BaseQuery;

/**
 * @Author: fangyongjie
 * @Description: 流程信息分页查询model
 * Date: 9:35 2021/1/8
 */
public class ModelQuery extends BaseQuery {

    /**
     * 场景名称
     */
    private String name;
    /**
     * 场景key
     */
    private String mkey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }
}
