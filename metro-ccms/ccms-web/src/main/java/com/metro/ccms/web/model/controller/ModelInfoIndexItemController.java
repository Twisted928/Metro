package com.metro.ccms.web.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;
import com.metro.ccms.web.model.service.ModelInfoIndexItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模型指标元素表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
 
@RestController
@RequestMapping("/model/modelInfoIndexItem")
public class ModelInfoIndexItemController extends BaseController {

	@Autowired
	private ModelInfoIndexItemService modelInfoIndexItemService;


	@PostMapping("/list")
	public Result list(@RequestBody JSONObject data){
		//查询列表数据
		Long modIndexItemId=data.getLong("modIndexItemId");
		List<ModelInfoIndexItemDO> modelInfoIndexItemList = modelInfoIndexItemService.list(modIndexItemId);
		return Result.success(modelInfoIndexItemList);
	}

	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	public Result save(@RequestBody ModelInfoIndexItemDO modelInfoIndexItem){
		if(modelInfoIndexItemService.save(modelInfoIndexItem)>0){
			return Result.success();
		}
		return Result.error();
	}
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody ModelInfoIndexItemDO modelInfoIndexItem){
		modelInfoIndexItemService.update(modelInfoIndexItem);
		return Result.success();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	public Result remove(@RequestBody JSONObject data){
		Long id=data.getLong("id");
		if(modelInfoIndexItemService.remove(id)>0){
			return Result.success();
		}
		return Result.error();
	}
	
}
