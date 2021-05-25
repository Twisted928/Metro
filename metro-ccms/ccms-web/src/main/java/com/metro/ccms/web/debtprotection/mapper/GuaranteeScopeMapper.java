package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.domian.GuaranteeScopeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GuaranteeScopeMapper {

    /**
     * 担保函管理-新增
     *
     * @param guaranteeScopeDO 范围表DO
     */
    void save(GuaranteeScopeDO guaranteeScopeDO);

    /**
     * 物理删除对应父表ID数据
     *
     * @param id 父表ID
     */
    void deleteByPrimaryKey(Long id);

    /**
     * 担保函管理-修改
     *
     * @param guaranteeScopeDO 范围表DO
     */
    void update(GuaranteeScopeDO guaranteeScopeDO);

    /**
     * 担保函管理-删除
     *
     * @param id 父表ID
     */
    void delete(String id);

    /**
     * 担保函管理-详情
     *
     * @param id 父表ID
     * @return List<GuaranteeScopeDO>
     */
    List<GuaranteeScopeDO> selectByMianID(Long id);

    /**
     * 根据ID删除
     *
     * @param id 主键
     */
    void deleteByID(Long id);

    /**
     * 根据ID修改主表ID
     *
     * @param guaranteeScopeDO 范围表
     */
    void updateByID(GuaranteeScopeDO guaranteeScopeDO);

    /**
     * 判断卡号是否重复
     *
     * @param cardCode  卡号
     * @param storeCode 门店
     * @param mainId    主表ID
     * @return int
     */
    int getCard(@Param("cardCode") String cardCode, @Param("storeCode") String storeCode, @Param("mainId") String mainId);

    /**
     * 根据主键物理删除卡号信息
     *
     * @param id 主键
     */
    void deleteId(String id);
}