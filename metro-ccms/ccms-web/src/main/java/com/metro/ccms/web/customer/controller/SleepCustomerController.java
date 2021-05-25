package com.metro.ccms.web.customer.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.customer.query.SleepCustomerQuery;
import com.metro.ccms.web.customer.service.SleepCustomerService;
import com.metro.ccms.web.customer.vo.SleepCustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *<p>
 *  睡眠客户管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/4
 * @desc
 **/
@RestController
@RequestMapping("/customer/sleepcustomer")
public class SleepCustomerController extends BaseController {
    @Autowired
    private SleepCustomerService sleepCustomerService;

    /**
     * 睡眠客户管理-查询
     * @param sleepCustomerQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:sleepcustomer:list')")
    @GetMapping("/list")
    public TableDataInfo pagesel (SleepCustomerQuery sleepCustomerQuery){
        startPage();
        sleepCustomerQuery.setDeleted("1");
        return getDataTable(sleepCustomerService.pagesel(sleepCustomerQuery));
    }
    /**
     * 睡眠客户管理-查询历史记录
     * @param sleepCustomerQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:sleepcustomer:listlishi')")
    @GetMapping("/listlishi")
    public TableDataInfo pagesellishi (SleepCustomerQuery sleepCustomerQuery){
        startPage();
        return getDataTable(sleepCustomerService.pagesells(sleepCustomerQuery));
    }
    /**
     * 导出
     * @param sleepCustomerQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:sleepcustomer:export')")
    @GetMapping("/export")
    public Result exportCreditGroupSubList(SleepCustomerQuery sleepCustomerQuery) throws Exception{

        sleepCustomerQuery.setDeleted("1");
        List<SleepCustomerVO> list=sleepCustomerService.pagesel(sleepCustomerQuery);
        ExcelUtil<SleepCustomerVO> excelUtil = new ExcelUtil<SleepCustomerVO>(SleepCustomerVO.class);
        return excelUtil.exportExcel(list,"睡眠客户管理");
    }


}
