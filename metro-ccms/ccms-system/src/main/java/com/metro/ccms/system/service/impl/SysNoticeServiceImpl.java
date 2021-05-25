package com.metro.ccms.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.system.domain.SysNoticeRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.metro.ccms.system.domain.SysNotice;
import com.metro.ccms.system.mapper.SysNoticeMapper;
import com.metro.ccms.system.service.ISysNoticeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公告 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService
{
    @Autowired
    private SysNoticeMapper noticeMapper;

    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId)
    {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 个人中心-分页获取公告列表
     * @return
     */
    @Override
    public List<SysNotice> getCenterNotice() {
        return noticeMapper.getCenterNotice(SecurityUtils.getLoginUser().getUser().getUserId());
    }

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice)
    {
        List<SysNotice> list=noticeMapper.selectNoticeList(notice);
        if (list!=null && list.size()>0){
            for (SysNotice sysNotice:list){
                sysNotice.setDeptIds(noticeMapper.getNoticeDept(sysNotice.getNoticeId()));
                sysNotice.setRoleIds(noticeMapper.getNoticeRole(sysNotice.getNoticeId()));
                List<String> dNames=noticeMapper.getDeptNameById(sysNotice.getDeptIds());
                if (dNames!=null && dNames.size()>0){
                    StringBuffer dNameBuffer=new StringBuffer();
                    for (String name:dNames){
                        dNameBuffer.append(name).append(",");
                    }
                    sysNotice.setDepts(dNameBuffer.substring(0,dNameBuffer.length()-1));
                }

                List<String> rNames=noticeMapper.getRoleNameById(sysNotice.getRoleIds());
                if (rNames!=null && rNames.size()>0){
                    StringBuffer rNameBuffer=new StringBuffer();
                    for (String name:rNames){
                        rNameBuffer.append(name).append(",");
                    }
                    sysNotice.setRoles(rNameBuffer.substring(0,rNameBuffer.length()-1));
                }
            }
        }
        return list;
    }

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice)
    {
        setStatus(notice);
        noticeMapper.insertNotice(notice);
        setPublic(notice);
        return 1;
    }

    private void setStatus(SysNotice notice){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        if (Integer.parseInt(sdf.format(notice.getValidFrom()))<=Integer.parseInt(sdf.format(new Date()))){
            notice.setStatus("1");
        }else{
            notice.setStatus("0");
        }
    }

    private void setPublic(SysNotice notice){
        if (notice.getDeptIds()!=null && notice.getDeptIds().size()>0){
            noticeMapper.deleteNoticeDept(notice.getNoticeId());
            for (Long deptId:notice.getDeptIds()){
                SysNoticeRs noticeRs=new SysNoticeRs();
                noticeRs.setNoticeId(notice.getNoticeId());
                noticeRs.setDeptId(deptId);
                noticeMapper.insertNoticeDept(noticeRs);
            }
        }
        if (notice.getRoleIds()!=null && notice.getRoleIds().size()>0){
            noticeMapper.deleteNoticeRole(notice.getNoticeId());
            for (Long roleId:notice.getRoleIds()){
                SysNoticeRs noticeRs=new SysNoticeRs();
                noticeRs.setNoticeId(notice.getNoticeId());
                noticeRs.setRoleId(roleId);
                noticeMapper.insertNoticeRole(noticeRs);
            }
        }
    }

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice)
    {
        setStatus(notice);
        noticeMapper.updateNotice(notice);
        setPublic(notice);
        return 1;
    }

    /**
     * 删除公告对象
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId)
    {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }

    @Override
    public List<String> getNoticeNickName(List<Long> depts,List<Long> roles){
        Set<String> allSet=new HashSet<>();
        if (depts!=null && depts.size()>0){
            allSet.addAll(noticeMapper.getNickNameByDeptId(depts));
        }
        if (roles!=null && roles.size()>0){
            allSet.addAll(noticeMapper.getNickNameByRoleId(roles));
        }
        return new ArrayList<>(allSet);
    }
}
