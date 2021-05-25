package com.metro.ccms.web.model.service.impl;


import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.model.domain.RuleModelInfoDO;
import com.metro.ccms.web.model.mapper.RuleModelInfoMapper;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;
import com.metro.ccms.web.model.query.RuleModelInfoQuery;
import com.metro.ccms.web.model.service.RuleModelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *<p>
 *规则引擎指标
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
@Service
public class RuleModelInfoServiceImpl implements RuleModelInfoService {

    @Autowired
    private RuleModelInfoMapper ruleModelInfoMapper;


    /**
     * 根据id获取指标信息
     * @param id
     * @return
     */
    @Override
    public RuleModelInfoDO get(Long id) {
        return ruleModelInfoMapper.get(id);
    }

    /**
     * 获取指标列表
     * @param query
     * @return
     */
    @Override
    public List<RuleModelInfoDO> list(RuleModelInfoQuery query) {
        return ruleModelInfoMapper.list(query);
    }

    /**
     * 保存指标信息
     * @param modelIndex
     * @return
     */
    @Override
    public Result save(RuleModelInfoDO modelIndex) {
        if (modelIndex == null) {
            return Result.error("引擎管理信息 不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getName())) {
            return Result.error("模型名称不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getDescription())) {
            return Result.error("模型描述不能为空！");
        }
        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        modelIndex.setCreatedBy(loginUser.getUser().getNickName());
        int rowNum = ruleModelInfoMapper.save(modelIndex);
        if (rowNum == 0) {
            return Result.error("保存失败");
        }
        return Result.success();
    }
    /**
     * 添加指标按钮查询指标
     * 根据模型编码查询中间表
     * @param modelIndex
     * @return
     */
    @Override
    public Result selindex(RuleModelIndexQuery modelIndex) {
        List<RuleModelIndexInfoDO> ruleModelIndexDO = ruleModelInfoMapper.selindex(modelIndex);
        return Result.success(ruleModelIndexDO);
    }
    /**
     * 添加指标
     * @param modelIndex
     * @return
     */
    @Override
    public Result addindex(RuleModelIndexInfoDO modelIndex) {

        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        modelIndex.setCreatedBy(loginUser.getUser().getNickName());
        int rowNum = ruleModelInfoMapper.addindex(modelIndex);
        if (rowNum == 0) {
            return Result.error("保存失败");
        }
        return Result.success();
    }

    /**
     * 更新指标信息
     * @param modelIndex
     * @return
     */
    @Override
    public Result update(RuleModelInfoDO modelIndex) {
        if (modelIndex == null) {
            return Result.error("模型信息不能为空！");
        }
        if(modelIndex.getId()==null){
            return Result.error("模型编码不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getName())) {
            return Result.error("模型名称不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getDescription())) {
            return Result.error("模型描述不能为空！");
        }

        modelIndex.setUpdateTime(new Date());
        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        modelIndex.setUpdatedBy(loginUser.getUser().getNickName());
        int rowNum = ruleModelInfoMapper.update(modelIndex);
        if (rowNum == 0) {
            return Result.error("保存失败");
        }
        return Result.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Result remove(Long id) {
        ruleModelInfoMapper.remove(id);
        return Result.success();
    }

    /**
     * 检查表达式是否合规
     *
     * @param expression
     * @return
     */
    public Result checkExperssion(String expression){
        if(StringUtils.isBlank(expression)){
            return Result.error("公式不能为空！");
        }
        /**
         * 常用公式例如：
         * [资产总额（百万）]/[负债总额（百万）]*100
         * ([资产总额（百万）]/[负债总额（百万）]+300)+[负债总额（百万）]*20
         */

        return Result.success();
    }

}
