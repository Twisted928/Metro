package com.metro.ccms.system.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.TreeSelect;
import com.metro.ccms.common.core.domain.entity.SysBasicData;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 参数服务
 * @Date: Created in 10:44 2020/12/14
 * @Modified By:
 */
public interface ISysBasicDataService {


    /**
     * 获取参数列表
     * @param sysBasicData
     * @return
     */
    public List<SysBasicData> getDataList(SysBasicData sysBasicData);
    /**
     * 新增参数
     * @param sysBasicData
     * @return
     */
    public Result insertDept(SysBasicData sysBasicData);

    /**
     * 构建前端所需要下拉树结构
     * @param datas 参数列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDeptTreeSelect(List<SysBasicData> datas);

    /**
     * 启用、禁用
     * @param sysBasicData
     * @return
     */
    public Result enableOrDisable(SysBasicData sysBasicData);

    /**
     * 是否存在子节点
     * @param id 参数ID
     * @return 结果
     */
    public boolean hasChildById(Long id);

    /**
     * 根据id删除参数
     * @param id
     * @return
     */
    public Result deleteDataById(Long id);

}
