package com.metro.ccms.web.debtprotection.mapper;

import com.metro.ccms.web.debtprotection.domian.InsureCompanyDO;
import com.metro.ccms.web.debtprotection.query.InsureCompanyQuery;
import com.metro.ccms.web.debtprotection.vo.InsuranceCombinationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsureCompanyInsureCompanyMapper {

    /**
     * 保险公司管理-查询
     *
     * @param insureCompanyQuery 保险公司表查询类
     * @return insureCompanyVOPage
     */
    List<InsureCompanyDO> listInsuranceCompanyManagements(InsureCompanyQuery insureCompanyQuery);

    /**
     * 保险公司管理-保存
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return int
     */
    int saveInsuranceManagement(InsuranceCombinationVO insuranceCombinationVO);

    /**
     * 保险公司管理-修改
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return int
     */
    int update(InsuranceCombinationVO insuranceCombinationVO);

    /**
     * 保险公司管理-删除
     *
     * @param insureCompanyDO 保险公司表
     * @return int
     */
    int delete(InsureCompanyDO insureCompanyDO);

    /**
     * 判断公司编码是否重复
     *
     * @param compCode 公司编码
     * @return int
     */
    int getCompanyCode(String compCode);

    InsuranceCombinationVO selectByPrimaryKey(Long id);

    /**
     * 判断公司名称是否重复
     * @param companyName 公司名称
     * @return int
     * 0 不重复
     * 1 重复
     */
    int getCompanyName(String companyName);
}