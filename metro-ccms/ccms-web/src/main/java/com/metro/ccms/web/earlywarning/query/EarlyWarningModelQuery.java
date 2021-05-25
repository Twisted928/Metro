package com.metro.ccms.web.earlywarning.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *<p>
 *  预警模型
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/8
 *
 **/
public class EarlyWarningModelQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    private String mocdelName;
    /**
     * 模型编码
     */
    private String mocdelCode;
    /**
     * 状态0无效1有效
     */
    private Integer status;

    public String getMocdelName() {
        return mocdelName;
    }

    public void setMocdelName(String mocdelName) {
        this.mocdelName = mocdelName;
    }

    public String getMocdelCode() {
        return mocdelCode;
    }

    public void setMocdelCode(String mocdelCode) {
        this.mocdelCode = mocdelCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
