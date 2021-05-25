package com.metro.ccms.web.activiti.mapper;

import com.metro.ccms.web.activiti.domain.ActBusinessDO;
import com.metro.ccms.web.activiti.domain.ActModelRuleDO;
import com.metro.ccms.web.activiti.query.ModelQuery;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ActModelMapper {

    /**
     * 获取规则信息
     * @param mid
     * @return
     */
    List<ActModelRuleDO> getModelRule(@Param("mid") String mid);

    /**
     * 保存规则信息
     * @param actModelRuleDO
     * @return
     */
    int saveModelRule(ActModelRuleDO actModelRuleDO);

    /**
     * 根据modelId删除规则信息
     * @param mid
     * @return
     */
    int deleteModelRule(@Param("mid") String mid);

    /**
     * 获取流程场景列表
     * @return
     */
    List<ActBusinessDO> selectList();

    /**
     * 更新流程配置表
     * @param actBusinessDO
     * @return
     */
    int updateActBusiness(ActBusinessDO actBusinessDO);

    /**
     * 根据场景key获取流程场景信息
     * @param mkey
     * @return
     */
    ActBusinessDO getBusinessByKey(@Param("mkey")String mkey);

    /**
     * 新增场景信息
     * @param actBusinessDO
     */
    void insertActBusiness(ActBusinessDO actBusinessDO);

    /**
     * 查询场景列表
     * @param modelQuery
     * @return
     */
    List<ActBusinessDO> getBusinessList(ModelQuery modelQuery);

    /**
     * 根据角色id获取角色名称
     * @param strs
     * @return
     */
    List<String> getRoleNameById(@Param("strs")String[] strs);

}
