package com.metro.ccms.web.debtprotection.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.web.debtprotection.domian.InsureDO;
import com.metro.ccms.web.debtprotection.domian.InvoiceDO;
import com.metro.ccms.web.debtprotection.mapper.ClaimMapper;
import com.metro.ccms.web.debtprotection.mapper.InsureMapper;
import com.metro.ccms.web.debtprotection.mapper.InvoiceMapper;
import com.metro.ccms.web.debtprotection.service.InsuranceManagementService;
import com.metro.ccms.web.debtprotection.vo.InsuranceManagementVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 投标管理
 *
 * @author guangle
 * @date 2020/12/8
 * @since 1.0.0
 */
@Service
public class InsuranceManagementServiceImpl implements InsuranceManagementService {

    private static final Logger logger = LoggerFactory.getLogger(InsuranceManagementServiceImpl.class);


    @Autowired
    private InsureMapper insureMapper;
    @Autowired
    private ClaimMapper claimMapper;
    @Autowired
    private InvoiceMapper invoiceMapper;

    /**
     * 投标管理-查询
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return List<InsuranceManagementVO>
     */
    @Override
    public List<InsuranceManagementVO> list(InsuranceManagementVO insuranceManagementVO) {
        List<InsuranceManagementVO> insuranceManagementVOList = new ArrayList<>();

        // 把前端传回来的汇总发票日期查询条件里面的结束时间转换为 yyyy_mm_dd 23:59:59
        if(insuranceManagementVO.getEndDate() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(insuranceManagementVO.getEndDate());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            insuranceManagementVO.setEndDate(calendar.getTime());
        }

        // 判断查询是未投保还是已投保 1未投保 其他均算已投保
        if (insuranceManagementVO.getStatus() == 1) {
            insuranceManagementVOList = invoiceMapper.list(insuranceManagementVO);
        } else  {
            insuranceManagementVOList = insureMapper.list(insuranceManagementVO);
        }
        return insuranceManagementVOList;
    }

    /**
     * 投标管理-投标
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(InsuranceManagementVO insuranceManagementVO) {

        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        insuranceManagementVO.setCreatedBy(loginUser.getUser().getNickName()); // 创建人
        try {
            for (String id : insuranceManagementVO.getIdList()) {
                InvoiceDO invoice = invoiceMapper.selectByPrimaryKey(Long.parseLong(id));
                // 校验是否是以投保的
                if ("1".equals(invoice.getBaseapply())){
                    throw new Exception("当前选择的单据中,存在已经投保过的单据,请去除后重试");
                }
                // 修改未投保发票表状态改成已投保 baseapply=1
                invoiceMapper.update(id);
                InvoiceDO invoiceDO = invoiceMapper.selectByPrimaryKey(Long.parseLong(id));
                // 保存到已投保发票表
                InsureDO insureDO = new InsureDO();
                BeanUtils.copyProperties(invoiceDO, insureDO);
                if (invoiceDO.getInvoicesum() != null){
                    insureDO.setInvoicesum(BigDecimal.valueOf(invoiceDO.getInvoicesum()));
                }
                insureMapper.save(insureDO);
            }


        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("投保失败");
        }

        return Result.success("投保成功");
    }

    /**
     * 投标管理-删除
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delete(InsuranceManagementVO insuranceManagementVO) {


        try {
            for (InsureDO insureDO : insuranceManagementVO.getInsureDOList()) {
                // 判断是否参与了报损
                int i = claimMapper.getByInsureID(insureDO.getId().toString());
                if (i > 0){
                    throw new Exception("当前选择的保单以报损,无法直接删除");
                }
                // 删除已投保发票表
                insureMapper.deleteByPrimaryKey(insureDO.getId());
                // 修改未投保发票表状态改成未投保 baseapply=0
                invoiceMapper.delete(insureDO);
            }
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error(e.getMessage());
        }

        return Result.success("删除成功");
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
