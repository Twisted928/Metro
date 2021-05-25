package com.metro.ccms.web.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.entity.SysDept;
import com.metro.ccms.common.core.domain.entity.SysRole;
import com.metro.ccms.common.core.domain.entity.SysUser;
import com.metro.ccms.common.exception.CustomException;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.system.mapper.SysDeptMapper;
import com.metro.ccms.web.activiti.domain.ActBusinessDO;
import com.metro.ccms.web.activiti.mapper.ActModelMapper;
import com.metro.ccms.web.activiti.mapper.AuthorizationMapper;
import com.metro.ccms.web.activiti.service.ActivitiService;
import com.metro.ccms.web.activiti.vo.RoleInfoVO;
import com.metro.ccms.web.activiti.vo.TaskVO;
import com.metro.ccms.web.credit.domain.PayTermDO;
import com.metro.ccms.web.debtprotection.domian.InsureCompanyDO;
import com.metro.ccms.web.debtprotection.query.InsureCompanyQuery;
import com.metro.ccms.web.utils.domain.ApprovalRecordDO;
import com.metro.ccms.web.utils.domain.BasicDataDO;
import com.metro.ccms.web.credit.domain.CustMembersDO;
import com.metro.ccms.web.credit.domain.CustPrimaryDO;
import com.metro.ccms.web.utils.mapper.CommonUtilsMapper;
import com.metro.ccms.web.utils.query.CustMembersQuery;
import com.metro.ccms.web.utils.query.CustPrimaryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static com.metro.ccms.web.utils.SFtpUtil.NO_FILE;
import static java.util.regex.Pattern.*;

/**
 * @Author: fangyongjie
 * @Description: 系统工具类
 * @Date: Created in 14:33 2020/12/9
 * @Modified By:
 */
@Component
public class CommonUtils {

    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

    @Autowired
    private CommonUtilsMapper commonUtilsMapper;
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private ActModelMapper actModelMapper;
    @Autowired
    private AuthorizationMapper authorizationMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SFtpUtil sFtpUtil;


    /**
     * 获取参数表信息
     * @param ctype
     * @return
     */
    public List<BasicDataDO> getBasicDataByCType(String ctype){
        return commonUtilsMapper.getBasicDataByCType(ctype);
    }

    /**
     * 生成单据号
     * @param id 业务表maxid
     * @param type 业务模块 1白名单 2限额申请 3信用复审 4额度转移 5合同管理 6催收管理 7对账管理 8预警模型 9报损案件编号 10单据授权 11角色授权
     * @return
     */
    public String getApplicationNo(Long id,int type) {
        String sq = String.valueOf(id.intValue()+1);
        String applicatioNo = this.getApplicationCode(type);
        applicatioNo = String.format(applicatioNo + "%" + 4 + "s", sq).replace(' ', '0');
        return applicatioNo;
    }

    private String getApplicationCode(int funCode) {
        String applicationCode = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String d = sdf.format(date);
        String s = d.substring(2);
        switch (funCode) {
            case 1:
                applicationCode = "WL" + s;
                break;
            case 2:
                applicationCode = "QA" + s;
                break;
            case 3:
                applicationCode = "CR" + s;
                break;
            case 4:
                applicationCode = "QT" + s;
                break;
            case 5:
                applicationCode = "CM" + s;
                break;
            case 6:
                applicationCode = "DE" + s;
                break;
            case 7:
                applicationCode = "RE" + s;
                break;
            case 8:
                applicationCode = "EW" + s;
                break;
            case 9:
                applicationCode = "AN" + s;
                break;
            case 10:
                applicationCode = "AD" + s;
                break;
            case 11:
                applicationCode = "AR" + s;
                break;
            default:
                applicationCode = s;
                break;
        }
        return applicationCode;
    }

    /**
     * 获取客户主数据列表
     * @param custPrimaryQuery
     * @return
     */
    public List<CustPrimaryDO> getCustPrimaryList(CustPrimaryQuery custPrimaryQuery){
        return commonUtilsMapper.getCustPrimaryList(custPrimaryQuery);
    }

    /**
     * 获取客户卡号关系列表
     * @param custMembersQuery
     * @return
     */
    public List<CustMembersDO> getCustMembersList(CustMembersQuery custMembersQuery){
        return commonUtilsMapper.getCustMembersList(custMembersQuery);
    }

    /**
     * 根据结算方式获取付款条件
     * @param settleType
     * @return
     */
    public List<PayTermDO> getPayTermBySettleType(String settleType){
        return commonUtilsMapper.getPayTermBySettleType(settleType);
    }

