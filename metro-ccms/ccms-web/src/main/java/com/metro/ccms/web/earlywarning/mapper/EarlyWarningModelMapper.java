package com.metro.ccms.web.earlywarning.mapper;

import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelConfigDO;
import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelDO;
import com.metro.ccms.web.earlywarning.query.EarlyWarningModelQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 *<p>
 *  预警模型
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/8
 * @desc
 **/
public interface EarlyWarningModelMapper {
    /**
     * 查询
     * @param earlywarningmodelQuery
     * @return
     */
    List<EarlyWarningModelDO> pagesel(EarlyWarningModelQuery earlywarningmodelQuery);
    /**
     * 根据模型编码查询信息
     * @param mocdelCode
     * @return
     */
    EarlyWarningModelDO getEarlyModelByCode(@Param("mocdelCode")String mocdelCode);

    /**
     * 根据编码获取模型配置
     * @param mocdelCode
     * @return
     */
    List<EarlyWarningModelConfigDO> getEarlyModelConfigByCode(@Param("mocdelCode")String mocdelCode);

    /**
     * 校验
     * @param mocdelCode
     * @param warnItem
     * @param upper
     * @param lower
     * @param roles
     * @return
     */
    int selectCount(@Param("mocdelCode")String mocdelCode, @Param("warnItem")String warnItem,@Param("upper")Integer upper,@Param("lower")Integer lower,@Param("roles")String roles);

    /**
     * 保存
     * @param earlyWarningModelConfigDO
     */
    void saveEwModelConfig(EarlyWarningModelConfigDO earlyWarningModelConfigDO);

    /**
     * 修改
     * @param earlyWarningModelConfigDO
     */
    void updateEwModelConfig(EarlyWarningModelConfigDO earlyWarningModelConfigDO);

    /**
     * 根据id批量更新配置表状态
     * @param ids
     */
    void updateConfigByIds(@Param("ids")List<Long> ids);

    /**
     * 根据id获取预警模型信息
     * @param id
     * @return
     */
    EarlyWarningModelDO getEwModelById(@Param("id") Long id);

    /**
     * 根据id修改预警模型状态
     * @param earlyWarningModelDO
     */
    void updateEwModel(EarlyWarningModelDO earlyWarningModelDO);

    /**
     * 保存预警启用、禁用记录表
     * @param earlyWarningModelDO
     */
    void saveEwModelRecord(EarlyWarningModelDO earlyWarningModelDO);

    /**
     * 根据预警模型编码获取启用、停用记录
     * @param mocdelCode
     * @return
     */
    List<EarlyWarningModelDO> getEwModelRecord(@Param("mocdelCode") String mocdelCode);

    /**
     * 更新预警模型状态
     * @param mocdelCode
     */
    void updateEwByCode(@Param("mocdelCode") String mocdelCode);

}