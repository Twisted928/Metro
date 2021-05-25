package com.metro.ccms.web.httpsInterface.domain;

import java.io.Serializable;

/**
 * @Author: fangyongjie
 * @Description: 行业代码配置对象 tb_interface_industry
 * @Date: Created in 17:36 2021/1/18
 * @Modified By:
 */
public class IndustryDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 一级行业代码 */
    private String custAssortSectionId;

    /** 一级行业名称 */
    private String custAssortSectionDesc;

    /** 二级行业代码 */
    private String branchMainGroupId;

    /** 二级行业名称 */
    private String branchMainGroupDesc;

    /** 三级行业代码 */
    private String branchFamilyId;

    /** 三级行业名称 */
    private String branchFamilyDesc;

    /** 四级行业代码 */
    private String branchId;

    /** 四级行业名称 */
    private String branchDesc;


    public String getCustAssortSectionId() {
        return custAssortSectionId;
    }

    public void setCustAssortSectionId(String custAssortSectionId) {
        this.custAssortSectionId = custAssortSectionId;
    }

    public String getCustAssortSectionDesc() {
        return custAssortSectionDesc;
    }

    public void setCustAssortSectionDesc(String custAssortSectionDesc) {
        this.custAssortSectionDesc = custAssortSectionDesc;
    }

    public String getBranchMainGroupId() {
        return branchMainGroupId;
    }

    public void setBranchMainGroupId(String branchMainGroupId) {
        this.branchMainGroupId = branchMainGroupId;
    }

    public String getBranchMainGroupDesc() {
        return branchMainGroupDesc;
    }

    public void setBranchMainGroupDesc(String branchMainGroupDesc) {
        this.branchMainGroupDesc = branchMainGroupDesc;
    }

    public String getBranchFamilyId() {
        return branchFamilyId;
    }

    public void setBranchFamilyId(String branchFamilyId) {
        this.branchFamilyId = branchFamilyId;
    }

    public String getBranchFamilyDesc() {
        return branchFamilyDesc;
    }

    public void setBranchFamilyDesc(String branchFamilyDesc) {
        this.branchFamilyDesc = branchFamilyDesc;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchDesc() {
        return branchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }
}
