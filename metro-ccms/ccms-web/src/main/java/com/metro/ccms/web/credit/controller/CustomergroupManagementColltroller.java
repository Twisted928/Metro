package com.metro.ccms.web.credit.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.credit.query.CustomerGroupQuery;
import com.metro.ccms.web.credit.service.CustomergroupManagementService;
import com.metro.ccms.web.credit.vo.CustomerGroupVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @description 客户组管理
* @author weiwenhui
* @date 2021/01/13 14:51
*/

@RestController
@RequestMapping("/Customergroup/management")
public class CustomergroupManagementColltroller extends BaseController {
    @Resource
    private CustomergroupManagementService customergroupManagementService;

    /**
     * 客户组列表查询
     * @param customerGroupQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('Customergroup:management:list')")
    @GetMapping("/list")
    public TableDataInfo selCustGroup(CustomerGroupQuery customerGroupQuery){
        startPage();
        return getDataTable(customergroupManagementService.selCustGroup(customerGroupQuery));
    }
    /**
     * 客户组详细查询
     * @param data
     * @return
     */
    @PreAuthorize("@ss.hasPermi('Customergroup:management:listInfo')")
    @PostMapping("/listInfo")
    public Result selCustGroupInfo(@RequestBody JSONObject data){
        String custGroup=data.getString("custGroup");
        return Result.success(customergroupManagementService.selCustGroupInfo(custGroup));
    }
    /**
     * 客户组导出
     * @param customerGroupQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('Customergroup:management:export')")
    @GetMapping("/export")
    public Result exportCustGroup(CustomerGroupQuery customerGroupQuery)throws Exception{
        List<CustomerGroupVO> customerGroupVOList=customergroupManagementService.selCustGroup(customerGroupQuery);
        ExcelUtil<CustomerGroupVO> excelUtil= new ExcelUtil<CustomerGroupVO>(CustomerGroupVO.class);
        return excelUtil.exportExcel(customerGroupVOList,"客户组信息清单");
    }
}
