package com.metro.ccms.web.credit.service.impl;

import com.metro.ccms.web.collection.service.impl.CollectionServiceImpl;
import com.metro.ccms.web.credit.mapper.CustomergroupManagementServiceMapper;
import com.metro.ccms.web.credit.query.CustomerGroupQuery;
import com.metro.ccms.web.credit.service.CustomergroupManagementService;
import com.metro.ccms.web.credit.vo.CustomerGroupVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CustomergroupManagementServiceImpl implements CustomergroupManagementService {

    private static final Logger logger = LoggerFactory.getLogger(CustomergroupManagementServiceImpl.class);

    @Resource
    private CustomergroupManagementServiceMapper customergroupManagementServiceMapper;

    /**
     * 列表查询
     * @param customerGroupQuery
     * @return
     */
    @Override
    public List<CustomerGroupVO> selCustGroup(CustomerGroupQuery customerGroupQuery) {
        return customergroupManagementServiceMapper.selCustGroup(customerGroupQuery);
    }

    /**
     * 客户组查询详细
     * @param custGroup
     * @return
     */
    @Override
    public List<CustomerGroupVO> selCustGroupInfo(String custGroup) {
        return customergroupManagementServiceMapper.selCustGroupInfo(custGroup);
    }
}
