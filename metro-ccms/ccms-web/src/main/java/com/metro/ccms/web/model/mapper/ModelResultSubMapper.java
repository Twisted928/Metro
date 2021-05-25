package com.metro.ccms.web.model.mapper;

import com.metro.ccms.web.model.domain.ModelResultSubDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 模型测算结果指标明细表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
@Mapper
public interface ModelResultSubMapper {

	List<ModelResultSubDO> get(Long mainId);

	int batchSave(List<ModelResultSubDO> list);
}
