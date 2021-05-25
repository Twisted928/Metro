package com.metro.ccms.web.contract.service.impl;

import com.metro.ccms.web.contract.mapper.ContracttemplateMapper;
import com.metro.ccms.web.contract.query.ContracttemplateQuery;
import com.metro.ccms.web.contract.service.ContracttemplateService;
import com.metro.ccms.web.contract.vo.ContracttemplateVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class ContracttemplateServiceImpl implements ContracttemplateService {

    private static final Logger logger = LoggerFactory.getLogger(ContracttemplateServiceImpl.class);

    @Autowired
    private ContracttemplateMapper contracttemplateMapper;

    /**
     * 查询
     *
     * @param contracttemplateQuery
     * @return
     */
    @Override
    public List<ContracttemplateVO> pagesel(ContracttemplateQuery contracttemplateQuery) {
        List<ContracttemplateVO> list=contracttemplateMapper.pagesel(contracttemplateQuery);
        return list;
    }
    /**
     * 查询
     *
     * @param contracttemplateQuery
     * @return
     */
    @Override
    public ContracttemplateVO selbyid(ContracttemplateQuery contracttemplateQuery) {
        ContracttemplateVO list=contracttemplateMapper.selbyid(contracttemplateQuery);
        return list;
    }

}
