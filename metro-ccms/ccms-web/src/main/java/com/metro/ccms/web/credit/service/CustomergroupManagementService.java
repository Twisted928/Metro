package com.metro.ccms.web.credit.service;

import com.metro.ccms.web.credit.query.CustomerGroupQuery;
import com.metro.ccms.web.credit.vo.CustomerGroupVO;

import java.util.List;

/**
* @description 客户组管理
* @author weiwenhui
* @date 2021/01/11 13:35
*/
public interface CustomergroupManagementService {
    /**
     * 列表查询
     * @param customerGroupQuery
     * @return
     */
    List<CustomerGroupVO> selCustGroup(CustomerGroupQuery customerGroupQuery);
    /**
     * 客户组详情查询
     * @param custGroup
     * @return
     */
    List<CustomerGroupVO> selCustGroupInfo(String custGroup);
}
