package com.metro.ccms.web.debtprotection.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.debtprotection.vo.InsuranceManagementVO;

import java.util.List;

/**
 * 投标管理
 *
 * @author guangle
 * @date 2020/12/8
 * @since 1.0.0
 */
public interface InsuranceManagementService {
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
     * @param insuranceManagementVO 投保管理合集VO
     * @return Result
     */
    Result save(InsuranceManagementVO insuranceManagementVO);

    /**
     * 投标管理-删除
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return Result
     */
    Result delete(InsuranceManagementVO insuranceManagementVO);
}
