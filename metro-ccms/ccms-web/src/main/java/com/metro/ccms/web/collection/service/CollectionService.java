package com.metro.ccms.web.collection.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.collection.vo.CollectionAndDetailVO;
import com.metro.ccms.web.collection.vo.CollectionVO;
import com.metro.ccms.web.collection.domian.CollectionDO;
import com.metro.ccms.web.collection.query.CollectionQuery;
import com.metro.ccms.web.collection.vo.CollectionletterVO;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;

import java.util.List;

/**
* @description
* @author weiwenhui
* @date 2020/12/11 17:56
*/
public interface CollectionService {
    /**
     * 催收管理-查询
     * @param collectionQuery
     * @return
     */
    List<CollectionVO> selectCollection(CollectionQuery collectionQuery);
    /**
     * 催收单据详情查询
     * @param collectionDO
     * @return
     */
    CollectionAndDetailVO selectCollectionDetail(CollectionDO collectionDO);
    /**
     * 催收单维护功能
     * @param collectionAndDetailVO
     * @return
     */
    Result saveCollection(CollectionAndDetailVO collectionAndDetailVO);
    /**
     * 催收单据删除功能
     * @param collectionDO
     * @return
     */
    Result deleteCollection(CollectionDO collectionDO);
    /**
     * 催收单据PDF预览根据id查询
     * @param collectionVO
     * @return
     */
    CollectionletterVO selPdfInfo(CollectionVO collectionVO);
    /**
     * 催收单据新增功能
     * @param bsegInterfaceDO
     * @return
     */
    Result insertCollection(BsegInterfaceDO bsegInterfaceDO);
    /**
     * 催收单据新增查询接口
     * @param bsegInterfaceDO
     * @return
     */
    List<BsegInterfaceDO> selRange(BsegInterfaceDO bsegInterfaceDO);
}
