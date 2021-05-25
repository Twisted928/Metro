package com.metro.ccms.web.customer.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.service.ISysFileService;
import com.metro.ccms.web.activiti.config.BusinessEnum;
import com.metro.ccms.web.activiti.domain.ActBusinessDO;
import com.metro.ccms.web.activiti.domain.StartDO;
import com.metro.ccms.web.activiti.domain.TaskDO;
import com.metro.ccms.web.activiti.service.ActivitiService;
import com.metro.ccms.web.credit.domain.CustMembersDO;
import com.metro.ccms.web.customer.domian.WhitelistmanagementDO;
import com.metro.ccms.web.customer.mapper.WhitelistmanagementMapper;
import com.metro.ccms.web.customer.query.WhitelistmanagementQuery;
import com.metro.ccms.web.customer.service.WhitelistmanagementService;
import com.metro.ccms.web.customer.vo.WhitelistmanagementVO;
import com.metro.ccms.web.customer.vo.WhitelistmanagementqdVO;
import com.metro.ccms.web.utils.CommonUtils;
import com.metro.ccms.web.utils.domain.ApprovalRecordDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *<p>
 *  白名单管理
 * </p>
 * 申请的时候判断 是否在黑白名单中存在，已经存在不能申请
 *  页面查询根据客户查询最新一笔客户状态。
 *  清单中移出把有效状态改成无效。
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
@Service
public class WhitelistmanagementServiceImpl implements WhitelistmanagementService {

    private static final Logger logger = LoggerFactory.getLogger(WhitelistmanagementServiceImpl.class);

    @Autowired
    private WhitelistmanagementMapper whitelistmanagementMapper;
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private ISysFileService iSysFileService;
    @Autowired
    private ActivitiService activitiService;

