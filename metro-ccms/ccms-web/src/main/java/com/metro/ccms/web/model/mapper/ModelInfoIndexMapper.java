package com.metro.ccms.web.model.mapper;

import com.metro.ccms.web.model.domain.ModelInfoIndexDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 模型指标表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
@Mapper
public interface ModelInfoIndexMapper {

	ModelInfoIndexDO get(@Param("id") Long id);
	
	List<ModelInfoIndexDO> list(@Param("modId") Long modId);
	
	int save(ModelInfoIndexDO modelInfoIndex);
	
	int update(ModelInfoIndexDO modelInfoIndex);
	
	int remove(@Param("id") Long id);
	
	int batchRemove(Integer[] ids);

	BigDecimal getSumWeightByModelId(@Param("modelId") Long modelId);
}
