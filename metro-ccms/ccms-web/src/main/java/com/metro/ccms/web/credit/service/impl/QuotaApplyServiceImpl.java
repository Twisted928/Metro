package com.metro.ccms.web.credit.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.system.mapper.SysDeptMapper;
import com.metro.ccms.web.credit.bo.QuotaAddApplyBO;
import com.metro.ccms.web.credit.domain.CustMembersDO;
import com.metro.ccms.web.credit.domain.QuotaApplyDO;
import com.metro.ccms.web.credit.mapper.QuotaApplyMapper;
import com.metro.ccms.web.credit.query.CustomerInterfaceQuery;
import com.metro.ccms.web.credit.query.QuotaApplyQuery;
import com.metro.ccms.web.credit.service.QuotaApplyService;
import com.metro.ccms.web.httpsInterface.domain.CustManagerDO;
import com.metro.ccms.web.httpsInterface.domain.CustomerInterfaceDO;
import com.metro.ccms.web.httpsInterface.domain.PotentialDO;
import com.metro.ccms.web.httpsInterface.mapper.HttpsInterfaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 13:28 2021/1/4
 * @Modified By:
 */
@Service
public class QuotaApplyServiceImpl implements QuotaApplyService {


    @Autowired
    private QuotaApplyMapper quotaApplyMapper;
    @Autowired
    private HttpsInterfaceMapper httpsInterfaceMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;





    /**
     * 查询限额申请列表
     * @param quotaApplyQuery
     * @return
     */
    @Override
    public List<QuotaApplyDO> getQuotaApplyList(QuotaApplyQuery quotaApplyQuery) {
        return quotaApplyMapper.selectTbCreditApplyList(quotaApplyQuery);
    }

    /**
     * 获取客户信息接口表列表（限额申请新增按钮查询）
     * @param customerInterfaceQuery
     * @return
     */
    @Override
    public List<CustomerInterfaceDO> getCustInterfaceList(CustomerInterfaceQuery customerInterfaceQuery) {
        return quotaApplyMapper.getInterfaceCustomerList(customerInterfaceQuery);
    }

    /**
     * 新增按钮-查询基本信息卡号信息
     * @param quotaAddApplyBO
     * @return
     */
    @Override
    public Result addBasicInfoInquiries(QuotaAddApplyBO quotaAddApplyBO) {
        if (StringUtils.isBlank(quotaAddApplyBO.getCustNo())){
            return Result.error("请选择卡号信息!");
        }
        if (StringUtils.isBlank(quotaAddApplyBO.getPostId())){
            return Result.error("岗位ID不能为空!");
        }
        if (StringUtils.isBlank(quotaAddApplyBO.getCustType())){
            return Result.error("请选择客户类型!");
        }
        if (StringUtils.isBlank(quotaAddApplyBO.getBusinessType())){
            return Result.error("请选择业务类型!");
        }
        if (StringUtils.isBlank(quotaAddApplyBO.getLimitType())){
            return Result.error("请选择额度类型!");
        }
        /** 检验发起权限 */
        CustManagerDO managerDO=httpsInterfaceMapper.getCustManagerInfoByPostId(quotaAddApplyBO.getPostId());
        if (!managerDO.getUserId().equals(SecurityUtils.getUsername())){
            return Result.error("当前用户不是此卡号负责客户经理，无发起限额申请权限!");
        }
        /** 客户经理配置对象接口获取客户经理信息 */
        quotaAddApplyBO.setCustManagerCode(managerDO.getUserId());
        quotaAddApplyBO.setCustManagerName(managerDO.getUserName());
        /** 采购潜力金额接口获取客户潜力 */
        PotentialDO potentialDO=httpsInterfaceMapper.getPotentialByCustNo(quotaAddApplyBO.getCustNo());
        if (potentialDO!=null && potentialDO.getSalesPotential()!=null){
            quotaAddApplyBO.setDealerPotential(potentialDO.getSalesPotential().toString());
        }
        if (StringUtils.isNotBlank(quotaAddApplyBO.getStoreName())){
            quotaAddApplyBO.setStoreName(quotaAddApplyBO.getStoreName()+"("+quotaAddApplyBO.getStoreNo()+")");
        }else{
            quotaAddApplyBO.setStoreName(quotaAddApplyBO.getStoreNo());
        }
        return Result.success(quotaAddApplyBO);
    }
}
