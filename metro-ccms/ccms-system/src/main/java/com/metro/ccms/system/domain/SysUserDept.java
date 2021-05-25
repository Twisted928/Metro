package com.metro.ccms.system.domain;

/**
 * 用户和岗位关联 sys_user_post
 * 
 * @author ruoyi
 */
public class SysUserDept
{
    /** 用户ID */
    private Long userId;
    
    /** 岗位ID */
    private Long deptId;

    /** 岗位编码 */
    private String deptCode;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String toString() {
        return "SysUserDept{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                '}';
    }
}
