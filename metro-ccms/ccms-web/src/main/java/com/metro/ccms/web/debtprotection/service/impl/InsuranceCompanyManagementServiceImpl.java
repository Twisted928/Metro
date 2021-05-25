package com.metro.ccms.web.debtprotection.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.web.debtprotection.domian.InsureCompanyDO;
import com.metro.ccms.web.debtprotection.domian.InsurePolicyDO;
import com.metro.ccms.web.debtprotection.domian.InsureScopeDO;
import com.metro.ccms.web.debtprotection.mapper.*;
import com.metro.ccms.web.debtprotection.query.InsureCompanyQuery;
import com.metro.ccms.web.debtprotection.service.InsuranceCompanyManagementService;
import com.metro.ccms.web.debtprotection.utils.FieldJudgmentUtils;
import com.metro.ccms.web.debtprotection.vo.InsuranceCombinationVO;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
import com.metro.ccms.web.debtprotection.vo.InsureScopeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 保险公司管理
 *
 * @author guangle
 * @create 2020/11/30
 * @since 1.0.0
 */
@Service
public class InsuranceCompanyManagementServiceImpl implements InsuranceCompanyManagementService {

    private static final Logger logger = LoggerFactory.getLogger(InsuranceCompanyManagementServiceImpl.class);

    @Autowired
    private InsureCompanyInsureCompanyMapper insureCompanyInsureCompanyMapper;
    @Autowired
    private InsurePolicyMapper insurePolicyMapper;
    @Autowired
    private InsureScopeMapper insureScopeMapper;
    @Autowired
    private FieldJudgmentUtils fieldJudgmentUtils;
    @Autowired
    private InsureMapper insureMapper;
    @Autowired
    private InsurecusMapper insurecusMapper;

    /**
     * 保险公司管理-查询
     *
     * @param insureCompanyQuery 保险公司表查询类
     * @return insureCompanyVOPage
     */
    @Override
    public List<InsureCompanyDO> insuranceCompanyManagementList(InsureCompanyQuery insureCompanyQuery) {
        return insureCompanyInsureCompanyMapper.listInsuranceCompanyManagements(insureCompanyQuery);
    }

