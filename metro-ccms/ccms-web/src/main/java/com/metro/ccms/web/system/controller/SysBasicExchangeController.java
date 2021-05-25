package com.metro.ccms.web.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.system.domain.SysBasicExchange;
import com.metro.ccms.system.service.ISysBasicExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 14:37 2020/12/15
 * @Modified By:
 */
@RestController
@RequestMapping("/system/exchange")
public class SysBasicExchangeController extends BaseController {

    @Autowired
    private ISysBasicExchangeService iSysBasicExchangeService;

    /**
     * 分页查询
     * @param sysBasicExchange
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:exchange:list')")
    @GetMapping("/getExchangeList")
    public TableDataInfo getExchangeList(SysBasicExchange sysBasicExchange){
        startPage();
        List<SysBasicExchange> list=iSysBasicExchangeService.getExchangeList(sysBasicExchange);
        return getDataTable(list);
    }

    /**
     * 导出
     * @param sysBasicExchange
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:exchange:export')")
    @GetMapping("/export")
    public Result export(SysBasicExchange sysBasicExchange){
        List<SysBasicExchange> list=iSysBasicExchangeService.getExchangeList(sysBasicExchange);
        ExcelUtil<SysBasicExchange> util=new ExcelUtil<>(SysBasicExchange.class);
        return util.exportExcel(list,"汇率管理");
    }

    /**
     * 新增汇率
     * @param sysBasicExchange
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:exchange:save')")
    @RequestMapping("/saveExchange")
    public Result saveExchange(@RequestBody SysBasicExchange sysBasicExchange){
        return iSysBasicExchangeService.saveExchange(sysBasicExchange);
    }

    /**
     * 根据id删除
     * @param data
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:exchange:delete')")
    @RequestMapping("/deleteExchangeById")
    public Result deleteExchangeById(@RequestBody JSONObject data){
        Long id=data.getLong("id");
        return iSysBasicExchangeService.deleteExchangeById(id);
    }
}
