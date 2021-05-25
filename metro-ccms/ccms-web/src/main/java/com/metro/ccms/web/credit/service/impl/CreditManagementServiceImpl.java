package com.metro.ccms.web.credit.service.impl;

import com.metro.ccms.web.credit.mapper.CreditManagementMapper;
import com.metro.ccms.web.credit.query.CarditManagementQuery;
import com.metro.ccms.web.credit.query.CreditManagementQuery;
import com.metro.ccms.web.credit.service.CreditManagementService;
import com.metro.ccms.web.credit.vo.CarditManagementVO;
import com.metro.ccms.web.credit.vo.CreditManagementVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *  授信管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/9
 * @desc
 **/
@Service
public class CreditManagementServiceImpl implements CreditManagementService {

    private static final Logger logger = LoggerFactory.getLogger(CreditManagementServiceImpl.class);

    @Autowired
    private CreditManagementMapper creditManagementMapper;

    /**
     * 查询
     *
     * @param creditManagementQuery
     * @return
     */
    @Override
    public List<CreditManagementVO> pagesel(CreditManagementQuery creditManagementQuery) {
        List<CreditManagementVO> list=creditManagementMapper.pagesel(creditManagementQuery);
        return list;
    }
    /**
     * 客户评估详情
     *
     * @param creditManagementQuery
     * @return
     */
    @Override
    public List<CreditManagementVO> pagedetails(CreditManagementQuery creditManagementQuery) {
        List<CreditManagementVO> list=creditManagementMapper.pagedetails(creditManagementQuery);
        return list;
    }
    /**
     * 客户评估历史记录
     *
     * @param creditManagementQuery
     * @return
     */
    @Override
    public List<CreditManagementVO> history(CreditManagementQuery creditManagementQuery) {
        List<CreditManagementVO> list=creditManagementMapper.history(creditManagementQuery);
        return list;
    }
    /**
     * 卡号查询
     *
     * @param carditManagementQuery
     * @return
     */
    @Override
    public List<CarditManagementVO> pagecard(CarditManagementQuery carditManagementQuery) {
        List<CarditManagementVO> list=creditManagementMapper.pagecard(carditManagementQuery);
        return list;
    }


}
