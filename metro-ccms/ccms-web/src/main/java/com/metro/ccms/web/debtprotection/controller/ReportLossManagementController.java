package com.metro.ccms.web.debtprotection.controller;

import com.alibaba.fastjson.JSON;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.debtprotection.domian.ClaimDO;
import com.metro.ccms.web.debtprotection.domian.ClaimProgressDO;
import com.metro.ccms.web.debtprotection.domian.InsureDO;
import com.metro.ccms.web.debtprotection.query.ClaimQuery;
import com.metro.ccms.web.debtprotection.service.ReportLossManagementService;
import com.metro.ccms.web.debtprotection.vo.ClaimExport;
import com.metro.ccms.web.debtprotection.vo.LossPortfolioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 报损管理
 *
 * @author guangle
 * @date 2020/12/10
 * @since 1.0.0
 */
@RestController
@RequestMapping("/reportLoss/management")
public class ReportLossManagementController extends BaseController {

    @Autowired
    private ReportLossManagementService reportLossManagementService;

    /**
     * 报损管理-查询
     *
     * @param claimQuery 报损管理查询类
     * @return page
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClaimQuery claimQuery) {
        startPage();
        return getDataTable(reportLossManagementService.list(claimQuery));
    }

    /**
     * 查看已投保保单
     *
     * @return page
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:listInsuredPolicys')")
    @GetMapping("/listInsuredPolicys")
    public TableDataInfo listInsuredPolicys(InsureDO insureDO) {
        startPage();
        return getDataTable(reportLossManagementService.listInsuredPolicys(insureDO));
    }

    /**
     * 根据选择的已投保保单ID查询信息
     *
     * @param insureDO 已投保保单
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:getInsure')")
    @PostMapping("/getInsure")
    public Result getInsure(@RequestBody InsureDO insureDO) {
        return Result.success(reportLossManagementService.getInsure(insureDO));
    }

    /**
     * 报损管理-新增
     *
     * @param lossPortfolioVO 报损管理组合VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:save')")
    @PostMapping("/save")
    public Result save(@RequestBody LossPortfolioVO lossPortfolioVO) {
        if (fieldJudgment(lossPortfolioVO)) {
            return Result.error("必填字段未全部填写，请检查！");
        }
        if (lossPortfolioVO.getClaimProgressDOList().size() > 1){
            return Result.error("一天只能添加一条报损信息");
        } else {
            if (lossPortfolioVO.getClaimProgressDOList() != null && !lossPortfolioVO.getClaimProgressDOList().isEmpty()) {
                if (fieldJudgment2(lossPortfolioVO)) {
                    return Result.error("保单必填字段未全部填写，请检查！");
                }
            }
        }

        return reportLossManagementService.save(lossPortfolioVO);
    }

    /**
     * 报损管理-台账维护
     *
     * @param lossPortfolioVO 报损管理组合VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:update')")
    @PostMapping("/update")
    public Result update(@RequestBody LossPortfolioVO lossPortfolioVO) {
        return reportLossManagementService.update(lossPortfolioVO);
    }

    /**
     * 报损管理-台账维护-台账删除
     *
     * @param claimProgressDO 报损进度表DO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:ledgerDelete')")
    @PostMapping("/ledgerDelete")
    public Result ledgerDelete(@RequestBody ClaimProgressDO claimProgressDO) {
        return reportLossManagementService.ledgerDelete(claimProgressDO);
    }

    /**
     * 报损管理-删除
     *
     * @param claimProgressDO 报损进度表
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:delete')")
    @PostMapping("/delete")
    public Result delete(@RequestBody ClaimProgressDO claimProgressDO) {
        return reportLossManagementService.delete(claimProgressDO);
    }

    /**
     * 报损管理-详细
     *
     * @param claimDO 报损表
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:getone')")
    @PostMapping("/getone")
    public Result getOne(@RequestBody ClaimDO claimDO) {
        return Result.success(reportLossManagementService.getOne(claimDO));
    }

    /**
     * 报损管理-导出
     *
     * @param claimQuery 报损管理查询类
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('reportLoss:management:export')")
    @GetMapping("/export")
    public Result insuranceCompanyManagementExport(ClaimQuery claimQuery) {
        List<ClaimDO> list = reportLossManagementService.list(claimQuery);
        List<ClaimExport> claimExports = list2OtherList(list, ClaimExport.class);
        ExcelUtil<ClaimExport> excelUtil = new ExcelUtil<>(ClaimExport.class);
        return excelUtil.exportExcel(claimExports, "报损管理导出");
    }

    /**
     * 必填字段判断
     *
     * @param lossPortfolioVO 报损管理组合VO
     * @return boolean
     */
    private boolean fieldJudgment(LossPortfolioVO lossPortfolioVO) {
        return  lossPortfolioVO.getCustCode() == null || lossPortfolioVO.getCustCode().isEmpty() ||
                lossPortfolioVO.getCustName() == null || lossPortfolioVO.getCustName().isEmpty() ||
                lossPortfolioVO.getInvoiceNo() == null || lossPortfolioVO.getInvoiceNo().isEmpty() ||
                lossPortfolioVO.getBuyerno() == null || lossPortfolioVO.getBuyerno().isEmpty() ||
                lossPortfolioVO.getInvoicedate() == null;
    }

    /**
     * 台账字段判断
     *
     * @param lossPortfolioVO 报损管理组合VO
     * @return boolean
     */
    private boolean fieldJudgment2(LossPortfolioVO lossPortfolioVO) {
        return lossPortfolioVO.getClaimProgressDOList().get(0).getCaseProgress() == null ||
                lossPortfolioVO.getClaimProgressDOList().get(0).getCaseProgress().isEmpty();
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
