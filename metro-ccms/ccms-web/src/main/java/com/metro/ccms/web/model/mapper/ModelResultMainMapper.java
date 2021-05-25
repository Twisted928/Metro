package com.metro.ccms.web.model.mapper;

import com.metro.ccms.web.model.domain.ModelResultMainDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 模型测算结果表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
@Mapper
public interface ModelResultMainMapper {

	ModelResultMainDO get(@Param("id") Long id);

	ModelResultMainDO getLastResult(@Param("documentNo") String documentNo);
	
	List<ModelResultMainDO> list(Map<String, Object> map);

	int save(ModelResultMainDO modelResultMain);

}
