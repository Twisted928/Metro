package com.metro.ccms.web.credit.mapper;

import com.metro.ccms.web.credit.query.CustomerGroupQuery;
import com.metro.ccms.web.credit.vo.CustomerGroupVO;

import java.util.List;

public interface CustomergroupManagementServiceMapper {

    /**
     * 列表查询
     * @param customerGroupQuery
     * @return
     */
    List<CustomerGroupVO> selCustGroup(CustomerGroupQuery customerGroupQuery);
    /**
     * 客户组详细查询
     * @param custGroup
     * @return
     */
    List<CustomerGroupVO> selCustGroupInfo(String custGroup);
}
