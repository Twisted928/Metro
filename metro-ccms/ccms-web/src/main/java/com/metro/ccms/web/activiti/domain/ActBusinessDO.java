package com.metro.ccms.web.activiti.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 流程场景配置表
 */
public class ActBusinessDO extends BaseEntity {


    private Long id;
    /**
     * 场景名称
     */
    private String name;
    /**
     * 场景key
     */
    private String mkey;
    /**
     * 流程图id
     */
    private String actModelId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;


    //冗余字段
    /**
     * 是否有发起权限
     */
    private boolean ifStart;
    /**
     * 新增场景发起人角色列表
     */
    private List<Long> roleIdList;


    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public boolean isIfStart() {
        return ifStart;
    }

    public void setIfStart(boolean ifStart) {
        this.ifStart = ifStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMkey() {
        return this.mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getActModelId() {
        return this.actModelId;
    }

    public void setActModelId(String actModelId) {
        this.actModelId = actModelId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public ActBusinessDO(String mkey, String actModelId) {
        this.mkey = mkey;
        this.actModelId = actModelId;
    }

    public ActBusinessDO() {
    }
}
