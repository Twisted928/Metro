package com.metro.ccms.web.collection.vo;

import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.web.collection.domian.CollectionDO;
import com.metro.ccms.web.collection.domian.CollectionDetailDO;
import com.metro.ccms.web.collection.domian.CollectionRecordDO;

import java.io.Serializable;
import java.util.List;

/**
* @description
* @author weiwenhui
* @date 2020/12/11 17:54
*/
public class CollectionAndDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private CollectionVO collectionVO;

    private List<CollectionDetailDO> collectionDetailDO;

    private List<CollectionRecordDO> collectionRecordDO;

    private List<SysBasicFile> sysBasicFile;

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

    public List<CollectionRecordDO> getCollectionRecordDO() {
        return collectionRecordDO;
    }

    public void setCollectionRecordDO(List<CollectionRecordDO> collectionRecordDO) {
        this.collectionRecordDO = collectionRecordDO;
    }

    public List<SysBasicFile> getSysBasicFile() {
        return sysBasicFile;
    }

    public void setSysBasicFile(List<SysBasicFile> sysBasicFile) {
        this.sysBasicFile = sysBasicFile;
    }

    @Override
    public String toString() {
        return "CollectionAndDetailVO{" +
                "collectionVO=" + collectionVO +
                ", collectionDetailDO=" + collectionDetailDO +
                ", collectionRecordDO=" + collectionRecordDO +
                ", sysBasicFile=" + sysBasicFile +
                '}';
    }
}
