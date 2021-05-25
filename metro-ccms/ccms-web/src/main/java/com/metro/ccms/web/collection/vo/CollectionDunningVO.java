package com.metro.ccms.web.collection.vo;

import com.metro.ccms.web.collection.domian.CollectionDO;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;

import java.io.Serializable;
import java.util.List;

/**
* @description 财务明细接口表查询范围返回给页面
* @author weiwenhui
* @date 2021/01/26 13:39
*/
public class CollectionDunningVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<BsegInterfaceDO> bsegInterfaceDOList;

    private CollectionDO collectionDO;

    public CollectionDO getCollectionDO() {
        return collectionDO;
    }

    public void setCollectionDO(CollectionDO collectionDO) {
        this.collectionDO = collectionDO;
    }

    public List<BsegInterfaceDO> getBsegInterfaceDOList() {
        return bsegInterfaceDOList;
    }

    public void setBsegInterfaceDOList(List<BsegInterfaceDO> bsegInterfaceDOList) {
        this.bsegInterfaceDOList = bsegInterfaceDOList;
    }
}
