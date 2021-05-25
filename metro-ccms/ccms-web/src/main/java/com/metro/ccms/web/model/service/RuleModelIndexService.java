package com.metro.ccms.web.model.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.query.ModelIndexQuery;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;

import java.util.List;

/**
 *<p>
 * 规则引擎指标
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public interface RuleModelIndexService {

	/**
	 * 信用准入流程校验
	 * 客户编码、卡号编码、门店编码
	 * @param custCode,cardCode,storeCode
	 * @return
	 */
	Result creditaccess(String custCode,String cardCode,String storeCode);
	/**
	 * 保险准入流程校验
	 * 客户编码、卡号编码、门店编码
	 * @param custCode,cardCode,storeCode
	 * @return
	 */
	Result insuranceaccess(String custCode,String cardCode,String storeCode);
	/**
	 * 获取指标列表
	 * @param query
	 * @return
	 */
	List<RuleModelIndexDO> list(RuleModelIndexQuery query);

	/**
	 * 保存指标信息
	 * @param modelIndex
	 * @return
	 */
	Result save(RuleModelIndexDO modelIndex);

	/**
	 * 更新指标信息
	 * @param modelIndex
	 * @return
	 */
	Result update(RuleModelIndexDO modelIndex);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Result remove(Long id);

}
