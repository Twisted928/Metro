package com.metro.ccms.web.customer.service.impl;

import com.metro.ccms.web.customer.vo.BlackListVO;
import com.metro.ccms.web.customer.mapper.BlackListMapper;
import com.metro.ccms.web.customer.query.BlackListQuery;
import com.metro.ccms.web.customer.service.BlackListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class BlackListServiceImpl implements BlackListService {

    private static final Logger logger = LoggerFactory.getLogger(BlackListServiceImpl.class);

    @Autowired
    private BlackListMapper blackListMapper;

    /**
     * 查询
     *
     * @param blackListQuery
     * @return
     */
    @Override
    public List<BlackListVO> pagesel(BlackListQuery blackListQuery) {
        List<BlackListVO> list=blackListMapper.pagesel(blackListQuery);
        return list;
    }
    /**
     * 查询历史
     *
     * @param blackListQuery
     * @return
     */
    @Override
    public List<BlackListVO> pagesells(BlackListQuery blackListQuery) {
        List<BlackListVO> list=blackListMapper.pagesells(blackListQuery);
        return list;
    }


}
