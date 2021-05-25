package com.metro.ccms.web.utils;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.constant.HttpStatus;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.PageDomain;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.core.page.TableSupport;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.ServletUtils;
import com.metro.ccms.system.domain.SysNotice;
import com.metro.ccms.system.service.ISysNoticeService;
import com.metro.ccms.web.activiti.service.ActivitiService;
import com.metro.ccms.web.activiti.vo.TaskVO;
import com.metro.ccms.web.credit.domain.PayTermDO;
import com.metro.ccms.web.debtprotection.domian.InsureCompanyDO;
import com.metro.ccms.web.debtprotection.query.InsureCompanyQuery;
import com.metro.ccms.web.utils.domain.BasicDataDO;
import com.metro.ccms.web.credit.domain.CustMembersDO;
import com.metro.ccms.web.credit.domain.CustPrimaryDO;
import com.metro.ccms.web.utils.query.CustMembersQuery;
import com.metro.ccms.web.utils.query.CustPrimaryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.metro.ccms.common.core.page.TableSupport.PAGE_NUM;
import static com.metro.ccms.common.core.page.TableSupport.PAGE_SIZE;

/**
 * @Author: fangyongjie
 * @Description: 公共服务接口
 * @Date: Created in 14:10 2020/12/17
 * @Modified By:
 */
@RestController
@RequestMapping("/common/utils")
public class CommonUtilsController extends BaseController {


    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private ISysNoticeService iSysNoticeService;

    /**
     * 根据参数大类获取参数列表
     * @param data
     * @return
     */
    @RequestMapping("/getBasciDataByCtype")
    public Result getBasciDataByCtype(@RequestBody JSONObject data){
        String ctype=data.getString("ctype");
        List<BasicDataDO> list=commonUtils.getBasicDataByCType(ctype);
        return Result.success(list);
    }

    /**
     * 获取客户主数据
     * @param custPrimaryQuery
     * @return
     */
    @GetMapping("/getCustPrimaryList")
    public TableDataInfo getCustPrimaryList(CustPrimaryQuery custPrimaryQuery){
        startPage();
        List<CustPrimaryDO> list=commonUtils.getCustPrimaryList(custPrimaryQuery);
        return getDataTable(list);
    }

    /**
     * 获取客户卡号关系
     * @param custMembersQuery
     * @return
     */
    @GetMapping("/getCustMembersList")
    public TableDataInfo getCustMembersList(CustMembersQuery custMembersQuery){
        startPage();
        List<CustMembersDO> list=commonUtils.getCustMembersList(custMembersQuery);
        return getDataTable(list);
    }

    /**
     * 根据结算方式获取付款条件
     * @param data
     * @return
     */
    @RequestMapping("/getPayTermBySettleType")
    public Result getPayTermBySettleType(@RequestBody JSONObject data){
        String settleType=data.getString("settleType");
        List<PayTermDO> list=commonUtils.getPayTermBySettleType(settleType);
        return Result.success(list);
    }

    /**
     * 分页获取保险公司列表
     * @param insureCompanyQuery
     * @return
     */
    @GetMapping("/getInsureCompanyList")
    public TableDataInfo getInsureCompanyList(InsureCompanyQuery insureCompanyQuery){
        startPage();
        List<InsureCompanyDO> list=commonUtils.getInsureCompanyList(insureCompanyQuery);
        return getDataTable(list);
    }

    /**
     * 分页获取待办信息
     * @return
     */
    @GetMapping("/getTodoList")
    public TableDataInfo getTodoList(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        List<TaskVO> list=activitiService.getTodoList(SecurityUtils.getUsername(),pageNum,pageSize);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(activitiService.getCountToDo(SecurityUtils.getUsername()));
        rspData.setCurrent(pageNum);
        rspData.setPageSize(pageSize);
        return rspData;
    }

    /**
     * 分页获取已办信息
     * @return
     */
    @GetMapping("/getHistoryList")
    public TableDataInfo getHistoryList(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        List<TaskVO> list=activitiService.getHistoryTaskByUser(SecurityUtils.getUsername(),pageNum,pageSize);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(activitiService.getCountHis(SecurityUtils.getUsername()));
        rspData.setCurrent(pageNum);
        rspData.setPageSize(pageSize);
        return rspData;
    }

    /**
     * 获取个人中心人员基本信息
     * @return
     */
    @RequestMapping("/getBasicUserInfo")
    public Result getBasicUserInfo(){
        return commonUtils.getBasicUserInfo();
    }

    /**
     * 个人中心-分页获取公告列表
     * @return
     */
    @GetMapping("/getCenterNotice")
    public TableDataInfo getCenterNotice(){
        startPage();
        List<SysNotice> list=iSysNoticeService.getCenterNotice();
        return getDataTable(list);
    }
}
