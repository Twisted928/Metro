package com.metro.ccms.test.debtprotection;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.debtprotection.controller.GuaranteeLetterManagementController;
import com.metro.ccms.web.debtprotection.domian.GuaranteeDO;
import com.metro.ccms.web.debtprotection.domian.GuaranteeScopeDO;
import com.metro.ccms.web.debtprotection.query.GuaranteeQuery;
import com.metro.ccms.web.debtprotection.service.GuaranteeLetterManagementService;
import com.metro.ccms.web.debtprotection.vo.GuaranteeVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 担保函范围测试
 *
 * @author guangle
 * @date 2020/12/14
 * @since 1.0.0
 */
public class guaranteeLetterManagementServiceTest extends BaseTest {

    @Autowired
    private GuaranteeLetterManagementService guaranteeLetterManagementService;
    @Autowired
    private GuaranteeLetterManagementController guaranteeLetterManagementController;

    /**
     * 担保函管理-查询
     *
     * @throws Exception
     */
    @Test
    public void list() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // format.parse("2020-12-14")
        GuaranteeQuery guaranteeQuery = new GuaranteeQuery();
        //guaranteeQuery.setCustCode("V11656"); // 客户编码
        //guaranteeQuery.setCustName("数"); // 客户名称
        //guaranteeQuery.setGtcode("10011"); // 担保函编码
        //guaranteeQuery.setGuaranteetype(1); // 担保函类型
        //guaranteeQuery.setBeginDate(format.parse("2020-12-1"));
        //guaranteeQuery.setEndDate(format.parse("2020-12-30"));
        List<GuaranteeDO> list = guaranteeLetterManagementService.list(guaranteeQuery);
        System.out.println(JSONObject.toJSONString(list));
    }

    /**
     * 担保函管理-新增
     *
     * @throws Exception
     */
    @Test
    public void save() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        GuaranteeVO guaranteeVO = new GuaranteeVO();
        guaranteeVO.setGtcode("10001"); // 担保函编码
        guaranteeVO.setCustCode("V11656"); // 客户编码
        guaranteeVO.setCustName("海尔数字科技"); // 客户名称
        guaranteeVO.setGtsum(BigDecimal.valueOf(100000)); // 担保金额
        guaranteeVO.setGuaranteetype("1"); // 担保函类型
        guaranteeVO.setValidFrom(format.parse("2020-12-14")); // 生效时间
        guaranteeVO.setValidTo(format.parse("2020-12-14")); // 到期时间

        List list = new ArrayList();
        GuaranteeScopeDO guaranteeScopeDO = new GuaranteeScopeDO();
        guaranteeScopeDO.setStoreCode("20001"); // 门店编码
        guaranteeScopeDO.setCardCode("20001"); // 卡号编码
        guaranteeScopeDO.setCardName("海尔数字科技"); // 卡号名称
        GuaranteeScopeDO guaranteeScopeDO1 = new GuaranteeScopeDO();
        guaranteeScopeDO1.setStoreCode("30001"); // 门店编码
        guaranteeScopeDO1.setCardCode("30001"); // 卡号编码
        guaranteeScopeDO1.setCardName("海尔科技"); // 卡号名称
        list.add(guaranteeScopeDO);
        list.add(guaranteeScopeDO1);
        guaranteeVO.setGuaranteeScopeDOList(list);
        Result save = guaranteeLetterManagementService.save(guaranteeVO);
        System.out.println(JSONObject.toJSONString(save));
    }

    /**
     * 担保函管理-修改
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        GuaranteeVO guaranteeVO = new GuaranteeVO();
        guaranteeVO.setId(4L);
        guaranteeVO.setGtcode("10011"); // 担保函编码
        guaranteeVO.setCustCode("V11656"); // 客户编码
        guaranteeVO.setCustName("海尔数字科技啊"); // 客户名称
        guaranteeVO.setGtsum(BigDecimal.valueOf(200000)); // 担保金额
        guaranteeVO.setGuaranteetype("3"); // 担保函类型
        guaranteeVO.setValidFrom(format.parse("2020-12-11")); // 生效时间
        guaranteeVO.setValidTo(format.parse("2020-12-13")); // 到期时间
        guaranteeVO.setCreatedBy("阿巴阿巴");

        List list = new ArrayList();
        GuaranteeScopeDO guaranteeScopeDO = new GuaranteeScopeDO();
        guaranteeScopeDO.setStoreCode("20012"); // 门店编码
        guaranteeScopeDO.setCardCode("20012"); // 卡号编码
        guaranteeScopeDO.setCardName("海尔数字科技啊"); // 卡号名称
        GuaranteeScopeDO guaranteeScopeDO1 = new GuaranteeScopeDO();
        guaranteeScopeDO1.setStoreCode("30011"); // 门店编码
        guaranteeScopeDO1.setCardCode("30011"); // 卡号编码
        guaranteeScopeDO1.setCardName("海尔科技呀"); // 卡号名称
        list.add(guaranteeScopeDO);
        list.add(guaranteeScopeDO1);
        guaranteeVO.setGuaranteeScopeDOList(list);
        Result update = guaranteeLetterManagementService.update(guaranteeVO);
        System.out.println(JSONObject.toJSONString(update));
    }

    /**
     * 担保函管理-删除
     */
    @Test
    public void delete() {
        GuaranteeVO guaranteeVO = new GuaranteeVO();
        List list = new ArrayList();
        list.add("4");
        guaranteeVO.setIds(list);
        guaranteeLetterManagementService.delete(guaranteeVO);
    }

    /**
     * 担保函管理-详情
     */
    @Test
    public void getOne() {
        GuaranteeVO guaranteeVO = new GuaranteeVO();
        guaranteeVO.setId(4L);
        GuaranteeVO one = guaranteeLetterManagementService.getOne(guaranteeVO);
        System.out.println(JSONObject.toJSONString(one));
    }

    /**
     * 获取担保参数类型
     */
    @Test
    public void type() {
        Result type = guaranteeLetterManagementController.type();
        System.out.println(JSONObject.toJSONString(type));
    }

    /**
     * 卡号新增
     */
    @Test
    public void cardSave(){
        GuaranteeScopeDO guaranteeScopeDO = new GuaranteeScopeDO();
        guaranteeScopeDO.setStoreCode("门店编码");
        guaranteeScopeDO.setCardCode("卡号编码");
        guaranteeScopeDO.setCardName("卡号名称");
        guaranteeScopeDO.setCustCode("客户编码");
        guaranteeScopeDO.setCustName("客户名称");
        Result result = guaranteeLetterManagementService.cardSave(guaranteeScopeDO);
        System.out.println(JSONObject.toJSONString(result));
    }

    /**
     * 卡号删除
     */
    @Test
    public void cardDelete(){
        GuaranteeScopeDO guaranteeScopeDO = new GuaranteeScopeDO();
        guaranteeScopeDO.setId(14L);
        Result result = guaranteeLetterManagementService.cardDelete(guaranteeScopeDO);
        System.out.println(JSONObject.toJSONString(result));
    }
}
