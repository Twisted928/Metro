package com.metro.ccms.web.model.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.ModelInfoDO;
import com.metro.ccms.web.model.query.ModelQuery;

import java.util.List;

/**
 * 模型信息表
 * 
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 * @date 2019-07-03 09:16:04
 */
public interface ModelInfoService {

	/**
	 * 根据id获取模型信息
	 * @param id
	 * @return
	 */
	ModelInfoDO get(Long id);

	/**
	 * 查询模型信息列表
	 * @param query
	 * @return
	 */
	List<ModelInfoDO> list(ModelQuery query);

	/**
	 * 保存模型信息
	 * @param modelInfo
	 * @return
	 */
	Result save(ModelInfoDO modelInfo);

	/**
	 * 修改模型信息
	 * @param modelInfo
	 * @return
	 */
	Result update(ModelInfoDO modelInfo);

	/**
	 * 根据id删除模型信息
	 * @param id
	 * @return
	 */
	Result remove(Long id);

	/**
	 * 发布模型
	 * @param id
	 * @return
	 */
	Result push(Long id);

	/**
	 * 撤销发布
	 * @param id
	 * @return
	 */
	Result reBackPush(Long id);

	/**
	 * 根据模型id获取模型、指标、加减分项详细信息
	 * @param id
	 * @return
	 */
	JSONObject getMod(Long id);

}
