package com.metro.ccms.web.model.query;

import com.metro.ccms.common.core.query.BaseQuery;

/**
 *<p>
 *  规则引擎指标
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public class RuleModelIndexQuery extends BaseQuery {

    private Long id;
    /**
     * 指标名称
     */
    private String name;
    /**
     * 指标类型（A:信用准入 B:保险准入）
     */
    private String type;
    /**
     * 类型
     */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
