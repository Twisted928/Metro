package com.metro.ccms.web.customer.mapper;

import com.metro.ccms.web.credit.domain.CustMembersDO;
import com.metro.ccms.web.customer.domian.WhitelistmanagementDO;
import com.metro.ccms.web.customer.query.WhitelistmanagementQuery;
import com.metro.ccms.web.customer.vo.WhitelistmanagementVO;
import com.metro.ccms.web.customer.vo.WhitelistmanagementqdVO;

import java.util.List;

/**
 *<p>
 *  黑名单管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
public interface WhitelistmanagementMapper {
    /**
     * 查询
     * @param whitelistmanagementQuery
     * @return
     */
    List<WhitelistmanagementVO> pagesel(WhitelistmanagementQuery whitelistmanagementQuery);
    /**
     * 查询详情
     * @param whitelistmanagementQuery
     * @return
     */
    WhitelistmanagementVO seldetails(WhitelistmanagementQuery whitelistmanagementQuery);
    /**
     *  修改
     * @param whitelistmanagementDO
     * @return
     */
    void updmonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  删除
     * @param whitelistmanagementDO
     * @return
     */
    void delmonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  查询最大ID
     * @param whitelistmanagementDO
     * @return
     */
    String selmaxid(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  新增
     * @param whitelistmanagementDO
     * @return
     */
    int savemonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  审批通过保存白名单
     * @param whitelistmanagementDO
     * @return
     */
    void insertwhite(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  审批通过更新白名单
     * @param whitelistmanagementDO
     * @return
     */
    void updatewhite(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  审批通过保存历史
     * @param whitelistmanagementDO
     * @return
     */
    void insertwhitels(WhitelistmanagementDO whitelistmanagementDO);

    /**
     *  修改
     * @param whitelistmanagementDO
     * @return
     */
    void updatemonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  修改流程id
     * @param whitelistmanagementDO
     * @return
     */
    void updateno(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  查询部门list
     * @param whitelistmanagementDO
     * @return
     */
    List<CustMembersDO> seldeplist(WhitelistmanagementDO whitelistmanagementDO);
    /**
     * 申请表查询
     * @param whitelistmanagementQuery
     * @return
     */
    List<WhitelistmanagementqdVO> pageselqd(WhitelistmanagementQuery whitelistmanagementQuery);
    /**
     * 申请表历史记录
     * @param whitelistmanagementQuery
     * @return
     */
    List<WhitelistmanagementqdVO> pagesells(WhitelistmanagementQuery whitelistmanagementQuery);
    /**
     *  移除
     * @param whitelistmanagementDO
     * @return
     */
    void updqd(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  判断是否当前登陆人申请单据
     * @param whitelistmanagementDO
     * @return
     */
    int selcount(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  判断是否在黑名单存在
     * @param whitelistmanagementDO
     * @return
     */
    int selhmdcount(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  判断是否在白名单存在
     * @param whitelistmanagementDO
     * @return
     */
    int selwhitecount(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  判断是否在白名单已经审批
     * @param whitelistmanagementDO
     * @return
     */
    int selwhite(WhitelistmanagementDO whitelistmanagementDO);
    /**
     *  判断是否已撤销
     * @param whitelistmanagementDO
     * @return
     */
    int selcxwhite(WhitelistmanagementDO whitelistmanagementDO);
}