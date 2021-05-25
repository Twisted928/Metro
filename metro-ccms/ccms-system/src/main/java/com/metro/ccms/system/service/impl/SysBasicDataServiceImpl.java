package com.metro.ccms.system.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.TreeSelect;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.common.core.domain.entity.SysBasicData;
import com.metro.ccms.system.mapper.SysBasicDataMapper;
import com.metro.ccms.system.service.ISysBasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: fangyongjie
 * @Description: 参数服务
 * @Date: Created in 10:47 2020/12/14
 * @Modified By:
 */
@Service
public class SysBasicDataServiceImpl implements ISysBasicDataService {

    @Autowired
    private SysBasicDataMapper sysBasicDataMapper;



    /**
     * 获取参数列表
     * @param sysBasicData
     * @return
     */
    @Override
    public List<SysBasicData> getDataList(SysBasicData sysBasicData) {
        return sysBasicDataMapper.selectTbBasicDataList(sysBasicData);
    }

    /**
     * 新增/修改
     * @param sysBasicData
     * @return
     */
    @Override
    public Result insertDept(SysBasicData sysBasicData) {
        if(sysBasicData.getParentId()!=0){
            SysBasicData bdata = sysBasicDataMapper.selectTbBasicDataById(sysBasicData.getParentId());
            // 如果父节点不为正常状态,则不允许新增子节点
            if (bdata.getStatus()==0) {
                return Result.error("类型停用，不允许新增!");
            }
        }
        int ctype=sysBasicDataMapper.getBasicDataBySelect(sysBasicData.getCtype(),null);
        int description=sysBasicDataMapper.getBasicDataBySelect(null,sysBasicData.getDescription());
        if (sysBasicData.getId()==null){
            if (ctype>0){
                return Result.error("该参数类型已存在!");
            }
            if (description>0){
                return Result.error("该参数名称已存在!");
            }
            sysBasicData.setCreatedBy(SecurityUtils.getLoginUser().getUser().getNickName());
            sysBasicDataMapper.insertTbBasicData(sysBasicData);
        }else{
            SysBasicData data=sysBasicDataMapper.selectTbBasicDataById(sysBasicData.getId());
            if (!data.getCtype().equals(sysBasicData.getCtype()) && ctype>0){
                return Result.error("该参数类型已存在!");
            }
            if (!data.getDescription().equals(sysBasicData.getDescription()) && description>0){
                return Result.error("该参数名称已存在!");
            }
            sysBasicData.setUpdatedBy(SecurityUtils.getLoginUser().getUser().getNickName());
            sysBasicDataMapper.updateTbBasicData(sysBasicData);
        }
        return Result.success();
    }

    /**
     * 构建前端所需要下拉树结构
     * @param datas 参数列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysBasicData> datas) {
        List<SysBasicData> dataTrees = buildDeptTree(datas);
        return dataTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 启用、禁用
     * @param sysBasicData
     * @return
     */
    @Override
    public Result enableOrDisable(SysBasicData sysBasicData) {
        sysBasicDataMapper.updateTbBasicData(sysBasicData);
        return Result.success();
    }

    /**
     * 是否存在子节点
     * @param id 参数ID
     * @return 结果
     */
    @Override
    public boolean hasChildById(Long id)
    {
        int result = sysBasicDataMapper.hasChildById(id);
        return result > 0 ? true : false;
    }

    /**
     * 根据id删除参数
     * @param id
     * @return
     */
    @Override
    public Result deleteDataById(Long id){
        sysBasicDataMapper.deleteTbBasicDataById(id);
        return Result.success();
    }

    /**
     * 构建前端所需要树结构
     * @param datas 参数列表
     * @return 树结构列表
     */
    public List<SysBasicData> buildDeptTree(List<SysBasicData> datas) {
        List<SysBasicData> returnList = new ArrayList<SysBasicData>();
        List<Long> tempList = new ArrayList<Long>();
        for (SysBasicData sysBasicData : datas)
        {
            tempList.add(sysBasicData.getId());
        }
        for (Iterator<SysBasicData> iterator = datas.iterator(); iterator.hasNext();)
        {
            SysBasicData data = (SysBasicData) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(data.getParentId()))
            {
                recursionFn(datas, data);
                returnList.add(data);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = datas;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysBasicData> list, SysBasicData t) {
        // 得到子节点列表
        List<SysBasicData> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysBasicData tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysBasicData> getChildList(List<SysBasicData> list, SysBasicData t)
    {
        List<SysBasicData> tlist = new ArrayList<SysBasicData>();
        Iterator<SysBasicData> it = list.iterator();
        while (it.hasNext()) {
            SysBasicData n = (SysBasicData) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysBasicData> list, SysBasicData t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }


}
