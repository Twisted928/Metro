package com.metro.ccms.web.debtprotection.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.debtprotection.domian.GuaranteeDO;
import com.metro.ccms.web.debtprotection.domian.GuaranteeScopeDO;
import com.metro.ccms.web.debtprotection.query.GuaranteeQuery;
import com.metro.ccms.web.debtprotection.vo.GuaranteeVO;

import java.util.List;

/**
 * 担保函管理
 *
 * @author guangle
 * @date 2020/12/14
 * @since 1.0.0
 */
public interface GuaranteeLetterManagementService {
    /**
     * 担保函管理-查询
     *
     * @param guaranteeQuery 条件查询Query
     * @return List<GuaranteeDO>
     */
    List<GuaranteeDO> list(GuaranteeQuery guaranteeQuery);

    /**
     * 担保函管理-新增
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
    Result save(GuaranteeVO guaranteeVO);

    /**
     * 担保函管理-修改
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
    Result update(GuaranteeVO guaranteeVO);

    /**
     * 担保函管理-删除
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
    Result delete(GuaranteeVO guaranteeVO);

    /**
     * 担保函管理-详情
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return GuaranteeVO
     */
    GuaranteeVO getOne(GuaranteeVO guaranteeVO);
    /**
     * 卡片新增
     * @param guaranteeScopeDO 担保函范围表
     * @return Result
     */
    Result cardSave(GuaranteeScopeDO guaranteeScopeDO);
    /**
     * 卡片删除
     * @param guaranteeScopeDO 担保函范围表
     * @return Result
     */
    Result cardDelete(GuaranteeScopeDO guaranteeScopeDO);
}
