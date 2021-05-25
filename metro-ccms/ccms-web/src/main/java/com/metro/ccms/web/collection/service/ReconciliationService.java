package com.metro.ccms.web.collection.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.collection.domian.ReconciliationDO;
import com.metro.ccms.web.collection.query.ReconciliationQuery;
import com.metro.ccms.web.collection.vo.ReconAndDetailVO;
import com.metro.ccms.web.collection.vo.ReconciliationVO;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;

import java.util.List;

/**
* @description 对账管理
* @author weiwenhui
* @date 2020/12/14 13:19
*/
public interface ReconciliationService {
    /**
     * 对账管理-列表查询
     * @param reconciliationQuery
     * @return
     */
    List<ReconciliationVO> selectReconciliation(ReconciliationQuery reconciliationQuery);
    /**
     * 对账管理-下载PDF时更新对账状态
     * @param reconciliationDO
     * @return
     */
    Result updateStaus(ReconciliationDO reconciliationDO);
    /**
     * 对账单PDF预览根据id查询
     * @param reconciliationDO
     * @return
     */
    ReconciliationVO selPdfInfo(ReconciliationDO reconciliationDO);
    /**
     * 对账单新增-查询汇总金额
     * @param bsegInterfaceDO
     * @return
     */
    ReconciliationDO selMoney(BsegInterfaceDO bsegInterfaceDO);
    /**
     * 对账单新增-查询范围
     * @param bsegInterfaceDO
     * @return
     */
    List<BsegInterfaceDO> selRange(BsegInterfaceDO bsegInterfaceDO);
    /**
     * 对账单新增功能
     * @param bsegInterfaceDO
     * @return
     */
    Result insertRecon(BsegInterfaceDO bsegInterfaceDO);
    /**
     * 对账单详情查询
     * @param reconciliationDO
     * @return
     */
    ReconAndDetailVO selectReconDetail(ReconciliationDO reconciliationDO);
    /**
     * 对账单修改维护台账功能
     * @param reconAndDetailVO
     * @return
     */
    Result updateReconRecord(ReconAndDetailVO reconAndDetailVO);
}
