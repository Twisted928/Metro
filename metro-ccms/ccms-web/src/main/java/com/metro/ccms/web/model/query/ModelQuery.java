package com.metro.ccms.web.model.query;

import com.metro.ccms.common.core.query.BaseQuery;

/**
 * @author ：yuanjh
 * @date ：Created in 2019/7/3
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class ModelQuery extends BaseQuery {

    /**
     * DSL：大商旅  ZZPT:自助平台
     */
    private String scope;
    /**
     * 发布 0:未发布 ，1:发布
     */
    private String publish;


    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
