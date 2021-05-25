package com.metro.ccms.web.earlywarning.service.impl;

import com.metro.ccms.web.earlywarning.mapper.WarningEmailMapper;
import com.metro.ccms.web.earlywarning.query.WarningEmailQuery;
import com.metro.ccms.web.earlywarning.service.WarningEmailService;
import com.metro.ccms.web.earlywarning.vo.WarningEmailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *  预警邮件
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/8
 * @desc
 **/
@Service
public class WarningEmailServiceImpl implements WarningEmailService {

    private static final Logger logger = LoggerFactory.getLogger(WarningEmailServiceImpl.class);

    @Autowired
    private WarningEmailMapper warningemailMapper;

    /**
     * 查询
     *
     * @param warningemailQuery
     * @return
     */
    @Override
    public List<WarningEmailVO> pagesel(WarningEmailQuery warningemailQuery) {
        List<WarningEmailVO> list=warningemailMapper.pagesel(warningemailQuery);
        return list;
    }

}
