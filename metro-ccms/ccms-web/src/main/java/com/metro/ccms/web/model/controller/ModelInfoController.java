package com.metro.ccms.web.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.domain.ModelInfoDO;
import com.metro.ccms.web.model.domain.ModelResultMainDO;
import com.metro.ccms.web.model.query.ModelQuery;
import com.metro.ccms.web.model.service.ModelInfoService;
import com.metro.ccms.web.model.service.impl.ModelInfoResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 模型信息表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */

@RestController
@RequestMapping("/model/info")
public class ModelInfoController extends BaseController {

    @Autowired
    private ModelInfoService modelInfoService;
    @Autowired
    private ModelInfoResultServiceImpl modelInfoResultService;


    /**
     * 分页查询
     * @param query
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(ModelQuery query) {
        startPage();
        List<ModelInfoDO> list = modelInfoService.list(query);
        return getDataTable(list);
    }

    /**
     * 导出
     */
    @GetMapping("/export")
    public Result export(ModelQuery query){
        List<ModelInfoDO> list = modelInfoService.list(query);
        ExcelUtil util=new ExcelUtil(ModelInfoDO.class);
        return util.exportExcel(list,"评估模型");
    }

    @PostMapping("/listByScope")
    public Result listByScope(@RequestBody ModelQuery query) {
        //查询列表数据
        query.setPublish("1");
        List<ModelInfoDO> list = modelInfoService.list(query);
        return Result.success(list);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody ModelInfoDO modelInfo) {

        if (modelInfo.getId() == null) {
            return modelInfoService.save(modelInfo);
        } else {
            return modelInfoService.update(modelInfo);
        }
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public Result remove(@RequestBody JSONObject data) {
        Long id=data.getLong("id");
        return modelInfoService.remove(id);
    }

    /**
     * 发布
     */
    @PostMapping("/push")
    public Result push(@RequestBody JSONObject data) {
        Long id=data.getLong("id");
        return modelInfoService.push(id);
    }

    /**
     * 撤销发布
     * @param data
     * @return
     */
    @RequestMapping("/reBackPush")
    public Result reBackPush(@RequestBody JSONObject data){
        Long id=data.getLong("id");
        return modelInfoService.reBackPush(id);
    }

    /**
     * 根据模型id获取模型、指标、加减分项详细信息
     * @param data
     * @return
     */
    @GetMapping("/getMod")
    public JSONObject getMod(@RequestBody JSONObject data) {
        Long modId=data.getLong("modId");
        return modelInfoService.getMod(modId);
    }

    /**
     * 试算/提交评估模型
     * @param modelResultMainDO
     * @return
     */
    @PostMapping("/getCurrentResult")
    public JSONObject getCurrentResult(@RequestBody ModelResultMainDO modelResultMainDO) {

        return modelInfoResultService.getCurrentResult(modelResultMainDO, modelResultMainDO.getMap());
    }


    /**
     * 获取上一次模型信息，根据单号documentNo
     * @return
     */
    @PostMapping("/getLastResult")
    public JSONObject getLastResult(@RequestBody JSONObject data) {
        String documentNo=data.getString("documentNo");
        return modelInfoResultService.getLastResult(documentNo);
    }

}
