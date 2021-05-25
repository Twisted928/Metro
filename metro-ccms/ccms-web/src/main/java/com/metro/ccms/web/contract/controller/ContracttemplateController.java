package com.metro.ccms.web.contract.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.web.contract.query.ContracttemplateQuery;
import com.metro.ccms.web.contract.service.ContracttemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *<p>
 *  合同模板
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/18
 * @desc
 **/
@RestController
@RequestMapping("/contract/template")
public class ContracttemplateController extends BaseController {
    @Autowired
    private ContracttemplateService contracttemplateService;

    /**
     * 查询
     * @param contracttemplateQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('contract:template:list')")
    @GetMapping("/list")
    public TableDataInfo pagesel (ContracttemplateQuery contracttemplateQuery){
        startPage();
        return getDataTable(contracttemplateService.pagesel(contracttemplateQuery));
    }
    /**
     * 根据id查询
     * @param contracttemplateQuery
     * @return insureCompanyVOPage
     */
    @GetMapping("/selbyid")
    public Result selbyid (ContracttemplateQuery contracttemplateQuery){
        return Result.success(contracttemplateService.selbyid(contracttemplateQuery));
    }

}
