package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.domian.InsureScopeDO;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
import com.metro.ccms.web.debtprotection.vo.InsureScopeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InsureScopeMapper {

    /**
     * 保险公司管理-保存
     *
     * @param insureScopeVO 数据对象
     * @return int
     */
    int save(InsureScopeVO insureScopeVO);

    /**
     * 保险公司管理-修改
     *
     * @param insureScopeVO 数据对象
     * @return int
     */
    int update(InsureScopeVO insureScopeVO);

    /**
     * 保险公司管理-删除
     *
     * @param id 主表主键
     * @return int
     */
    int delete(String id);

    /**
     * 根据保单ID删除数据
     *
     * @param id 保单ID
     */
    void deleteByPolicId(String id);

    /**
     * 查看保单范围
     *
     * @param insureScopeVO 范围表VO
     * @return List<InsureScopeVO>
     */
    List<InsureScopeVO> getByPolicId(InsureScopeVO insureScopeVO);

    /**
     * 查询保单表ID对应范围表数据
     *
     * @param id 保单表ID
     * @return InsureScopeDO
     */
    List<InsureScopeDO> getlist(Long id);

    /**
     * 修改状态
     *
     * @param insurePolicyVO 参数对象
     */
    void updateStatus(InsurePolicyVO insurePolicyVO);

    /**
     * 根据保单ID查询,获取保单范围
     *
     * @param id 保单ID
     * @return List<String>
     */
    List<String> getByPolicyID(@Param("id") List<String> id);
}