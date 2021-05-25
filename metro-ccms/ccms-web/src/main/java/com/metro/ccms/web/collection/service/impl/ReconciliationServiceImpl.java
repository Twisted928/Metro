package com.metro.ccms.web.collection.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.service.ISysFileService;
import com.metro.ccms.web.collection.domian.ReconDetailsDO;
import com.metro.ccms.web.collection.domian.ReconRecordDO;
import com.metro.ccms.web.collection.domian.ReconciliationDO;
import com.metro.ccms.web.collection.mapper.ReconciliationMapper;
import com.metro.ccms.web.collection.query.ReconciliationQuery;
import com.metro.ccms.web.collection.service.ReconciliationService;
import com.metro.ccms.web.collection.vo.ReconAndDetailVO;
import com.metro.ccms.web.collection.vo.ReconciliationVO;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;
import com.metro.ccms.web.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
* @description 对账管理
* @author weiwenhui
* @date 2020/12/21 10:34
*/
@Service
public class ReconciliationServiceImpl implements ReconciliationService {
    private static final Logger logger = LoggerFactory.getLogger(ReconciliationServiceImpl.class);
    @Resource
    private ReconciliationMapper reconciliationMapper;
    @Resource
    private ISysFileService iSysFileService;
    @Resource
    private CommonUtils commonUtils;

