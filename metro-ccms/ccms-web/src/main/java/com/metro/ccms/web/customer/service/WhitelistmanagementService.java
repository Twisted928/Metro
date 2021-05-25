package com.metro.ccms.web.customer.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.customer.domian.WhitelistmanagementDO;
import com.metro.ccms.web.customer.query.WhitelistmanagementQuery;
import com.metro.ccms.web.customer.vo.WhitelistmanagementVO;
import com.metro.ccms.web.customer.vo.WhitelistmanagementqdVO;

import java.util.List;

/**
 *<p>
 *  白名单管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
public interface WhitelistmanagementService {
    /**
     * 白名单管理-查询
     * @param whitelistmanagementQuery
     * @return
     */
    List<WhitelistmanagementVO> pagesel(WhitelistmanagementQuery whitelistmanagementQuery);
    /**
     * 白名单管理-查询详情
     * @param whitelistmanagementQuery
     * @return
     */
    WhitelistmanagementVO seldetails(WhitelistmanagementQuery whitelistmanagementQuery);
    /**
     * 白名单管理-修改
     * @param whitelistmanagementDO
     * @return
     */
    Result updmonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     * 白名单管理-删除
     * @param whitelistmanagementDO
     * @return
     */
    Result delmonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     * 白名单管理-新增
     * @param whitelistmanagementDO
     * @return
     */
    Result savemonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     * 白名单管理-审批
     * @param whitelistmanagementDO
     * @return
     */
    Result updatemonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     * 白名单管理-撤销
     * @param whitelistmanagementDO
     * @return
     */
    Result deletemonitoring(WhitelistmanagementDO whitelistmanagementDO);
    /**
     * 白名单清单-查询
     * @param whitelistmanagementQuery
     * @return
     */
    List<WhitelistmanagementqdVO> pageselqd(WhitelistmanagementQuery whitelistmanagementQuery);
    /**
     * 白名单清单-历史记录
     * @param whitelistmanagementQuery
     * @return
     */
    List<WhitelistmanagementqdVO> pagesells(WhitelistmanagementQuery whitelistmanagementQuery);
    /**
     * 移除
     * @param whitelistmanagementDO
     * @return
     */
    Result remove(WhitelistmanagementDO whitelistmanagementDO);
}
