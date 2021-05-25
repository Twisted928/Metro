package com.metro.ccms.web.activiti.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.entity.SysRole;
import com.metro.ccms.web.activiti.domain.ActBusinessDO;
import com.metro.ccms.web.activiti.domain.ActModelRuleDO;
import com.metro.ccms.web.activiti.query.ModelQuery;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 9:50 2021/1/8
 * @Modified By:
 */
public interface ModelService {

    /**
     * 获取流程场景列表
     * @param modelQuery
     * @return
     */
    public List<ActBusinessDO> getBusinessList(ModelQuery modelQuery);

    /**
     * 获取流程下拉列表
     * @return
     */
    public List<ActBusinessDO> getBusiness();

    /**
     * 更新modelId到流程配置表
     * @param actBusinessDO
     */
    public void updateBusinessMid(ActBusinessDO actBusinessDO);

    /**
     * 保存流程配置表
     * @param actBusinessDO
     * @return
     */
    public Result saveActBusiness(ActBusinessDO actBusinessDO);

    /**
     * 获取规则信息
     * @param mid
     * @return
     */
    public List<ActModelRuleDO> getModelRule(String mid);

    /**
     * 保存规则信息
     * @param list
     * @return
     */
    public Result saveModelRule(List<ActModelRuleDO> list);

    /**
     * 获取角色信息
     * @return
     */
    public List<SysRole> getRole();
}
