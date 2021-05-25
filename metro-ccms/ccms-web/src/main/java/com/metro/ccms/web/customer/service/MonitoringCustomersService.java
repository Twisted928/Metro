package com.metro.ccms.web.customer.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.customer.domian.MonitoringCustomersDO;
import com.metro.ccms.web.customer.query.CustomerlistQuery;
import com.metro.ccms.web.customer.query.MonitoringCustomersQuery;
import com.metro.ccms.web.customer.vo.CustomerlistVO;
import com.metro.ccms.web.customer.vo.MonitoringCustomersVO;

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
public interface MonitoringCustomersService {
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
     * 新增
     * @param monitoringCustomersDO
     * @return
     */
    Result savemonitoring(MonitoringCustomersDO monitoringCustomersDO);
    /**
     * 修改
     * @param monitoringCustomersDO
     * @return
     */
    Result updatemonitoring(MonitoringCustomersDO monitoringCustomersDO);
    /**
     * 删除
     * @param monitoringCustomersDO
     * @return
     */
    Result updmonitoring(MonitoringCustomersDO monitoringCustomersDO);

}