    /**
     * 获取保险公司列表
     * @param insureCompanyQuery
     * @return
     */
    public List<InsureCompanyDO> getInsureCompanyList(InsureCompanyQuery insureCompanyQuery){
        return commonUtilsMapper.getInsuranceCompanyList(insureCompanyQuery);
    }

    /**
     * 获取审批记录
     * @param instanceId
     * @return
     */
    public List<ApprovalRecordDO> getApprovalRecord(String instanceId) {
        List<ApprovalRecordDO> approvalDOList = commonUtilsMapper.getAppravalRecord(instanceId);
        List<TaskVO> taskVOList=activitiService.getProcessActive(instanceId);
        ApprovalRecordDO approvalRecordDO=null;
        if(taskVOList!=null && taskVOList.size()>0){
            for (TaskVO taskVO:taskVOList){
                approvalRecordDO=new ApprovalRecordDO();
                approvalRecordDO.setApplicationNo(taskVO.getBusinessId());
                approvalRecordDO.setInstanceId(instanceId);
                approvalRecordDO.setTaskId(taskVO.getTaskId());
                approvalRecordDO.setApprovalUserId(taskVO.getUsername());
                approvalRecordDO.setApprovalUser(commonUtilsMapper.getNickNameByUserName(taskVO.getUsername()));
                approvalRecordDO.setApprovalRole(commonUtilsMapper.getRoleNameByRoleId(Long.parseLong(taskVO.getRoleId())));
                approvalRecordDO.setApprovalRoleId(Long.parseLong(taskVO.getRoleId()));
                if (taskVO.getUsername().equals(SecurityUtils.getUsername())){
                    approvalRecordDO.setIfTask(1);
                }
                approvalDOList.add(approvalRecordDO);
            }
        }
        return approvalDOList;
    }

    /**
     * 保存审批记录
     * @param approvalRecordDO
     * @return
     */
    public Result saveApprovalRecord(ApprovalRecordDO approvalRecordDO){
        commonUtilsMapper.insertApprovalRecord(approvalRecordDO);
        return Result.success();
    }

