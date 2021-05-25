package com.metro.ccms.web.model.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.query.ModelIndexQuery;
import java.util.List;

/**
 * 指标表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
public interface ModelIndexService {

	/**
	 * 根据id获取指标信息
	 * @param id
	 * @return
	 */
	ModelIndexDO get(Long id);

	/**
	 * 获取指标列表
	 * @param query
	 * @return
	 */
	List<ModelIndexDO> list(ModelIndexQuery query);

	/**
	 * 保存指标信息
	 * @param modelIndex
	 * @return
	 */
	Result save(ModelIndexDO modelIndex);

	/**
	 * 更新指标信息
	 * @param modelIndex
	 * @return
	 */
	Result update(ModelIndexDO modelIndex);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Result remove(Long id);
}
