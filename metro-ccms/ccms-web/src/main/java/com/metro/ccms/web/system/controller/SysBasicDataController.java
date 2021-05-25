package com.metro.ccms.web.system.controller;

import com.metro.ccms.common.annotation.Log;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.enums.BusinessType;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.common.core.domain.entity.SysBasicData;
import com.metro.ccms.system.service.ISysBasicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 9:54 2020/12/15
 * @Modified By:
 */
@RestController
@RequestMapping("/system/data")
public class SysBasicDataController extends BaseController {

    @Autowired
    private ISysBasicDataService iSysBasicDataService;


    /**
     * 获取参数列表
     */
    @GetMapping("/list")
    public Result list(SysBasicData sysBasicData)
    {
        List<SysBasicData> datas = iSysBasicDataService.getDataList(sysBasicData);
        return Result.success(datas);
    }

    /**
     * 导出
     * @param sysBasicData
     * @return
     */
    @GetMapping("/export")
    public Result export(SysBasicData sysBasicData){
        List<SysBasicData> datas = iSysBasicDataService.getDataList(sysBasicData);
        ExcelUtil util=new ExcelUtil(SysBasicData.class);
        return util.exportExcel(datas,"参数数据");
    }

    /**
     * 获取参数下拉树列表
     */
    @GetMapping("/treeselect")
    public Result treeselect(SysBasicData sysBasicData)
    {
        List<SysBasicData> datas = iSysBasicDataService.getDataList(sysBasicData);
        return Result.success(iSysBasicDataService.buildDeptTreeSelect(datas));
    }

    /**
     * 新增部门
     */
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody SysBasicData sysBasicData) {
        sysBasicData.setCreatedBy(SecurityUtils.getUsername());
        return iSysBasicDataService.insertDept(sysBasicData);
    }

    /**
     * 启用、禁用
     * @param sysBasicData
     * @return
     */
    @GetMapping("/enableOrDisable")
    public Result enableOrDisable(SysBasicData sysBasicData) {
        return iSysBasicDataService.enableOrDisable(sysBasicData);
    }

    /**
     * 删除参数
     */
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id)
    {
        if (iSysBasicDataService.hasChildById(id)) {
            return Result.error("存在下级参数,不允许删除");
        }
        return iSysBasicDataService.deleteDataById(id);
    }
}
