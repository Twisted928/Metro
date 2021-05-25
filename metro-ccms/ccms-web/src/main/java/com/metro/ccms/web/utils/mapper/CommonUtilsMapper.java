package com.metro.ccms.web.utils.mapper;

import com.metro.ccms.common.core.domain.entity.SysRole;
import com.metro.ccms.common.core.domain.entity.SysUser;
import com.metro.ccms.web.activiti.domain.AutherRoleDO;
import com.metro.ccms.web.credit.domain.PayTermDO;
import com.metro.ccms.web.debtprotection.domian.InsureCompanyDO;
import com.metro.ccms.web.debtprotection.query.InsureCompanyQuery;
import com.metro.ccms.web.utils.domain.ApprovalRecordDO;
import com.metro.ccms.web.utils.domain.BasicDataDO;
import com.metro.ccms.web.credit.domain.CustMembersDO;
import com.metro.ccms.web.credit.domain.CustPrimaryDO;
import com.metro.ccms.web.utils.query.CustMembersQuery;
import com.metro.ccms.web.utils.query.CustPrimaryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 14:39 2020/12/9
 * @Modified By:
 */
public interface CommonUtilsMapper {

    /**
     * 根据类型获取data表数据
     * @param ctype
     * @return
     */
    public List<BasicDataDO> getBasicDataByCType(@Param("ctype") String ctype);

    /**
     * 获取客户主数据列表
     * @param custPrimaryQuery
     * @return
     */
    public List<CustPrimaryDO> getCustPrimaryList(CustPrimaryQuery custPrimaryQuery);

    /**
     * 获取客户卡号关系列表
     * @param custMembersQuery
     * @return
     */
    public List<CustMembersDO> getCustMembersList(CustMembersQuery custMembersQuery);

    /**
     * 根据结算方式获取付款条件
     * @param settleType
     * @return
     */
    public List<PayTermDO> getPayTermBySettleType(@Param("settleType")String settleType);

    /**
     * 查询保险公司列表
     * @param insureCompanyQuery
     * @return
     */
    public List<InsureCompanyDO> getInsuranceCompanyList(InsureCompanyQuery insureCompanyQuery);

    /**
     * 保存审批记录
     * @param approvalRecordDO
     */
    public void insertApprovalRecord(ApprovalRecordDO approvalRecordDO);

    /**
     * 根据流程实例id获取审批记录
     * @param instanceId
     * @return
     */
    public List<ApprovalRecordDO> getAppravalRecord(@Param("instanceId")String instanceId);

    /**
     * 根据用户编码获取用户名称
     * @param userName
     * @return
     */
    public String getNickNameByUserName(@Param("userName") String userName);

    /**
     * 根据角色id获取角色名称
     * @param roleId
     * @return
     */
    public String getRoleNameByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据用户id获取角色信息
     * @param userId
     * @return
     */
    public List<SysRole> getRoleInfoByUserId(@Param("userId") Long userId);

    /**
     * 根据客户编码头模糊查询
     * @param custCode
     * @return
     */
    public String getCustCodeByHead(@Param("custCode") String custCode);

    /**
     * 获取审批人方法
     *
     * @param roleId
     * @param deptCodes
     * @return
     */
    public List<String> getCandidateUser(@Param("roleId") Long roleId, @Param("deptCodes") List<String> deptCodes);

    /**
     * 获取角色收取数据
     * @param roleId
     * @param date
     * @return
     */
    public List<AutherRoleDO> getAutherRole(@Param("roleId")String roleId, @Param("date") Date date);

    /**
     * 根据用户编码获取用户基本信息
     * @param userName
     * @return
     */
    public SysUser getUserInfoByUserName(@Param("userName") String userName);


    /*Test*/
    public List<CustPrimaryDO> getPrimaryTemp();

    public void updateCustCode(@Param("custCode") String custCode,@Param("id") Long id);

}
