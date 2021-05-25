package com.metro.ccms.web.collection.mapper;

import com.metro.ccms.web.collection.vo.CollectionVO;
import com.metro.ccms.web.collection.domian.*;
import com.metro.ccms.web.collection.query.CollectionQuery;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @description
* @author weiwenhui
* @date 2020/12/11 17:55
*/
public interface CollectionMapper {
    /**
     * 查询
     * @param collectionQuery
     * @return CollectionVO
     */
    List<CollectionVO> selectCollection(CollectionQuery collectionQuery);
    /**
     * 催收单据详情查询-单据基本信息
     * @param id
     * @return CollectionVO
     */
    CollectionDO getCollection(Long id);
    /**
     * 催收单据详情查询-获取单据明细
     * @param applicationNo
     * @return CollectionDetailDO
     */
    List<CollectionDetailDO> getCollectionDetails(String applicationNo);
    /**
     * 催收单据详情查询-单据台账记录
     * @param applicationNo
     * @return CollectionRecordDO
     */
    List<CollectionRecordDO> getCollectionRecords(String applicationNo);
    /**
     * 催收单据维护功能-新增台账信息
     * @param collectionRecordDO
     * @return
     */
    void saveTzInfo(CollectionRecordDO collectionRecordDO);
    /**
     * 催收单据维护功能-修改台账信息
     * @param collectionRecordDO
     * @return
     */
    void updateTzInfo(CollectionRecordDO collectionRecordDO);
    /**
     * 催收单据维护功能-删除台账信息
     * @param applicationNo
     * @return
     */
    void delTzInfo(@Param("updatedBy") String updatedBy, @Param("applicationNo")String applicationNo);
    /**
     * 催收单据维护功能-新增或修改台账状态同时更新主表单据状态
     * @param applicationNo
     * @return
     */
    void updunningStatus(String applicationNo);
    /**
     * 催收单据维护功能-删除台账状态同时更新主表单据状态
     * @param applicationNo
     * @return
     */
    void upunningStatusForappNo(String applicationNo);
    /**
     * 催收单据维护功能-根据单号查条数
     * @param applicationNo
     * @return
     */
    int selCountRecord(String applicationNo);
    /**
     * 催收单据删除功能
     * @param collectionDO
     * @return
     */
    void deleteCollection(CollectionDO collectionDO);
    /**
     * 催收单据PDF预览根据id查询
     * @param id
     * @return
     */
    CollectionVO selPdfInfo(Long id);
    /**
     * 催收单据PDF预览查询日期
     * @param collectionVO
     * @return
     */
    List<Date> selDate(CollectionVO collectionVO);
    /**
     * 催收单据新增查询范围
     * @param bsegInterfaceDO
     * @return
     */
    List<BsegInterfaceDO> selRange(BsegInterfaceDO bsegInterfaceDO);
    /**
     * 催收单据新增查询逾期明细返回汇总金额
     * @param bsegInterfaceDO
     * @return
     */
    Double selIdueDay(@Param("bsegInterfaceDO") BsegInterfaceDO bsegInterfaceDO,@Param("min") String min,@Param("max") String max);
    Double sel721Idue(BsegInterfaceDO bsegInterfaceDO);
    Double selIdue(BsegInterfaceDO bsegInterfaceDO);
    Double selIar(BsegInterfaceDO bsegInterfaceDO);
    Double selUndue(BsegInterfaceDO bsegInterfaceDO);
    /**
     * 催收单据新增
     * @param collectionDO
     * @return
     */
    void saveDunning(CollectionDO collectionDO);
    /**
     *  查询最大ID
     * @param collectionDO
     * @return
     */
    String selmaxid(CollectionDO collectionDO);
    /**
     * 催收单据明细新增
     * @param collectionDetailDO
     * @return
     */
    void saveDunningDetail(CollectionDetailDO collectionDetailDO);
}
