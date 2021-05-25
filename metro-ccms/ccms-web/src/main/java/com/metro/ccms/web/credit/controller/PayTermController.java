package com.metro.ccms.web.credit.controller;

import com.alibaba.fastjson.JSON;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.credit.domain.PayTermDO;
import com.metro.ccms.web.credit.query.PayTermQuery;
import com.metro.ccms.web.credit.service.PayTermService;
import com.metro.ccms.web.credit.vo.PayTermExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 付款条件
 *
 * @author guangle
 * @date 2020/12/16
 * @since 1.0.0
 */
@RestController
@RequestMapping("/payTerm")
public class PayTermController extends BaseController {

    @Autowired
    private PayTermService payTermService;

    /**
     * 付款条件-查询
     *
     * @param payTermQuery 付款条件的查询条件
     * @return page
     */
    @PreAuthorize("@ss.hasPermi('credit:payTerm:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayTermQuery payTermQuery) {
        startPage();
        return getDataTable(payTermService.list(payTermQuery));
    }

    /**
     * 付款条件-新增
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('credit:payTerm:save')")
    @PostMapping("/save")
    public Result save(@RequestBody PayTermDO payTermDO) {
        return payTermService.save(payTermDO);
    }

    /**
     * 付款条件-修改
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('credit:payTerm:update')")
    @PostMapping("/update")
    public Result update(@RequestBody PayTermDO payTermDO) {
        return payTermService.update(payTermDO);
    }

    /**
     * 付款条件-删除
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('credit:payTerm:delete')")
    @PostMapping("/delete")
    public Result delete(@RequestBody PayTermDO payTermDO) {
        return payTermService.delete(payTermDO);
    }

    /**
     * 付款条件-导出
     *
     * @param payTermQuery 付款条件的查询条件
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('credit:payTerm:export')")
    @GetMapping("/export")
    public Result export(PayTermQuery payTermQuery) {
        List<PayTermDO> list = payTermService.list(payTermQuery);
        List<PayTermExport> guaranteeExports = list2OtherList(list, PayTermExport.class);
        ExcelUtil<PayTermExport> excelUtil = new ExcelUtil<>(PayTermExport.class);
        return excelUtil.exportExcel(guaranteeExports, "付款条件导出");
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
