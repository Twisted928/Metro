package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.domian.ClaimDO;
import com.metro.ccms.web.debtprotection.domian.ClaimProgressDO;
import com.metro.ccms.web.debtprotection.query.ClaimQuery;
import com.metro.ccms.web.debtprotection.vo.LossPortfolioVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClaimMapper {

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return ClaimDO
     */
    ClaimDO selectByPrimaryKey(Long id);

    /**
     * 报损管理-查询
     *
     * @param claimQuery 报损管理查询类
     * @return List<ClaimDO>
     */
    List<ClaimDO> list(ClaimQuery claimQuery);

    /**
     * 报损管理-新增
     *
     * @param lossPortfolioVO 报损管理组合VO
     */
    void save(LossPortfolioVO lossPortfolioVO);

    /**
     * 报损管理-删除
     *
     * @param claimProgressDO 报损进度表
     */
    void delete(ClaimProgressDO claimProgressDO);

    /**
     * 获取最大ID
     *
     * @return String
     */
    Long getMaxId();

    /**
     * 根据ID修改案件状态
     *
     * @param lossPortfolioVO VO
     */
    void update(LossPortfolioVO lossPortfolioVO);

    /**
     * 根据已投保表ID查询数据库
     * @param insureId 已投保表ID
     * @return int
     */
    int getByInsureID(String insureId);

    /**
     * 查询报损表维护了哪些已投保保单
     * @return id
     */
    List<Long> listId();
}