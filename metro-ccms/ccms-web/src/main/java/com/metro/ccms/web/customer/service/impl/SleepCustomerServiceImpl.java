package com.metro.ccms.web.customer.service.impl;

import com.metro.ccms.web.customer.vo.SleepCustomerVO;
import com.metro.ccms.web.customer.mapper.SleepCustomerMapper;
import com.metro.ccms.web.customer.query.SleepCustomerQuery;
import com.metro.ccms.web.customer.service.SleepCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class SleepCustomerServiceImpl implements SleepCustomerService {

    private static final Logger logger = LoggerFactory.getLogger(SleepCustomerServiceImpl.class);

    @Autowired
    private SleepCustomerMapper sleepCustomerMapper;

    /**
     * 查询
     *
     * @param sleepCustomerQuery
     * @return
     */
    @Override
    public List<SleepCustomerVO> pagesel(SleepCustomerQuery sleepCustomerQuery) {
        List<SleepCustomerVO> list=sleepCustomerMapper.pagesel(sleepCustomerQuery);
        return list;
    }
    /**
     * 查询历史
     *
     * @param sleepCustomerQuery
     * @return
     */
    @Override
    public List<SleepCustomerVO> pagesells(SleepCustomerQuery sleepCustomerQuery) {
        List<SleepCustomerVO> list=sleepCustomerMapper.pagesells(sleepCustomerQuery);
        return list;
    }


}
