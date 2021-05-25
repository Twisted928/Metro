package com.metro.ccms.web.activiti.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.entity.SysRole;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.system.mapper.SysRoleMapper;
import com.metro.ccms.web.activiti.domain.ActModelRuleDO;
import com.metro.ccms.web.activiti.mapper.ActModelMapper;
import com.metro.ccms.web.activiti.domain.ActBusinessDO;
import com.metro.ccms.web.activiti.query.ModelQuery;
import com.metro.ccms.web.activiti.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ActModelMapper modelDao;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    public ModelServiceImpl() {
    }

    /**
     * 获取流程场景列表
     * @param modelQuery
     * @return
     */
    @Override
    public List<ActBusinessDO> getBusinessList(ModelQuery modelQuery){
        List<ActBusinessDO> list=modelDao.getBusinessList(modelQuery);
        if (list!=null && list.size()>0){
            StringBuffer buffer=null;
            for (ActBusinessDO businessDO:list){
                String[] strs=businessDO.getRoleId().split(",");
                List<String> nameList=modelDao.getRoleNameById(strs);
                buffer=new StringBuffer();
                for (String name:nameList){
                    buffer.append(name).append(",");
                }
                businessDO.setRoleName(buffer.substring(0,buffer.length()-1));
            }
        }
        return list;
    }

    /**
     * 获取流程下拉列表
     * @return
     */
    @Override
    public List<ActBusinessDO> getBusiness() {
        return this.modelDao.selectList();
    }

    /**
     * 更新modelId到流程配置表
     * @param actBusinessDO
     */
    @Override
    public void updateBusinessMid(ActBusinessDO actBusinessDO) {
        this.modelDao.updateActBusiness(actBusinessDO);
    }

    /**
     * 保存流程配置表
     * @param actBusinessDO
     * @return
     */
    @Override
    public Result saveActBusiness(ActBusinessDO actBusinessDO){
        if (StringUtils.isBlank(actBusinessDO.getMkey())){
            return Result.error("流程编码不能为空!");
        }
        if (StringUtils.isBlank(actBusinessDO.getName())){
            return Result.error("流程名称不能为空!");
        }
        if (actBusinessDO.getRoleIdList()==null || actBusinessDO.getRoleIdList().size()==0){
            return Result.error("发起人角色不能为空!");
        }
        StringBuffer buffer=new StringBuffer();
        for (Long roleId:actBusinessDO.getRoleIdList()){
            buffer.append(roleId).append(",");
        }
        actBusinessDO.setRoleId(buffer.substring(0,buffer.length()-1));
        actBusinessDO.setCreatedBy(SecurityUtils.getLoginUser().getUser().getNickName());
        modelDao.insertActBusiness(actBusinessDO);
        return Result.success();
    }

    /**
     * 获取规则信息
     * @param mid
     * @return
     */
    @Override
    public List<ActModelRuleDO> getModelRule(String mid) {
        return this.modelDao.getModelRule(mid);
    }

    /**
     * 保存规则信息
     * @param list
     * @return
     */
    @Transactional
    @Override
    public Result saveModelRule(List<ActModelRuleDO> list) {
        if (list==null || list.size()==0){
            return Result.error("规则信息不能为空!");
        }
        this.modelDao.deleteModelRule(list.get(0).getMid());
        for (ActModelRuleDO ruleDO:list){
            modelDao.saveModelRule(ruleDO);
        }
        return Result.success();
    }


    /**
     * 获取角色信息
     * @return
     */
    @Override
    public List<SysRole> getRole() {
        SysRole sysRole=new SysRole();
        return this.sysRoleMapper.selectRoleList(sysRole);
    }
}
