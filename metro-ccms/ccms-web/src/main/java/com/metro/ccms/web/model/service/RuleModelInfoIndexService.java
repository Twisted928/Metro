package com.metro.ccms.web.model.service;


import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.ModelInfoIndexDO;
import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoitemDO;
import com.metro.ccms.web.utils.domain.BasicDataDO;

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
public interface RuleModelInfoIndexService {

	/**
	 * 根据id获取模型指标关系表信息
	 * @param id
	 * @return
	 */
	RuleModelIndexInfoDO get(Long id);
	/**
	 * 根据模型id查询模型下的指标信息
	 * @param modId
	 * @return
	 */
	List<RuleModelIndexInfoDO> list(Long modId);
	/**
	 * 查询客户类型
	 * @param
	 * @return
	 */
	List<BasicDataDO> listkehu();
	/**
	 * 保存模型指标元素表
	 * @param modelInfoIndex
	 * @param itemList
	 * @return
	 */
	Result save(RuleModelIndexInfoDO modelInfoIndex, List<RuleModelIndexInfoitemDO> itemList);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Result remove(Long id);

}