    /**
     * 查询
     *
     * @param whitelistmanagementQuery
     * @return
     */
    @Override
    public List<WhitelistmanagementVO> pagesel(WhitelistmanagementQuery whitelistmanagementQuery) {
        List<WhitelistmanagementVO> list=whitelistmanagementMapper.pagesel(whitelistmanagementQuery);
        return list;
    }
    /**
     * 查询详情
     *
     * @param whitelistmanagementQuery
     * @return
     */
    @Override
    public WhitelistmanagementVO seldetails(WhitelistmanagementQuery whitelistmanagementQuery) {
        WhitelistmanagementVO whitelistmanagementVO=whitelistmanagementMapper.seldetails(whitelistmanagementQuery);
        //获取附件列表
        List<SysBasicFile> list=iSysFileService.getFileList(whitelistmanagementQuery.getId(),"1");
        whitelistmanagementVO.setSysBasicFile(list);
        //获取审批记录
        if(whitelistmanagementVO.getInstanceid()!=null){
            List<ApprovalRecordDO> approvalRecordDO=commonUtils.getApprovalRecord(whitelistmanagementVO.getInstanceid());
            whitelistmanagementVO.setApprovalRecordDO(approvalRecordDO);
        }
        return whitelistmanagementVO;
    }
    /**
     *<p>
     *  校验黑白名单中是否存在
     * </p>
     *
     * @author  JIXIANG.SONG
     * @create  2020/12/10
     * @desc
     **/
    public String hbmingdan (WhitelistmanagementDO whitelistmanagementDO){
        //判断黑名单是否存在
        int black=whitelistmanagementMapper.selhmdcount(whitelistmanagementDO);
        if(black>0){
            return "客户编码在黑名单中存在！";
        }
        int white=whitelistmanagementMapper.selwhitecount(whitelistmanagementDO);
        if(white>0){
            return "客户编码在白名单中已经存在有效数据！";
        }
        return "success";
    }
    /**
     * 新增
     *  判断是否在黑名单存在，已存在提示先移除黑名单再申请，
     *  判断在白名单清单已经存在，已经存在提示不用申请
     * @param whitelistmanagementDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result savemonitoring(WhitelistmanagementDO whitelistmanagementDO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            whitelistmanagementDO.setCreatedby(loginUser.getUser().getNickName());
            whitelistmanagementDO.setApprovestatus("1");
            //判断黑名单是否存在
            String ifhb=hbmingdan(whitelistmanagementDO);
            String ret="success";
            if(!ret.equals(ifhb)){
                return Result.error(ifhb);
            }
            //查询最大ID,生成单号
            String id =whitelistmanagementMapper.selmaxid(whitelistmanagementDO);
            String applicatioNo=commonUtils.getApplicationNo(Long.parseLong(id==null ? "0":id),1);
            whitelistmanagementDO.setApplicationno(applicatioNo);
            whitelistmanagementMapper.savemonitoring(whitelistmanagementDO);
            //调用审批流方法
            Result shenpiliu=shenpiliu(whitelistmanagementDO);
            //判断审批流是否成功，失败需要回滚
            if(!shenpiliu.isSuccess()){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error(shenpiliu.get("msg").toString());
            }
             whitelistmanagementMapper.updateno(whitelistmanagementDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("保存失败");
        }

        return Result.success(whitelistmanagementDO);
    }
        /**
         * @Param 申请保存调用审批流
         * @description
         * @author JiXiang.Song
         * @date 2020/12/30
         * @return
         * @throws
         */
    public Result shenpiliu(WhitelistmanagementDO whitelistmanagementDO){

        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //审批流每一步方法操作都需要保存结果
        ApprovalRecordDO approvalRecordDO =new ApprovalRecordDO();
        //startdo中的参数前台传入
        StartDO startDO=new StartDO();
        Map<String,Object> mapVariable=new HashMap<>();
        mapVariable.put("approvalFlag","APPROVE");
        startDO.setVariable(mapVariable);
        //根据当前登陆人获取对应角色信息，
        Result qx=commonUtils.getBusinessByKey(BusinessEnum.CREDIT_WHITE_1.toString());
        if(qx.isSuccess()){
            ActBusinessDO actBusinessDO=(ActBusinessDO)qx.get("data");
            //审批角色
            approvalRecordDO.setApprovalRole(actBusinessDO.getRoleName());
            //审批角色id
            approvalRecordDO.setApprovalRoleId(Long.valueOf(actBusinessDO.getRoleId()));
        }else{
            return Result.error(qx.get("msg").toString());
        }
        //部门编码，此处部门需要根据客户编码查询出对应部门编码集合
        List<String> depts=new ArrayList<>();
        List<CustMembersDO> list=whitelistmanagementMapper.seldeplist(whitelistmanagementDO);
        for (CustMembersDO dep: list) {
            depts.add(dep.getStoreCode());
        }
        //部门编码
        startDO.setDeptCodes(depts);
        //业务表id或单号
        startDO.setBusinessId(whitelistmanagementDO.getApplicationno());
        startDO.setBusinessKey(BusinessEnum.CREDIT_WHITE_1);
        //页面URL,前台传入待修改
        startDO.setFormUrl(whitelistmanagementDO.getFormUrl());
        //审批流程标题
        startDO.setTitle("白名单审批");
        //客户名称
        startDO.setBusinessName(whitelistmanagementDO.getCustname());
        //发起人
        startDO.setStartUser(loginUser.getUser().getUserName());
        Result spl=activitiService.startProcess(startDO);
        //判断审批流是否成功，失败需要回滚.
        if(!spl.isSuccess()){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error(spl.get("msg").toString());
        }
        whitelistmanagementDO.setInstanceid(spl.get("data").toString());
        //需要给approvalRecordDO赋值
        //单号
        approvalRecordDO.setApplicationNo(whitelistmanagementDO.getApplicationno());
        //审批意见
        approvalRecordDO.setApprovalOpinion("SUBMIT");
        //审批人
        approvalRecordDO.setApprovalUser(loginUser.getUser().getNickName());
        //审批人id
        approvalRecordDO.setApprovalUserId(loginUser.getUser().getUserId().toString());
        //流程实例id
        approvalRecordDO.setInstanceId(whitelistmanagementDO.getInstanceid());
        //任务id,新增时可以空着
        approvalRecordDO.setTaskId("");
        saveApprovalRecord(approvalRecordDO);

        return Result.success("创建审批流程成功");
    }
    /**
     *  审批流每一步方法操作都需要保存结果
     * @description
     */
    public Result saveApprovalRecord(ApprovalRecordDO approvalRecordDO){
        return commonUtils.saveApprovalRecord(approvalRecordDO);
    }

