package com.metro.ccms.web.collection.mapper;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.collection.domian.ReconDetailsDO;
import com.metro.ccms.web.collection.domian.ReconRecordDO;
import com.metro.ccms.web.collection.domian.ReconciliationDO;
import com.metro.ccms.web.collection.query.ReconciliationQuery;
import com.metro.ccms.web.collection.vo.ReconciliationVO;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description
* @author weiwenhui
* @date 2020/12/14 13:30
*/
public interface ReconciliationMapper {
    /**
     * 对账列表查询
     * @param reconciliationQuery
     * @return ReconciliationVO
     */
    List<ReconciliationVO> selectReconciliation(ReconciliationQuery reconciliationQuery);
    /**
     * 对账管理-下载PDF时更新对账状态
     * @param reconciliationDO
     * @return ReconciliationVO
     */
    void updateStaus(ReconciliationDO reconciliationDO);
    /**
     * 对账单PDF预览根据id查询
     * @param reconciliationDO
     * @return ReconciliationVO
     */
    ReconciliationVO selPdfInfo(ReconciliationDO reconciliationDO);
    /**
     * 对账单新增查询范围
     * @param bsegInterfaceDO
     * @return
     */
    List<BsegInterfaceDO> selRange(BsegInterfaceDO bsegInterfaceDO);
    /**
     * 对账单新增查询明细返回金额
     * @param bsegInterfaceDO
     * @return
     */
    Double selIdue(BsegInterfaceDO bsegInterfaceDO);
    Double selIar(BsegInterfaceDO bsegInterfaceDO);
    /**
     * 查询最大id
     * @param reconciliationDO
     * @return
     */
    String selmaxid(ReconciliationDO reconciliationDO);
    /**
     * 对账单保存
     * @param reconciliationDO
     * @return
     */
    void saveRecon(ReconciliationDO reconciliationDO);
    /**
     * 对账单明细保存
     * @param reconDetailsDO
     * @return
     */
    void saveReconDetails(ReconDetailsDO reconDetailsDO);
    /**
     * 对账单详情查询-基本信息
     * @param id
     * @return CollectionVO
     */
    ReconciliationVO getRecon(Long id);
    /**
     * 对账单详情查询-获取对账明细
     * @param applicationNo
     * @return CollectionDetailDO
     */
    List<ReconDetailsDO> getReconDetails(String applicationNo);
    /**
     * 对账单详情查询-对账台账记录  updateTzInfo
     * @param applicationNo
     * @return CollectionRecordDO
     */
    List<ReconRecordDO> getReconRecord(String applicationNo);
    /**
     * 对账单修改台账
     * @param reconRecordDO
     * @return CollectionRecordDO
     */
    int updateTzInfo(ReconRecordDO reconRecordDO);
    /**
     * 对账单新增台账
     * @param reconRecordDO
     * @return CollectionRecordDO
     */
    int saveTzInfo(ReconRecordDO reconRecordDO);
    /**
     * 对账单删除台账
     * @param updatedBy
     * @param id
     * @return CollectionRecordDO
     */
    void delTzInfo(@Param("updatedBy") String updatedBy, @Param("id") String id);
}
