package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.vo.InsuranceManagementVO;
import com.metro.ccms.web.debtprotection.domian.InsureDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InsureMapper {
    /**
     * 根据ID删除
     *
     * @param id 主键
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 投标管理-查询
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return List<InsuranceManagementVO>
     */
    List<InsuranceManagementVO> list(InsuranceManagementVO insuranceManagementVO);

    /**
     * 投标管理-投标
     *
     * @param insureDO 投保
     */
    void save(InsureDO insureDO);

    /**
     * 查看已投保保单
     *
     * @return List<InsureDO>
     */
    List<InsureDO> listInsuredPolicys(@Param("insureDO") InsureDO insureDO, @Param("id") List<Long> id);

    /**
     * 根据公司编码查询保险公司是否参与投保
     * @param compCode 公司编码
     * @return int
     */
    int getByCompCode(String compCode);

    /**
     * 根据选择的已投保保单ID查询信息
     *
     * @param insureDO 已投保保单
     * @return InsureDO
     */
    InsureDO getById(InsureDO insureDO);

    /**
     * 根据ID查询
     *
     * @param insureId 主键
     * @return InsureDO
     */
    InsureDO getByPrimaryKey(String insureId);
}