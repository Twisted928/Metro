package com.metro.ccms.web.contract.service;

import com.metro.ccms.web.contract.query.ContracttemplateQuery;
import com.metro.ccms.web.contract.vo.ContracttemplateVO;

import java.util.List;

/**
 *<p>
 *  合同模板
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/18
 * @desc
 **/
public interface ContracttemplateService {
    /**
     * 查询
     * @param contracttemplateQuery
     * @return
     */
    List<ContracttemplateVO> pagesel(ContracttemplateQuery contracttemplateQuery);
    /**
     * 查询
     * @param contracttemplateQuery
     * @return
     */
    ContracttemplateVO selbyid(ContracttemplateQuery contracttemplateQuery);


}
