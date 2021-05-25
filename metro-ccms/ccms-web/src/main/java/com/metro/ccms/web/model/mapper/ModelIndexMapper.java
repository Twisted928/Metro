package com.metro.ccms.web.model.mapper;

import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.query.ModelIndexQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 指标表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 * @date 2019-07-03 09:16:04
 */
@Mapper
public interface ModelIndexMapper {

	/**
	 * 根据id获取指标信息
	 * @param id
	 * @return
	 */
	ModelIndexDO get(@Param("id") Long id);

	/**
	 * 查询指标列表
	 * @param modelIndexQuery
	 * @return
	 */
	List<ModelIndexDO> list(ModelIndexQuery modelIndexQuery);

	/**
	 * 根据指标名称获取指标信息
	 * @param name
	 * @return
	 */
	ModelIndexDO getIndexByName(@Param("name") String name);

	/**
	 * 保存指标信息
	 * @param modelIndex
	 * @return
	 */
	int save(ModelIndexDO modelIndex);

	/**
	 * 更新指标信息
	 * @param modelIndex
	 * @return
	 */
	int update(ModelIndexDO modelIndex);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int remove(@Param("id") Long id);
}
