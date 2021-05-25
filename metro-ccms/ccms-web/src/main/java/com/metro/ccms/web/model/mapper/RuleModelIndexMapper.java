package com.metro.ccms.web.model.mapper;

import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *<p>
 * 规则引擎指标
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public interface RuleModelIndexMapper {


	/**
	 * 查询指标列表
	 * @param modelIndexQuery
	 * @return
	 */
	List<RuleModelIndexDO> list(RuleModelIndexQuery modelIndexQuery);

	/**
	 * 根据指标名称获取指标信息
	 * @param name
	 * @return
	 */
	RuleModelIndexDO getIndexByName(@Param("name") String name);

	/**
	 * 保存指标信息
	 * @param modelIndex
	 * @return
	 */
	int save(RuleModelIndexDO modelIndex);

	/**
	 * 更新指标信息
	 * @param modelIndex
	 * @return
	 */
	int update(RuleModelIndexDO modelIndex);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int remove(@Param("id") Long id);
	/**
	 * 查询指标是否在使用中
	 * @param id
	 * @return
	 */
	int selindex(@Param("id") Long id);

	/**
	 * 根据客户编码获取是否在黑名单中
	 * @param custCode
	 * @return
	 */
	int getCountForBlackByCustCode(@Param("custCode") String custCode);
}
