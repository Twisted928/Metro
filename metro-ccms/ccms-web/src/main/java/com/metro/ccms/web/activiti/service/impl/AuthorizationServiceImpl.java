package com.metro.ccms.web.activiti.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.web.activiti.domain.AutherDocumentMainDO;
import com.metro.ccms.web.activiti.domain.AutherRoleMainDO;
import com.metro.ccms.web.activiti.mapper.AuthorizationMapper;
import com.metro.ccms.web.activiti.domain.AutherDocumentDO;
import com.metro.ccms.web.activiti.domain.AutherRoleDO;
import com.metro.ccms.web.activiti.vo.RoleInfoVO;
import com.metro.ccms.web.activiti.query.AutherDocumentQuery;
import com.metro.ccms.web.activiti.query.AutherRoleQuery;
import com.metro.ccms.web.activiti.service.AuthorizationService;
import com.metro.ccms.web.activiti.service.ActivitiService;
import com.metro.ccms.web.activiti.vo.TaskVO;
import com.metro.ccms.web.utils.CommonUtils;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 单据/角色授权服务
 * Created by fangyongjie
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationMapper authorizationMapper;
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private TaskService taskService;



    /**
     * 分页查询单据授权
     * @param autherDocumentQuery
     * @return
     */
    @Override
    public List<AutherDocumentDO> getAutherDocumentlistPage(AutherDocumentQuery autherDocumentQuery) {
        return authorizationMapper.getAutherDocumentlistPage(autherDocumentQuery);
    }

    /**
     * 获取角色下对应的人
     * @param roleId
     * @return
     */
    @Override
    public List<RoleInfoVO> getRoleUserInfoList(Long roleId) {
        return authorizationMapper.getRoleUserInfo(roleId);
    }

    /**
     * 新增单据授权
     * @param list
     * @return
     */
    @Override
    public Result auther(List<AutherDocumentDO> list) {
        if (list==null || list.size()==0){
            return Result.error("授权单据信息不能为空!");
        }
        StringBuffer receiveId=new StringBuffer();
        StringBuffer dateTime=new StringBuffer();
        for (AutherDocumentDO autherDo:list){
            if (autherDo.getReceiveId()==null){
                receiveId.append(autherDo.getApplicationNo()).append(",");
            }
            if (autherDo.getBeginTime()==null || autherDo.getEndTime()==null){
                dateTime.append(autherDo.getApplicationNo()).append(",");
            }
        }
        /** 校验接收人、生失效时间 */
        if (receiveId!=null && receiveId.length()>0){
            return Result.error("单号"+receiveId.substring(0,receiveId.length()-1)+"单据未选择接收人!");
        }
        if (dateTime!=null && dateTime.length()>0){
            return Result.error("单号"+dateTime.substring(0,receiveId.length()-1)+"单据未选择有效区间!");
        }
        /** 检验是否重复授权 */
        for (AutherDocumentDO autherDo:list){
            List<AutherDocumentDO> autherIf= authorizationMapper.getAutherDoCountByTaskId(autherDo.getTaskId());
            if (autherIf!=null && autherIf.size()>0){
                for (AutherDocumentDO adDo:autherIf){
                    if (adDo.getReceiveId().intValue()==autherDo.getReceiveId().intValue()){
                        return Result.error("单据"+adDo.getApplicationNo()+"已授权给"+adDo.getReceiveUser());
                    }
                }
            }
        }

        /** 授权单据并保存到授权表 */
        StringBuffer msg=new StringBuffer();
        for (AutherDocumentDO autherDocumentDO:list){
            Result r= activitiService.addCandidateUser(autherDocumentDO.getTaskId(),autherDocumentDO.getReceiveId().toString());
            if (!r.isSuccess()){
                msg.append(autherDocumentDO.getApplicationNo()).append(",");
            }else{
                autherDocumentDO.setAutherId(SecurityUtils.getLoginUser().getUser().getUserId());
                autherDocumentDO.setAutherUser(SecurityUtils.getLoginUser().getUser().getNickName());
                List<RoleInfoVO> roleInfoVOList = authorizationMapper.getRoleByUser(SecurityUtils.getLoginUser().getUser().getUserId());
                if (roleInfoVOList !=null && roleInfoVOList.size()>0){
                    StringBuffer idBuffer=new StringBuffer();
                    StringBuffer nameBuffer=new StringBuffer();
                    for (RoleInfoVO infoVO:roleInfoVOList){
                        idBuffer.append(infoVO.getRoleId()).append(",");
                        nameBuffer.append(infoVO.getRoleName()).append(",");
                    }
                    autherDocumentDO.setAutherRoleId(idBuffer.substring(0,idBuffer.length()-1));
                    autherDocumentDO.setAutherRoleName(nameBuffer.substring(0,nameBuffer.length()-1));
                }
//                autherDocumentDO.setType(autherDocumentDO.getType());
                authorizationMapper.saveAutherDocument(autherDocumentDO);
            }
        }
        if (msg!=null && msg.length()>0){
            return Result.success("单据号为:"+msg.substring(0,msg.length()-1)+"的单据未授权成功!");
        }
        return Result.success();
    }

    /**
     * 修改单据授权
     * @param list
     * @return
     */
    @Override
    public Result updateAuther(List<AutherDocumentDO> list) {
        if (list==null && list.size()==0){
            return Result.error("未获取到删除信息!");
        }
        StringBuffer buffer=new StringBuffer();
        for (AutherDocumentDO autherDo:list){
            Task task=taskService.createTaskQuery().processInstanceId(autherDo.getInstanceId()).active().singleResult();
            if (task!=null && !task.getId().equals(autherDo.getTaskId())){
                buffer.append(autherDo.getApplicationNo()).append(",");
            }
        }
        if (buffer!=null && buffer.length()>0){
            return Result.error("单据:"+buffer.substring(0, buffer.length()-1)+"的授权待办已审批，无法删除!");
        }
        StringBuffer bufferDel=new StringBuffer();
        for (AutherDocumentDO autherDo:list){
            Result r=activitiService.deleteCandidateUser(autherDo.getTaskId(),autherDo.getReceiveId().toString());
            if (!r.isSuccess()){
                buffer.append(autherDo.getApplicationNo()).append(",");
            }else{
                authorizationMapper.deleteAutherDoById(autherDo.getId());
            }
        }
        if (bufferDel!=null && bufferDel.length()>0){
            return Result.success("单据:"+buffer.substring(0,buffer.length()-1)+"删除候选人失败!");
        }
        return Result.success();
    }

    /**
     * 根据授权单据主表id获取授权单据信息
     * @param mainId
     * @return
     */
    @Override
    public List<AutherDocumentDO> getAutherDoListByMainId(Long mainId) {
        return authorizationMapper.getAutherDoListByMainId(mainId);
    }

    /**
     * 删除单据授权主表
     * @param id
     * @return
     */
    @Override
    public Result deleteAutherDoMain(Long id) {
        List<AutherDocumentDO> list=this.getAutherDoListByMainId(id);
        Result r=this.updateAuther(list);
        if (!r.isSuccess()){
            return Result.error(r.get("msg").toString());
        }
        authorizationMapper.deleteAutherDoMainById(id);
        return Result.success();
    }

    /**
     * 单据授权-作废
     * @param autherDocumentDO
     * @return
     */
    @Override
    public Result deleteAutherDo(AutherDocumentDO autherDocumentDO){
        Task task=taskService.createTaskQuery().processInstanceId(autherDocumentDO.getInstanceId()).active().singleResult();
        if (task!=null && !task.getId().equals(autherDocumentDO.getTaskId())){
            return Result.error("此授权待办已审批，无法删除!");
        }
        Result r=activitiService.deleteCandidateUser(autherDocumentDO.getTaskId(),autherDocumentDO.getReceiveId().toString());
        if (!r.isSuccess()){
            return Result.success("删除候选人失败!");
        }else{
            authorizationMapper.deleteDo(autherDocumentDO.getId());
        }
        return Result.success();
    }

    /**
     * 分页查询角色授权
     * @param autherRoleQuery
     * @return
     */
    @Override
    public List<AutherRoleDO> getAutherRolelistPage(AutherRoleQuery autherRoleQuery) {
        return authorizationMapper.getAutherRolelistPage(autherRoleQuery);
    }

    /**
     * 新增授权角色
     * @param list
     * @return
     */
    @Override
    public Result autherRole(List<AutherRoleDO> list) {
        if (list==null || list.size()==0){
            return Result.error("角色授权信息不能为空!");
        }
        /** 校验接收人、生失效时间 */
        for (AutherRoleDO autherDo:list){
            if (autherDo.getReceiveId()==null){
                return Result.error("接收人不能为空!");
            }
            if (autherDo.getBeginTime()==null || autherDo.getEndTime()==null){
                return Result.error("生失效时间不能为空!");
            }
        }

        /** 检验接收人已有的角色与接收人被授权的角色是否重合，重合则返回错误提示 */
        Map<Long, List<AutherRoleDO>> map = list.stream().collect(Collectors.groupingBy(AutherRoleDO::getReceiveId));
        for (Map.Entry<Long, List<AutherRoleDO>> entry:map.entrySet()){
            List<RoleInfoVO> roleList = authorizationMapper.getRoleByUser(entry.getKey());
            if (roleList!=null && roleList.size()>0){
                for (RoleInfoVO roleInfoVO:roleList){
                    for (AutherRoleDO autherDO:entry.getValue()){
                        if (autherDO.getAutherRole().intValue()==roleInfoVO.getRoleId().intValue()){
                            return Result.error("接收人"+autherDO.getReceiveUser()+"已拥有"+autherDO.getAutherRoleNa()+"角色,无需授权此角色!");
                        }
                    }
                }
            }
        }

        /** 检验是否重复授权 */
        for (AutherRoleDO roleDO :list){
            int count = authorizationMapper.getAutherRoleByUser(roleDO.getAutherRole(),roleDO.getReceiveId());
            if (count>0){
                return Result.error("角色:"+ roleDO.getAutherRoleNa()+"已授权给"+roleDO.getReceiveUser());
            }
        }

        /** 保存角色授权信息 */
        for (AutherRoleDO autherDo :list){
            autherDo.setAutherId(SecurityUtils.getLoginUser().getUser().getUserId());
            autherDo.setAutherUser(SecurityUtils.getLoginUser().getUser().getNickName());
            List<RoleInfoVO> roleInfoVOList = authorizationMapper.getRoleByUser(SecurityUtils.getLoginUser().getUser().getUserId());
            if (roleInfoVOList !=null && roleInfoVOList.size()>0){
                StringBuffer idBuffer=new StringBuffer();
                StringBuffer nameBuffer=new StringBuffer();
                for (RoleInfoVO infoVO:roleInfoVOList){
                    idBuffer.append(infoVO.getRoleId()).append(",");
                    nameBuffer.append(infoVO.getRoleName()).append(",");
                }
                autherDo.setAutherRoleId(idBuffer.substring(0,idBuffer.length()-1));
                autherDo.setAutherRoleName(nameBuffer.substring(0,nameBuffer.length()-1));
            }
            authorizationMapper.saveAutherRole(autherDo);
        }
        return Result.success();
    }


    /**
     * 修改授权角色
     * @param list
     * @return
     */
    @Override
    public Result updateAutherRole(List<AutherRoleDO> list) {
        if (list==null || list.size()==0){
            return Result.error("未获取到删除信息!");
        }
        for (AutherRoleDO roleDO:list){
            authorizationMapper.deleteAutherRoleById(roleDO.getId());
        }
        return Result.success();
    }

    /**
     * 根据授权角色主表id获取授权角色信息
     * @param mainId
     * @return
     */
    @Override
    public List<AutherRoleDO> getAutherRoleListByMainId(Long mainId) {
        return authorizationMapper.getAutherRoleListByMainId(mainId);
    }

    /**
     * 删除角色授权主表
     * @param id
     * @return
     */
    @Override
    public Result deleteAutherRoleMain(Long id) {
        List<AutherRoleDO> list=this.getAutherRoleListByMainId(id);
        Result r=this.updateAutherRole(list);
        if (!r.isSuccess()){
            return Result.error(r.get("msg").toString());
        }
        authorizationMapper.deleteAutherRoleMainById(id);
        return Result.success();
    }

    /**
     * 角色授权-作废
     * @param id
     * @return
     */
    @Override
    public Result deleteAutherRole(Long id){
        authorizationMapper.deleteRole(id);
        return Result.success();
    }

    /**
     * 角色授权-查询授权人的角色信息
     * @return
     */
    @Override
    public List<RoleInfoVO> getUserRoleList(){
        List<RoleInfoVO> list=null;
        if (SecurityUtils.getLoginUser().getUser().getUserId()==1){
            list=authorizationMapper.getRoleInfoAll();
        }else{
            list=authorizationMapper.getRoleByUser(SecurityUtils.getLoginUser().getUser().getUserId());
        }
        return list;
    }


}
