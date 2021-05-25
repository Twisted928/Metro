package com.metro.ccms.system.mapper;

import java.util.List;
import java.util.Set;

import com.metro.ccms.system.domain.SysNotice;
import com.metro.ccms.system.domain.SysNoticeRs;
import org.apache.ibatis.annotations.Param;

/**
 * 通知公告表 数据层
 * 
 * @author ruoyi
 */
public interface SysNoticeMapper
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public SysNotice selectNoticeById(Long noticeId);

    /**
     * 个人中心-分页获取公告
     * @param userId
     * @return
     */
    public List<SysNotice> getCenterNotice(@Param("userId") Long userId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(SysNotice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(SysNotice notice);

    /**
     * 批量删除公告
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);

    /**
     * 根据部门id获取部门名称
     * @param depts
     * @return
     */
    public List<String> getDeptNameById(@Param("depts") List<Long> depts);

    /**
     * 根据角色id获取角色名称
     * @param roles
     * @return
     */
    public List<String> getRoleNameById(@Param("roles") List<Long> roles);

    /**
     * 根据公告id获取公告部门关系
     * @param noticeId
     * @return
     */
    public List<Long> getNoticeDept(@Param("noticeId") Long noticeId);

    /**
     * 根据公告id获取公告角色关系
     * @param noticeId
     * @return
     */
    public List<Long> getNoticeRole(@Param("noticeId") Long noticeId);

    /**
     * 根据公告id删除公告部门关系
     * @param noticeId
     * @return
     */
    public void deleteNoticeDept(@Param("noticeId") Long noticeId);

    /**
     * 根据公告id删除公告角色关系
     * @param noticeId
     * @return
     */
    public void deleteNoticeRole(@Param("noticeId") Long noticeId);

    /**
     * 保存公告部门关系
     * @param sysNoticeRs
     * @return
     */
    public void insertNoticeDept(SysNoticeRs sysNoticeRs);

    /**
     * 保存公告角色关系
     * @param sysNoticeRs
     * @return
     */
    public void insertNoticeRole(SysNoticeRs sysNoticeRs);

    /**
     * 获取部门下的人员名称
     * @param depts
     * @return
     */
    public Set<String> getNickNameByDeptId(@Param("depts") List<Long> depts);

    /**
     * 获取角色下的人员名称
     * @param roles
     * @return
     */
    public Set<String> getNickNameByRoleId(@Param("roles") List<Long> roles);
}
