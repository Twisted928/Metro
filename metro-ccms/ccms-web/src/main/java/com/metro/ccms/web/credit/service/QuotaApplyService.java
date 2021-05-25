package com.metro.ccms.web.credit.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.credit.bo.QuotaAddApplyBO;
import com.metro.ccms.web.credit.domain.CustMembersDO;
import com.metro.ccms.web.credit.domain.QuotaApplyDO;
import com.metro.ccms.web.credit.query.CustomerInterfaceQuery;
import com.metro.ccms.web.credit.query.QuotaApplyQuery;
import com.metro.ccms.web.httpsInterface.domain.CustomerInterfaceDO;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 限额申请接口
 * @Date: Created in 13:20 2021/1/4
 * @Modified By:
 */
public interface QuotaApplyService {

    /**
     * 查询限额申请列表
     * @param quotaApplyQuery
     * @return
     */
    public List<QuotaApplyDO> getQuotaApplyList(QuotaApplyQuery quotaApplyQuery);

    /**
     * 获取客户信息接口表列表（限额申请新增按钮查询）
     * @param customerInterfaceQuery
     * @return
     */
    public List<CustomerInterfaceDO> getCustInterfaceList(CustomerInterfaceQuery customerInterfaceQuery);

    /**
     * 新增按钮-查询基本信息卡号信息
     * @param quotaAddApplyBO
     * @return
     */
    public Result addBasicInfoInquiries(QuotaAddApplyBO quotaAddApplyBO);
}
