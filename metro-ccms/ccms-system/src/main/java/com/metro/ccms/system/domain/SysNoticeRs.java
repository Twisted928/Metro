package com.metro.ccms.system.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

/**
 * @Author: fangyongjie
 * @Description: 公告-部门   公告-角色
 * @Date: Created in 15:50 2021/2/19
 * @Modified By:
 */
public class SysNoticeRs extends BaseEntity {

    /**
     * 公告id
     */
    private Long noticeId;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 角色id
     */
    private Long roleId;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
