package com.metro.ccms.web.collection.vo;

import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.web.collection.domian.ReconDetailsDO;
import com.metro.ccms.web.collection.domian.ReconRecordDO;

import java.io.Serializable;
import java.util.List;
/**
* @description 对账详情页显示类
* @author weiwenhui
* @date 2021/02/06 14:41
*/
public class ReconAndDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 对账基本信息
     */
    private ReconciliationVO reconciliationVO;
    /**
     * 对账明细
     */
    private List<ReconDetailsDO> reconDetailsDOS;
    /**
     * 对账台账记录
     */
    private List<ReconRecordDO> reconRecordDOS;
    /**
     * 获取对账附件信息
     */
    private List<SysBasicFile> sysBasicFile;
    /**
     * 对账修改时删除的台账ID集合
     */
    private List<String> deleteId;

    public ReconciliationVO getReconciliationVO() {
        return reconciliationVO;
    }

    public void setReconciliationVO(ReconciliationVO reconciliationVO) {
        this.reconciliationVO = reconciliationVO;
    }

    public List<ReconDetailsDO> getReconDetailsDOS() {
        return reconDetailsDOS;
    }

    public void setReconDetailsDOS(List<ReconDetailsDO> reconDetailsDOS) {
        this.reconDetailsDOS = reconDetailsDOS;
    }

    public List<ReconRecordDO> getReconRecordDOS() {
        return reconRecordDOS;
    }

    public void setReconRecordDOS(List<ReconRecordDO> reconRecordDOS) {
        this.reconRecordDOS = reconRecordDOS;
    }

    public List<SysBasicFile> getSysBasicFile() {
        return sysBasicFile;
    }

    public void setSysBasicFile(List<SysBasicFile> sysBasicFile) {
        this.sysBasicFile = sysBasicFile;
    }

    public List<String> getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(List<String> deleteId) {
        this.deleteId = deleteId;
    }

}
