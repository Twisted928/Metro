package com.metro.ccms.web.contract.vo;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.Date;

/**
 *<p>
 *  合同模板
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/18
 * @desc
 **/
public class ContracttemplateVO extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 合同文本
     */
    private String contracttext;
    /**
     * 合同类型
     */
    private String contracttype;
    /**
     * 模板名称
     */
    private String contracttemp;
    /**
     * 状态0无效1有效
     */
    private String status;
    /**
     * 删除标记0正常1已删除
     */
    private String deleted;
    /**
     * 创建人
     */
    private String createdby;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 更新人
     */
    private String updatedby;

    /**
     * 更新时间
     */
    private Date updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContracttext() {
        return contracttext;
    }

    public void setContracttext(String contracttext) {
        this.contracttext = contracttext;
    }

    public String getContracttype() {
        return contracttype;
    }

    public void setContracttype(String contracttype) {
        this.contracttype = contracttype;
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "ContracttemplateVO{" +
                "id='" + id + '\'' +
                ", contracttext='" + contracttext + '\'' +
                ", contracttype='" + contracttype + '\'' +
                ", contracttemp='" + contracttemp + '\'' +
                ", status='" + status + '\'' +
                ", deleted='" + deleted + '\'' +
                ", createdby='" + createdby + '\'' +
                ", createtime=" + createtime +
                ", updatedby='" + updatedby + '\'' +
                ", updatetime=" + updatetime +
                '}';
    }
}