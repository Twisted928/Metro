package com.metro.ccms.system.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.system.domain.SysBasicExchange;
import com.metro.ccms.system.mapper.SysBasicExchangeMapper;
import com.metro.ccms.system.service.ISysBasicExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 14:34 2020/12/15
 * @Modified By:
 */
@Service
public class SysBasicExchangeServiceImpl implements ISysBasicExchangeService {

    @Autowired
    private SysBasicExchangeMapper sysBasicExchangeMapper;


    /**
     * 查询汇率列表
     * @param sysBasicExchange
     * @return
     */
    @Override
    public List<SysBasicExchange> getExchangeList(SysBasicExchange sysBasicExchange) {
        return sysBasicExchangeMapper.selectTbBasicExchangeList(sysBasicExchange);
    }

    /**
     * 新增/修改汇率
     * @param sysBasicExchange
     * @return
     */
    @Override
    public Result saveExchange(SysBasicExchange sysBasicExchange) {
        SysBasicExchange query=new SysBasicExchange();
        query.setCurrency(sysBasicExchange.getCurrency());
        query.setCurrencyDh(sysBasicExchange.getCurrencyDh());
        query.setMonth(sysBasicExchange.getMonth());
        if (sysBasicExchangeMapper.selectTbBasicExchangeList(query).size()>0){
            return Result.error("该月度汇率已存在!");
        }
        if (sysBasicExchange.getId()==null){
            sysBasicExchangeMapper.insertTbBasicExchange(sysBasicExchange);
        }else{
            sysBasicExchangeMapper.updateTbBasicExchange(sysBasicExchange);
        }
        return Result.success();
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public Result deleteExchangeById(Long id) {
        sysBasicExchangeMapper.deleteTbBasicExchangeById(id);
        return Result.success();
    }
}
