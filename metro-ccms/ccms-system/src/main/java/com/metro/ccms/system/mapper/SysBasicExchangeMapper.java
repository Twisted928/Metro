package com.metro.ccms.system.mapper;

import com.metro.ccms.system.domain.SysBasicExchange;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 14:22 2020/12/15
 * @Modified By:
 */
public interface SysBasicExchangeMapper {

    /**
     * 查询汇率
     *
     * @param id 汇率ID
     * @return 汇率
     */
    public SysBasicExchange selectTbBasicExchangeById(Long id);

    /**
     * 查询汇率列表
     *
     * @param sysBasicExchange 汇率
     * @return 汇率集合
     */
    public List<SysBasicExchange> selectTbBasicExchangeList(SysBasicExchange sysBasicExchange);

    /**
     * 新增汇率
     *
     * @param sysBasicExchange 汇率
     * @return 结果
     */
    public int insertTbBasicExchange(SysBasicExchange sysBasicExchange);

    /**
     * 修改汇率
     *
     * @param sysBasicExchange 汇率
     * @return 结果
     */
    public int updateTbBasicExchange(SysBasicExchange sysBasicExchange);

    /**
     * 删除汇率
     *
     * @param id 汇率ID
     * @return 结果
     */
    public int deleteTbBasicExchangeById(Long id);

    /**
     * 批量删除汇率
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbBasicExchangeByIds(Long[] ids);
}
