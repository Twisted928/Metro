package com.metro.ccms.test.customer;

import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.customer.domian.MonitoringCustomersDO;
import com.metro.ccms.web.customer.query.MonitoringCustomersQuery;
import com.metro.ccms.web.customer.query.SleepCustomerQuery;
import com.metro.ccms.web.customer.service.MonitoringCustomersService;
import com.metro.ccms.web.customer.service.SleepCustomerService;
import com.metro.ccms.web.customer.vo.MonitoringCustomersVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *<p>
 *  监控客户清单单元测试
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
public class MonitoringServiceTest extends BaseTest {

    @Autowired
    private MonitoringCustomersService monitoringCustomersService;
    @Autowired
    private SleepCustomerService sleepCustomerService;

    /**
     * @Param 监控客户清单查询
     * @description
     * @author JiXiang.Song
     * @date 2020/12/3 16:26
     * @return
     * @throws
     */
    @Test
    public void test(){
        MonitoringCustomersQuery blackListQuery=new MonitoringCustomersQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setCustcode("123456");
        blackListQuery.setCustname("test");
        blackListQuery.setStatus("1");
//        blackListQuery.setValidfrom(formatter.format(date));
//        blackListQuery.setValidto(formatter.format(date));
        List<MonitoringCustomersVO> list=monitoringCustomersService.pagesel(blackListQuery);
        System.out.println(list);
    }
    /**
     * @Param 监控客户清单新增
     * @description
     * @author JiXiang.Song
     * @date 2020/12/3 16:26
     * @return
     * @throws
     */
    @Test
    public void savemonitoring(){
        MonitoringCustomersDO blackListQuery=new MonitoringCustomersDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setCustcode("123456");
        blackListQuery.setCustname("test");
        blackListQuery.setStatus("1");
        blackListQuery.setValidfrom(formatter.format(date));
        blackListQuery.setValidto(formatter.format(date));
        blackListQuery.setReason("测试原因");
        monitoringCustomersService.savemonitoring(blackListQuery);
    }
    /**
     * @Param 删除
     * @description
     * @author JiXiang.Song
     * @date 2020/12/3 16:26
     * @return
     * @throws
     */
    @Test
    public void updmonitoring(){
        MonitoringCustomersDO blackListQuery=new MonitoringCustomersDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setId("2");
        monitoringCustomersService.updmonitoring(blackListQuery);
    }
    /**
     * @Param 睡眠客户管理
     * @description
     * @author JiXiang.Song
     * @date 2020/12/3 16:26
     * @return
     * @throws
     */
    @Test
    public void pagesel(){
        SleepCustomerQuery blackListQuery=new SleepCustomerQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setCustcode("123456");
        blackListQuery.setCustname("test");
        blackListQuery.setStatus("1");
//        blackListQuery.setValidfrom(formatter.format(date));
//        blackListQuery.setValidto(formatter.format(date));
        sleepCustomerService.pagesel(blackListQuery);
    }
}
