package com.metro.ccms.web.earlywarning.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelConfigDO;
import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelDO;
import com.metro.ccms.web.earlywarning.query.EarlyWarningModelQuery;
import com.metro.ccms.web.earlywarning.vo.EarlyWarningModelVO;
import io.swagger.models.auth.In;

import java.util.List;

/**
 *<p>
 *  预警模型
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/8
 * @desc
 **/
public interface EarlyWarningModelService {
    /**
     * 预警模型-查询
     * @param earlywarningmodelQuery
     * @return
     */
    List<EarlyWarningModelDO> pagesel(EarlyWarningModelQuery earlywarningmodelQuery);
    /**
     * 预警模型-查询
     * @param mocdelCode
     * @return
     */
    EarlyWarningModelVO detailed(String mocdelCode);

    /**
     * 保存预警模型配置信息
     * @param earlyWarningModelVO
     * @return
     */
    Result saveEarlyModelConfig(EarlyWarningModelVO earlyWarningModelVO);

    /**
     * 启用禁用
     * @param id
     * @param status
     * @return
     */
    Result enableOrDisable(Long id,Integer status);

    /**
     * 获取启用、停用记录
     * @param mocdelCode
     * @return
     */
    List<EarlyWarningModelDO> getEwModelRecord(String mocdelCode);

}
