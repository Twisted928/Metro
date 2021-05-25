package com.metro.ccms.web.collection.vo;

import com.metro.ccms.web.collection.domian.CollectionDetailDO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @description PDF催收函显示类
* @author weiwenhui
* @date 2021/01/07 09:26
*/
public class CollectionletterVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private CollectionVO collectionVO;

    private List<CollectionDetailDO> collectionDetailDO;

    private List<Date> fdate;

    public CollectionVO getCollectionVO() {
        return collectionVO;
    }

    public void setCollectionVO(CollectionVO collectionVO) {
        this.collectionVO = collectionVO;
    }

    public List<CollectionDetailDO> getCollectionDetailDO() {
        return collectionDetailDO;
    }

    public void setCollectionDetailDO(List<CollectionDetailDO> collectionDetailDO) {
        this.collectionDetailDO = collectionDetailDO;
    }

    public List<Date> getFdate() {
        return fdate;
    }

    public void setFdate(List<Date> fdate) {
        this.fdate = fdate;
    }
}
