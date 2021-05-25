package com.metro.ccms.web.earlywarning.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelConfigDO;
import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelDO;
import com.metro.ccms.web.earlywarning.query.EarlyWarningModelQuery;
import com.metro.ccms.web.earlywarning.service.EarlyWarningModelService;
import com.metro.ccms.web.earlywarning.vo.EarlyWarningModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *<p>
 *  预警模型
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/8
 * @desc
 **/
@RestController
@RequestMapping("/earlywarning/warningmodel")
public class EarlyWarningModelController extends BaseController {
    @Autowired
    private EarlyWarningModelService earlywarningmodelService;

    /**
     * 预警模型-查询
     * @param earlywarningmodelQuery
     * @return insureCompanyVOPage
     */
    @PreAuthorize("@ss.hasPermi('earlywarning:warningmodel:list')")
    @GetMapping("/list")
    public TableDataInfo pagesel (EarlyWarningModelQuery earlywarningmodelQuery){
        startPage();
        return getDataTable(earlywarningmodelService.pagesel(earlywarningmodelQuery));
    }
    /**
     * 预警模型-详细
     * @param data
     * @return insureCompanyVOPage
     */
    @PostMapping("/detailed")
    public Result pagedetailed (@RequestBody JSONObject data){
        String code=data.getString("mocdelCode");
        return Result.success(earlywarningmodelService.detailed(code));
    }

    /**
     * 保存预警模型配置信息
     * @param data
     * @return
     */
    @RequestMapping("/saveEarlyModelConfig")
    public Result saveEarlyModelConfig(@RequestBody JSONObject data){
        EarlyWarningModelVO earlyWarningModelVO=new EarlyWarningModelVO();
        earlyWarningModelVO.setConfigDO(data.getJSONArray("configDO").toJavaList(EarlyWarningModelConfigDO.class));
        earlyWarningModelVO.setConfigIds(data.getJSONArray("configIds").toJavaList(Long.class));
        return earlywarningmodelService.saveEarlyModelConfig(earlyWarningModelVO);
    }

    /**
     * 启用禁用
     * @return
     */
    @RequestMapping("/enableOrDisable")
    public Result enableOrDisable(@RequestBody JSONObject data){
        Long id=data.getLong("id");
        Integer status=data.getInteger("status");
        return earlywarningmodelService.enableOrDisable(id,status);
    }

    /**
     * 获取启用、停用记录
     * @param data
     * @return
     */
    @RequestMapping("/getEwModelRecord")
    public List<EarlyWarningModelDO> getEwModelRecord(@RequestBody JSONObject data) {
        String mocdelCode=data.getString("mocdelCode");
        return earlywarningmodelService.getEwModelRecord(mocdelCode);
    }


}
