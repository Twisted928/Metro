package com.metro.ccms.web.model.vo;

import com.metro.ccms.web.model.domain.ModelInfoDO;
import com.metro.ccms.web.model.domain.ModelInfoIndexDO;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 15:57 2021/1/29
 * @Modified By:
 */
public class ModelInfoIndexVO implements Serializable {

    /**
     * 模型信息
     */
    private ModelInfoDO modelInfoDO;
    /**
     * 模型指标列表
     */
    private List<ModelInfoIndexDO> list;


    public ModelInfoDO getModelInfoDO() {
        return modelInfoDO;
    }

    public void setModelInfoDO(ModelInfoDO modelInfoDO) {
        this.modelInfoDO = modelInfoDO;
    }

    public List<ModelInfoIndexDO> getList() {
        return list;
    }

    public void setList(List<ModelInfoIndexDO> list) {
        this.list = list;
    }
}
