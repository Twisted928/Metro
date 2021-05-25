package com.metro.ccms.web.credit.mapper;


import com.metro.ccms.web.credit.domain.PayTermDO;
import com.metro.ccms.web.credit.query.PayTermQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayTermMapper {

    /**
     * 付款条件-查询
     *
     * @param payTermQuery 付款条件的查询条件
     * @return List<PayTermDO>
     */
    List<PayTermDO> list(PayTermQuery payTermQuery);

    /**
     * 付款条件-新增
     *
     * @param payTermDO 付款条件DO
     */
    void save(PayTermDO payTermDO);

    /**
     * 付款条件-修改
     *
     * @param payTermDO 付款条件DO
     */
    void update(PayTermDO payTermDO);

    /**
     * 付款条件-删除
     *
     * @param payTermDO 付款条件DO
     */
    void delete(PayTermDO payTermDO);

    /**
     * 根据系统代码,查询数据是否唯一
     *
     * @param payTermDO 付款条件DO
     * @return int
     */
    int getCode(PayTermDO payTermDO);

    /**
     * 根据付款条件天数和结算方式,查询数据是否唯一
     *
     * @param payTermDO 付款条件DO
     * @return int
     */
    int getUnique(PayTermDO payTermDO);
}