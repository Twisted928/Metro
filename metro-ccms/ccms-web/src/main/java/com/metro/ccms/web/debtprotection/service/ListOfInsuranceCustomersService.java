package com.metro.ccms.web.debtprotection.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.debtprotection.domian.InsurecusDO;
import com.metro.ccms.web.debtprotection.query.InsurecusQuery;
import com.metro.ccms.web.debtprotection.vo.InsureChecklistVO;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;

import java.util.List;

/**
 * 保险清单
 *
 * @author guangle
 * @create 2020/12/7
 * @since 1.0.0
 */
public interface ListOfInsuranceCustomersService {

    /**
     * 保险清单-查询
     *
     * @param insurecusQuery 保险客户清单表查询类
     * @return List<InsureDO>
     */
    List<InsurecusDO> list(InsurecusQuery insurecusQuery);

    /**
     * 保险清单-新增
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     */
    Result save(InsureChecklistVO insureChecklistVO);

    /**
     * 保险清单-补录
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     */
    Result update(InsureChecklistVO insureChecklistVO);

    /**
     * 保险清单-详情
     *
     * @param insureChecklistVO 主表
     * @return List<SysBasicFile>
     */
    InsureChecklistVO getFile(InsureChecklistVO insureChecklistVO);

    /**
     * 判断公司编码 客户编码 保单号是否重复
     *
     * @param insureChecklistVO 清单组合VO
     * @return Result
     */
    Result judgment(InsureChecklistVO insureChecklistVO);

    /**
     * 保险清单-删除
     *
     * @param insureChecklistVO 主表
     * @return Result
     */
    Result delete(InsureChecklistVO insureChecklistVO);

    /**
     * 查询保单
     *
     * @return List<InsurePolicyVO>
     */
    List<InsurePolicyVO> listPolicy(InsurePolicyVO insurePolicyVO);
}
