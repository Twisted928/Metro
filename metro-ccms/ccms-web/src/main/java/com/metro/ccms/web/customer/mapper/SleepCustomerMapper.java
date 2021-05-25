package com.metro.ccms.web.customer.mapper;

import com.metro.ccms.web.customer.vo.SleepCustomerVO;
import com.metro.ccms.web.customer.query.SleepCustomerQuery;

import java.util.List;

/**
 *<p>
 *  睡眠客户管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
public interface SleepCustomerMapper {
    /**
     * 查询
     * @param sleepCustomerQuery
     * @return
     */
    List<SleepCustomerVO> pagesel(SleepCustomerQuery sleepCustomerQuery);
    /**
     * 查询历史
     * @param sleepCustomerQuery
     * @return
     */
    List<SleepCustomerVO> pagesells(SleepCustomerQuery sleepCustomerQuery);


}