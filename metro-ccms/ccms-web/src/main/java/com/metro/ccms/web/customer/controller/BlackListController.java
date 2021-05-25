package com.metro.ccms.web.customer.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.customer.query.BlackListQuery;
import com.metro.ccms.web.customer.service.BlackListService;
import com.metro.ccms.web.customer.vo.BlackListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *<p>
 *  黑名单管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
@RestController
@RequestMapping("/customer/blacklist")
public class BlackListController extends BaseController {
    @Autowired
    private BlackListService blackListService;

    /**
     * 黑名单管理-查询
     * @param blackListQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:blacklist:list')")
    @GetMapping("/list")
    public TableDataInfo pagesel (BlackListQuery blackListQuery){
        startPage();
        blackListQuery.setDeleted("1");
        return getDataTable(blackListService.pagesel(blackListQuery));
    }
    /**
     * 黑名单管理-查询历史记录
     * @param blackListQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:blacklist:listlishi')")
    @GetMapping("/listlishi")
    public TableDataInfo pagesellishi (BlackListQuery blackListQuery){
        startPage();
        return getDataTable(blackListService.pagesells(blackListQuery));
    }
    /**
     * 导出
     * @param blackListQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:blacklist:export')")
    @GetMapping("/export")
    public Result exportCreditGroupSubList(BlackListQuery blackListQuery) throws Exception{
        blackListQuery.setDeleted("1");
        List<BlackListVO> list=blackListService.pagesel(blackListQuery);
        ExcelUtil<BlackListVO> excelUtil = new ExcelUtil<BlackListVO>(BlackListVO.class);
        return excelUtil.exportExcel(list,"黑名单管理");
    }

}
