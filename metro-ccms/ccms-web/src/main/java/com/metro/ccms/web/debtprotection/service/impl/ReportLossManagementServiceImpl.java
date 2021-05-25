package com.metro.ccms.web.debtprotection.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.mapper.SysFileMapper;
import com.metro.ccms.web.debtprotection.domian.ClaimDO;
import com.metro.ccms.web.debtprotection.domian.ClaimProgressDO;
import com.metro.ccms.web.debtprotection.domian.InsureDO;
import com.metro.ccms.web.debtprotection.mapper.ClaimMapper;
import com.metro.ccms.web.debtprotection.mapper.ClaimProgressMapper;
import com.metro.ccms.web.debtprotection.mapper.InsureMapper;
import com.metro.ccms.web.debtprotection.query.ClaimQuery;
import com.metro.ccms.web.debtprotection.service.ReportLossManagementService;
import com.metro.ccms.web.debtprotection.vo.LossPortfolioVO;
import com.metro.ccms.web.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.List;

/**
 * 报损管理
 *
 * @author guangle
 * @date 2020/12/10
 * @since 1.0.0
 */
@Service
public class ReportLossManagementServiceImpl implements ReportLossManagementService {

    private static final Logger logger = LoggerFactory.getLogger(ReportLossManagementServiceImpl.class);


    @Autowired
    private ClaimMapper claimMapper;
    @Autowired
    private ClaimProgressMapper claimProgressMapper;
    @Autowired
    private InsureMapper insureMapper;
    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private CommonUtils commonUtils;

    /**
     * 报损管理-查询
     *
     * @param claimQuery 报损管理查询类
     * @return List<ClaimDO>
     */
    @Override
    public List<ClaimDO> list(ClaimQuery claimQuery) {
        if (claimQuery.getEndDate() != null) {
            // 把前端传回来的汇总发票日期查询条件里面的结束时间转换为 yyyy_mm_dd 23:59:59
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(claimQuery.getEndDate());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            claimQuery.setEndDate(calendar.getTime());
        }
        return claimMapper.list(claimQuery);
    }

