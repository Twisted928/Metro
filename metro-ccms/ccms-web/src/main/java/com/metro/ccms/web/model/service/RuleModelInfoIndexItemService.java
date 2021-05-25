package com.metro.ccms.web.model.service;


import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoitemDO;

import java.util.List;

/**
 *<p>
 * 规则指标管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/29
 * @desc
 **/
public interface RuleModelInfoIndexItemService {

	RuleModelIndexInfoitemDO get(Long id);
	
	List<RuleModelIndexInfoitemDO> list(Long modIndexId);

	int save(RuleModelIndexInfoitemDO modelInfoIndexItem);
	
	int update(RuleModelIndexInfoitemDO modelInfoIndexItem);
	
	int remove(Long id);
}
