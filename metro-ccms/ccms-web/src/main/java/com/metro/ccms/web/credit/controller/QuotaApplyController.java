package com.metro.ccms.web.credit.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.credit.domain.QuotaApplyDO;
import com.metro.ccms.web.credit.query.CustomerInterfaceQuery;
import com.metro.ccms.web.credit.query.QuotaApplyQuery;
import com.metro.ccms.web.credit.service.QuotaApplyService;
import com.metro.ccms.web.httpsInterface.domain.CustomerInterfaceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 限额申请
 * @Date: Created in 11:32 2021/1/5
 * @Modified By:
 */
@RestController
@RequestMapping("/credit/quotaApply")
public class QuotaApplyController extends BaseController {

    @Autowired
    private QuotaApplyService quotaApplyService;


    /**
     * 分页查询
     * @param quotaApplyQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('credit:quotaApply:list')")
    @GetMapping("/getQuotaApplyList")
    public TableDataInfo getQuotaApplyList(QuotaApplyQuery quotaApplyQuery){
        startPage();
        List<QuotaApplyDO> list=quotaApplyService.getQuotaApplyList(quotaApplyQuery);
        return getDataTable(list);
    }

    /**
     * 导出
     * @return
     */
    @PreAuthorize("@ss.hasPermi('credit:quotaApply:export')")
    @GetMapping("/export")
    public Result export(QuotaApplyQuery quotaApplyQuery){
        List<QuotaApplyDO> list=quotaApplyService.getQuotaApplyList(quotaApplyQuery);
        ExcelUtil<QuotaApplyDO> util=new ExcelUtil<>(QuotaApplyDO.class);
        return util.exportExcel(list,"限额申请");
    }

    /**
     * 获取客户信息接口表列表（限额申请新增按钮查询）
     * @param customerInterfaceQuery
     * @return
     */
    @GetMapping("/getCustInterfaceList")
    public List<CustomerInterfaceDO> getCustInterfaceList(CustomerInterfaceQuery customerInterfaceQuery){
        return quotaApplyService.getCustInterfaceList(customerInterfaceQuery);
    }
}
