package com.metro.ccms.web.model.service;


import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;

import java.util.List;

/**
 * 模型指标元素表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
public interface ModelInfoIndexItemService {

	ModelInfoIndexItemDO get(Long id);
	
	List<ModelInfoIndexItemDO> list(Long modIndexId);

	int save(ModelInfoIndexItemDO modelInfoIndexItem);
	
	int update(ModelInfoIndexItemDO modelInfoIndexItem);
	
	int remove(Long id);
}
