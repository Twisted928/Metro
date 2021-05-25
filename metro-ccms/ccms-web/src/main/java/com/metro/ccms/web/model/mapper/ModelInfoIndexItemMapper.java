package com.metro.ccms.web.model.mapper;


import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 模型指标元素表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
@Mapper
public interface ModelInfoIndexItemMapper {

	/**
	 * 根据id获取模型指标元素信息
	 * @param id
	 * @return
	 */
	ModelInfoIndexItemDO get(@Param("id") Long id);

	/**
	 * 根据指标id获取模型指标元素信息
	 * @param modIndexId
	 * @return
	 */
	List<ModelInfoIndexItemDO> list(@Param("modIndexId") Long modIndexId);

	/**
	 * 根据模型id获取模型指标元素信息
	 * @param modId
	 * @return
	 */
	List<ModelInfoIndexItemDO> listByModId(@Param("modId") String modId);

	/**
	 * 保存
	 * @param modelInfoIndexItem
	 * @return
	 */
	int save(ModelInfoIndexItemDO modelInfoIndexItem);

	/**
	 * 批量保存
	 * @param list
	 * @return
	 */
	int batchSave(List<ModelInfoIndexItemDO> list);

	/**
	 * 更新
	 * @param modelInfoIndexItem
	 * @return
	 */
	int update(ModelInfoIndexItemDO modelInfoIndexItem);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int remove(@Param("id") Long id);
	
	int batchRemove(Integer[] ids);

	int getItemByInfoIndexId(@Param("modIndexId") Long modIndexId);
}
