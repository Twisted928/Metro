package com.metro.ccms.web.model.mapper;


import com.metro.ccms.web.model.domain.RuleModelIndexInfoitemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *<p>
 *
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/29
 * @desc
 **/
public interface RuleModelInfoIndexItemMapper {

	/**
	 * 根据id获取模型指标元素信息
	 * @param id
	 * @return
	 */
	RuleModelIndexInfoitemDO get(@Param("id") Long id);

	/**
	 * 根据指标id获取模型指标元素信息
	 * @param modIndexId
	 * @return
	 */
	List<RuleModelIndexInfoitemDO> list(@Param("modIndexId") Long modIndexId);

	/**
	 * 根据模型id获取模型指标元素信息
	 * @param modId
	 * @return
	 */
	List<RuleModelIndexInfoitemDO> listByModId(@Param("modId") String modId);

	/**
	 * 保存
	 * @param modelInfoIndexItem
	 * @return
	 */
	int save(RuleModelIndexInfoitemDO modelInfoIndexItem);

	/**
	 * 批量保存
	 * @param list
	 * @return
	 */
	int batchSave(List<RuleModelIndexInfoitemDO> list);

	/**
	 * 更新
	 * @param modelInfoIndexItem
	 * @return
	 */
	int update(RuleModelIndexInfoitemDO modelInfoIndexItem);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int remove(@Param("id") Long id);
	
	int batchRemove(Integer[] ids);
}
