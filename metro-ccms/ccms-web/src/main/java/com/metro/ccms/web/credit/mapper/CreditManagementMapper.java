package com.metro.ccms.web.credit.mapper;

import com.metro.ccms.web.credit.query.CarditManagementQuery;
import com.metro.ccms.web.credit.query.CreditManagementQuery;
import com.metro.ccms.web.credit.vo.CarditManagementVO;
import com.metro.ccms.web.credit.vo.CreditManagementVO;

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
public interface CreditManagementMapper {
    /**
     * 查询
     * @param creditManagementQuery
     * @return
     */
    List<CreditManagementVO> pagesel(CreditManagementQuery creditManagementQuery);
    /**
     * 客户评估详情
     * @param creditManagementQuery
     * @return
     */
    List<CreditManagementVO> pagedetails(CreditManagementQuery creditManagementQuery);
    /**
     * 客户评估历史
     * @param creditManagementQuery
     * @return
     */
    List<CreditManagementVO> history(CreditManagementQuery creditManagementQuery);
    /**
     * 卡号查询
     * @param carditManagementQuery
     * @return
     */
    List<CarditManagementVO> pagecard(CarditManagementQuery carditManagementQuery);


}