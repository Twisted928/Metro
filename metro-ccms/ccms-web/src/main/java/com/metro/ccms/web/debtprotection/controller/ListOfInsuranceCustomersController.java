package com.metro.ccms.web.debtprotection.controller;

import com.alibaba.fastjson.JSON;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.debtprotection.domian.InsurecusDO;
import com.metro.ccms.web.debtprotection.query.InsurecusQuery;
import com.metro.ccms.web.debtprotection.service.ListOfInsuranceCustomersService;
import com.metro.ccms.web.debtprotection.vo.InsureChecklistVO;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
import com.metro.ccms.web.debtprotection.vo.InsurecusExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 保险清单
 *
 * @author guangle
 * @create 2020/12/7
 * @since 1.0.0
 */
@RestController
@RequestMapping("/insurance/checklist")
public class ListOfInsuranceCustomersController extends BaseController {
    @Autowired
    private ListOfInsuranceCustomersService listOfInsuranceCustomersService;

    /**
     * 保险清单-查询
     *
     * @param insurecusQuery 保险客户清单表查询类
     * @return page
     */
//    @PreAuthorize("@ss.hasPermi('insurance:checklist:list')")
    @GetMapping("/list")
    public TableDataInfo list(InsurecusQuery insurecusQuery) {
        startPage();
        return getDataTable(listOfInsuranceCustomersService.list(insurecusQuery));
    }

    /**
     * 保险清单-新增
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:checklist:save')")
    @PostMapping("/save")
    public Result save(@RequestBody InsureChecklistVO insureChecklistVO) {
        if (fieldJudgment(insureChecklistVO)) {
            return Result.error("必填字段未全部填写，请检查！");
        }
        return listOfInsuranceCustomersService.save(insureChecklistVO);
    }

    /**
     * 判断公司编码 客户编码 保单号是否重复
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:checklist:judgment')")
    @PostMapping("/judgment")
    public Result judgment(@RequestBody InsureChecklistVO insureChecklistVO) {
        return listOfInsuranceCustomersService.judgment(insureChecklistVO);
    }

    /**
     * 保险清单-补录
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:checklist:update')")
    @PostMapping("/update")
    public Result update(@RequestBody InsureChecklistVO insureChecklistVO) {
        if (fieldJudgment(insureChecklistVO)) {
            return Result.error("必填字段未全部填写，请检查！");
        }
        return listOfInsuranceCustomersService.update(insureChecklistVO);
    }

    /**
     * 保险清单-详情
     *
     * @param insureChecklistVO 主表
     * @return List<SysBasicFile>
     */
//    @PreAuthorize("@ss.hasPermi('insurance:checklist:getFile')")
    @PostMapping("getFile")
    public Result getFile(@RequestBody InsureChecklistVO insureChecklistVO) {
        return Result.success(listOfInsuranceCustomersService.getFile(insureChecklistVO));
    }

    /**
     * 保险清单-删除
     *
     * @param insureChecklistVO 主表
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:checklist:delete')")
    @PostMapping("/delete")
    public Result delete(@RequestBody InsureChecklistVO insureChecklistVO) {
        return listOfInsuranceCustomersService.delete(insureChecklistVO);
    }

    /**
     * 查询保单
     *
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:checklist:listPolicy')")
    @PostMapping("/listPolicy")
    public Result listPolicy(@RequestBody InsurePolicyVO insurePolicyVO) {
        return Result.success(listOfInsuranceCustomersService.listPolicy(insurePolicyVO));
    }

    /**
     * 保险清单-导出
     *
     * @param insurecusQuery 保险客户清单表查询类
     */
//    @PreAuthorize("@ss.hasPermi('insurance:checklist:export')")
    @GetMapping("/export")
    public Result export(InsurecusQuery insurecusQuery) {
        List<InsurecusDO> list = listOfInsuranceCustomersService.list(insurecusQuery);
        List<InsurecusExport> insurecusExports = list2OtherList(list, InsurecusExport.class);
        ExcelUtil<InsurecusExport> excelUtil = new ExcelUtil<>(InsurecusExport.class);
        return excelUtil.exportExcel(insurecusExports, "保险客户清单导出");
    }

    /**
     * 必填字段判断
     *
     * @param insureChecklistVO 接收对象
     * @return boolean
     */
    private boolean fieldJudgment(InsureChecklistVO insureChecklistVO) {
        return insureChecklistVO.getCompCode() == null || insureChecklistVO.getCompCode().isEmpty() ||
                insureChecklistVO.getCompName() == null || insureChecklistVO.getCompName().isEmpty() ||
                insureChecklistVO.getBuyerno() == null || insureChecklistVO.getBuyerno().isEmpty() ||
                insureChecklistVO.getCustCode() == null || insureChecklistVO.getCustCode().isEmpty() ||
                insureChecklistVO.getCustName() == null || insureChecklistVO.getCustName().isEmpty() ||
                insureChecklistVO.getPolicyno() == null || insureChecklistVO.getPolicyno().isEmpty();
    }

    /**
     * 转为新列表(对象属性名要相同)
     *
     * @param originList 原列表
     * @param tClass     新列表类对象
     * @param <T>        泛型
     * @return list
     */
    public static <T> List<T> list2OtherList(List originList, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        for (Object info : originList) {
            T t = JSON.parseObject(JSON.toJSONString(info), tClass);
            list.add(t);
        }
        return list;
    }
}
