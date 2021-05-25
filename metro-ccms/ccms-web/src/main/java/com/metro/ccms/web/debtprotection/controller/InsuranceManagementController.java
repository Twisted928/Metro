package com.metro.ccms.web.debtprotection.controller;

import com.alibaba.fastjson.JSON;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.debtprotection.service.InsuranceManagementService;
import com.metro.ccms.web.debtprotection.vo.InsuranceManagementExport;
import com.metro.ccms.web.debtprotection.vo.InsuranceManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 投标管理
 *
 * @author guangle
 * @date 2020/12/8
 * @since 1.0.0
 */
@RestController
@RequestMapping("/insurance/manage")
public class InsuranceManagementController extends BaseController {
    @Autowired
    private InsuranceManagementService insuranceManagementService;

    /**
     * 投标管理-查询
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return page
     */
//    @PreAuthorize("@ss.hasPermi('insurance:manage:list')")
    @GetMapping("list")
    public TableDataInfo list(InsuranceManagementVO insuranceManagementVO) {
        startPage();
        return getDataTable(insuranceManagementService.list(insuranceManagementVO));
    }

    /**
     * 投标管理-投标
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:manage:save')")
    @PostMapping("/save")
    public Result save(@RequestBody InsuranceManagementVO insuranceManagementVO) {
        if (insuranceManagementVO.getIdList() == null || insuranceManagementVO.getIdList().isEmpty()) {
            return Result.error("请选择一条或者多条保单后,在点击投保");
        }
        return insuranceManagementService.save(insuranceManagementVO);
    }

    /**
     * 投标管理-删除
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:manage:delete')")
    @PostMapping("/delete")
    public Result delete(@RequestBody InsuranceManagementVO insuranceManagementVO) {
        return insuranceManagementService.delete(insuranceManagementVO);
    }

    /**
     * 投标管理-导出
     *
     * @param insuranceManagementVO 投保管理合集VO
     */
//    @PreAuthorize("@ss.hasPermi('insurance:manage:export')")
    @GetMapping("/export")
    public Result export(InsuranceManagementVO insuranceManagementVO) {
        List<InsuranceManagementVO> list = insuranceManagementService.list(insuranceManagementVO);
        List<InsuranceManagementExport> insuranceManagementExports = list2OtherList(list, InsuranceManagementExport.class);
        ExcelUtil<InsuranceManagementExport> excelUtil = new ExcelUtil<>(InsuranceManagementExport.class);
        return excelUtil.exportExcel(insuranceManagementExports, "投标管理导出");
    }

    /**
     *  转为新列表(对象属性名要相同)
     * @param originList 原列表
     * @param tClass 新列表类对象
     * @param <T> 泛型
     * @return list
     */
    public static <T> List<T> list2OtherList(List originList,Class<T> tClass){
        List<T> list = new ArrayList<>();
        for (Object info : originList) {
            T t = JSON.parseObject(JSON.toJSONString(info),tClass);
            list.add(t);
        }
        return list;
    }
}
