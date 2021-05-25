package com.metro.ccms.web.earlywarning.vo;


import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelConfigDO;
import com.metro.ccms.web.earlywarning.domain.EarlyWarningModelDO;

import java.util.ArrayList;
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
public class EarlyWarningModelVO {

    /**
     * 模型信息
     */
    private EarlyWarningModelDO modelDO;
    /**
     * 模型配置信息
     */
    private List<EarlyWarningModelConfigDO> configDO=new ArrayList<>();
    /**
     * 配置表id集合
     */
    private List<Long> configIds;


    public EarlyWarningModelDO getModelDO() {
        return modelDO;
    }

    public void setModelDO(EarlyWarningModelDO modelDO) {
        this.modelDO = modelDO;
    }

    public List<EarlyWarningModelConfigDO> getConfigDO() {
        return configDO;
    }

    public void setConfigDO(List<EarlyWarningModelConfigDO> configDO) {
        this.configDO = configDO;
    }

    public List<Long> getConfigIds() {
        return configIds;
    }

    public void setConfigIds(List<Long> configIds) {
        this.configIds = configIds;
    }
}