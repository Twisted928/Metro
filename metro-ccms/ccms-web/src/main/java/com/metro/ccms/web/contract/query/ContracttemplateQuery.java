package com.metro.ccms.web.contract.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;

/**
 *<p>
 *  合同模板
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/18
 * @desc
 **/
public class ContracttemplateQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 模板名称
     */
    private String contracttemp;
    /**
     * 有效状态
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContracttemp() {
        return contracttemp;
    }

    public void setContracttemp(String contracttemp) {
        this.contracttemp = contracttemp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ContracttemplateQuery{" +
                "id='" + id + '\'' +
                ", contracttemp='" + contracttemp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