    /**
     * 报损管理-新增 案件状态/1自追/2委托/3关闭
     *
     * @param lossPortfolioVO 报损管理组合VO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(LossPortfolioVO lossPortfolioVO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            lossPortfolioVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
            Long id = claimMapper.getMaxId();
            lossPortfolioVO.setCaseno(commonUtils.getApplicationNo(id, 9)); // 案件编号

            InsureDO insureDO = insureMapper.getByPrimaryKey(lossPortfolioVO.getInsureId());
            if (insureDO.getInsuresum() != null) {
                lossPortfolioVO.setInsuresum(insureDO.getInsuresum()); // 投保金额
            }
            lossPortfolioVO.setInvoicesum(insureDO.getInvoicesum()); // 汇总发票金额
            lossPortfolioVO.setCompCode(insureDO.getCompCode()); // 保险公司编码
            lossPortfolioVO.setPaymode(insureDO.getPaymode()); // 付款方式
            claimMapper.save(lossPortfolioVO);

            for (ClaimProgressDO claimProgressDO : lossPortfolioVO.getClaimProgressDOList()) {
                claimProgressDO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
                claimProgressDO.setMainId(lossPortfolioVO.getId()); // 主表ID
                claimProgressDO.setCaseno(lossPortfolioVO.getCaseno()); // 案件编号
                claimProgressMapper.save(claimProgressDO);
                // 修改主表案件状态
                lossPortfolioVO.setCaseStatus(claimProgressDO.getCaseStatus());
                claimMapper.update(lossPortfolioVO);
            }
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("新增失败");
        }
        return Result.success(lossPortfolioVO);
    }

    /**
     * 报损管理-台账维护
     *
     * @param lossPortfolioVO 报损管理组合VO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result update(LossPortfolioVO lossPortfolioVO) {
        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        try {
            // 先删除,考虑到可能刚新增就删除,在新增的操作
            if (lossPortfolioVO.getIds() != null && !lossPortfolioVO.getIds().isEmpty()) {
                ClaimProgressDO claimProgress = new ClaimProgressDO();
                claimProgress.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
                for (String id : lossPortfolioVO.getIds()) {
                    claimProgress.setId(Long.parseLong(id));
                    claimProgressMapper.ledgerDelete(claimProgress);
                }
            }

            if (lossPortfolioVO.getClaimProgressDOList() != null && !lossPortfolioVO.getClaimProgressDOList().isEmpty()) {
                for (ClaimProgressDO claimProgressDO : lossPortfolioVO.getClaimProgressDOList()) {
                    // 新增
                    if (claimProgressDO.getId() == null) {
                        claimProgressDO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
                        claimProgressDO.setMainId(lossPortfolioVO.getId()); // 主表ID
                        claimProgressDO.setCaseno(lossPortfolioVO.getCaseno()); // 案件编号
                        // 查询今天是否有创建
                        int maxDate = claimProgressMapper.getMaxDate(claimProgressDO);
                        if (maxDate > 0) {
                            throw new Exception("一天只能维护一条报损单据信息");
                        }
                        // 根据mianid查询获取最后一条状态
                        ClaimProgressDO claimProgress = claimProgressMapper.getCaseStatus(claimProgressDO.getMainId());
                        if (claimProgress != null) {
                            if (claimProgress.getCaseStatus() != null) {
                                if ("3".equals(claimProgress.getCaseStatus())) {
                                    throw new Exception("请修改最后一条报损单据状态");
                                }
                            }
                        }

                        claimProgressMapper.insert(claimProgressDO);
                        // 获取最后一条案件状态
                        ClaimProgressDO caseStatus = claimProgressMapper.getCaseStatus(lossPortfolioVO.getId());
                        // 修改主表案件状态
                        lossPortfolioVO.setCaseStatus(caseStatus.getCaseStatus());
                        claimMapper.update(lossPortfolioVO);
                    } else { // 修改
                        claimProgressDO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
                        claimProgressDO.setMainId(lossPortfolioVO.getId()); // 主表ID
                        claimProgressDO.setCaseno(lossPortfolioVO.getCaseno()); // 案件编号
                        claimProgressMapper.update(claimProgressDO);
                        // 获取最后一条案件状态
                        ClaimProgressDO caseStatus = claimProgressMapper.getCaseStatus(lossPortfolioVO.getId());
                        // 修改主表案件状态
                        lossPortfolioVO.setCaseStatus(caseStatus.getCaseStatus());
                        claimMapper.update(lossPortfolioVO);
                    }
                }
            }


        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            return Result.error(e.getMessage());
        }

        return Result.success(lossPortfolioVO);
    }

    /**
     * 报损管理-删除
     *
     * @param claimProgressDO 报损进度表
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delete(ClaimProgressDO claimProgressDO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            claimProgressDO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
            // 判断是否维护过台账信息
            int i = claimProgressMapper.getByMainId(claimProgressDO.getId());
            if (i > 0) {
                return Result.error("该报损单据以维护过台账信息,暂时无法删除,请先删除台账信息后,重试");
            }
            // 根据ID删除
            claimMapper.delete(claimProgressDO);
            // 根据案件编号和主表ID删除
            claimProgressMapper.delete(claimProgressDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            return Result.error("删除发生错误,请稍后再试");
        }
        return Result.success();
    }

    /**
     * 报损管理-详细
     *
     * @param claimDO 报损表
     * @return Claim
     */
    @Override
    public ClaimDO getOne(ClaimDO claimDO) {
        // 根据ID查询
        ClaimDO claim = claimMapper.selectByPrimaryKey(claimDO.getId());
        // 根据主表ID 案件编号查询
        List<ClaimProgressDO> claimProgressDOList = claimProgressMapper.getList(claimDO);
        // 根据主表ID 类型查询
        List<SysBasicFile> fileListByNoAndType = sysFileMapper.getFileListByNoAndType(claimDO.getId().toString(), "8");
        claim.setClaimProgressDOList(claimProgressDOList);
        claim.setSysBasicFileList(fileListByNoAndType);
        return claim;
    }

    /**
     * 报损管理-台账维护-台账删除
     *
     * @param claimProgressDO 报损进度表DO
     * @return Result
     */
    @Override
    public Result ledgerDelete(ClaimProgressDO claimProgressDO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            claimProgressDO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
            claimProgressMapper.ledgerDelete(claimProgressDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            return Result.error("删除发生错误,请稍后再试");
        }
        return Result.success();
    }

    /**
     * 查看已投保保单
     *
     * @return List<InsureDO>
     */
    @Override
    public List<InsureDO> listInsuredPolicys(InsureDO insureDO) {
        // 先查询报损表维护了哪些已投保保单
        List<Long> id = claimMapper.listId();
        return insureMapper.listInsuredPolicys(insureDO, id);
    }

    /**
     * 根据选择的已投保保单ID查询信息
     *
     * @param insureDO 已投保保单
     * @return InsureDO
     */
    @Override
    public InsureDO getInsure(InsureDO insureDO) {
        return insureMapper.getById(insureDO);
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
