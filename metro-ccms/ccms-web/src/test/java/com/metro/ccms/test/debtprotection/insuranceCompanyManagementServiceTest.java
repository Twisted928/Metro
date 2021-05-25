package com.metro.ccms.test.debtprotection;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.debtprotection.domian.InsureCompanyDO;
import com.metro.ccms.web.debtprotection.domian.InsurePolicyDO;
import com.metro.ccms.web.debtprotection.query.InsureCompanyQuery;
import com.metro.ccms.web.debtprotection.service.InsuranceCompanyManagementService;
import com.metro.ccms.web.debtprotection.vo.InsuranceCombinationVO;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
import com.metro.ccms.web.debtprotection.vo.InsureScopeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 保险公司管理
 *
 * @author guangle
 * @create 2020/12/1
 * @since 1.0.0
 */
public class insuranceCompanyManagementServiceTest extends BaseTest {
    @Autowired
    private InsuranceCompanyManagementService insuranceCompanyManagementService;

    /**
     * 保险公司管理-查询
     */
    @Test
    public void insuranceCompanyManagementListTest(){
        InsureCompanyQuery insureCompanyQuery = new InsureCompanyQuery();
        //insureCompanyQuery.setCompName("第1次");
        //insureCompanyQuery.setStatus(1);
        List<InsureCompanyDO> insureCompanyVOS = insuranceCompanyManagementService.insuranceCompanyManagementList(insureCompanyQuery);

        System.out.println(JSONObject.toJSONString(insureCompanyVOS));
    }

