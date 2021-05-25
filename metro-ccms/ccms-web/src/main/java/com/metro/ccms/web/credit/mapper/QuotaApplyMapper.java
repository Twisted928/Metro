package com.metro.ccms.web.credit.mapper;

import com.metro.ccms.web.credit.domain.QuotaApplyDO;
import com.metro.ccms.web.credit.query.CustomerInterfaceQuery;
import com.metro.ccms.web.credit.query.QuotaApplyQuery;
import com.metro.ccms.web.httpsInterface.domain.CustomerInterfaceDO;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 16:30 2021/1/4
 * @Modified By:
 */
public interface QuotaApplyMapper {

    /**
     * 查询限额申请
     *
     * @param id 限额申请ID
     * @return 限额申请
     */
    public QuotaApplyDO selectTbCreditApplyById(Long id);

    /**
     * 查询限额申请列表
     *
     * @param quotaApplyQuery 限额申请
     * @return 限额申请集合
     */
    public List<QuotaApplyDO> selectTbCreditApplyList(QuotaApplyQuery quotaApplyQuery);

    /**
     * 新增限额申请
     *
     * @param quotaApplyDO 限额申请
     * @return 结果
     */
    public int insertTbCreditApply(QuotaApplyDO quotaApplyDO);

    /**
     * 修改限额申请
     *
     * @param quotaApplyDO 限额申请
     * @return 结果
     */
    public int updateTbCreditApply(QuotaApplyDO quotaApplyDO);

    /**
     * 删除限额申请
     *
     * @param id 限额申请ID
     * @return 结果
     */
    public int deleteTbCreditApplyById(Long id);

    /**
     * 批量删除限额申请
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbCreditApplyByIds(Long[] ids);

    /**
     * 查询客户信息接口表
     * @param customerInterfaceQuery
     * @return
     */
    public List<CustomerInterfaceDO> getInterfaceCustomerList(CustomerInterfaceQuery customerInterfaceQuery);

}
