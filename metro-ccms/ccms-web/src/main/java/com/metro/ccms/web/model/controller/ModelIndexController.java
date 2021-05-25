package com.metro.ccms.web.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.query.ModelIndexQuery;
import com.metro.ccms.web.model.service.ModelIndexService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 指标表
 *
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 * @date 2019-07-03 09:16:04
 */

@RestController
@RequestMapping("/model/index")
public class ModelIndexController extends BaseController {


    @Autowired
    private ModelIndexService modelIndexService;


    /**
     * 分页查询指标信息
     * @param query
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo list(ModelIndexQuery query) {
        startPage();
        List<ModelIndexDO> list = modelIndexService.list(query);
        return getDataTable(list);
    }

    /**
     * 导出
     * @param query
     * @return
     */
    @GetMapping("/export")
    public Result export(ModelIndexQuery query){
        List<ModelIndexDO> list = modelIndexService.list(query);
        ExcelUtil util=new ExcelUtil(ModelIndexDO.class);
        return util.exportExcel(list,"评估指标");
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody ModelIndexDO modelIndex) {
        if (modelIndex.getId() == null) {
            return modelIndexService.save(modelIndex);
        } else {
            return modelIndexService.update(modelIndex);
        }
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public Result remove(@RequestBody JSONObject data) {
        Long id=data.getLong("id");
        return modelIndexService.remove(id);
    }

}
