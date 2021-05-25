package com.metro.ccms.web.activiti.mapper;

import com.metro.ccms.web.activiti.domain.AutherDocumentDO;
import com.metro.ccms.web.activiti.domain.AutherDocumentMainDO;
import com.metro.ccms.web.activiti.domain.AutherRoleDO;
import com.metro.ccms.web.activiti.domain.AutherRoleMainDO;
import com.metro.ccms.web.activiti.vo.RoleInfoVO;
import com.metro.ccms.web.activiti.query.AutherDocumentQuery;
import com.metro.ccms.web.activiti.query.AutherRoleQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * Created by fangyongjie
 */
public interface AuthorizationMapper {


    /**
     * 根据角色id获取人员角色信息
     * @param roleId
     * @return
     */
    public List<RoleInfoVO> getRoleUserInfo(@Param("roleId")Long roleId);

    /**
     * 根据任务id获取授权记录信息
     * @param taskId
     * @return
     */
    public List<AutherDocumentDO> getAutherByTaskId(@Param("taskId")String taskId);

    /**
     * 获取所有角色信息
     * @return
     */
    public List<RoleInfoVO> getRoleInfoAll();

    /**
     * 根据用户id获取用户角色信息
     * @param userId
     * @return
     */
    public List<RoleInfoVO> getRoleByUser(@Param("userId")Long userId);

    /**
     * 保存授权单据信息
     * @param autherDocumentDO
     */
    public void saveAutherDocument(AutherDocumentDO autherDocumentDO);

    /**
     * 获取授权单据主表最大id
     * @return
     */
    public int getAutherNoMaxId();

    /**
     * 获取角色授权主表最大id
     * @return
     */
    public int getAutherRoleMaxId();

    /**
     * 保存授权单据主表信息
     * @param autherDocumentMainDO
     */
    public void saveAutherDocumentMain(AutherDocumentMainDO autherDocumentMainDO);

    /**
     * 保存角色授权主表信息
     * @param autherRoleMainDO
     */
    public void saveAutherRoleMain(AutherRoleMainDO autherRoleMainDO);

    /**
     * 根据id删除单据授权
     * @param id
     */
    public void deleteAutherDoById(@Param("id") Long id);

    /**
     * 根据单据授权主表id获取单据授权信息
     * @param mainId
     * @return
     */
    public List<AutherDocumentDO> getAutherDoListByMainId(@Param("mainId") Long mainId);

    /**
     * 根据id删除单据授权主表
     * @param id
     */
    public void deleteAutherDoMainById(@Param("id") Long id);

    /**
     * 分页查询单据授权信息
     * @param autherDocumentQuery
     * @return
     */
    public List<AutherDocumentDO> getAutherDocumentlistPage(AutherDocumentQuery autherDocumentQuery);

    /**
     * 分页查询角色授权信息
     * @param autherRoleQuery
     * @return
     */
    public List<AutherRoleDO> getAutherRolelistPage(AutherRoleQuery autherRoleQuery);

    /**
     * 保存角色授权主表信息
     * @param autherRoleDO
     */
    public void saveAutherRole(AutherRoleDO autherRoleDO);

    /**
     * 根据被授权角色与接收人获取数量
     * @param roleId
     * @param receiveId
     * @return
     */
    public int getAutherRoleByUser(@Param("roleId")Long roleId, @Param("receiveId")Long receiveId);

    /**
     * 根据id删除角色授权
     * @param id
     */
    public void deleteAutherRoleById(@Param("id") Long id);

    /**
     * 根据角色授权主表id获取角色授权信息
     * @param mainId
     * @return
     */
    public List<AutherRoleDO> getAutherRoleListByMainId(@Param("mainId") Long mainId);

    /**
     * 根据id删除角色授权主表
     * @param id
     */
    public void deleteAutherRoleMainById(@Param("id") Long id);

    /**
     * 根据任务id获取有效的授权单据信息
     * @param taskId
     * @return
     */
    public List<AutherDocumentDO> getAutherDoCountByTaskId(@Param("taskId")String taskId);

    /**
     * 作废单据授权
     * @param id
     */
    public void deleteDo(@Param("id")Long id);

    /**
     * 作废角色授权
     * @param id
     */
    public void deleteRole(@Param("id")Long id);
}
