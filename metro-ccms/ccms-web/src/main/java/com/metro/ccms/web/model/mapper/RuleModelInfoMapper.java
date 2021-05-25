package com.metro.ccms.web.model.mapper;

import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.model.domain.RuleModelInfoDO;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;
import com.metro.ccms.web.model.query.RuleModelInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *<p>
 * 规则引擎管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public interface RuleModelInfoMapper {

	/**
	 * 根据id获取信息
	 * @param id
	 * @return
	 */
	RuleModelInfoDO get(@Param("id") Long id);

	/**
	 * 查询列表
	 * @param modelInfoQuery
	 * @return
	 */
	List<RuleModelInfoDO> list(RuleModelInfoQuery modelInfoQuery);

	/**
	 * 保存信息
	 * @param modelInfo
	 * @return
	 */
	int save(RuleModelInfoDO modelInfo);
	/**
	 * 查询指标信息
	 * @param modelInfo
	 * @return
	 */
	List<RuleModelIndexInfoDO> selindex(RuleModelIndexQuery modelInfo);
	/**
	 * 添加指标信息
	 * @param modelInfo
	 * @return
	 */
	int addindex(RuleModelIndexInfoDO modelInfo);

	/**
	 * 更新信息
	 * @param modelInfo
	 * @return
	 */
	int update(RuleModelInfoDO modelInfo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int remove(@Param("id") Long id);
}
