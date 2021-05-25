package com.metro.ccms.web.earlywarning.service;

import com.metro.ccms.web.earlywarning.query.WarningEmailQuery;
import com.metro.ccms.web.earlywarning.vo.WarningEmailVO;

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
public interface WarningEmailService {
    /**
     * 预警邮件-查询
     * @param warningemailQuery
     * @return
     */
    List<WarningEmailVO> pagesel(WarningEmailQuery warningemailQuery);


}
