package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.domian.InsurecusDO;
import com.metro.ccms.web.debtprotection.query.InsurecusQuery;
import com.metro.ccms.web.debtprotection.vo.InsuranceCombinationVO;
import com.metro.ccms.web.debtprotection.vo.InsureChecklistVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InsurecusMapper {
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
     */
    void save(InsureChecklistVO insureChecklistVO);

    /**
     * 保险清单-补录
     *
     * @param insureChecklistVO 清单组合VO
     */
    void update(InsureChecklistVO insureChecklistVO);

    /**
     * 判断公司编码 客户编码 保单号是否重复
     *
     * @param insureChecklistVO 清单组合VO
     * @return int
     */
    int getJudgment(InsureChecklistVO insureChecklistVO);

    /**
     * 公司编码 客户编码 保单号 重复修改上条状态为0
     *
     * @param insureChecklistVO 清单组合VO
     */
    void updateByType(InsureChecklistVO insureChecklistVO);

    /**
     * 根据ID查询
     *
     * @param insureChecklistVO 清单组合VO
     * @return InsureChecklistVO
     */
    InsureChecklistVO getByID(InsureChecklistVO insureChecklistVO);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(Long id);

    /**
     * 根据状态和公司编码查询
     *
     * @param insuranceCombinationVO 保险公司VO
     * @return int
     */
    int getByCompanyCode(InsuranceCombinationVO insuranceCombinationVO);

    /**
     * 保险公司修改时,删除保单判断保单是否可以删除
     *
     * @param policyno 保单号
     * @return int
     */
    int getByPolicyno(String policyno);
}