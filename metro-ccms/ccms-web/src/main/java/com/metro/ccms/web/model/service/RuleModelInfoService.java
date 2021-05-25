package com.metro.ccms.web.model.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.model.domain.RuleModelInfoDO;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;
import com.metro.ccms.web.model.query.RuleModelInfoQuery;

import java.util.List;

/**
 *<p>
 * 规则引擎模型
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public interface RuleModelInfoService {

	/**
	 * 根据id获取模型信息
	 * @param id
	 * @return
	 */
	RuleModelInfoDO get(Long id);

	/**
	 * 获取模型列表
	 * @param query
	 * @return
	 */
	List<RuleModelInfoDO> list(RuleModelInfoQuery query);

	/**
	 * 保存模型信息
	 * @param modelIndex
	 * @return
	 */
	Result save(RuleModelInfoDO modelIndex);
	/**
	 * 添加指标信息
	 * @param modelIndex
	 * @return
	 */
	Result addindex(RuleModelIndexInfoDO modelIndex);
	/**
	 * 查询指标信息
	 * @param modelIndex
	 * @return
	 */
	Result selindex(RuleModelIndexQuery modelIndex);

	/**
	 * 更新模型信息
	 * @param modelIndex
	 * @return
	 */
	Result update(RuleModelInfoDO modelIndex);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Result remove(Long id);
}
