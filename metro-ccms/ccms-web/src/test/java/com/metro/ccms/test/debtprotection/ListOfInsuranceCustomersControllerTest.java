package com.metro.ccms.test.debtprotection;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.debtprotection.query.InsurecusQuery;
import com.metro.ccms.web.debtprotection.service.ListOfInsuranceCustomersService;
import com.metro.ccms.web.debtprotection.vo.InsureChecklistVO;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * 保险客户清单测试
 *
 * @author guangle
 * @date 2020/12/7
 * @since 1.0.0
 */
public class ListOfInsuranceCustomersControllerTest extends BaseTest {
    @Autowired
    private ListOfInsuranceCustomersService listOfInsuranceCustomersService;

    /**
     * 保险客户清单-查询
     */
    @Test
    public void list() {
        InsurecusQuery insurecusQuery = new InsurecusQuery();
        //insurecusQuery.setStatus(0);
        //insurecusQuery.setCompCode("V2");
        //insurecusQuery.setCompName("啥啥啥");
        listOfInsuranceCustomersService.list(insurecusQuery);
        System.out.println(JSONObject.toJSONString(listOfInsuranceCustomersService.list(insurecusQuery)));
    }

    @Test
    public void delete(){
        InsureChecklistVO insureChecklistVO = new InsureChecklistVO();
        insureChecklistVO.setId(64L);
        insureChecklistVO.setCreditLevel("1231");
        insureChecklistVO.setQuota(BigDecimal.valueOf(1));
        insureChecklistVO.setQuotaDays(BigDecimal.valueOf(8888888));
        Result delete = listOfInsuranceCustomersService.delete(insureChecklistVO);
        System.out.println(JSONObject.toJSONString(delete));
    }

    /**
     * 保险客户清单-新增
     */
    @Test
    public void save() {
        InsureChecklistVO insureChecklistVO = new InsureChecklistVO();
        insureChecklistVO.setCompCode("v11565");
        insureChecklistVO.setCompName("遵义啥啥啥有限公司");
        insureChecklistVO.setBuyerno("V12930");
        insureChecklistVO.setCustCode("V10000");
        insureChecklistVO.setCustName("海尔数字科技");
        insureChecklistVO.setPolicyno("202012071733");
        insureChecklistVO.setCreditLevel("1");
        insureChecklistVO.setStatus(0);
        System.out.println(JSONObject.toJSONString(listOfInsuranceCustomersService.save(insureChecklistVO)));
    }

    /**
     * 保险客户清单-补录
     */
    @Test
    public void updateTest() {
        InsureChecklistVO insureChecklistVO = new InsureChecklistVO();
        insureChecklistVO.setId(2L);
        insureChecklistVO.setCompCode("v11565");
        insureChecklistVO.setCompName("遵义啥啥啥有限公司2");
        insureChecklistVO.setBuyerno("V12930");
        insureChecklistVO.setCustCode("V10000");
        insureChecklistVO.setCustName("海尔数字科技2");
        insureChecklistVO.setPolicyno("202012071733");
        insureChecklistVO.setCreditLevel("111");
        insureChecklistVO.setStatus(1);
        System.out.println(JSONObject.toJSONString(listOfInsuranceCustomersService.update(insureChecklistVO)));
    }

    /**
     * 保险清单-详情
     */
    @Test
    public void getFile() {
        InsureChecklistVO insureChecklistVO = new InsureChecklistVO();
        insureChecklistVO.setId(66L);
        InsureChecklistVO file = listOfInsuranceCustomersService.getFile(insureChecklistVO);
        System.out.println(JSONObject.toJSONString(file));
    }


    /**
     * 判断公司编码 客户编码 保单号是否重复
     */
    @Test
    public void judgment() {
        InsureChecklistVO insureChecklistVO = new InsureChecklistVO();
        insureChecklistVO.setStatus(1);
        insureChecklistVO.setCompCode("5334");
        insureChecklistVO.setCustCode("1");
        insureChecklistVO.setPolicyno("1");
        Result judgment = listOfInsuranceCustomersService.judgment(insureChecklistVO);
        System.out.println(JSONObject.toJSONString(judgment));
    }

    @Test
    public void listPolicy (){
        InsurePolicyVO insurePolicyVO =  new InsurePolicyVO();
        insurePolicyVO.setCompName("麦德龙风控公司");
        List<InsurePolicyVO> insurePolicyVOS = listOfInsuranceCustomersService.listPolicy(insurePolicyVO);
        System.out.println(JSONObject.toJSONString(insurePolicyVOS));
    }
}
