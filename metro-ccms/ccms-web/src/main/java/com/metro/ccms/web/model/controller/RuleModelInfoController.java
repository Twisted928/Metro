package com.metro.ccms.web.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.model.domain.RuleModelInfoDO;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;
import com.metro.ccms.web.model.query.RuleModelInfoQuery;
import com.metro.ccms.web.model.service.RuleModelIndexService;
import com.metro.ccms.web.model.service.RuleModelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *<p>
 * 规则引擎管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/

@RestController
@RequestMapping("/rulemodel/info")
public class RuleModelInfoController extends BaseController {


    @Autowired
    private RuleModelInfoService ruleModelInfoService;


    /**
     * 分页查询信息
     * @param query
     * @return
     */
    @PreAuthorize("@ss.hasPermi('rulemodel:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(RuleModelInfoQuery query) {
        startPage();
        List<RuleModelInfoDO> list = ruleModelInfoService.list(query);
        return getDataTable(list);
    }
    /**
     * 查询信息
     * @param data
     * @return
     */
    @PostMapping("/getbyid")
    public Result get(@RequestBody JSONObject data) {
        Long id=data.getLong("id");
        RuleModelInfoDO list = ruleModelInfoService.get(id);
        return Result.success(list);
    }

    /**
     * 保存
     */
    @PreAuthorize("@ss.hasPermi('rulemodel:info:save')")
    @PostMapping("/save")
    public Result save(@RequestBody RuleModelInfoDO modelIndex) {
        if (modelIndex.getId() == null) {
            return ruleModelInfoService.save(modelIndex);
        } else {
            return ruleModelInfoService.update(modelIndex);
        }
    }
    /**
     * 查询指标中间表
     * 用不到
     */
    @PostMapping("/selindex")
    public Result selindex(@RequestBody RuleModelIndexQuery modelIndex) {
        return ruleModelInfoService.selindex(modelIndex);
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('rulemodel:info:remove')")
    @PostMapping("/remove")
    public Result remove(@RequestBody JSONObject data) {
        Long id=data.getLong("id");
        return ruleModelInfoService.remove(id);
    }
    /**
     * 添加指标
     */
//    @PostMapping("/addindex")
//    public Result addindex(@RequestBody RuleModelIndexInfoDO modelIndex) {
//        return ruleModelInfoService.addindex(modelIndex);
//    }



}
