package com.metro.ccms.system.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.system.domain.SysBasicExchange;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 湖绿服务
 * @Date: Created in 14:29 2020/12/15
 * @Modified By:
 */
public interface ISysBasicExchangeService {


    /**
     * 查询汇率列表
     * @param sysBasicExchange
     * @return
     */
    public List<SysBasicExchange> getExchangeList(SysBasicExchange sysBasicExchange);

    /**
     * 新增汇率
     * @param sysBasicExchange
     * @return
     */
    public Result saveExchange(SysBasicExchange sysBasicExchange);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public Result deleteExchangeById(Long id);
}
