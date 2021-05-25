package com.metro.ccms.web.earlywarning.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelConfigDO;
import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelDO;
import com.metro.ccms.web.earlywarning.mapper.EarlyWarningModelMapper;
import com.metro.ccms.web.earlywarning.query.EarlyWarningModelQuery;
import com.metro.ccms.web.earlywarning.service.EarlyWarningModelService;
import com.metro.ccms.web.earlywarning.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *<p>
 *  预警模型
 * </p>
 * 增加新增的时候下拉框，查询指标大类，根据指标大类查询预警指标
 * @author  JIXIANG.SONG
 * @create  2020/12/8
 * @desc
 **/
@Service
public class EarlyWarningModelServiceImpl implements EarlyWarningModelService {

    private static final Logger logger = LoggerFactory.getLogger(EarlyWarningModelServiceImpl.class);

    @Autowired
    private EarlyWarningModelMapper earlywarningmodelMapper;

    /**
     * 查询
     *
     * @param earlywarningmodelQuery
     * @return
     */
    @Override
    public List<EarlyWarningModelDO> pagesel(EarlyWarningModelQuery earlywarningmodelQuery) {
        List<EarlyWarningModelDO> list=earlywarningmodelMapper.pagesel(earlywarningmodelQuery);
        return list;
    }

    /**
     * 查询详情
     * @param mocdelCode
     * @return
     */
    @Override
    public EarlyWarningModelVO detailed(String mocdelCode) {
        EarlyWarningModelVO modelVO=new EarlyWarningModelVO();
        modelVO.setModelDO(earlywarningmodelMapper.getEarlyModelByCode(mocdelCode));
        List<EarlyWarningModelConfigDO> configDOList=earlywarningmodelMapper.getEarlyModelConfigByCode(mocdelCode);
        if (configDOList!=null && configDOList.size()>0){
            for (EarlyWarningModelConfigDO configDO:configDOList){
                String[] roles=configDO.getRoles().split(",");
                configDO.setRoleIds(Arrays.asList(roles));
            }
            modelVO.setConfigDO(configDOList);
        }
        return modelVO;
    }

    /**
     * 保存预警模型配置信息
     * @param earlyWarningModelVO
     * @return
     */
    @Override
    public Result saveEarlyModelConfig(EarlyWarningModelVO earlyWarningModelVO) {
        if (earlyWarningModelVO.getConfigDO()==null){
            return Result.error("请至少添加一条配置信息后提交!");
        }
        for (EarlyWarningModelConfigDO configDO:earlyWarningModelVO.getConfigDO()){
            if (configDO.getRoleIds()==null || configDO.getRoleIds().size()==0){
                return Result.error("通知角色不能为空!");
            }else{
                StringBuffer roles=new StringBuffer();
                for (String role:configDO.getRoleIds()){
                    roles.append(role);
                }
                configDO.setRoles(roles.substring(0,roles.length()-1));
            }
            if (configDO.getUpper()>=configDO.getLower()){
                return Result.error("上限不能大于等于下限!");
            }
//            int count=earlywarningmodelMapper.selectCount(configDO.getMocdelCode(),configDO.getWarnItem(),configDO.getUpper(),configDO.getLower(),configDO.getRoles());
//            if (count>0){
//                return Result.error("请勿重复维护预警项!");
//            }
        }
        for (EarlyWarningModelConfigDO configDO:earlyWarningModelVO.getConfigDO()){
            StringBuffer roles=new StringBuffer();
            for (String role:configDO.getRoleIds()){
                roles.append(role).append(",");
            }
            configDO.setRoles(roles.substring(0,roles.length()-1));
            if (configDO.getId()!=null){
                configDO.setUpdatedBy(SecurityUtils.getLoginUser().getUser().getNickName());
                configDO.setUpdateTime(new Date());
                earlywarningmodelMapper.updateEwModelConfig(configDO);
            }else{
                configDO.setCreatedBy(SecurityUtils.getLoginUser().getUser().getNickName());
                earlywarningmodelMapper.saveEwModelConfig(configDO);
            }
        }
        if (earlyWarningModelVO.getConfigIds()!=null && earlyWarningModelVO.getConfigIds().size()>0){
            earlywarningmodelMapper.updateConfigByIds(earlyWarningModelVO.getConfigIds());
            int count=earlywarningmodelMapper.selectCount(earlyWarningModelVO.getConfigDO().get(0).getMocdelCode(),null,null,null,null);
            if (count==0){
                earlywarningmodelMapper.updateEwByCode(earlyWarningModelVO.getConfigDO().get(0).getMocdelCode());
            }
        }
        return Result.success();
    }

    /**
     * 启用禁用
     * @param id
     * @param status
     * @return
     */
    @Override
    public Result enableOrDisable(Long id,Integer status) {
        EarlyWarningModelDO modelDO=earlywarningmodelMapper.getEwModelById(id);
        if (status==1 && earlywarningmodelMapper.selectCount(modelDO.getMocdelCode(),null,null,null,null)==0){
            return Result.error("请先配置预警模型信息后再启用!");
        }
        modelDO.setStatus(status);
        modelDO.setUpdatedBy(SecurityUtils.getLoginUser().getUser().getNickName());
        modelDO.setUpdateTime(new Date());
        modelDO.setCreatedBy(SecurityUtils.getLoginUser().getUser().getNickName());
        earlywarningmodelMapper.updateEwModel(modelDO);
        earlywarningmodelMapper.saveEwModelRecord(modelDO);
        return Result.success();
    }

    /**
     * 获取启用、停用记录
     * @param mocdelCode
     * @return
     */
    @Override
    public List<EarlyWarningModelDO> getEwModelRecord(String mocdelCode) {
        return earlywarningmodelMapper.getEwModelRecord(mocdelCode);
    }


}
