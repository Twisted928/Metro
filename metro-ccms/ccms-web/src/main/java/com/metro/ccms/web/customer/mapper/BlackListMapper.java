package com.metro.ccms.web.customer.mapper;

import com.metro.ccms.web.customer.vo.BlackListVO;
import com.metro.ccms.web.customer.query.BlackListQuery;

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
public interface BlackListMapper {
    /**
     * 查询
     * @param blackListQuery
     * @return
     */
    List<BlackListVO> pagesel(BlackListQuery blackListQuery);
    /**
     * 查询历史
     * @param blackListQuery
     * @return
     */
    List<BlackListVO> pagesells(BlackListQuery blackListQuery);


}