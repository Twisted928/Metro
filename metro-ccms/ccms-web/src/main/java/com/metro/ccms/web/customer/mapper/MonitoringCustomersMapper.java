package com.metro.ccms.web.customer.mapper;

import com.metro.ccms.web.customer.query.CustomerlistQuery;
import com.metro.ccms.web.customer.query.MonitoringCustomersQuery;
import com.metro.ccms.web.customer.vo.CustomerlistVO;
import com.metro.ccms.web.customer.vo.MonitoringCustomersVO;
import com.metro.ccms.web.customer.domian.MonitoringCustomersDO;

import java.util.List;

/**
 *<p>
 *  监控客户清单
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/4
 * @desc
 **/
public interface MonitoringCustomersMapper {
    /**
     * 查询
     * @param monitoringCustomersQuery
     * @return
     */
    List<MonitoringCustomersVO> pagesel(MonitoringCustomersQuery monitoringCustomersQuery);
    /**
     * 查询客户清单
     * @param customerlistQuery
     * @return
     */
    List<CustomerlistVO> pageselkehu(CustomerlistQuery customerlistQuery);
    /**
     *  新增
     * @param monitoringCustomersDO
     * @return
     */
    void savemonitoring(MonitoringCustomersDO monitoringCustomersDO);
    /**
     *  修改
     * @param monitoringCustomersDO
     * @return
     */
    void updatemonitoring(MonitoringCustomersDO monitoringCustomersDO);
    /**
     *  删除
     * @param monitoringCustomersDO
     * @return
     */
    void updmonitoring(MonitoringCustomersDO monitoringCustomersDO);
    /**
     *  查询是否存在
     * @param monitoringCustomersDO
     * @return
     */
    int selcount(MonitoringCustomersDO monitoringCustomersDO);

}