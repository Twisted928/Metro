package com.metro.ccms.web.model.controller;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoitemDO;
import com.metro.ccms.web.model.service.RuleModelInfoIndexItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *<p>
 *规则管理指标元素表
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/29
 * @desc
 **/
 
@RestController
@RequestMapping("/rulemodel/modelInfoIndexItem")
public class RuleModelInfoIndexItemController extends BaseController {

	@Autowired
	private RuleModelInfoIndexItemService ruleModelInfoIndexItemService;


	@PostMapping("/list")
	public Result list(@RequestBody JSONObject data){
		//查询列表数据
		Long modIndexItemId=data.getLong("modIndexItemId");
		List<RuleModelIndexInfoitemDO> modelInfoIndexItemList = ruleModelInfoIndexItemService.list(modIndexItemId);
		return Result.success(modelInfoIndexItemList);
	}

	
//	/**
//	 * 保存
//	 */
//	@PostMapping("/save")
//	public Result save(@RequestBody RuleModelIndexInfoitemDO modelInfoIndexItem){
//		if(ruleModelInfoIndexItemService.save(modelInfoIndexItem)>0){
//			return Result.success();
//		}
//		return Result.error();
//	}
//	/**
//	 * 修改
//	 */
//	@RequestMapping("/update")
//	public Result update(@RequestBody RuleModelIndexInfoitemDO modelInfoIndexItem){
//		ruleModelInfoIndexItemService.update(modelInfoIndexItem);
//		return Result.success();
//	}
//
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	public Result remove(@RequestBody JSONObject data){
//		Long id=data.getLong("id");
//		if(ruleModelInfoIndexItemService.remove(id)>0){
//			return Result.success();
//		}
//		return Result.error();
//	}
	
}
