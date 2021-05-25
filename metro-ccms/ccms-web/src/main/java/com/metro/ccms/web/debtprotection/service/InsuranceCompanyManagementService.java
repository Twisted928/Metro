package com.metro.ccms.web.debtprotection.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.debtprotection.domian.InsureCompanyDO;
import com.metro.ccms.web.debtprotection.domian.InsurePolicyDO;
import com.metro.ccms.web.debtprotection.query.InsureCompanyQuery;
import com.metro.ccms.web.debtprotection.vo.InsuranceCombinationVO;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
import com.metro.ccms.web.debtprotection.vo.InsureScopeVO;

import java.util.List;

/**
 * 保险公司管理
 *
 * @author guangle
 * @create 2020/11/30
 * @since 1.0.0
 */
public interface InsuranceCompanyManagementService {
    /**
     * 保险公司管理-查询
     *
     * @param insureCompanyQuery 保险公司表查询类
     * @return insureCompanyVOPage
     */
    List<InsureCompanyDO> insuranceCompanyManagementList(InsureCompanyQuery insureCompanyQuery);

    /**
     * 保险公司管理-新增
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
    Result saveInsuranceCompanyManagement(InsuranceCombinationVO insuranceCombinationVO);

    /**
     * 保险公司管理-修改
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
    Result insuranceCompanyManagementUpdate(InsuranceCombinationVO insuranceCombinationVO);

    /**
     * 保险公司管理-删除
     *
     * @param insureCompany 保险公司表
     * @return Result
     */
    Result insuranceCompanyManagementDelete(InsureCompanyDO insureCompanyDO);

    /**
     * 保险公司管理-详细
     *
     * @param insuranceCombinationVO 组合VO
     * @return InsureCompanyVO
     */
    InsuranceCombinationVO insuranceCompanyManagementGetById(InsuranceCombinationVO insuranceCombinationVO);

    /**
     * 查看保单范围
     *
     * @param insureScopeVO 保单范围表VO
     * @return List<InsureScopeVO>
     */
    List<InsureScopeVO> insureScopeList(InsureScopeVO insureScopeVO);

    /**
     * 保单删除
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
    Result policyDelete(InsurePolicyVO insurePolicyVO);

    /**
     * 保单修改
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
    Result policyUpdate(InsurePolicyVO insurePolicyVO);

    /**
     * 保单新增
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
    Result policySave(InsurePolicyVO insurePolicyVO);

    /**
     * 保单新增时,判断时间
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
    Result timeJudgment(InsuranceCombinationVO insuranceCombinationVO);

    /**
     * 保险公司修改时,删除保单判断保单是否可以删除
     *
     * @param insurePolicyDO 保单表DO
     * @return Result
     */
    Result insurePolicyJudgment(InsurePolicyDO insurePolicyDO);
}
