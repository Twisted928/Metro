package com.metro.ccms.test.debtprotection;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.debtprotection.service.InsuranceManagementService;
import com.metro.ccms.web.debtprotection.vo.InsuranceManagementVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 投标管理
 *
 * @author guangle
 * @date 2020/12/9
 * @since 1.0.0
 */
public class insuranceManagementServiceTest extends BaseTest {

    @Autowired
    private InsuranceManagementService insuranceManagementService;

    /**
     * 投标管理-查询
     */
    @Test
    public void list() throws Exception {
        InsuranceManagementVO insuranceManagementVO = new InsuranceManagementVO();
        //insuranceManagementVO.setCustName("1");
        //insuranceManagementVO.setCustCode("5");
        insuranceManagementVO.setStatus(1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        insuranceManagementVO.setBeginDate(format.parse("2020-11-1"));
        insuranceManagementVO.setEndDate(format.parse("2020-12-30"));
        List<InsuranceManagementVO> list = insuranceManagementService.list(insuranceManagementVO);
        System.out.println(JSONObject.toJSONString(list));
    }

    /**
     * 投标管理-投标
     */
    @Test
    public void save() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        InsuranceManagementVO insuranceManagementVO = new InsuranceManagementVO();
        insuranceManagementVO.setId(3L);
        insuranceManagementVO.setStatus(1);
        insuranceManagementVO.setCompCode("V10086"); // 公司编码
        insuranceManagementVO.setPolicyno("202012091415"); // 保单号
        insuranceManagementVO.setCustCode("V10010"); // 客户编码
        insuranceManagementVO.setCustName("海尔数字科技"); // 客户名称
        insuranceManagementVO.setBuyerno("V10000");  // 买方代码
        insuranceManagementVO.setBody("冲冲冲"); // 保单主体
        insuranceManagementVO.setInvoiceNo("99999"); // 汇总发票号
        insuranceManagementVO.setInvoicesum(BigDecimal.valueOf(999999)); // 汇总发票金额
        //insuranceManagementVO.setInsuresum(BigDecimal.valueOf(9999999)); // 投保金额
        //insuranceManagementVO.setPaymode("WX"); // 付款方式
        //insuranceManagementVO.setTransportdate(format.parse("2020-12-9")); // 出运日期
        insuranceManagementVO.setQuotaDays(12); //保险账期
        insuranceManagementVO.setInvoicedate(format.parse("2020-12-9 00:00:00")); //汇总发票日期
        //insuranceManagementVO.setCode10("1234567890"); //商品代码
        //insuranceManagementVO.setGoodsname("保险"); // 商品名称
        //insuranceManagementVO.setPremiumrate(BigDecimal.valueOf(11.2)); // 费率
        //insuranceManagementVO.setPremium(BigDecimal.valueOf(12.2)); // 保费
        //insuranceManagementVO.setDollarrate(BigDecimal.valueOf(11.3)); // 汇率
        insuranceManagementVO.setQztype("ZFB");  //清账类型
        insuranceManagementVO.setBtime(format.parse("2020-12-1 00:00:00")); //发票汇总最早发票日期
        insuranceManagementVO.setEtime(format.parse("2020-12-6 00:00:00")); // 发票汇总最晚发票日期


        Result save = insuranceManagementService.save(insuranceManagementVO);
        System.out.println(JSONObject.toJSONString(save));
    }

    /**
     * 投标管理-删除
     */
    @Test
    public void delete() throws Exception {
        InsuranceManagementVO insuranceManagementVO = new InsuranceManagementVO();
        insuranceManagementVO.setId(10L);
        insuranceManagementVO.setStatus(2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        insuranceManagementVO.setInvoiceNo("99999");
        insuranceManagementVO.setInvoicedate(format.parse("2020-12-9 00:00:00"));
        Result delete = insuranceManagementService.delete(insuranceManagementVO);
        System.out.println(JSONObject.toJSONString(delete));
    }
}