    /**
     * 保险公司管理-新增
     */
    @Test
    public void insuranceCompanyManagementAddTest(){
        InsuranceCombinationVO insuranceCombinationVO = new InsuranceCombinationVO();
        insuranceCombinationVO.setCompCode("V10000");
        insuranceCombinationVO.setCompName("麦德龙");
        insuranceCombinationVO.setValidFrom(new Date());
        insuranceCombinationVO.setValidTo(new Date());

        List<InsurePolicyVO> list = new ArrayList();
        InsurePolicyVO insurePolicyVO = new InsurePolicyVO();
        insurePolicyVO.setCompCode(insuranceCombinationVO.getCompCode()); // 公司编码
        insurePolicyVO.setCompName(insuranceCombinationVO.getCompName()); // 公司名称
        insurePolicyVO.setPolicyno("20201222110601"); // 保单号
        insurePolicyVO.setPayperiod(10);   // 最长付款期限
        insurePolicyVO.setQuotafree(10);  // 限额闲置期
        insurePolicyVO.setPaywait(10); // 赔款等待期
        insurePolicyVO.setCurrency("NB"); // 币种
        insurePolicyVO.setBody("保单主体"); // 保单主体
        insurePolicyVO.setPolicySum(BigDecimal.valueOf(10000)); // 约定投保金额
        insurePolicyVO.setMaxpaySum(BigDecimal.valueOf(10000)); // 最高赔偿限额
        insurePolicyVO.setPayLv(BigDecimal.valueOf(10)); // 赔偿比例
        insurePolicyVO.setValidFrom(new Date()); // 生效时间
        insurePolicyVO.setValidTo(new Date());   // 到期时间
        List<String> list1 = new ArrayList<>();
        list1.add("1015");
        list1.add("1045");
        list1.add("1057");
        insurePolicyVO.setInsureScopeDOList(list1); // 门店编码
        //insurePolicyVO.setTime(insuranceCombinationVO.getValidFrom()); //保险公司的生效时间
        list.add(insurePolicyVO);

        InsurePolicyVO insurePolicy = new InsurePolicyVO();
        insurePolicy.setCompCode(insuranceCombinationVO.getCompCode()); // 公司编码
        insurePolicy.setCompName(insuranceCombinationVO.getCompName()); // 公司名称
        insurePolicy.setPolicyno("20201222110602"); // 保单号
        insurePolicy.setPayperiod(20);   // 最长付款期限
        insurePolicy.setQuotafree(20);  // 限额闲置期
        insurePolicy.setPaywait(20); // 赔款等待期
        insurePolicy.setCurrency("NB"); // 币种
        insurePolicy.setBody("保单主体"); // 保单主体
        insurePolicy.setPolicySum(BigDecimal.valueOf(20000)); // 约定投保金额
        insurePolicy.setMaxpaySum(BigDecimal.valueOf(20000)); // 最高赔偿限额
        insurePolicy.setPayLv(BigDecimal.valueOf(20)); // 赔偿比例
        insurePolicy.setValidFrom(new Date()); // 生效时间
        insurePolicy.setValidTo(new Date());   // 到期时间
        List<String> list2 = new ArrayList<>();
        list2.add("1009");
        list2.add("1008");
        insurePolicy.setInsureScopeDOList(list2); // 门店编码
        //insurePolicy.setTime(insuranceCombinationVO.getValidFrom()); //保险公司的生效时间
        list.add(insurePolicy);


        insuranceCombinationVO.setInsurePolicyVOList(list);
        Result result = insuranceCompanyManagementService.saveInsuranceCompanyManagement(insuranceCombinationVO);

        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 保险公司管理-修改
     */
    @Test
    public void insuranceCompanyManagementUpdateTest(){
        InsuranceCombinationVO insuranceCombinationVO = new InsuranceCombinationVO();
        insuranceCombinationVO.setId(92L);
        insuranceCombinationVO.setCompCode("v10000");
        insuranceCombinationVO.setCompName("麦德龙");
        insuranceCombinationVO.setValidFrom(new Date());
        insuranceCombinationVO.setValidTo(new Date());
        insuranceCombinationVO.setStatus(1);

        List<InsurePolicyVO> list = new ArrayList();
        InsurePolicyVO insurePolicyVO = new InsurePolicyVO();
        insurePolicyVO.setId(139L);
        insurePolicyVO.setCompCode(insuranceCombinationVO.getCompCode()); // 公司编码
        insurePolicyVO.setCompName(insuranceCombinationVO.getCompName()); // 公司名称
        insurePolicyVO.setPolicyno("2020122211060101"); // 保单号
        insurePolicyVO.setPayperiod(300);   // 最长付款期限
        insurePolicyVO.setQuotafree(300);  // 限额闲置期
        insurePolicyVO.setPaywait(300); // 赔款等待期
        insurePolicyVO.setCurrency("Ccc"); // 币种
        insurePolicyVO.setBody("保单主体"); // 保单主体
        insurePolicyVO.setPolicySum(BigDecimal.valueOf(300001)); // 约定投保金额
        insurePolicyVO.setMaxpaySum(BigDecimal.valueOf(300001)); // 最高赔偿限额
        insurePolicyVO.setPayLv(BigDecimal.valueOf(300)); // 赔偿比例
        insurePolicyVO.setValidFrom(new Date()); // 生效时间
        insurePolicyVO.setValidTo(new Date());   // 到期时间
        List<String> list1 = new ArrayList<>();
        list1.add("1015");
        list1.add("1045");
        insurePolicyVO.setInsureScopeDOList(list1); // 门店编码
        //insurePolicyVO.setTime(insuranceCombinationVO.getValidFrom()); //保险公司的生效时间
        list.add(insurePolicyVO);

        InsurePolicyVO insurePolicy = new InsurePolicyVO();
        insurePolicy.setId(140L);
        insurePolicy.setCompCode(insuranceCombinationVO.getCompCode()); // 公司编码
        insurePolicy.setCompName(insuranceCombinationVO.getCompName()); // 公司名称
        insurePolicy.setPolicyno("20201222110602"); // 保单号
        insurePolicy.setPayperiod(100);   // 最长付款期限
        insurePolicy.setQuotafree(100);  // 限额闲置期
        insurePolicy.setPaywait(100); // 赔款等待期
        insurePolicy.setCurrency("NB"); // 币种
        insurePolicy.setBody("保单主体"); // 保单主体
        insurePolicy.setPolicySum(BigDecimal.valueOf(100001)); // 约定投保金额
        insurePolicy.setMaxpaySum(BigDecimal.valueOf(100001)); // 最高赔偿限额
        insurePolicy.setPayLv(BigDecimal.valueOf(100)); // 赔偿比例
        insurePolicy.setValidFrom(new Date()); // 生效时间
        insurePolicy.setValidTo(new Date());   // 到期时间
        List<String> list2 = new ArrayList<>();
        list2.add("1009");
        //list2.add("1008");
        insurePolicy.setInsureScopeDOList(list2); // 门店编码
        //insurePolicy.setTime(insuranceCombinationVO.getValidFrom()); //保险公司的生效时间
        list.add(insurePolicy);

        InsurePolicyVO insurePolicy1 = new InsurePolicyVO();
        insurePolicy1.setCompCode(insuranceCombinationVO.getCompCode()); // 公司编码
        insurePolicy1.setCompName(insuranceCombinationVO.getCompName()); // 公司名称
        insurePolicy1.setPolicyno("20201222110603"); // 保单号
        insurePolicy1.setPayperiod(101);   // 最长付款期限
        insurePolicy1.setQuotafree(101);  // 限额闲置期
        insurePolicy1.setPaywait(101); // 赔款等待期
        insurePolicy1.setCurrency("NB"); // 币种
        insurePolicy1.setBody("保单主体"); // 保单主体
        insurePolicy1.setPolicySum(BigDecimal.valueOf(100004)); // 约定投保金额
        insurePolicy1.setMaxpaySum(BigDecimal.valueOf(100004)); // 最高赔偿限额
        insurePolicy1.setPayLv(BigDecimal.valueOf(101)); // 赔偿比例
        insurePolicy1.setValidFrom(new Date()); // 生效时间
        insurePolicy1.setValidTo(new Date());   // 到期时间
        List<String> list3 = new ArrayList<>();
        list3.add("1168");
        insurePolicy1.setInsureScopeDOList(list3); // 门店编码
        //insurePolicy1.setTime(insuranceCombinationVO.getValidFrom()); //保险公司的生效时间
        list.add(insurePolicy1);

        insuranceCombinationVO.setInsurePolicyVOList(list);
        Result result = insuranceCompanyManagementService.insuranceCompanyManagementUpdate(insuranceCombinationVO);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 保险公司管理-删除
     */
    @Test
    public void insuranceCompanyManagementDeleteTest(){
        InsureCompanyDO insureCompanyDO = new InsureCompanyDO();
        insureCompanyDO.setId(92L);
        insureCompanyDO.setCompCode("v10000");

        Result result = insuranceCompanyManagementService.insuranceCompanyManagementDelete(insureCompanyDO);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 保险公司管理-详细 InsuranceCombinationVO
     */
    @Test
    public void insuranceCompanyManagementGetByIdTest(){
        InsuranceCombinationVO insuranceCombinationVO = new InsuranceCombinationVO();
        insuranceCombinationVO.setId(92L);
        InsuranceCombinationVO insuranceCombinationVO1 = insuranceCompanyManagementService.insuranceCompanyManagementGetById(insuranceCombinationVO);
        System.out.println(JSONObject.toJSONString(insuranceCombinationVO1));
    }

    /**
     * 新增保单时间校验
     */
    @Test
    public void timeJudgment () throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        InsuranceCombinationVO insuranceCombinationVO = new InsuranceCombinationVO();
        insuranceCombinationVO.setValidFrom(format.parse("2020-12-28"));
        insuranceCombinationVO.setValidTo(format.parse("2020-12-30"));
        List<InsurePolicyVO> list = new ArrayList();
        InsurePolicyVO insurePolicy = new InsurePolicyVO();
        insurePolicy.setValidFrom(format.parse("2020-12-28")); // 生效时间
        insurePolicy.setValidTo(format.parse("2020-12-30"));   // 到期时间
        list.add(insurePolicy);
        insuranceCombinationVO.setInsurePolicyVOList(list);
        Result result = insuranceCompanyManagementService.timeJudgment(insuranceCombinationVO);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 查看保单范围
     */
    @Test
    public void insureScopeListTest(){
        InsureScopeVO insureScopeVO = new InsureScopeVO();
        insureScopeVO.setPolicId("139");
        List<String> list = new ArrayList<>();
        list.add("1045");
        list.add("1015");
        insureScopeVO.setStoreCodeList(list);
        List<InsureScopeVO> insureScopeVOS = insuranceCompanyManagementService.insureScopeList(insureScopeVO);
        System.out.println(JSONObject.toJSONString(insureScopeVOS));
    }

    /**
     * 保单新增
     */
    @Test
    public void policySave(){
        InsurePolicyVO insurePolicyVO = new InsurePolicyVO();
        insurePolicyVO.setCompCode("111");
        insurePolicyVO.setCompName("那么");
        insurePolicyVO.setPolicyno("20201202104701");
        insurePolicyVO.setPayperiod(100);
        insurePolicyVO.setQuotafree(100);
        insurePolicyVO.setPaywait(100);
        insurePolicyVO.setCurrency("zh_CN");
        insurePolicyVO.setBody("保单主体");
        insurePolicyVO.setPolicySum(BigDecimal.valueOf(112131));
        insurePolicyVO.setMaxpaySum(BigDecimal.valueOf(112131));
        insurePolicyVO.setPayLv(BigDecimal.valueOf(112131));
        insurePolicyVO.setValidFrom(new Date());
        insurePolicyVO.setValidTo(new Date());
        Result result = insuranceCompanyManagementService.policySave(insurePolicyVO);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 保单修改
     */
    @Test
    public void policyUpdate(){
        InsurePolicyVO insurePolicyVO = new InsurePolicyVO();
        insurePolicyVO.setId(1L);
        insurePolicyVO.setCompCode("111");
        insurePolicyVO.setCompName("那么");
        insurePolicyVO.setPolicyno("20201202104701");
        insurePolicyVO.setPayperiod(100);
        insurePolicyVO.setQuotafree(100);
        insurePolicyVO.setPaywait(100);
        insurePolicyVO.setCurrency("zh_CN");
        insurePolicyVO.setBody("保单主体");
        insurePolicyVO.setPolicySum(BigDecimal.valueOf(112131));
        insurePolicyVO.setMaxpaySum(BigDecimal.valueOf(112131));
        insurePolicyVO.setPayLv(BigDecimal.valueOf(112131));
        insurePolicyVO.setValidFrom(new Date());
        insurePolicyVO.setValidTo(new Date());
        Result result = insuranceCompanyManagementService.policyUpdate(insurePolicyVO);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 保单删除
     */
    @Test
    public void policyDelete(){
        InsurePolicyVO insurePolicyVO = new InsurePolicyVO();
        insurePolicyVO.setId(1L);
        Result result = insuranceCompanyManagementService.policyDelete(insurePolicyVO);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 保险公司修改时,删除保单判断保单是否可以删除
     */
    @Test
    public void insurePolicyJudgment(){
        InsurePolicyDO insurePolicyDO = new InsurePolicyDO();
        insurePolicyDO.setPolicyno("14756811");
        Result result = insuranceCompanyManagementService.insurePolicyJudgment(insurePolicyDO);
        System.out.println(JSONObject.toJSONString(result));
    }
}
