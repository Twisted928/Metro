package com.metro.ccms.web.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.ModelInfoIndexDO;
import com.metro.ccms.web.model.service.ModelInfoIndexService;
import com.metro.ccms.web.model.service.ModelInfoService;
import com.metro.ccms.web.model.vo.ModelInfoIndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 模型指标表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */

@RestController
@RequestMapping("/model/modelInfoIndex")
public class ModelInfoIndexController extends BaseController {

    @Autowired
    private ModelInfoIndexService modelInfoIndexService;
    @Autowired
    private ModelInfoService modelInfoService;


    /**
     * 根据模型id查询模型下的指标信息
     * @param data
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody JSONObject data) {
        ModelInfoIndexVO vo=new ModelInfoIndexVO();
        //查询列表数据
        Long modId=data.getLong("modId");
        List<ModelInfoIndexDO> modelInfoIndexList = modelInfoIndexService.list(modId);
        vo.setList(modelInfoIndexList);
        vo.setModelInfoDO(modelInfoService.get(modId));
        return Result.success(vo);
    }

    /**
     * 保存
     */

    @PostMapping("/save")
    public Result save(@RequestBody ModelInfoIndexDO modelInfoIndex) {
        return modelInfoIndexService.save(modelInfoIndex, modelInfoIndex.getItemList());
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public Result remove(@RequestBody JSONObject data) {
        Long id=data.getLong("id");
        return modelInfoIndexService.remove(id);
    }


}
