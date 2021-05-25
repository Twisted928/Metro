package com.metro.ccms.web.customer.service;

import com.metro.ccms.web.customer.query.BlackListQuery;
import com.metro.ccms.web.customer.vo.BlackListVO;

import java.util.List;

/**
 *<p>
 *  黑名单管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
public interface BlackListService {
    /**
     * 查询
     * @param blackListQuery
     * @return
     */
    List<BlackListVO> pagesel(BlackListQuery blackListQuery);
    /**
     * 查询历史记录
     * @param blackListQuery
     * @return
     */
    List<BlackListVO> pagesells(BlackListQuery blackListQuery);


}
