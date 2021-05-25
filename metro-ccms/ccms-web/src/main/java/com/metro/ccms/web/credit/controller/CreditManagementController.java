package com.metro.ccms.web.credit.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.credit.query.CarditManagementQuery;
import com.metro.ccms.web.credit.query.CreditManagementQuery;
import com.metro.ccms.web.credit.service.CreditManagementService;
import com.metro.ccms.web.credit.vo.CarditManagementVO;
import com.metro.ccms.web.credit.vo.CreditManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *<p>
 *  授信管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/9
 * @desc
 **/
@RestController
@RequestMapping("/credit/management")
public class CreditManagementController extends BaseController {
    @Autowired
    private CreditManagementService creditManagementService;

    /**
     * 授信管理-信用客户查询
     * @param creditManagementQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('credit:management:list')")
    @GetMapping("/list")
    public TableDataInfo pagesel (CreditManagementQuery creditManagementQuery){
        startPage();
        creditManagementQuery.setDeleted("0");
        return getDataTable(creditManagementService.pagesel(creditManagementQuery));
    }
    /**
     * 授信管理-信用客户评估详情（逻辑待定）
     * @param creditManagementQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('credit:management:detailslist')")
    @GetMapping("/detailslist")
    public TableDataInfo pagedetails (CreditManagementQuery creditManagementQuery){
        startPage();
        creditManagementQuery.setDeleted("0");
        return getDataTable(creditManagementService.pagedetails(creditManagementQuery));
    }
    /**
     * 授信管理-信用客户查询历史记录
     * @param creditManagementQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('credit:management:listlishi')")
    @GetMapping("/listlishi")
    public TableDataInfo pagesellishi (CreditManagementQuery creditManagementQuery){
        startPage();
        return getDataTable(creditManagementService.history(creditManagementQuery));
    }
    /**
     * 导出
     * @param creditManagementQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('credit:management:export')")
    @GetMapping("/export")
    public Result exportCreditGroupSubList(CreditManagementQuery creditManagementQuery) throws Exception{
        creditManagementQuery.setDeleted("0");
        List<CreditManagementVO> list=creditManagementService.pagesel(creditManagementQuery);
        ExcelUtil<CreditManagementVO> excelUtil = new ExcelUtil<CreditManagementVO>(CreditManagementVO.class);
        return excelUtil.exportExcel(list,"信用客户");
    }
    /**
     * 授信管理-信用卡号查询
     * @param carditManagementQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('credit:management:cardlist')")
    @GetMapping("/cardlist")
    public TableDataInfo pagecard (CarditManagementQuery carditManagementQuery){
        startPage();
        carditManagementQuery.setDeleted("1");
        return getDataTable(creditManagementService.pagecard(carditManagementQuery));
    }
    /**
     * 导出
     * @param carditManagementQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('credit:management:cardexport')")
    @GetMapping("/cardexport")
    public Result exportCard(CarditManagementQuery carditManagementQuery) throws Exception{
        List<CarditManagementVO> list=creditManagementService.pagecard(carditManagementQuery);
        ExcelUtil<CarditManagementVO> excelUtil = new ExcelUtil<CarditManagementVO>(CarditManagementVO.class);
        return excelUtil.exportExcel(list,"信用卡号");
    }
}
