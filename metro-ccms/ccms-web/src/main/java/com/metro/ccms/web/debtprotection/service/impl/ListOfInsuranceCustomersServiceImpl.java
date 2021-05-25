package com.metro.ccms.web.debtprotection.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.mapper.SysFileMapper;
import com.metro.ccms.web.debtprotection.domian.InsurecusDO;
import com.metro.ccms.web.debtprotection.mapper.InsurePolicyMapper;
import com.metro.ccms.web.debtprotection.mapper.InsurecusMapper;
import com.metro.ccms.web.debtprotection.query.InsurecusQuery;
import com.metro.ccms.web.debtprotection.service.ListOfInsuranceCustomersService;
import com.metro.ccms.web.debtprotection.vo.InsureChecklistVO;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
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
 * 保险清单
 *
 * @author guangle
 * @create 2020/12/7
 * @since 1.0.0
 */
@Service
public class ListOfInsuranceCustomersServiceImpl implements ListOfInsuranceCustomersService {

    private static final Logger logger = LoggerFactory.getLogger(ListOfInsuranceCustomersServiceImpl.class);

    @Autowired
    private InsurecusMapper insurecusMapper;
    @Autowired
    private InsurePolicyMapper insurePolicyMapper;
    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 保险清单-查询
     *
     * @param insurecusQuery 保险客户清单表查询类
     * @return List<InsureDO>
     */
    @Override
    public List<InsurecusDO> list(InsurecusQuery insurecusQuery) {
        return insurecusMapper.list(insurecusQuery);
    }

    /**
     * 保险清单-新增
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(InsureChecklistVO insureChecklistVO) {

        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();

        insureChecklistVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
        try {
            // type等于1时,需要修改上条数据
            if ("1".equals(insureChecklistVO.getType())) {
                insurecusMapper.updateByType(insureChecklistVO);
            }
            insurecusMapper.save(insureChecklistVO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("新增信息失败");
        }

        return Result.success(insureChecklistVO);
    }

    /**
     * 保险清单-补录
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result update(InsureChecklistVO insureChecklistVO) {

        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();

        insureChecklistVO.setUpdatedBy(loginUser.getUser().getNickName()); // 修改人
        try {
            // 查询保单是否还存在
            int i = insurePolicyMapper.getPolicy(insureChecklistVO.getPolicyno());
            if (i == 0){
                throw new Exception("保单号不存在(可能已经被删除或已失效),请重新勾选保单号");
            }
            // type等于1时,需要修改上条数据
            if ("1".equals(insureChecklistVO.getType())) {
                insurecusMapper.updateByType(insureChecklistVO);
            }
            insurecusMapper.update(insureChecklistVO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("补录信息失败");
        }
        return Result.success("补录信息成功");
    }

    /**
     * 获取附件信息
     *
     * @param insureChecklistVO 主表
     * @return List<SysBasicFile>
     */
    @Override
    public InsureChecklistVO getFile(InsureChecklistVO insureChecklistVO) {
        InsureChecklistVO insureChecklist = insurecusMapper.getByID(insureChecklistVO);
        List<SysBasicFile> fileListByNoAndType = sysFileMapper.getFileListByNoAndType(insureChecklistVO.getId().toString(), "7");
        insureChecklist.setSysBasicFileList(fileListByNoAndType);
        return insureChecklist;
    }

    /**
     * 判断公司编码 客户编码 保单号是否重复
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     * 1 不重复,不提示
     * 0 重复,提示
     */
    @Override
    public Result judgment(InsureChecklistVO insureChecklistVO) {
        if (1 == insureChecklistVO.getStatus()) {
            int i = insurecusMapper.getJudgment(insureChecklistVO);
            if (i != 0) {
                return Result.success("重复", "0");
            }
        }
        return Result.success("不重复", "1");
    }

    /**
     * 保险清单-删除
     *
     * @param insureChecklistVO 主表
     * @return Result
     */
    @Override
    public Result delete(InsureChecklistVO insureChecklistVO) {

        //InsureChecklistVO byID = insurecusMapper.getByID(insureChecklistVO);
        // 保险评级,保险额度,保险账期维护过不能删除
        /*if (byID.getCreditLevel() != null && byID.getCreditLevel().isEmpty() ||
                byID.getQuota() != null || byID.getQuotaDays() != null) {
            return Result.error("保险评级,保险额度,保险账期维护过不能删除");
        }*/
        insurecusMapper.delete(insureChecklistVO.getId());

        return Result.success();
    }

    /**
     * 查询保单
     *
     * @return List<InsurePolicyVO>
     */
    @Override
    public List<InsurePolicyVO> listPolicy(InsurePolicyVO insurePolicyVO) {
        return insurePolicyMapper.listPolicy(insurePolicyVO);
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
