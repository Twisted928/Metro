package com.metro.ccms.web.activiti.vo;

import java.io.Serializable;

/**
 * 人员-角色信息
 * Created by fangyongjie
 */
public class RoleInfoVO implements Serializable {

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户编码
     */
    private String userName;
    /**
     * 用户名称
     */
    private String nickName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
