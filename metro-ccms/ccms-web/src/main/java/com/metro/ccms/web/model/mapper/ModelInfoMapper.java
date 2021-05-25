package com.metro.ccms.web.model.mapper;

import com.metro.ccms.web.model.domain.ModelInfoDO;
import com.metro.ccms.web.model.query.ModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 模型信息表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
@Mapper
public interface ModelInfoMapper {

	/**
	 * 根据id获取模型信息
	 * @param id
	 * @return
	 */
	ModelInfoDO get(@Param("id") Long id);

	/**
	 * 查询模型信息列表
	 * @param modelQuery
	 * @return
	 */
	List<ModelInfoDO> list(ModelQuery modelQuery);

	/**
	 * 保存模型信息
	 * @param modelInfo
	 * @return
	 */
	int save(ModelInfoDO modelInfo);

	/**
	 * 修改模型信息
	 * @param modelInfo
	 * @return
	 */
	int update(ModelInfoDO modelInfo);

	/**
	 * 根据id删除模型信息
	 * @param id
	 * @return
	 */
	int remove(@Param("id") Long id);
}
