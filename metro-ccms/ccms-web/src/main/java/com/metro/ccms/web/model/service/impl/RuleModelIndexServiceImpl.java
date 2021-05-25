package com.metro.ccms.web.model.service.impl;


import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.web.customer.domian.WhitelistmanagementDO;
import com.metro.ccms.web.customer.mapper.WhitelistmanagementMapper;
import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.mapper.RuleModelIndexMapper;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;
import com.metro.ccms.web.model.service.RuleModelIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.WeakHashMap;

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
public class RuleModelIndexServiceImpl implements RuleModelIndexService {

    @Autowired
    private RuleModelIndexMapper ruleModelIndexMapper;
    @Autowired
    private WhitelistmanagementMapper whitelistmanagementMapper;

    /**
     * 获取指标列表
     * @param query
     * @return
     */
    @Override
    public List<RuleModelIndexDO> list(RuleModelIndexQuery query) {
        return ruleModelIndexMapper.list(query);
    }

    /**
     * 保存指标信息
     * @param modelIndex
     * @return
     */
    @Override
    public Result save(RuleModelIndexDO modelIndex) {
        if (modelIndex == null) {
            return Result.error("指标信息不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getName())) {
            return Result.error("指标名称不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getType())) {
            return Result.error("指标类型不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getDescription())) {
            return Result.error("指标描述不能为空！");
        }
        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        modelIndex.setCreatedBy(loginUser.getUser().getNickName());
        int rowNum = ruleModelIndexMapper.save(modelIndex);
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
    public Result update(RuleModelIndexDO modelIndex) {
        if (modelIndex == null) {
            return Result.error("指标信息不能为空！");
        }
        if(modelIndex.getId()==null){
            return Result.error("指标编码不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getName())) {
            return Result.error("指标名称不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getType())) {
            return Result.error("指标类型不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getDescription())) {
            return Result.error("指标描述不能为空！");
        }

        modelIndex.setUpdateTime(new Date());
        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        modelIndex.setUpdatedBy(loginUser.getUser().getNickName());
        int rowNum = ruleModelIndexMapper.update(modelIndex);
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
        int a=ruleModelIndexMapper.selindex(id);
        if(a>0){
            return Result.error("指标已使用无法删除！");
        }
        ruleModelIndexMapper.remove(id);
        return Result.success();
    }

    /**
     * @Param 信用准入校验
     * @description
     * @date 2020/12/24
     * @return
     * @throws
     */
    @Override
    public Result creditaccess(String custCode, String cardCode, String storeCode) {
        //1.校验是否白名单，如果是提示可以直接进入评估模型
        //2.否则，判断 是否黑名单，如果是 提示 黑名单拒绝进入模型评估
        //3.否则，判断 即时逾期大于10天，如果是 提示 即时逾期大于10天拒绝进入模型评估
        //4.否则，判断 是否锁定 ，如果是 提示 锁定用户拒绝进入模型评估
        //5.否则，判断 平均预期/还款天数>70，如果是 提示 平均预期/还款天数>70拒绝进入模型评估
        //6.否则，提示可以直接进入评估模型
        WhitelistmanagementDO whiteDOQuery=new WhitelistmanagementDO();
        whiteDOQuery.setCustcode(custCode);
        if (whitelistmanagementMapper.selwhitecount(whiteDOQuery)>0){
            return Result.success();
        }
        if (ruleModelIndexMapper.getCountForBlackByCustCode(custCode)>0){
            return Result.error("此客户是黑名单客户!");
        }

        return null;
    }
    /**
     * @Param 保险准入校验
     * @description
     * @date 2020/12/24
     * @return
     * @throws
     */
    @Override
    public Result insuranceaccess(String custCode, String cardCode, String storeCode) {
        //1.校验保险准入要素为是否为NKA客户 或者 授信申请额度是否大于100万
        //2.获取当前的卡号的申请额度和同一客户下其他卡号的发布额度
        return null;
    }
}
