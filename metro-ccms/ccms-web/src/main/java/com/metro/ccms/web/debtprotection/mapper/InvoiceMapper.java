package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.domian.InsureDO;
import com.metro.ccms.web.debtprotection.domian.InvoiceDO;
import com.metro.ccms.web.debtprotection.vo.InsuranceManagementVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvoiceMapper {

    /**
     * 根据ID查询
     *
     * @param id 主键
     * @return InvoiceDO
     */
    InvoiceDO selectByPrimaryKey(Long id);

    /**
     * 投标管理-查询
     *
     * @param insuranceManagementVO 投保管理合集VO
     * @return List<InsuranceManagementVO>
     */
    List<InsuranceManagementVO> list(InsuranceManagementVO insuranceManagementVO);

    /**
     * 投标管理-投标
     *
     * @param id 主键
     */
    void update(String id);

    /**
     * 投标管理-删除
     */
    void delete(InsureDO insureDO);
}