package com.metro.ccms.web.credit.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.credit.domain.PayTermDO;
import com.metro.ccms.web.credit.query.PayTermQuery;

import java.util.List;

/**
 * 付款条件
 *
 * @author guangle
 * @date 2020/12/16
 * @since 1.0.0
 */
public interface PayTermService {
    /**
     * 付款条件-查询
     *
     * @param payTermQuery 付款条件的查询条件
     * @return page
     */
    List<PayTermDO> list(PayTermQuery payTermQuery);

    /**
     * 付款条件-新增
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    Result save(PayTermDO payTermDO);

    /**
     * 付款条件-修改
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    Result update(PayTermDO payTermDO);

    /**
     * 付款条件-删除
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    Result delete(PayTermDO payTermDO);
}
