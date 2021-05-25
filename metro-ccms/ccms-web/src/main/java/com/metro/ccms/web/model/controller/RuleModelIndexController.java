package com.metro.ccms.web.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.query.ModelIndexQuery;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;
import com.metro.ccms.web.model.service.ModelIndexService;
import com.metro.ccms.web.model.service.RuleModelIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *<p>
 * 规则引擎指标
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/

@RestController
@RequestMapping("/rulemodel/index")
public class RuleModelIndexController extends BaseController {


    @Autowired
    private RuleModelIndexService ruleModelIndexService;


    /**
     * 分页查询指标信息
     * @param query
     * @return
     */
    @PreAuthorize("@ss.hasPermi('rulemodel:index:list')")
    @GetMapping("/list")
    public TableDataInfo list(RuleModelIndexQuery query) {
        startPage();
        List<RuleModelIndexDO> list = ruleModelIndexService.list(query);
        return getDataTable(list);
    }

    /**
     * 保存
     */
    @PreAuthorize("@ss.hasPermi('rulemodel:index:save')")
    @PostMapping("/save")
    public Result save(@RequestBody RuleModelIndexDO modelIndex) {
        if (modelIndex.getId() == null) {
            return ruleModelIndexService.save(modelIndex);
        } else {
            return ruleModelIndexService.update(modelIndex);
        }
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('rulemodel:index:remove')")
    @PostMapping("/remove")
    public Result remove(@RequestBody JSONObject data) {
        Long id=data.getLong("id");
        return ruleModelIndexService.remove(id);
    }

}