    /**
     * 保险公司管理-新增
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveInsuranceCompanyManagement(InsuranceCombinationVO insuranceCombinationVO) {
        try {
            // 判断保险公司表的生效时间是否小于到期时间 compareTo 小于返回-1 等于返回0 大于返回1
            int compareTo = insuranceCombinationVO.getValidFrom().compareTo(insuranceCombinationVO.getValidTo());
            if (compareTo > 0) {
                return Result.error("生效时间不能在到期时间之后");
            }
            // 公司名称是否重复
            int companyName = fieldJudgmentUtils.companyName(insuranceCombinationVO.getCompName());
            if (companyName == 1) {
                return Result.error("公司名称重复,请修改");
            }
            // 判断公司编码是否重复
            int companyCode = insureCompanyInsureCompanyMapper.getCompanyCode(insuranceCombinationVO.getCompCode());
            if (companyCode > 0) {
                return Result.error("公司编码重复,请修改");
            }
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            insuranceCombinationVO.setCreatedBy(loginUser.getUser().getNickName());
            // 保险公司表
            insureCompanyInsureCompanyMapper.saveInsuranceManagement(insuranceCombinationVO);

            // 保单表集合不为空的话
            if (insuranceCombinationVO.getInsurePolicyVOList() != null && !insuranceCombinationVO.getInsurePolicyVOList().isEmpty()) {
                for (InsurePolicyVO insurePolicyVO : insuranceCombinationVO.getInsurePolicyVOList()) {
                    // 判断在同一公司下保单号是否重复
                    int policyNumber = fieldJudgmentUtils.policyNumber(insurePolicyVO.getCompCode(), insurePolicyVO.getPolicyno());
                    if (policyNumber == 1) {
                        throw new Exception("保单号" + insurePolicyVO.getPolicyno() + "重复,请修改");
                    }
                    // 判断保单范围是否重复
                    int policyScope = fieldJudgmentUtils.policyScope(insurePolicyVO.getInsureScopeDOList(), insurePolicyVO.getCompCode());
                    if (policyScope == 0) {
                        throw new Exception("保单号为:" + insurePolicyVO.getPolicyno() + ",勾选的保单范围重复,请修改");
                    }
                    insurePolicyVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
                    // 赔偿比例 / 100
                    if (insurePolicyVO.getPayLv() != null) {
                        int compare = BigDecimal.ZERO.compareTo(insurePolicyVO.getPayLv());
                        if (compare > 0) {
                            throw new Exception("赔偿比例不能小于0");
                        }
                        insurePolicyVO.setPayLv(insurePolicyVO.getPayLv().divide(BigDecimal.valueOf(100)));
                    }
                    insurePolicyMapper.save(insurePolicyVO);

                    // 保单范围
                    if (insurePolicyVO.getInsureScopeDOList() != null && !insurePolicyVO.getInsureScopeDOList().isEmpty()) {
                        for (Object code : insurePolicyVO.getInsureScopeDOList()) {
                            InsureScopeVO insureScopeVO = new InsureScopeVO();
                            insureScopeVO.setPolicId(insurePolicyVO.getId().toString());  // 保单表ID
                            insureScopeVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
                            insureScopeVO.setStoreCode(code.toString()); // 门店编码
                            insureScopeMapper.save(insureScopeVO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error(e.getMessage());
        }
        return Result.success("保存成功");
    }

    /**
     * 保险公司管理-修改
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insuranceCompanyManagementUpdate(InsuranceCombinationVO insuranceCombinationVO) {
        try {
            // 判断保险公司表的生效时间是否小于到期时间 compareTo 小于返回-1 等于返回0 大于返回1
            int compareTo = insuranceCombinationVO.getValidFrom().compareTo(insuranceCombinationVO.getValidTo());
            if (compareTo > 0) {
                return Result.error("生效时间不能在到期时间之前");
            }

            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            insuranceCombinationVO.setUpdatedBy(loginUser.getUser().getNickName());

            insureCompanyInsureCompanyMapper.update(insuranceCombinationVO);

            // 保单表
            if (insuranceCombinationVO.getInsurePolicyVOList() != null && !insuranceCombinationVO.getInsurePolicyVOList().isEmpty()) {
                for (InsurePolicyVO insurePolicyVO : insuranceCombinationVO.getInsurePolicyVOList()) {
                    // 判断保单范围是否重复 先删除自己的判断是否重复
                    if (insurePolicyVO.getId() != null) {
                        insureScopeMapper.deleteByPolicId(insurePolicyVO.getId().toString());
                    }
                    // 判断保单范围是否重复
                    int policyScope = fieldJudgmentUtils.policyScope(insurePolicyVO.getInsureScopeDOList(), insurePolicyVO.getCompCode());
                    if (policyScope == 0) {
                        throw new Exception("保单号为:" + insurePolicyVO.getPolicyno() + ",勾选的保单范围重复,请修改");
                    }
                    // 判断 IdType是否为纯数字,ture是修改,false是新增
                    boolean numeric = fieldJudgmentUtils.isNumeric(insurePolicyVO.getIdType());
                    if (numeric) {
                        insurePolicyVO.setId(Long.parseLong(insurePolicyVO.getIdType())); // ID
                        insurePolicyVO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
                        insurePolicyVO.setStatus(insuranceCombinationVO.getStatus()); // 状态
                        // 赔偿比例 / 100
                        if (insurePolicyVO.getPayLv() != null) {
                            int compare = BigDecimal.ZERO.compareTo(insurePolicyVO.getPayLv());
                            if (compare > 0) {
                                throw new Exception("赔偿比例不能小于0");
                            }
                            insurePolicyVO.setPayLv(insurePolicyVO.getPayLv().divide(BigDecimal.valueOf(100)));
                        }
                        insurePolicyMapper.update(insurePolicyVO);

                        // 保单范围
                        if (insurePolicyVO.getInsureScopeDOList() != null && !insurePolicyVO.getInsureScopeDOList().isEmpty()) {
                            for (Object code : insurePolicyVO.getInsureScopeDOList()) {
                                InsureScopeVO insureScopeVO = new InsureScopeVO();
                                insureScopeVO.setPolicId(insurePolicyVO.getId().toString());  // 保单表ID
                                insureScopeVO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
                                insureScopeVO.setStoreCode(code.toString()); // 门店编码
                                insureScopeVO.setStatus(insuranceCombinationVO.getStatus()); // 状态
                                insureScopeVO.setUpdateTime(new Date()); // 修改时间
                                insureScopeMapper.save(insureScopeVO);
                            }
                        }
                    } else {
                        // 判断在同一公司下保单号是否重复
                        int policyNumber = fieldJudgmentUtils.policyNumber(insurePolicyVO.getCompCode(), insurePolicyVO.getPolicyno());
                        if (policyNumber == 1) {
                            throw new Exception("保单号" + insurePolicyVO.getPolicyno() + "重复,请修改");
                        }
                        insurePolicyVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
                        insurePolicyVO.setStatus(insuranceCombinationVO.getStatus()); // 状态
                        insurePolicyVO.setUpdateTime(new Date()); // 修改时间
                        // 赔偿比例 / 100
                        if (insurePolicyVO.getPayLv() != null) {
                            int compare = BigDecimal.ZERO.compareTo(insurePolicyVO.getPayLv());
                            if (compare > 0) {
                                throw new Exception("赔偿比例不能小于0");
                            }
                            insurePolicyVO.setPayLv(insurePolicyVO.getPayLv().divide(BigDecimal.valueOf(100)));
                        }
                        insurePolicyMapper.save(insurePolicyVO);

                        // 保单范围
                        if (insurePolicyVO.getInsureScopeDOList() != null && !insurePolicyVO.getInsureScopeDOList().isEmpty()) {
                            for (Object code : insurePolicyVO.getInsureScopeDOList()) {
                                InsureScopeVO insureScopeVO = new InsureScopeVO();
                                insureScopeVO.setPolicId(insurePolicyVO.getId().toString());  // 保单表ID
                                insureScopeVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
                                insureScopeVO.setStoreCode(code.toString()); // 门店编码
                                insureScopeVO.setStatus(insuranceCombinationVO.getStatus()); // 状态
                                insureScopeVO.setUpdateTime(new Date()); // 修改时间
                                insureScopeMapper.save(insureScopeVO);
                            }
                        }
                    }
                }
            }

            // 根据ID逻辑删除保单和保单范围
            if (insuranceCombinationVO.getDeleteId() != null && !insuranceCombinationVO.getDeleteId().isEmpty()) {
                for (String id : insuranceCombinationVO.getDeleteId()) {
                    insurePolicyMapper.deleteById(id);
                    insureScopeMapper.delete(id);
                }
            }
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error(e.getMessage());
        }
        return Result.success(insuranceCombinationVO);
    }

    /**
     * 保险公司管理-删除
     *
     * @param insureCompanyDO 保险公司表
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insuranceCompanyManagementDelete(InsureCompanyDO insureCompanyDO) {
        try {
            // 根据ID查询保险公司表
            InsuranceCombinationVO insuranceCombinationVO = insureCompanyInsureCompanyMapper.selectByPrimaryKey(insureCompanyDO.getId());
            // 判断保险公司是否参与投保 已经投保的状态下,不允许删除
            int compCode = insureMapper.getByCompCode(insuranceCombinationVO.getCompCode());
            if (compCode > 0) {
                return Result.error("该公司以进行投保,不能删除");
            }
            // 判断保险客户清单是否有此公司的客户清单
            int companyCode = insurecusMapper.getByCompanyCode(insuranceCombinationVO);
            if (companyCode > 0) {
                return Result.error("已存在此公司的保险客户清单,请先删除客户清单后,重试");
            }
            insureCompanyInsureCompanyMapper.delete(insureCompanyDO);
            // 根据公司编码获取保单ID 然根据保单ID删除
            List<InsurePolicyVO> byCompCode = insurePolicyMapper.getByCompCode(insuranceCombinationVO.getCompCode());
            // 逻辑删除保单表
            insurePolicyMapper.delete(insuranceCombinationVO.getCompCode());
            for (InsurePolicyVO insurePolicyVO : byCompCode) {
                insureScopeMapper.delete(insurePolicyVO.getId().toString());
            }
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }

    /**
     * 保险公司管理-详细
     *
     * @param insuranceCombinationVO 组合VO
     * @return InsureCompanyVO
     */
    @Override
    public InsuranceCombinationVO insuranceCompanyManagementGetById(InsuranceCombinationVO insuranceCombinationVO) {
        // 保险公司表 根据ID查询
        InsuranceCombinationVO insuranceCombination = insureCompanyInsureCompanyMapper.selectByPrimaryKey(insuranceCombinationVO.getId());
        // 保单表 根据公司编码查询
        List<InsurePolicyVO> byCompCode = insurePolicyMapper.getByCompCode(insuranceCombination.getCompCode());
        // 保单范围表 根据保单表ID查询
        for (InsurePolicyVO insurePolicyVO : byCompCode) {
            List<String> list = new ArrayList<>();
            List<InsureScopeDO> getlist = insureScopeMapper.getlist(insurePolicyVO.getId());
            for (InsureScopeDO insureScopeDO : getlist) {
                list.add(insureScopeDO.getStoreCode());
            }
            insurePolicyVO.setInsureScopeDOList(list);
        }
        insuranceCombination.setInsurePolicyVOList(byCompCode);
        return insuranceCombination;
    }