    /**
     * 修改
     * @param whitelistmanagementDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updmonitoring(WhitelistmanagementDO whitelistmanagementDO) {
        try {
            if(whitelistmanagementDO.getInstanceid()==null){
                return Result.error("流程实例id不能为空！");
            }
            //修改前需要判断审批流是否已经审核，未审核的才可以进行修改(true 第一个审批人未审批   false 第一个审批人已审批)
            boolean ifshenpi=commonUtils.ifFirstApprover(whitelistmanagementDO.getInstanceid());
            if(ifshenpi) {
                //根据id判断是否审批通过或者退回
                int ifyn = whitelistmanagementMapper.selwhite(whitelistmanagementDO);
                if (ifyn > 0) {
                    return Result.error("当前数据已经审批或者撤销不能修改！");
                }
                // 获取当前登陆人信息
                LoginUser loginUser = SecurityUtils.getLoginUser();
                whitelistmanagementDO.setUpdatedby(loginUser.getUser().getNickName());
                whitelistmanagementDO.setApprovestatus("1");
                whitelistmanagementMapper.updmonitoring(whitelistmanagementDO);
            }else{
                return Result.error("第一个审批人审批之后不允许修改！");
            }
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }
    /**
     * 删除
     * 已撤销的才可以删除
     * @param whitelistmanagementDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delmonitoring(WhitelistmanagementDO whitelistmanagementDO) {
        try {
            //根据id判断是否撤销
            int cxcount=whitelistmanagementMapper.selcxwhite(whitelistmanagementDO);
            if(cxcount<=0){
                return Result.error("只有撤销状态的单据才可以删除！");
            }
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            whitelistmanagementDO.setUpdatedby(loginUser.getUser().getNickName());
            whitelistmanagementMapper.delmonitoring(whitelistmanagementDO);
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
     * 审批
     * 任意节点驳回，审批流程结束,审批状态根据前端传入状态更新
     * @param whitelistmanagementDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updatemonitoring(WhitelistmanagementDO whitelistmanagementDO) {
        try {
            Map<String,Object> mapVariable=new HashMap<>();
            ApprovalRecordDO approvalRecordDO=new ApprovalRecordDO();
            Result fanhui;
            String approvalOpinion="";
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            whitelistmanagementDO.setUpdatedby(loginUser.getUser().getNickName());
            whitelistmanagementDO.setCreatedby(loginUser.getUser().getNickName());
            //根据id判断是否审批通过或者退回
            int ifyn=whitelistmanagementMapper.selwhite(whitelistmanagementDO);
            if(ifyn>0){
                return Result.error("当前数据已经审批或者撤销不需要审批！");
            }
            //根据当前登陆人获取对应角色信息，
            Result qx=commonUtils.getBusinessByKey(BusinessEnum.CREDIT_WHITE_1.toString());
            if(qx.isSuccess()){
                ActBusinessDO actBusinessDO=(ActBusinessDO)qx.get("data");
                //审批角色
                approvalRecordDO.setApprovalRole(actBusinessDO.getRoleName());
                //审批角色id
                approvalRecordDO.setApprovalRoleId(Long.valueOf(actBusinessDO.getRoleId()));
            }else{
                return Result.error(qx.get("msg").toString());
            }
            //审批通过
            String tongguo="APPROVE";
            if(tongguo.equals(whitelistmanagementDO.getApprovalOpinion())){
                //判断黑白名单是否存在
                String ifhb=hbmingdan(whitelistmanagementDO);
                String ret="success";
                if(!ret.equals(ifhb.toString())){
                    return Result.error(ifhb);
                }
                mapVariable.put("approvalFlag","APPROVE");
                approvalOpinion="APPROVE";
                //代办ID、登陆人、审批意见、是否结束流程、任务参数
                TaskDO taskDO=new TaskDO(whitelistmanagementDO.getTaskId(),loginUser.getUser().getNickName(),approvalOpinion,false,mapVariable);
                //调用审批流
                fanhui=activitiService.completeTask(taskDO);
                //判断审批流程是否结束
                if(activitiService.isEnded(whitelistmanagementDO.getInstanceid())) {
                    whitelistmanagementDO.setStatus("1");
                    whitelistmanagementDO.setDeleted("1");
                    whitelistmanagementDO.setApprovestatus("2");
                    whitelistmanagementMapper.insertwhite(whitelistmanagementDO);
                    whitelistmanagementMapper.insertwhitels(whitelistmanagementDO);
                    //更新审批状态
                    whitelistmanagementMapper.updatemonitoring(whitelistmanagementDO);
                }
            }else{
                //审批退回
                mapVariable.put("approvalFlag","REJECT");
                approvalOpinion="REJECT";
                //代办ID、登陆人、审批意见、是否结束流程、任务参数
                TaskDO taskDO=new TaskDO(whitelistmanagementDO.getTaskId(),loginUser.getUser().getNickName(),approvalOpinion,false,mapVariable);
                //调用审批流
                fanhui=activitiService.completeTask(taskDO);
                //更新审批状态
                whitelistmanagementDO.setApprovestatus("3");
                whitelistmanagementMapper.updatemonitoring(whitelistmanagementDO);
            }
            //判断审批流是否成功，失败需要回滚.
            if(!fanhui.isSuccess()){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error("审批流程失败！");
            }
//流程撤销传入值
            //单号
            approvalRecordDO.setApplicationNo(whitelistmanagementDO.getApplicationno());
            //审批意见
            approvalRecordDO.setApprovalOpinion(approvalOpinion);
            //审批描述
            approvalRecordDO.setRemark(whitelistmanagementDO.getRemark());
            //审批人
            approvalRecordDO.setApprovalUser(loginUser.getUser().getNickName());
            //审批人id
            approvalRecordDO.setApprovalUserId(loginUser.getUser().getUserId().toString());
            //流程实例id
            approvalRecordDO.setInstanceId(whitelistmanagementDO.getInstanceid());
            //任务id,前台传入
            approvalRecordDO.setTaskId(whitelistmanagementDO.getTaskId());
            saveApprovalRecord(approvalRecordDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("审批失败");
        }
        return Result.success("审批成功");
    }
    /**
     *  撤销
     *  撤销状态改为4
     * @param whitelistmanagementDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deletemonitoring(WhitelistmanagementDO whitelistmanagementDO) {
        try {
            ApprovalRecordDO approvalRecordDO=new ApprovalRecordDO();
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            whitelistmanagementDO.setUpdatedby(loginUser.getUser().getNickName());
            //根据当前登陆人获取对应角色信息，
            Result qx=commonUtils.getBusinessByKey(BusinessEnum.CREDIT_WHITE_1.toString());
            if(qx.isSuccess()){
                ActBusinessDO actBusinessDO=(ActBusinessDO)qx.get("data");
                //审批角色
                approvalRecordDO.setApprovalRole(actBusinessDO.getRoleName());
                //审批角色id
                approvalRecordDO.setApprovalRoleId(Long.valueOf(actBusinessDO.getRoleId()));
            }else{
                return Result.error(qx.get("msg").toString());
            }
            //判断是否当前登陆人申请单据
            int a=whitelistmanagementMapper.selcount(whitelistmanagementDO);
                if(a>0){
                    //审批人待办信息同步撤回，单据流程结束
                    whitelistmanagementDO.setApprovestatus("4");
                    whitelistmanagementMapper.updatemonitoring(whitelistmanagementDO);
                }else{
                    return Result.error("只能撤销自己申请的单据");
                }
            //流程撤销
            Result cxlc=activitiService.rollback(whitelistmanagementDO.getInstanceid());
            //判断审批流撤销是否成功，失败需要回滚
            if(!cxlc.isSuccess()){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error("流程撤销失败！");
            }
//流程撤销传入值
            //单号
            approvalRecordDO.setApplicationNo(whitelistmanagementDO.getApplicationno());
//审批意见
            approvalRecordDO.setApprovalOpinion("ROLLBACK");
            //审批人
            approvalRecordDO.setApprovalUser(loginUser.getUser().getNickName());
            //审批人id
            approvalRecordDO.setApprovalUserId(loginUser.getUser().getUserId().toString());
            //流程实例id
            approvalRecordDO.setInstanceId(whitelistmanagementDO.getInstanceid());
            //任务id,不在详情页进入可以为空
            approvalRecordDO.setTaskId(whitelistmanagementDO.getTaskId());
            saveApprovalRecord(approvalRecordDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("撤销失败");
        }
        return Result.success("撤销成功");
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


    /**
     * 申请表查询
     *
     * @param whitelistmanagementQuery
     * @return
     */
    @Override
    public List<WhitelistmanagementqdVO> pageselqd(WhitelistmanagementQuery whitelistmanagementQuery) {
        List<WhitelistmanagementqdVO> list=whitelistmanagementMapper.pageselqd(whitelistmanagementQuery);
        return list;
    }
    /**
     * 申请表历史记录
     *
     * @param whitelistmanagementQuery
     * @return
     */
    @Override
    public List<WhitelistmanagementqdVO> pagesells(WhitelistmanagementQuery whitelistmanagementQuery) {
        List<WhitelistmanagementqdVO> list=whitelistmanagementMapper.pagesells(whitelistmanagementQuery);
        return list;
    }
    /**
     * 移出
     * 维护白名单移出原因，提交单据，发起审批流。
     * @param whitelistmanagementDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result remove(WhitelistmanagementDO whitelistmanagementDO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            whitelistmanagementDO.setCreatedby(loginUser.getUser().getNickName());
            whitelistmanagementDO.setUpdatedby(loginUser.getUser().getNickName());
            whitelistmanagementDO.setStatus("0");
            whitelistmanagementDO.setDeleted("1");
            whitelistmanagementMapper.updqd(whitelistmanagementDO);
            whitelistmanagementMapper.insertwhitels(whitelistmanagementDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("移出失败");
        }
        return Result.success("移出成功");
    }
}
