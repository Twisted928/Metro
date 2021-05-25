package com.metro.ccms.web.model.service.impl;

import com.metro.ccms.web.model.domain.RuleModelIndexInfoitemDO;
import com.metro.ccms.web.model.mapper.RuleModelInfoIndexItemMapper;
import com.metro.ccms.web.model.service.RuleModelInfoIndexItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/29
 * @desc
 **/
@Service
public class RuleModelInfoIndexItemServiceImpl implements RuleModelInfoIndexItemService {


	@Autowired
	private RuleModelInfoIndexItemMapper ruleModelInfoIndexItemMapper;



	@Override
	public RuleModelIndexInfoitemDO get(Long id){
		return ruleModelInfoIndexItemMapper.get(id);
	}
	
	@Override
	public List<RuleModelIndexInfoitemDO> list(Long modIndexId){
		return ruleModelInfoIndexItemMapper.list(modIndexId);
	}
	

	
	@Override
	public int save(RuleModelIndexInfoitemDO modelInfoIndexItem){
		return ruleModelInfoIndexItemMapper.save(modelInfoIndexItem);
	}
	
	@Override
	public int update(RuleModelIndexInfoitemDO modelInfoIndexItem){
		return ruleModelInfoIndexItemMapper.update(modelInfoIndexItem);
	}
	
	@Override
	public int remove(Long id){
		return ruleModelInfoIndexItemMapper.remove(id);
	}
	
}
