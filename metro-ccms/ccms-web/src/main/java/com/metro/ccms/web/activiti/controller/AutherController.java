package com.metro.ccms.web.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.web.activiti.domain.AutherDocumentDO;
import com.metro.ccms.web.activiti.domain.AutherDocumentMainDO;
import com.metro.ccms.web.activiti.domain.AutherRoleDO;
import com.metro.ccms.web.activiti.domain.AutherRoleMainDO;
import com.metro.ccms.web.activiti.vo.RoleInfoVO;
import com.metro.ccms.web.activiti.query.AutherDocumentQuery;
import com.metro.ccms.web.activiti.query.AutherRoleQuery;
import com.metro.ccms.web.activiti.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 授权
 * Created by fangyongjie
 */
@RestController
@RequestMapping("/activiti/authorization")
public class AutherController extends BaseController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * 分页查询单据授权
     * @param autherDocumentQuery
     * @return
     */
    @GetMapping("/documentlistPage")
    public TableDataInfo getAutherDocumentlistPage(AutherDocumentQuery autherDocumentQuery) {
        startPage();
        List<AutherDocumentDO> list = authorizationService.getAutherDocumentlistPage(autherDocumentQuery);
        return getDataTable(list);
    }

    /**
     * 获取角色下对应的人
     * @param data
     * @return
     */
    @RequestMapping("/getRoleUserInfoList")
    public List<RoleInfoVO> getRoleUserInfoList(@RequestBody JSONObject data) {
        Long roleId = data.getLong("roleId");
        return authorizationService.getRoleUserInfoList(roleId);
    }

    /**
     * 新增单据授权
     * @param data
     * @return
     */
    @RequestMapping("/auther")
    public Result auther(@RequestBody JSONObject data){
        List<AutherDocumentDO> list = data.getJSONArray("list").toJavaList(AutherDocumentDO.class);
        return authorizationService.auther(list);
    }

    /**
     * 修改单据授权
     * @param data
     * @return
     */
    @RequestMapping("/updateAuther")
    public Result updateAuther(@RequestBody JSONObject data){
        List<AutherDocumentDO> list = data.getJSONArray("list").toJavaList(AutherDocumentDO.class);
        return authorizationService.updateAuther(list);
    }

    /**
     * 根据授权单据主表id获取授权单据信息
     * @param data
     * @return
     */
    @RequestMapping("/getAutherDoListByMainId")
    public List<AutherDocumentDO> getAutherDoListByMainId(@RequestBody JSONObject data){
        Long mainId=data.getLong("mainId");
        return authorizationService.getAutherDoListByMainId(mainId);
    }

    /**
     * 删除单据授权主表
     * @param data
     * @return
     */
    @RequestMapping("/deleteAutherDoMain")
    public Result deleteAutherDoMain(@RequestBody JSONObject data){
        Long id=data.getLong("id");
        return authorizationService.deleteAutherDoMain(id);
    }

    /**
     * 单据授权-作废
     * @param autherDocumentDO
     * @return
     */
    @RequestMapping("/deleteAutherDo")
    public Result deleteAutherDo(@RequestBody AutherDocumentDO autherDocumentDO){
        return authorizationService.deleteAutherDo(autherDocumentDO);
    }

    /**
     * 分页查询角色授权
     * @param autherRoleQuery
     * @return
     */
    @GetMapping("/rolelistPage")
    public TableDataInfo getAutherRolelistPage(AutherRoleQuery autherRoleQuery){
        startPage();
        List<AutherRoleDO> list=authorizationService.getAutherRolelistPage(autherRoleQuery);
        return getDataTable(list);
    }

    /**
     * 新增授权角色
     * @param data
     * @return
     */
    @RequestMapping("/autherRole")
    public Result autherRole(@RequestBody JSONObject data){
        List<AutherRoleDO> list = data.getJSONArray("list").toJavaList(AutherRoleDO.class);
        return authorizationService.autherRole(list);
    }

    /**
     * 修改授权角色
     * @param data
     * @return
     */
    @RequestMapping("/updateAutherRole")
    public Result updateAutherRole(@RequestBody JSONObject data){
        List<AutherRoleDO> list = data.getJSONArray("list").toJavaList(AutherRoleDO.class);
        return authorizationService.updateAutherRole(list);
    }

    /**
     * 根据授权角色主表id获取授权角色信息
     * @param data
     * @return
     */
    @RequestMapping("/getAutherRoleListByMainId")
    public List<AutherRoleDO> getAutherRoleListByMainId(@RequestBody JSONObject data){
        Long mainId=data.getLong("mainId");
        return authorizationService.getAutherRoleListByMainId(mainId);
    }

    /**
     * 删除角色授权主表
     * @param data
     * @return
     */
    @RequestMapping("/deleteAutherRoleMain")
    public Result deleteAutherRoleMain(@RequestBody JSONObject data){
        Long id=data.getLong("id");
        return authorizationService.deleteAutherRoleMain(id);
    }

    /**
     * 角色授权-作废
     * @param data
     * @return
     */
    @RequestMapping("/deleteAutherRole")
    public Result deleteAutherRole(@RequestBody JSONObject data){
        Long id=data.getLong("id");
        return authorizationService.deleteAutherRole(id);
    }

    /**
     * 角色授权-查询授权人的角色信息
     * @return
     */
    @GetMapping("/getUserRoleList")
    public TableDataInfo getUserRoleList(){
        startPage();
        List<RoleInfoVO> list=authorizationService.getUserRoleList();
        return getDataTable(list);
    }

}
