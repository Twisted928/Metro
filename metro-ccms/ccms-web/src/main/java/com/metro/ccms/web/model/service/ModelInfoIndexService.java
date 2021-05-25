package com.metro.ccms.web.model.service;


import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.ModelInfoIndexDO;
import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;

import java.util.List;

/**
 * 模型指标表
 * 
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 * @date 2019-07-03 09:16:04
 */
public interface ModelInfoIndexService {

	/**
	 * 根据id获取模型指标关系表信息
	 * @param id
	 * @return
	 */
	ModelInfoIndexDO get(Long id);
	/**
	 * 根据模型id查询模型下的指标信息
	 * @param modId
	 * @return
	 */
	List<ModelInfoIndexDO> list(Long modId);
	/**
	 * 保存模型指标元素表
	 * @param modelInfoIndex
	 * @param itemList
	 * @return
	 */
	Result save(ModelInfoIndexDO modelInfoIndex, List<ModelInfoIndexItemDO> itemList);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Result remove(Long id);

}
