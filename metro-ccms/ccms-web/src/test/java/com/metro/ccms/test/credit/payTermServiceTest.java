package com.metro.ccms.test.credit;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.credit.domain.PayTermDO;
import com.metro.ccms.web.credit.query.PayTermQuery;
import com.metro.ccms.web.credit.service.PayTermService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 付款条件
 *
 * @author guangle
 * @date 2020/12/16
 * @since 1.0.0
 */
public class payTermServiceTest extends BaseTest {

    @Autowired
    private PayTermService payTermService;

    /**
     * 付款条件-查询
     */
    @Test
    public void list() {
        PayTermQuery payTermQuery = new PayTermQuery();
        payTermQuery.setSettleType("按结账周期结算（Each 25th）"); // 结算方式
        //payTermQuery.setPaymentDesc("TenXun"); // 付款条件描述
        //payTermQuery.setPaymentCode("1123");
        List<PayTermDO> list = payTermService.list(payTermQuery);
        System.out.println(JSONObject.toJSONString(list));
    }

    /**
     * 付款条件-新增
     */
    @Test
    public void save() {
        PayTermDO payTermDO = new PayTermDO();
        payTermDO.setSettleType("ZFB"); // 结算方式
        payTermDO.setPaymentDesc("Ali"); // 付款条件描述
        payTermDO.setPaymentCode("A1000"); // 付款条件代码
        payTermDO.setPaymentDays(10); // 参考付款条件天数
        Result save = payTermService.save(payTermDO);
        System.out.println(JSONObject.toJSONString(save));
    }

    /**
     * 付款条件-修改
     */
    @Test
    public void update() {
        PayTermDO payTermDO = new PayTermDO();
        payTermDO.setId(55L);
        payTermDO.setSettleType("WX"); // 结算方式
        payTermDO.setPaymentDesc("TenXun"); // 付款条件描述
        payTermDO.setPaymentCode("1123"); // 付款条件代码
        payTermDO.setPaymentDays(1); // 参考付款条件天数
        Result update = payTermService.update(payTermDO);
        System.out.println(JSONObject.toJSONString(update));
    }

    /**
     * 付款条件-删除
     */
    @Test
    public void delete() {
        PayTermDO payTermDO = new PayTermDO();
        payTermDO.setId(55L);
        Result delete = payTermService.delete(payTermDO);
        System.out.println(JSONObject.toJSONString(delete));
    }
}
