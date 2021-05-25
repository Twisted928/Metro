package com.metro.ccms.web.model.query;

import com.metro.ccms.common.core.query.BaseQuery;

/**
 *<p>
 *  规则引擎管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public class RuleModelInfoQuery extends BaseQuery {

    private Long id;
    /**
     * 指标名称
     */
    private String name;
    /**
     * 状态(0：无效，1：有效)
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
