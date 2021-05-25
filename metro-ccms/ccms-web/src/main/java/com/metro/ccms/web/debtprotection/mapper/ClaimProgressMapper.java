package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.domian.ClaimDO;
import com.metro.ccms.web.debtprotection.domian.ClaimProgressDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClaimProgressMapper {

    /**
     * 报损管理-新增
     *
     * @param claimProgressDO 报损进度表
     */
    void save(ClaimProgressDO claimProgressDO);

    /**
     * 报损管理-台账维护-新增
     *
     * @param claimProgressDO 报损管理组合VO
     */
    void insert(ClaimProgressDO claimProgressDO);

    /**
     * 获取今日是否维护报损单据信息
     *
     * @return int
     */
    int getMaxDate(ClaimProgressDO claimProgressDO);

    /**
     * 报损管理-台账维护-修改
     *
     * @param claimProgressDO 报损进度表DO
     */
    void update(ClaimProgressDO claimProgressDO);

    /**
     * 报损管理-删除
     *
     * @param claimProgressDO 报损进度表
     */
    void delete(ClaimProgressDO claimProgressDO);

    /**
     * 根据主表ID以及案件编号查询对应报损进度
     *
     * @param claimDO 报损申请表
     * @return List<ClaimProgressDO>
     */
    List<ClaimProgressDO> getList(ClaimDO claimDO);

    /**
     * 报损管理-台账维护-台账删除
     *
     * @param claimProgressDO 报损进度表DO
     */
    void ledgerDelete(ClaimProgressDO claimProgressDO);

    /**
     * 根据mianid查询获取最后一条状态
     *
     * @param mainId 主表ID
     * @return String
     */
    ClaimProgressDO getCaseStatus(Long mainId);

    /**
     * 判断是否维护过台账信息
     *
     * @param id 主表ID
     * @return int
     */
    int getByMainId(Long id);
}