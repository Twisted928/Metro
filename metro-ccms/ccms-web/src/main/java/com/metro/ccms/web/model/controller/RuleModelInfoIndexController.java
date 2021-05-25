package com.metro.ccms.web.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.ModelInfoIndexDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.model.service.RuleModelInfoIndexService;
import com.metro.ccms.web.utils.domain.BasicDataDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *<p>
 * 中间表
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/29
 * @desc
 **/

@RestController
@RequestMapping("/rulemodel/modelInfoIndex")
public class RuleModelInfoIndexController extends BaseController {

    @Autowired
    private RuleModelInfoIndexService rulemodelInfoIndexService;


    /**
     * 根据模型id查询模型下的指标信息
     * @param data
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody JSONObject data) {
        //查询列表数据
        Long modId=data.getLong("modId");
        List<RuleModelIndexInfoDO> modelInfoIndexList = rulemodelInfoIndexService.list(modId);
        return Result.success(modelInfoIndexList);
    }
    /**
     * 查询有效客户类型
     * @param
     * @return
     */
    @PostMapping("/listkehu")
    public Result listkehu() {
        List<BasicDataDO> modelInfoIndexList = rulemodelInfoIndexService.listkehu();
        return Result.success(modelInfoIndexList);
    }

    /**
     * 保存
     */

    @PreAuthorize("@ss.hasPermi('rulemodel:modelInfoIndex:listkehu')")
    @PostMapping("/save")
    public Result save(@RequestBody RuleModelIndexInfoDO modelInfoIndex) {
        return rulemodelInfoIndexService.save(modelInfoIndex, modelInfoIndex.getItemList());
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('rulemodel:modelInfoIndex:remove')")
    @PostMapping("/remove")
    public Result remove(@RequestBody JSONObject data) {
        Long id=data.getLong("id");
        return rulemodelInfoIndexService.remove(id);
    }


}