    /**
     * 判断第一个审批人是否审批
     * @param instanceId
     * @return true 第一个审批人未审批   false 第一个审批人已审批
     */
    public boolean ifFirstApprover(String instanceId){
        if (commonUtilsMapper.getAppravalRecord(instanceId).size()==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 根据流程场景key获取场景信息
     * @param mkey
     * @return
     */
    public Result getBusinessByKey(String mkey){
        ActBusinessDO actBusinessDO=actModelMapper.getBusinessByKey(mkey);
        if (actBusinessDO==null){
            return Result.error("未获取到流程场景信息!");
        }
        if (actBusinessDO.getRoleId()==null || "".equals(actBusinessDO.getRoleId())){
            return Result.error("未获取到发起角色，请检查流程是否配置发起角色!");
        }
        List<SysRole> roles=commonUtilsMapper.getRoleInfoByUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        if (roles==null || roles.size()==0){
            return Result.error("未获取到当前登录人角色，请检查是否配置当前登录人角色信息!");
        }
        boolean ifStart=false;
        StringBuffer bufferId=new StringBuffer();
        StringBuffer bufferName=new StringBuffer();
        String[] strs=actBusinessDO.getRoleId().split(",");
        for (String str:strs){
            for (SysRole sysRole:roles){
                if (str.equals(sysRole.getRoleId().toString())){
                    bufferId.append(sysRole.getRoleId()).append(",");
                    bufferName.append(sysRole.getRoleName()).append(",");
                    ifStart=true;
                    break;
                }
            }
        }
        if (!ifStart){
            return Result.error("当前登录人无发起此审批流权限!");
        }
        actBusinessDO.setRoleId(bufferId.substring(0,bufferId.length()-1));
        actBusinessDO.setRoleName(bufferName.substring(0,bufferName.length()-1));
        actBusinessDO.setIfStart(ifStart);
        return Result.success(actBusinessDO);
    }

    /**
     * 生成客户编码
     * @param creditCode
     * @return
     */
    public Result makeCustomerCode(String creditCode){
        String code="";
        String codeHead="";
        if (StringUtils.isBlank(creditCode)){
            codeHead="C99";
        }else{
            codeHead="C"+creditCode.substring(0,2);
        }
        String custCode=commonUtilsMapper.getCustCodeByHead(codeHead);
        if (StringUtils.isBlank(custCode)){
            code=codeHead+"0000001";
        }else{
            int maxCode=9999999;
            int subCode=Integer.parseInt(custCode.substring(3));
            if (subCode>maxCode){
                return Result.error("客户编码长度超过上限!");
            }
            code=String.format(codeHead + "%07d",subCode+1);
        }
        return Result.success("操作成功",code);
    }

    /**
     * 将Sap门店编码转为CRM门店编码
     * @return
     */
    public String storeCodeSapToCrm(String storeCode){
        String crmStoreCode="";
        if ("4080".equals(storeCode)){
            crmStoreCode="80";
        }else{
            crmStoreCode= String.valueOf(Integer.parseInt(storeCode)-1000);
        }
        return crmStoreCode;
    }

    /**
     * 将Sap卡号编码转为CRM卡号编码
     * @return
     */
    public String cardCodeSapToCrm(String cardCode){
        String crmCardCode="";
        String a=cardCode.substring(4,5);
        Pattern pattern = compile("^[-\\+]?[\\d]*$");
        if (pattern.matcher(a).matches()){
            crmCardCode=String.valueOf(Integer.parseInt(cardCode.substring(4)));
        }else{
            switch (a) {
                case "A":
                    crmCardCode="20"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "B":
                    crmCardCode="21"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "C":
                    crmCardCode="22"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "D":
                    crmCardCode="23"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "E":
                    crmCardCode="24"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "F":
                    crmCardCode="25"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "G":
                    crmCardCode="26"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "H":
                    crmCardCode="27"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "I":
                    crmCardCode="28"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "J":
                    crmCardCode="29"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "K":
                    crmCardCode="30"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "L":
                    crmCardCode="31"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "M":
                    crmCardCode="32"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "N":
                    crmCardCode="33"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "O":
                    crmCardCode="34"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "P":
                    crmCardCode="35"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "Q":
                    crmCardCode="36"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "R":
                    crmCardCode="37"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "S":
                    crmCardCode="38"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "T":
                    crmCardCode="39"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "U":
                    crmCardCode="40"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "V":
                    crmCardCode="41"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "W":
                    crmCardCode="42"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "X":
                    crmCardCode="43"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "Y":
                    crmCardCode="44"+Integer.parseInt(cardCode.substring(5));
                    break;
                case "Z":
                    crmCardCode="45"+Integer.parseInt(cardCode.substring(5));
                    break;
                default:
                    break;
            }
        }
        return crmCardCode;
    }

    /**
     * 获取当前登录人人员信息、部门信息、角色信息
     * @return
     */
    public Result getBasicUserInfo(){
        SysUser sysUser=commonUtilsMapper.getUserInfoByUserName(SecurityUtils.getUsername());
        List<RoleInfoVO> roleInfo=authorizationMapper.getRoleByUser(SecurityUtils.getLoginUser().getUser().getUserId());
        List<SysRole> roles=new ArrayList<>();
        for (RoleInfoVO roleInfoVO:roleInfo){
            SysRole sysRole=new SysRole();
            sysRole.setRoleId(roleInfoVO.getRoleId());
            sysRole.setRoleName(roleInfoVO.getRoleName());
            roles.add(sysRole);
        }
        sysUser.setRoles(roles);
        sysUser.setDepts(sysDeptMapper.selectDeptsByUserName(SecurityUtils.getUsername()));
        return Result.success(sysUser);
    }


    /**
     * 下载单个文件
     * @param directory      远程下载目录(以路径符号结束)
     * @param remoteFileName FTP服务器文件名称 如：xxx.txt ||xxx.txt.zip
     * @param localFile      本地文件路径 如 D:\\xxx.txt
     * @return
     */
    public File downloadFile(String directory, String remoteFileName, String localFile){
        log.info("ftp下载文件" + remoteFileName + "开始");
        ChannelSftp sftp=sFtpUtil.connect();
        File file = null;
        OutputStream output = null;
        try {
            file = new File(localFile);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            //进入FTP服务器文件目录
            sftp.cd(directory);
            output = new FileOutputStream(file);
            sftp.get(remoteFileName, output);
            log.info("DownloadFile:" + remoteFileName + "success from sftp");
        } catch (SftpException e) {
            if (e.toString().equals(NO_FILE)) {
                log.info("sftp下载文件失败" + directory + remoteFileName + "不存在");
                throw new CustomException("ftp下载文件失败" + directory + remoteFileName + "不存在");
            }
            throw new CustomException("ftp目录或者文件异常，检查ftp目录和文件" + e.toString());
        } catch (FileNotFoundException e) {
            throw new CustomException("本地目录异常,请检查" + file.getPath() + e.getMessage());
        } catch (IOException e) {
            throw new CustomException("创建本地文件失败" + file.getPath() + e.getMessage());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    throw new CustomException("Close stream error" + e.getMessage());
                }
            }
            sFtpUtil.disconnect();
        }

        log.info("ftp下载文件结束");
        return file;
    }

}