    @Override
    public List<ReconciliationVO> selectReconciliation(ReconciliationQuery reconciliationQuery) {
        return reconciliationMapper.selectReconciliation(reconciliationQuery);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateStaus(ReconciliationDO reconciliationDO) {
        try {
            //获取当前用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
            if (reconciliationDO.getId()!=null){
                reconciliationDO.setUpdatedBy(loginUser.getUser().getNickName());
                reconciliationMapper.updateStaus(reconciliationDO);
            }else {
                return Result.error("更新对账状态失败！");
            }
        }catch (Exception e){
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("更新对账状态失败！");
        }
        return Result.success("更新对账状态成功！");
    }
    /**
     * 对账单PDF预览根据id查询
     */
    @Override
    public ReconciliationVO selPdfInfo(ReconciliationDO reconciliationDO) {
        return reconciliationMapper.selPdfInfo(reconciliationDO);
    }
    /**
     * 对账单新增查询汇总金额
     */
    @Override
    public ReconciliationDO selMoney(BsegInterfaceDO bsegInterfaceDO) {
        ReconciliationDO reconciliationDO = new ReconciliationDO();
        reconciliationDO.setIar(reconciliationMapper.selIar(bsegInterfaceDO));
        reconciliationDO.setIdue(reconciliationMapper.selIdue(bsegInterfaceDO));
        return reconciliationDO;
    }
    /**
     * 对账单新增查询范围
     */
    @Override
    public List<BsegInterfaceDO> selRange(BsegInterfaceDO bsegInterfaceDO) {
        return reconciliationMapper.selRange(bsegInterfaceDO);
    }

    /**
     * 对账单据新增保存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertRecon(BsegInterfaceDO bsegInterfaceDO) {
        try {
            //获取当前用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
                //获取list字段值
                List<BsegInterfaceDO> bsegInterfaceDOList =reconciliationMapper.selRange(bsegInterfaceDO);
                if(bsegInterfaceDOList.size()<=0){
                    return Result.error("未查到符合条件的数据");
                }
                BsegInterfaceDO b= bsegInterfaceDOList.get(0);
                //查询汇总后的金额
                ReconciliationDO reconciliationDO = this.selMoney(bsegInterfaceDO);
                if (reconciliationDO == null){
                    return Result.error("未查到符合条件的数据");
                }
                reconciliationDO.setCreatedBy(loginUser.getUser().getNickName());
                reconciliationDO.setCardCode(b.getCustomerId());
                reconciliationDO.setCardName(b.getCardName());
                reconciliationDO.setCustCode(b.getCustCode());
                reconciliationDO.setCustName(b.getCustName());
                reconciliationDO.setDepartCode(b.getDepartCode());
                reconciliationDO.setStoreCode(b.getStoreId());
                String id =reconciliationMapper.selmaxid(reconciliationDO);
                String applicationNo = commonUtils.getApplicationNo(Long.parseLong(id==null ? "0":id),7);
                reconciliationDO.setApplicationNo(applicationNo);
                //保存到对账主表中
                reconciliationMapper.saveRecon(reconciliationDO);
                //循环list保存到对账明细表
                for (BsegInterfaceDO bseg:bsegInterfaceDOList
                ) {
                    ReconDetailsDO reconDetailsDO = new ReconDetailsDO();
                    reconDetailsDO.setCreatedBy(loginUser.getUser().getNickName());
                    reconDetailsDO.setApplicationNo(reconciliationDO.getApplicationNo());
                    reconDetailsDO.setCardName(bseg.getCardName());
                    reconDetailsDO.setCustName(bseg.getCustName());
                    reconDetailsDO.setCustCode(bseg.getCustCode());
                    reconDetailsDO.setDepartCode(bseg.getDepartCode());
                    reconDetailsDO.setAmount(bseg.getWrbtr());
                    reconDetailsDO.setCardCode(bseg.getCustomerId());
                    reconDetailsDO.setCsbvcode(bseg.getBelnr());
                    reconDetailsDO.setDduedate(bseg.getZaldt());
                    reconDetailsDO.setDsbvdate(bseg.getBudat());
                    reconDetailsDO.setPaymentTerm(bseg.getZterm());
                    reconDetailsDO.setStandardCurrency(bseg.getlWaers());
                    reconDetailsDO.setStoreCode(bseg.getStoreId());
                    reconciliationMapper.saveReconDetails(reconDetailsDO);
                }
        }catch (Exception e){
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("新增失败！");
        }
        return Result.success("新增成功！");
    }
    /**
    * 对账单详情查询
    */
    @Override
    public ReconAndDetailVO selectReconDetail(ReconciliationDO reconciliationDO) {
        ReconAndDetailVO reconAndDetailVO = new ReconAndDetailVO();
        //查询对账基本信息
        ReconciliationVO recon=reconciliationMapper.getRecon(reconciliationDO.getId());
        //查询对账明细
        List<ReconDetailsDO> reconDetailsDO = reconciliationMapper.getReconDetails(reconciliationDO.getApplicationNo());
        //查询对账记录
        List<ReconRecordDO> recordDOS = reconciliationMapper.getReconRecord(reconciliationDO.getApplicationNo());
        //查询附件
        List<SysBasicFile> sysBasicFiles = iSysFileService.getFileList(reconciliationDO.getApplicationNo(),"11");
        reconAndDetailVO.setReconciliationVO(recon);
        reconAndDetailVO.setReconDetailsDOS(reconDetailsDO);
        reconAndDetailVO.setReconRecordDOS(recordDOS);
        reconAndDetailVO.setSysBasicFile(sysBasicFiles);
        return reconAndDetailVO;
    }
    /**
    *对账单维护台账修改功能
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateReconRecord(ReconAndDetailVO reconAndDetailVO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            //获取台账记录
            List<ReconRecordDO> recordDOS=reconAndDetailVO.getReconRecordDOS();
            //循环页面获取的修改或新增的台账信息
            for (ReconRecordDO recon:recordDOS
                 ) {
                // 判断 IdType是否为纯数字,ture是修改,false是新增
                boolean numeric =isNumeric(recon.getIdType());
                if (numeric){
                    //获取修改的id 根据页面传的id修改台账
                    recon.setId(Long.parseLong(recon.getIdType()));
                    recon.setUpdatedBy(loginUser.getUser().getNickName());
                    //修改台账
                    reconciliationMapper.updateTzInfo(recon);
                }else {
                    //获取对账单的对账单号
                    recon.setApplicationNo(reconAndDetailVO.getReconciliationVO().getApplicationNo());
                    //获取创建人
                    recon.setCreatedBy(loginUser.getUser().getNickName());
                    recon.setDepartCode(reconAndDetailVO.getReconciliationVO().getDepartCode());
                    recon.setStoreCode(reconAndDetailVO.getReconciliationVO().getStoreCode());
                    //保存对账单台账
                    reconciliationMapper.saveTzInfo(recon);
                }
            }
            //如果集合的DeleteId不为空 即删除
            if (reconAndDetailVO.getDeleteId()!=null && !reconAndDetailVO.getDeleteId().isEmpty()){
                for (String id:reconAndDetailVO.getDeleteId()
                ) {
                    reconciliationMapper.delTzInfo(loginUser.getUser().getNickName(),id);
                }
            }

        }catch (Exception e){
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("提交失败！");
        }
        return Result.success("提交成功！");
    }
    /**
     * 判断字符串是否为纯数字
     *
     * @param str string字符串
     * @return boolean
     * true 纯数字
     * false 非纯数字
     */
    public boolean isNumeric(String str) {
        if (str != null && !"".equals(str.trim())) {
            return str.matches("^[0-9]*$");
        } else {
            return false;
        }
    }
    /**
     * 将异常信息转换为string类型
     * @param e 异常
     * @return string后的异常信息
     */
    public static String getExceptionInfo(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        return baos.toString();
    }
}
