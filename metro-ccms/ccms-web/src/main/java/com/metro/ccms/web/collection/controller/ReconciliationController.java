package com.metro.ccms.web.collection.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.collection.domian.ReconciliationDO;
import com.metro.ccms.web.collection.query.ReconciliationQuery;
import com.metro.ccms.web.collection.service.ReconciliationService;
import com.metro.ccms.web.collection.vo.ReconAndDetailVO;
import com.metro.ccms.web.collection.vo.ReconciliationVO;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @description
* @author weiwenhui
* @date 2020/12/14 15:12
*/
@RestController
@RequestMapping("/reconciliation/management")
public class ReconciliationController extends BaseController {
    @Resource
    private ReconciliationService reconciliationService;

    /**
     * 对账管理-列表查询
     *
     * @param reconciliationQuery 对账管理查询类
     * @return TableDataInfo 分页返回显示对象
     */
    @PreAuthorize("@ss.hasPermi('reconciliation:management:list')")
    @GetMapping("/list")
    public TableDataInfo selectReconciliation(ReconciliationQuery reconciliationQuery) {
        startPage();
        return getDataTable(reconciliationService.selectReconciliation(reconciliationQuery));
    }
    /**
     * 对账管理-对账详情查询
     *
     * @param reconciliationDO
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('reconciliation:management:selectDetail')")
    @PostMapping("/selectDetail")
    public Result selectReconDetail(@RequestBody ReconciliationDO reconciliationDO){
        return Result.success(reconciliationService.selectReconDetail(reconciliationDO));
    }
    /**
     * 对账管理-对账结果维护台账修改功能
     *
     * @param reconAndDetailVO
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('reconciliation:management:upReconRecord')")
    @PostMapping("/upReconRecord")
    public Result updateReconRecord(@RequestBody ReconAndDetailVO reconAndDetailVO){
        return reconciliationService.updateReconRecord(reconAndDetailVO);
    }
    /**
     * 对账管理-下载PDF时更新对账状态
     *
     * @param reconciliationDO
     * @return Result
     */
    @PostMapping("/updateStaus")
    public Result updateStaus(@RequestBody ReconciliationDO reconciliationDO){
        return reconciliationService.updateStaus(reconciliationDO);
    }
    /**
     * 对账管理-对账导出功能
     *
     * @param reconciliationQuery
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('reconciliation:management:export')")
    @GetMapping("/export")
    public Result exportReconliciation(ReconciliationQuery reconciliationQuery)throws Exception{
        reconciliationQuery.setDeleted(0);
        List<ReconciliationVO> reconciliationVOList=reconciliationService.selectReconciliation(reconciliationQuery);
        ExcelUtil<ReconciliationVO> excelUtil= new ExcelUtil<ReconciliationVO>(ReconciliationVO.class);
        return excelUtil.exportExcel(reconciliationVOList,"对账清单");
    }
    /**
     * 对账管理-对账预览PDF查询
     *
     * @param reconciliationDO
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('reconciliation:management:selPDF')")
    @PostMapping("/selPDF")
    public Result selPdfInfo(@RequestBody ReconciliationDO reconciliationDO){
        return Result.success(reconciliationService.selPdfInfo(reconciliationDO));
    }
    /**
     * 对账单新增-查询汇总金额
     * @param bsegInterfaceDO
     * @return
     */
    @PostMapping("/selMoney")
    public Result selMoney(@RequestBody BsegInterfaceDO bsegInterfaceDO){
        return Result.success(reconciliationService.selMoney(bsegInterfaceDO));
    }
    /**
     * 对账单新增-查询范围
     * @param bsegInterfaceDO
     * @return
     */
    @GetMapping("/selRange")
    public TableDataInfo selRange(BsegInterfaceDO bsegInterfaceDO){
        startPage();
        return getDataTable(reconciliationService.selRange(bsegInterfaceDO));
    }
    /**
     * 对账单据新增保存
     * @param bsegInterfaceDO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('reconciliation:management:insertRecon')")
    @PostMapping("/insertRecon")
    public Result insertRecon(@RequestBody BsegInterfaceDO bsegInterfaceDO){
        return reconciliationService.insertRecon(bsegInterfaceDO);
    }
}
