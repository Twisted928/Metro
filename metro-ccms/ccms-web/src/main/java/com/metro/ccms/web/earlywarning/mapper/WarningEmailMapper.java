package com.metro.ccms.web.earlywarning.mapper;

import com.metro.ccms.web.earlywarning.query.WarningEmailQuery;
import com.metro.ccms.web.earlywarning.vo.WarningEmailVO;

import java.util.List;

/**
 *<p>
 *  预警邮件
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
public interface WarningEmailMapper {
    /**
     * 查询
     * @param warningemailQuery
     * @return
     */
    List<WarningEmailVO> pagesel(WarningEmailQuery warningemailQuery);


}