package com.metro.ccms.web.debtprotection.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.mapper.SysFileMapper;
import com.metro.ccms.web.debtprotection.domian.GuaranteeDO;
import com.metro.ccms.web.debtprotection.domian.GuaranteeScopeDO;
import com.metro.ccms.web.debtprotection.mapper.GuaranteeMapper;
import com.metro.ccms.web.debtprotection.mapper.GuaranteeScopeMapper;
import com.metro.ccms.web.debtprotection.query.GuaranteeQuery;
import com.metro.ccms.web.debtprotection.service.GuaranteeLetterManagementService;
import com.metro.ccms.web.debtprotection.utils.FieldJudgmentUtils;
import com.metro.ccms.web.debtprotection.vo.GuaranteeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * 担保函管理
 *
 * @author guangle
 * @date 2020/12/14
 * @since 1.0.0
 */
@Service
public class GuaranteeLetterManagementServiceImpl implements GuaranteeLetterManagementService {

    private static final Logger logger = LoggerFactory.getLogger(GuaranteeLetterManagementServiceImpl.class);

    @Autowired
    private GuaranteeMapper guaranteeMapper;
    @Autowired
    private GuaranteeScopeMapper guaranteeScopeMapper;
    @Autowired
    private SysFileMapper sysFileMapper;
    @Autowired
    private FieldJudgmentUtils fieldJudgmentUtils;

    /**
     * 担保函管理-查询
     *
     * @param guaranteeQuery 条件查询Query
     * @return List<GuaranteeDO>
     */
    @Override
    public List<GuaranteeDO> list(GuaranteeQuery guaranteeQuery) {
        return guaranteeMapper.list(guaranteeQuery);
    }

    /**
     * 担保函管理-新增
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(GuaranteeVO guaranteeVO) {
        try {
            int compareTo = guaranteeVO.getValidFrom().compareTo(guaranteeVO.getValidTo());
            if (compareTo > 0) {
                return Result.error("生效时间不能晚于到期时间");
            }

            // 判断担保函编码是否唯一
            int i = guaranteeMapper.getByCode(guaranteeVO.getGtcode());
            if (i > 0) {
                return Result.error("担保函编码重复,请修改");
            }

            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            guaranteeVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
            guaranteeMapper.save(guaranteeVO);

            if (guaranteeVO.getGuaranteeScopeDOList() != null && !guaranteeVO.getGuaranteeScopeDOList().isEmpty()) {
                for (GuaranteeScopeDO guaranteeScopeDO : guaranteeVO.getGuaranteeScopeDOList()) {
                    int card = fieldJudgmentUtils.card(guaranteeScopeDO.getCardCode(), guaranteeScopeDO.getStoreCode(), guaranteeVO.getId().toString());
                    if (card != 0) {
                        throw new Exception("请不要勾选相同的卡号");
                    }
                    guaranteeScopeDO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
                    guaranteeScopeDO.setMainId(guaranteeVO.getId().toString()); // 父表ID
                    guaranteeScopeMapper.save(guaranteeScopeDO);
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

        return Result.success(guaranteeVO);
    }

    /**
     * 担保函管理-修改
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result update(GuaranteeVO guaranteeVO) {
        try {
            int compareTo = guaranteeVO.getValidFrom().compareTo(guaranteeVO.getValidTo());
            if (compareTo > 0) {
                return Result.error("生效时间不能晚于到期时间");
            }

            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            guaranteeVO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
            guaranteeMapper.update(guaranteeVO);

            // 范围表 全删全存
            if (guaranteeVO.getGuaranteeScopeDOList() != null && !guaranteeVO.getGuaranteeScopeDOList().isEmpty()) {
                guaranteeScopeMapper.deleteByPrimaryKey(guaranteeVO.getId());
                for (GuaranteeScopeDO guaranteeScopeDO : guaranteeVO.getGuaranteeScopeDOList()) {
                    int card = fieldJudgmentUtils.card(guaranteeScopeDO.getCardCode(), guaranteeScopeDO.getStoreCode(), guaranteeVO.getId().toString());
                    if (card != 0) {
                        throw new Exception("请不要勾选相同的卡号");
                    }
                    guaranteeScopeDO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
                    guaranteeScopeDO.setMainId(guaranteeVO.getId().toString()); // 父表ID
                    guaranteeScopeMapper.save(guaranteeScopeDO);

                }
            }

            // 删除卡号信息
            if (guaranteeVO.getScopeList() != null && !guaranteeVO.getScopeList().isEmpty()){
                for (String id : guaranteeVO.getScopeList()) {
                    guaranteeScopeMapper.deleteId(id);
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

        return Result.success(guaranteeVO);
    }

    /**
     * 担保函管理-删除
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
    @Override
    public Result delete(GuaranteeVO guaranteeVO) {
        // 循环获取ID
        for (String id : guaranteeVO.getIds()) {
            guaranteeMapper.delete(id);
            guaranteeScopeMapper.delete(id);
        }
        return Result.success();
    }

    /**
     * 担保函管理-详情
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return GuaranteeVO
     */
    @Override
    public GuaranteeVO getOne(GuaranteeVO guaranteeVO) {
        // 根据ID查询
        GuaranteeVO guarantee = guaranteeMapper.selectByPrimaryKey(guaranteeVO.getId());
        // 根据父表ID查询
        guarantee.setGuaranteeScopeDOList(guaranteeScopeMapper.selectByMianID(guaranteeVO.getId()));
        List<SysBasicFile> list = sysFileMapper.getFileListByNoAndType(guaranteeVO.getId().toString(), "9");
        guarantee.setList(list);
        return guarantee;
    }

    /**
     * 卡片新增
     *
     * @param guaranteeScopeDO 担保函范围表
     * @return Result
     */
    @Override
    public Result cardSave(GuaranteeScopeDO guaranteeScopeDO) {
        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        guaranteeScopeDO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
        guaranteeScopeMapper.save(guaranteeScopeDO);
        return Result.success(guaranteeScopeDO);
    }

    /**
     * 卡片删除
     *
     * @param guaranteeScopeDO 担保函范围表
     * @return Result
     */
    @Override
    public Result cardDelete(GuaranteeScopeDO guaranteeScopeDO) {
        guaranteeScopeMapper.deleteByID(guaranteeScopeDO.getId());
        return Result.success();
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
