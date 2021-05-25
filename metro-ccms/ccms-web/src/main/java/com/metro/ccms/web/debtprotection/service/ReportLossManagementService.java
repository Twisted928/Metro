package com.metro.ccms.web.debtprotection.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.debtprotection.domian.ClaimDO;
import com.metro.ccms.web.debtprotection.domian.ClaimProgressDO;
import com.metro.ccms.web.debtprotection.domian.InsureDO;
import com.metro.ccms.web.debtprotection.query.ClaimQuery;
import com.metro.ccms.web.debtprotection.vo.LossPortfolioVO;

import java.util.List;

/**
 * 报损管理
 *
 * @author guangle
 * @date 2020/12/10
 * @since 1.0.0
 */
public interface ReportLossManagementService {
    /**
     * 报损管理-查询
     *
     * @param claimQuery 报损管理查询类
     * @return List<ClaimDO>
     */
    List<ClaimDO> list(ClaimQuery claimQuery);

    /**
     * 报损管理-新增
     *
     * @param lossPortfolioVO 报损管理组合VO
     * @return Result
     */
    Result save(LossPortfolioVO lossPortfolioVO);

    /**
     * 报损管理-台账维护
     *
     * @param lossPortfolioVO 报损管理组合VO
     * @return Result
     */
    Result update(LossPortfolioVO lossPortfolioVO);

    /**
     * 报损管理-删除
     *
     * @param claimProgressDO 报损进度表
     * @return Result
     */
    Result delete(ClaimProgressDO claimProgressDO);

    /**
     * 报损管理-详细
     *
     * @param claimDO 报损表
     * @return ClaimDO
     */
    ClaimDO getOne(ClaimDO claimDO);

    /**
     * 报损管理-台账维护-台账删除
     *
     * @param claimProgressDO 报损进度表DO
     * @return Result
     */
    Result ledgerDelete(ClaimProgressDO claimProgressDO);

    /**
     * 查看已投保保单
     * @param insureDO 已投保保单表
     * @return List<InsureDO>
     */
    List<InsureDO> listInsuredPolicys(InsureDO insureDO);

    /**
     * 根据选择的已投保保单ID查询信息
     *
     * @param insureDO 已投保保单
     * @return InsureDO
     */
    InsureDO getInsure(InsureDO insureDO);
}
