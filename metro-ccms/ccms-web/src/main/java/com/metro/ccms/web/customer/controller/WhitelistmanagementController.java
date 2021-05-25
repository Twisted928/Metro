package com.metro.ccms.web.customer.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.service.ISysFileService;
import com.metro.ccms.web.customer.domian.WhitelistmanagementDO;
import com.metro.ccms.web.customer.query.WhitelistmanagementQuery;
import com.metro.ccms.web.customer.service.WhitelistmanagementService;
import com.metro.ccms.web.customer.vo.WhitelistmanagementVO;
import com.metro.ccms.web.customer.vo.WhitelistmanagementqdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *<p>
 *  白名单管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
@RestController
@RequestMapping("/customer/whitelistmanagement")
public class WhitelistmanagementController extends BaseController {
    @Autowired
    private WhitelistmanagementService whitelistmanagementService;
    @Autowired
    private ISysFileService iSysFileService;

    /**
     * 白名单管理-查询
     * @param whitelistmanagementQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:list')")
    @GetMapping("/list")
    public TableDataInfo pagesel (WhitelistmanagementQuery whitelistmanagementQuery){
        startPage();
        whitelistmanagementQuery.setDeleted("1");
        return getDataTable(whitelistmanagementService.pagesel(whitelistmanagementQuery));
    }
    /**
     * 白名单管理-查询详情
     * @param whitelistmanagementQuery
     * @return insureCompanyVOPage
     */
    @PostMapping("/details")
    public Result seldetails (@RequestBody WhitelistmanagementQuery whitelistmanagementQuery){
        whitelistmanagementQuery.setDeleted("1");
        return Result.success(whitelistmanagementService.seldetails(whitelistmanagementQuery));
    }
    /**
     * 白名单管理-修改
     * @param whitelistmanagementDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:update')")
    @PostMapping("/update")
    public Result updmonitoring (@RequestBody WhitelistmanagementDO whitelistmanagementDO){
        return whitelistmanagementService.updmonitoring(whitelistmanagementDO);
    }
    /**
     * 白名单管理-删除
     * @param whitelistmanagementDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:delete')")
    @PostMapping("/delete")
    public Result delmonitoring (@RequestBody WhitelistmanagementDO whitelistmanagementDO){
        return whitelistmanagementService.delmonitoring(whitelistmanagementDO);
    }
    /**
     * 白名单管理-新增
     * @param whitelistmanagementDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:save')")
    @PostMapping("/save")
    public Result savemonitoring (@RequestBody WhitelistmanagementDO whitelistmanagementDO){
        return whitelistmanagementService.savemonitoring(whitelistmanagementDO);
    }
    /**
     * 白名单管理-审批
     * @param whitelistmanagementDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:approval')")
    @PostMapping("/approval")
    public Result updatemonitoring (@RequestBody WhitelistmanagementDO whitelistmanagementDO){
        return whitelistmanagementService.updatemonitoring(whitelistmanagementDO);
    }
    /**
     * 白名单管理-撤销
     * @param whitelistmanagementDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:revoke')")
    @PostMapping("/revoke")
    public Result deletemonitoring (@RequestBody WhitelistmanagementDO whitelistmanagementDO){
        return whitelistmanagementService.deletemonitoring(whitelistmanagementDO);
    }
    /**
     * 导出
     * @param whitelistmanagementQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:export')")
    @GetMapping("/export")
    public Result exportCreditGroupSubList(WhitelistmanagementQuery whitelistmanagementQuery) throws Exception{

        whitelistmanagementQuery.setDeleted("1");
        List<WhitelistmanagementVO> list=whitelistmanagementService.pagesel(whitelistmanagementQuery);
        ExcelUtil<WhitelistmanagementVO> excelUtil = new ExcelUtil<WhitelistmanagementVO>(WhitelistmanagementVO.class);
        return excelUtil.exportExcel(list,"白名单申请单");
    }
    /**
     * 白名单清单-查询
     * @param whitelistmanagementQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:qdlist')")
    @GetMapping("/qdlist")
    public TableDataInfo pageselqd (WhitelistmanagementQuery whitelistmanagementQuery){
        startPage();
        whitelistmanagementQuery.setDeleted("1");
        return getDataTable(whitelistmanagementService.pageselqd(whitelistmanagementQuery));
    }
    /**
     * 白名单清单-移除
     * @param whitelistmanagementDO
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:remove')")
    @PostMapping("/remove")
    public Result remove (@RequestBody WhitelistmanagementDO whitelistmanagementDO){
        return whitelistmanagementService.remove(whitelistmanagementDO);
    }
    /**
     * 白名单清单-历史记录
     * @param whitelistmanagementQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:lslist')")
    @GetMapping("/lslist")
    public TableDataInfo pagesells (WhitelistmanagementQuery whitelistmanagementQuery){
        startPage();
        whitelistmanagementQuery.setDeleted("1");
        return getDataTable(whitelistmanagementService.pagesells(whitelistmanagementQuery));
    }
    /**
     * 清单导出
     * @param whitelistmanagementQuery
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:whitelistmanagement:qdexport')")
    @GetMapping("/qdexport")
    public Result exportqdList(WhitelistmanagementQuery whitelistmanagementQuery) throws Exception{

        whitelistmanagementQuery.setDeleted("1");
        List<WhitelistmanagementqdVO> list=whitelistmanagementService.pageselqd(whitelistmanagementQuery);
        ExcelUtil<WhitelistmanagementqdVO> excelUtil = new ExcelUtil<WhitelistmanagementqdVO>(WhitelistmanagementqdVO.class);
        return excelUtil.exportExcel(list,"白名单清单");
    }

    /**
     * 获取附件列表
     * 传入的为主表id
     * @param whitelistmanagementDO
     * @return
     */
    @PostMapping("/enclosure")
    public Result pagesellishi (@RequestBody WhitelistmanagementDO whitelistmanagementDO){
        List<SysBasicFile> list=iSysFileService.getFileList(whitelistmanagementDO.getId(),"1");
        return Result.success(list);
    }
}
