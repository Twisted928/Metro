package com.metro.ccms.web.earlywarning.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.earlywarning.query.WarningEmailQuery;
import com.metro.ccms.web.earlywarning.service.WarningEmailService;
import com.metro.ccms.web.earlywarning.vo.WarningEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *<p>
 *  预警邮件
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/8
 * @desc
 **/
@RestController
@RequestMapping("/earlywarning/warningemail")
public class WarningEmailController extends BaseController {
    @Autowired
    private WarningEmailService warningemailService;

    /**
     * 预警邮件-查询
     * @param warningemailQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('earlywarning:warningemail:list')")
    @GetMapping("/list")
    public TableDataInfo pagesel (WarningEmailQuery warningemailQuery){
        startPage();
        return getDataTable(warningemailService.pagesel(warningemailQuery));
    }

    /**
     * 导出
     * @param warningemailQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('earlywarning:warningemail:export')")
    @GetMapping("/export")
    public Result exportCreditGroupSubList(WarningEmailQuery warningemailQuery) throws Exception{

        List<WarningEmailVO> list=warningemailService.pagesel(warningemailQuery);
        ExcelUtil<WarningEmailVO> excelUtil = new ExcelUtil<WarningEmailVO>(WarningEmailVO.class);
        return excelUtil.exportExcel(list,"预警邮件");
    }


}
