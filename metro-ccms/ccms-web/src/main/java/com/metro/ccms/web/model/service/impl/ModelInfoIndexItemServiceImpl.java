package com.metro.ccms.web.model.service.impl;

import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;
import com.metro.ccms.web.model.mapper.ModelInfoIndexItemMapper;
import com.metro.ccms.web.model.service.ModelInfoIndexItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ModelInfoIndexItemServiceImpl implements ModelInfoIndexItemService {


	@Autowired
	private ModelInfoIndexItemMapper modelInfoIndexItemMapper;



	@Override
	public ModelInfoIndexItemDO get(Long id){
		return modelInfoIndexItemMapper.get(id);
	}
	
	@Override
	public List<ModelInfoIndexItemDO> list(Long modIndexId){
		return modelInfoIndexItemMapper.list(modIndexId);
	}
	

	
	@Override
	public int save(ModelInfoIndexItemDO modelInfoIndexItem){
		return modelInfoIndexItemMapper.save(modelInfoIndexItem);
	}
	
	@Override
	public int update(ModelInfoIndexItemDO modelInfoIndexItem){
		return modelInfoIndexItemMapper.update(modelInfoIndexItem);
	}
	
	@Override
	public int remove(Long id){
		return modelInfoIndexItemMapper.remove(id);
	}
	
}
