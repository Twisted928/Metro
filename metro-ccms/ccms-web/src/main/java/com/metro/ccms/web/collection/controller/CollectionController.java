package com.metro.ccms.web.collection.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.collection.vo.CollectionAndDetailVO;
import com.metro.ccms.web.collection.vo.CollectionVO;
import com.metro.ccms.web.collection.domian.CollectionDO;
import com.metro.ccms.web.collection.query.CollectionQuery;
import com.metro.ccms.web.collection.service.CollectionService;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 催收管理
 * @author wwh
 *
 */
@RestController
@RequestMapping("/collection/management")
public class CollectionController extends BaseController {
    @Resource
    private CollectionService collectionService;
    /**
     * 催收管理-查询
     *
     * @param collectionQuery 催收管理查询类
     * @return TableDataInfo 分页返回显示对象
     */
    @PreAuthorize("@ss.hasPermi('collection:management:list')")
    @GetMapping("/list")
    public TableDataInfo selectCollection(CollectionQuery collectionQuery) {
        startPage();
        return getDataTable(collectionService.selectCollection(collectionQuery));
    }
    /**
     * 催收管理-详情页面查询
     *
     * @param collectionDO
     * @return CollectionAndDetailVO 详情返回显示对象
     */
    @PostMapping("/detail")
    public Result selectCollectionDetail(@RequestBody CollectionDO collectionDO) {
        return Result.success(collectionService.selectCollectionDetail(collectionDO));
    }
    /**
     * 催收管理-维护页面
     *
     * @param collectionAndDetailVO
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('collection:management:save')")
    @PostMapping("/save")
    public Result saveCollection(@RequestBody CollectionAndDetailVO collectionAndDetailVO){
        return collectionService.saveCollection(collectionAndDetailVO);
    }

    /**
     * 催收管理-催收单据删除功能
     *
     * @param collectionDO
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('collection:management:del')")
    @PostMapping("/del")
    public Result deleteCollection(@RequestBody CollectionDO collectionDO){
        return collectionService.deleteCollection(collectionDO);
    }
    /**
     * 催收单据PDF预览根据id查询
     * @param collectionVO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('collection:management:selPDFinfo')")
    @PostMapping("/selPDFinfo")
    public Result selPdfInfo(@RequestBody CollectionVO collectionVO){
        return Result.success(collectionService.selPdfInfo(collectionVO));
    }
    /**
     * 催收单据新增分页返回查询范围
     * @param bsegInterfaceDO
     * @return
     */
    @GetMapping("/selRange")
    public TableDataInfo selRange(BsegInterfaceDO bsegInterfaceDO){
        startPage();
        return getDataTable(collectionService.selRange(bsegInterfaceDO));
    }
    /**
     * 催收单据新增-保存
     * @param bsegInterfaceDO
     * @return
     */
    @PreAuthorize("@ss.hasPermi('collection:management:insert')")
    @PostMapping("/insert")
    public Result insertCollection(@RequestBody BsegInterfaceDO bsegInterfaceDO){
    return collectionService.insertCollection(bsegInterfaceDO);
    }
    /**
     * 催收管理-催收单据导出功能
     *
     * @param collectionQuery
     * @return Result
     */
    @PreAuthorize("@ss.hasPermi('collection:management:export')")
    @GetMapping("/export")
    public Result exportCollection(CollectionQuery collectionQuery)throws Exception{
        collectionQuery.setDeleted(0);
        List<CollectionVO> collectionVOList=collectionService.selectCollection(collectionQuery);
        ExcelUtil<CollectionVO> excelUtil= new ExcelUtil<CollectionVO>(CollectionVO.class);
        return excelUtil.exportExcel(collectionVOList,"催收单据清单");
    }
}
