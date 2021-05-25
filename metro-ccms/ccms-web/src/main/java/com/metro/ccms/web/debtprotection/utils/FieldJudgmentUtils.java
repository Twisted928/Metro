package com.metro.ccms.web.debtprotection.utils;

import com.metro.ccms.web.debtprotection.mapper.GuaranteeScopeMapper;
import com.metro.ccms.web.debtprotection.mapper.InsureCompanyInsureCompanyMapper;
import com.metro.ccms.web.debtprotection.mapper.InsurePolicyMapper;
import com.metro.ccms.web.debtprotection.mapper.InsureScopeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 字段校验
 *
 * @author guangle
 * @date 2020/12/21
 * @since 1.0.0
 */
@Component
public class FieldJudgmentUtils {
    @Autowired
    private InsureCompanyInsureCompanyMapper insureCompanyInsureCompanyMapper;
    @Autowired
    private InsurePolicyMapper insurePolicyMapper;
    @Autowired
    private InsureScopeMapper insureScopeMapper;
    @Autowired
    private GuaranteeScopeMapper guaranteeScopeMapper;

    /**
     * 判断公司名称是否重复
     *
     * @param companyName 公司名称
     * @return int
     * 0 不重复
     * 1 重复
     */
    public int companyName(String companyName) {
        int name = insureCompanyInsureCompanyMapper.getCompanyName(companyName);
        return name == 0 ? 0 : 1;
    }

    /**
     * 判断相同公司下,保单号是否重复
     *
     * @param compCode 公司编码
     * @param policyNo 保单号
     * @return int
     * 0 不重复
     * 1 重复
     */
    public int policyNumber(String compCode, String policyNo) {
        int policyNumber = insurePolicyMapper.getPolicyNumber(compCode, policyNo);
        return policyNumber == 0 ? 0 : 1;
    }

    /**
     * 判断保单范围是否重复
     *
     * @param insureScopeDOList 门店编码
     * @param companyCode       公司编码
     * @return int
     * 0 重复
     * 1 不重复
     */
    public int policyScope(List<String> insureScopeDOList, String companyCode) {
        // 先查保单表,获取相同保险公司下,所有的保单id
        List<String> id = insurePolicyMapper.getByCompanyCode(companyCode);
        // 然后在根据保单ID查询,获取保单范围
        if (id != null && !id.isEmpty()) {
            List<String> storeCode = insureScopeMapper.getByPolicyID(id);
            // 最后在判断包含不包含
            if (insureScopeDOList != null && !insureScopeDOList.isEmpty()) {
                if (storeCode.size() > 0) {
                    for (int i = 0; i < insureScopeDOList.size(); i++) {
                        boolean contains = storeCode.contains(insureScopeDOList.get(i));
                        if (contains) {
                            return 0;
                        }
                    }
                }
            }
        }
        return 1;
    }

    /**
     * 判断字符串是否为纯数字
     *
     * @param str string字符串
     * @return boolean
     * true 纯数字
     * false 非纯数字
     */
    public boolean isNumeric(String str) {
        if (str != null && !"".equals(str.trim())) {
            return str.matches("^[0-9]*$");
        } else {
            return false;
        }
    }

    /**
     * 判断卡号是否重复
     *
     * @param cardCode  卡号
     * @param storeCode 门店
     * @param mainId    主表ID
     * @return int
     * 0 不重复
     * 1 重复
     */
    public int card(String cardCode, String storeCode, String mainId) {
        int card = guaranteeScopeMapper.getCard(cardCode, storeCode, mainId);
        return card == 0 ? 0 : 1;
    }

}