    /**
     * 查看保单范围
     *
     * @param insureScopeVO 保单范围表VO
     * @return List<InsureScopeVO>
     */
    @Override
    public List<InsureScopeVO> insureScopeList(InsureScopeVO insureScopeVO) {
        return insureScopeMapper.getByPolicId(insureScopeVO);
    }

    /**
     * 保单删除
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
    @Override
    public Result policyDelete(InsurePolicyVO insurePolicyVO) {
        try {
            insurePolicyMapper.policyDelete(insurePolicyVO.getId());
            insureScopeMapper.delete(insurePolicyVO.getId().toString());
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // TODO 你处理信息
            return Result.error("删除失败");
        }
        return Result.success();
    }

    /**
     * 保单修改
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
    @Override
    public Result policyUpdate(InsurePolicyVO insurePolicyVO) {
        try {
            // 判断保单生效时间是否小于保险公司生效时间 compareTo 小于返回-1 等于返回0 大于返回1
//            int to = insuranceCombinationVO.getValidFrom().compareTo(insurePolicyVO.getValidFrom());
//            if (to > 0) {
//                return Result.error("保单生效时间不能在保险公司生效时间");
//            }
            // 判断生效时间是否小于到期时间 compareTo 小于返回-1 等于返回0 大于返回1
            int compareTo = insurePolicyVO.getValidFrom().compareTo(insurePolicyVO.getValidTo());
            if (compareTo > 0) {
                return Result.error("生效时间不能在到期时间之前");
            }
            // 判断保单范围是否重复
            int policyScope = fieldJudgmentUtils.policyScope(insurePolicyVO.getInsureScopeDOList(), insurePolicyVO.getCompCode());
            if (policyScope == 0) {
                return Result.error("勾选的保单范围重复,请修改");
            }
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            insurePolicyVO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
            // 赔偿比例 / 100
            if (insurePolicyVO.getPayLv() != null) {
                int compare = BigDecimal.ZERO.compareTo(insurePolicyVO.getPayLv());
                if (compare == 1) {
                    return Result.error("赔偿比例不能小于0");
                }
                insurePolicyVO.setPayLv(insurePolicyVO.getPayLv().divide(BigDecimal.valueOf(100)));
            }
            insurePolicyMapper.update(insurePolicyVO);

            // 保单范围 先删后改 物理删除
            if (insurePolicyVO.getInsureScopeDOList() != null && !insurePolicyVO.getInsureScopeDOList().isEmpty()) {
                insureScopeMapper.deleteByPolicId(insurePolicyVO.getId().toString());

                for (Object code : insurePolicyVO.getInsureScopeDOList()) {
                    InsureScopeVO insureScopeVO = new InsureScopeVO();
                    insureScopeVO.setPolicId(insurePolicyVO.getId().toString());  // 保单表ID
                    insureScopeVO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
                    insureScopeVO.setStoreCode(code.toString()); // 门店编码
                    insureScopeMapper.save(insureScopeVO);
                }
            }
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // TODO 你处理信息
            return Result.error("修改失败");
        }
        return Result.success(insurePolicyVO);
    }

    /**
     * 保单新增
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
    @Override
    public Result policySave(InsurePolicyVO insurePolicyVO) {
        try {
            // 判断保单生效时间是否小于保险公司生效时间 compareTo 小于返回-1 等于返回0 大于返回1
//            int to = insurePolicyVO.getTime().compareTo(insurePolicyVO.getValidFrom());
//            if (to > 0) {
//                return Result.error("保单生效时间不能在保险公司生效时间之前");
//            }
            // 判断生效时间是否小于到期时间 compareTo 小于返回-1 等于返回0 大于返回1
            int compareTo = insurePolicyVO.getValidFrom().compareTo(insurePolicyVO.getValidTo());
            if (compareTo > 0) {
                return Result.error("生效时间不能在到期时间之前");
            }
            // 判断在同一公司下保单号是否重复
            int policyNumber = fieldJudgmentUtils.policyNumber(insurePolicyVO.getCompCode(), insurePolicyVO.getPolicyno());
            if (policyNumber == 1) {
                return Result.error("保单号重复,请修改");
            }
            // 判断保单范围是否重复
            int policyScope = fieldJudgmentUtils.policyScope(insurePolicyVO.getInsureScopeDOList(), insurePolicyVO.getCompCode());
            if (policyScope == 0) {
                return Result.error("勾选的保单范围重复,请修改");
            }
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            insurePolicyVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
            // 赔偿比例 / 100
            if (insurePolicyVO.getPayLv() != null) {
                int compare = BigDecimal.ZERO.compareTo(insurePolicyVO.getPayLv());
                if (compare == 1) {
                    return Result.error("赔偿比例不能小于0");
                }
                insurePolicyVO.setPayLv(insurePolicyVO.getPayLv().divide(BigDecimal.valueOf(100)));
            }
            insurePolicyMapper.save(insurePolicyVO);

            // 保单范围
            if (insurePolicyVO.getInsureScopeDOList() != null && !insurePolicyVO.getInsureScopeDOList().isEmpty()) {
                for (Object code : insurePolicyVO.getInsureScopeDOList()) {
                    InsureScopeVO insureScopeVO = new InsureScopeVO();
                    insureScopeVO.setPolicId(insurePolicyVO.getId().toString());  // 保单表ID
                    insureScopeVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
                    insureScopeVO.setStoreCode(code.toString()); // 门店编码
                    insureScopeMapper.save(insureScopeVO);
                }
            }

        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // TODO 你处理信息
            return Result.error("新增失败");
        }
        insurePolicyVO.setCreateTime(new Date());
        return Result.success(insurePolicyVO);
    }

    /**
     * 保单新增时,判断时间
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
    @Override
    public Result timeJudgment(InsuranceCombinationVO insuranceCombinationVO) {
        try {
            for (InsurePolicyVO insurePolicyVO : insuranceCombinationVO.getInsurePolicyVOList()) {
                // 判断保单生效时间是否小于保险公司生效时间 compareTo 小于返回-1 等于返回0 大于返回1
                int to = insuranceCombinationVO.getValidFrom().compareTo(insurePolicyVO.getValidFrom());
                if (to > 0) {
                    throw new Exception("保单生效时间不能在保险公司生效时间之前");
                }
                int validTo = insurePolicyVO.getValidTo().compareTo(insuranceCombinationVO.getValidTo());
                if (validTo > 0) {
                    throw new Exception("保单到期时间不能在保险公司到期时间之后");
                }
                // 判断生效时间是否小于到期时间 compareTo 小于返回-1 等于返回0 大于返回1
                int comTo = insurePolicyVO.getValidFrom().compareTo(insurePolicyVO.getValidTo());
                if (comTo > 0) {
                    throw new Exception("保单生效时间不能在到期时间之后");
                }
            }
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // TODO 你处理信息
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    /**
     * 保险公司修改时,删除保单判断保单是否可以删除
     *
     * @param insurePolicyDO 保单表DO
     * @return Result
     * 0 不重复
     * 1 重复
     */
    @Override
    public Result insurePolicyJudgment(InsurePolicyDO insurePolicyDO) {
        int i = insurecusMapper.getByPolicyno(insurePolicyDO.getPolicyno());
        if (i > 0) {
            return Result.error("该保单已在客户清单维护,暂时无法删除,请先删除客户清单数据后,重试" , 1);
        }
        return Result.success("不重复" , 0);
    }


    /**
     * 将异常信息转换为string类型
     *
     * @param e 异常
     * @return string后的异常信息
     */
    public static String getExceptionInfo(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        return baos.toString();
    }
}
