package com.metro.ccms.web.customer.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.customer.domian.MonitoringCustomersDO;
import com.metro.ccms.web.customer.query.CustomerlistQuery;
import com.metro.ccms.web.customer.query.MonitoringCustomersQuery;
import com.metro.ccms.web.customer.service.MonitoringCustomersService;
import com.metro.ccms.web.customer.vo.MonitoringCustomersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/customer/monitoringcustomers")
public class MonitoringCustomersController extends BaseController {
    @Autowired
    private MonitoringCustomersService monitoringCustomersService;

    /**
     * 监控客户清单-查询
     * @param monitoringCustomersQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:monitoringcustomers:list')")
    @GetMapping("/list")
    public TableDataInfo pagesel (MonitoringCustomersQuery monitoringCustomersQuery){
        startPage();
        monitoringCustomersQuery.setDeleted("1");
        return getDataTable(monitoringCustomersService.pagesel(monitoringCustomersQuery));
    }
    /**
     * 监控客户清单-查询客户信息
     * @param customerlistQuery
     * @return
     */
    @GetMapping("/kehulist")
    public TableDataInfo pageselkehu (CustomerlistQuery customerlistQuery){
        startPage();
        return getDataTable(monitoringCustomersService.pageselkehu(customerlistQuery));
    }
    /**
     * 监控客户清单-新增
     * @param monitoringCustomersDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:monitoringcustomers:save')")
    @PostMapping("/save")
    public Result savemonitoring (@RequestBody MonitoringCustomersDO monitoringCustomersDO){
        return monitoringCustomersService.savemonitoring(monitoringCustomersDO);
    }
    /**
     * 监控客户清单-修改
     * @param monitoringCustomersDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:monitoringcustomers:uodqd')")
    @PostMapping("/uodqd")
    public Result updatemonitoring (@RequestBody MonitoringCustomersDO monitoringCustomersDO){
        return monitoringCustomersService.updatemonitoring(monitoringCustomersDO);
    }
    /**
     * 监控客户清单-删除
     * @param monitoringCustomersDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:monitoringcustomers:update')")
    @PostMapping("/update")
    public Result updmonitoring (@RequestBody MonitoringCustomersDO monitoringCustomersDO){
        return monitoringCustomersService.updmonitoring(monitoringCustomersDO);
    }
    /**
     * 监控客户清单-详情
     * @param monitoringCustomersQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:monitoringcustomers:listdetails')")
    @PostMapping("/listdetails")
    public Result pagesellishi (@RequestBody MonitoringCustomersQuery monitoringCustomersQuery){
        monitoringCustomersQuery.setDeleted("1");
        List<MonitoringCustomersVO> list=monitoringCustomersService.pagesel(monitoringCustomersQuery);
        return Result.success(list);
    }
    /**
     * 导出
     * @param monitoringCustomersQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:monitoringcustomers:export')")
    @GetMapping("/export")
    public Result exportCreditGroupSubList(MonitoringCustomersQuery monitoringCustomersQuery) throws Exception{
        monitoringCustomersQuery.setDeleted("1");
        List<MonitoringCustomersVO> list=monitoringCustomersService.pagesel(monitoringCustomersQuery);
        ExcelUtil<MonitoringCustomersVO> excelUtil = new ExcelUtil<MonitoringCustomersVO>(MonitoringCustomersVO.class);
        return excelUtil.exportExcel(list,"监控客户清单");
    }


}
