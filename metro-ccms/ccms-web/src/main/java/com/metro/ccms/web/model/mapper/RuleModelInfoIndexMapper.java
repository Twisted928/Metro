package com.metro.ccms.web.model.mapper;

import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.utils.domain.BasicDataDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *<p>
 *	中间表
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/29
 * @desc
 **/
@Mapper
public interface RuleModelInfoIndexMapper {

	RuleModelIndexInfoDO get(@Param("id") Long id);
	
	List<RuleModelIndexInfoDO> list(@Param("modId") Long modId);

	List<BasicDataDO> listkehu();

	int save(RuleModelIndexInfoDO modelInfoIndex);
	
	int update(RuleModelIndexInfoDO modelInfoIndex);
	
	int remove(@Param("id") Long id);
	
	int batchRemove(Integer[] ids);
}
