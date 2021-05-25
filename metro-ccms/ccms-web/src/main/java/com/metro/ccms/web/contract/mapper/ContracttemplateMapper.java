package com.metro.ccms.web.contract.mapper;

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
public interface ContracttemplateMapper {
    /**
     * 查询
     * @param contracttemplateQuery
     * @return
     */
    List<ContracttemplateVO> pagesel(ContracttemplateQuery contracttemplateQuery);
    /**
     * 根据id查询
     * @param contracttemplateQuery
     * @return
     */
    ContracttemplateVO selbyid(ContracttemplateQuery contracttemplateQuery);


}