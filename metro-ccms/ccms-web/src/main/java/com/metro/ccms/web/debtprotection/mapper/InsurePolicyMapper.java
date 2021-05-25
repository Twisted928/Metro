package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InsurePolicyMapper {

    /**
     * 获取最新的ID
     *
     * @return id
     */
    String getId();

    /**
     * 保险公司管理-保存
     *
     * @param insurePolicyVO 展示对象
     * @return int
     */
    int save(InsurePolicyVO insurePolicyVO);

    /**
     * 保险公司管理-修改
     *
     * @param insurePolicyVO 展示对象
     * @return int
     */
    int update(InsurePolicyVO insurePolicyVO);

    /**
     * 保险公司管理-删除
     *
     * @param compCode 门店编码
     * @return int
     */
    int delete(String compCode);

    /**
     * 保险公司管理-详细
     *
     * @param compCode 公司编码
     * @return List<InsurePolicyVO>
     */
    List<InsurePolicyVO> getByCompCode(String compCode);

    /**
     * 保单删除
     *
     * @param id 主键
     */
    void policyDelete(Long id);

    /**
     * 修改状态
     *
     * @param insurePolicyVO 参数对象
     */
    void updateStatus(InsurePolicyVO insurePolicyVO);

    /**
     * 根据公司编码查询对应有效保单ID
     *
     * @param companyCode 公司编码
     * @return List<String>
     */
    List<String> getByCompanyCode(String companyCode);

    /**
     * 判断相同公司下,保单号是否重复
     *
     * @param compCode 公司编码
     * @param policyno    保单号
     * @return int
     */
    int getPolicyNumber(@Param("compCode") String compCode, @Param("policyno")String policyno);

    /**
     * 根据ID删除数据
     * @param id 主键
     */
    void deleteById(String id);

    /**
     * 查询保单
     *
     * @return List<InsurePolicyVO>
     */
    List<InsurePolicyVO> listPolicy(InsurePolicyVO insurePolicyVO);

    /**
     * 判断保单号是否存在
     *
     * @param policyno 保单号
     * @return int
     */
    int getPolicy(String policyno);
}