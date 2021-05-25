package com.metro.ccms.web.activiti.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.activiti.domain.AutherDocumentDO;
import com.metro.ccms.web.activiti.domain.AutherDocumentMainDO;
import com.metro.ccms.web.activiti.domain.AutherRoleDO;
import com.metro.ccms.web.activiti.domain.AutherRoleMainDO;
import com.metro.ccms.web.activiti.vo.RoleInfoVO;
import com.metro.ccms.web.activiti.query.AutherDocumentQuery;
import com.metro.ccms.web.activiti.query.AutherRoleQuery;
import java.util.List;

/**
 * 单据/角色授权服务
 * Created by fangyongjie
 */
public interface AuthorizationService {


    /**
     * 分页查询单据授权
     * @param autherDocumentQuery
     * @return
     */
    public List<AutherDocumentDO> getAutherDocumentlistPage(AutherDocumentQuery autherDocumentQuery);

    /**
     * 获取角色下对应的人
     * @param roleId
     * @return
     */
    public List<RoleInfoVO> getRoleUserInfoList(Long roleId);

    /**
     * 新增单据授权
     * @param list
     * @return
     */
    public Result auther(List<AutherDocumentDO> list);

    /**
     * 修改单据授权
     * @param list
     * @return
     */
    public Result updateAuther(List<AutherDocumentDO> list);

    /**
     * 根据授权单据主表id获取授权单据信息
     * @param mainId
     * @return
     */
    public List<AutherDocumentDO> getAutherDoListByMainId(Long mainId);

    /**
     * 删除单据授权主表
     * @param id
     * @return
     */
    public Result deleteAutherDoMain(Long id);

    /**
     * 单据授权-作废
     * @param autherDocumentDO
     * @return
     */
    public Result deleteAutherDo(AutherDocumentDO autherDocumentDO);

    /**
     * 分页查询角色授权
     * @param autherRoleQuery
     * @return
     */
    public List<AutherRoleDO> getAutherRolelistPage(AutherRoleQuery autherRoleQuery);

    /**
     * 新增授权角色
     * @param list
     * @return
     */
    public Result autherRole(List<AutherRoleDO> list);

    /**
     * 修改授权角色
     * @param list
     * @return
     */
    public Result updateAutherRole(List<AutherRoleDO> list);

    /**
     * 根据授权角色主表id获取授权角色信息
     * @param mainId
     * @return
     */
    public List<AutherRoleDO> getAutherRoleListByMainId(Long mainId);

    /**
     * 删除角色授权主表
     * @param id
     * @return
     */
    public Result deleteAutherRoleMain(Long id);

    /**
     * 角色授权-作废
     * @param id
     * @return
     */
    public Result deleteAutherRole(Long id);

    /**
     * 角色授权-查询授权人的角色信息
     * @return
     */
    public List<RoleInfoVO> getUserRoleList();
}
