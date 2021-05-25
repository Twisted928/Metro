package com.metro.ccms.test.debtprotection;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.debtprotection.controller.ReportLossManagementController;
import com.metro.ccms.web.debtprotection.domian.ClaimDO;
import com.metro.ccms.web.debtprotection.domian.ClaimProgressDO;
import com.metro.ccms.web.debtprotection.domian.InsureDO;
import com.metro.ccms.web.debtprotection.query.ClaimQuery;
import com.metro.ccms.web.debtprotection.service.ReportLossManagementService;
import com.metro.ccms.web.debtprotection.vo.LossPortfolioVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 报损管理测试
 *
 * @author guangle
 * @date 2020/12/11
 * @since 1.0.0
 */
public class reportLossManagementServiceTest extends BaseTest {

    @Autowired
    private ReportLossManagementService reportLossManagementService;
    @Autowired
    private ReportLossManagementController reportLossManagementController;

    /**
     * 报损管理-查询
     */
    @Test
    public void list() {
        ClaimQuery claimQuery = new ClaimQuery();
        List<ClaimDO> list = reportLossManagementService.list(claimQuery);
        System.out.println(JSONObject.toJSONString(list));
    }

    /**
     * 报损管理-新增
     *
     * @throws Exception
     */
    @Test
    public void save() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LossPortfolioVO lossPortfolioVO = new LossPortfolioVO();
        lossPortfolioVO.setCaseno("案件编号"); // 案件编号
        lossPortfolioVO.setDeclaration("被保险人声明"); // 被保险人声明
        lossPortfolioVO.setCustCode("客户编码"); // 客户编码
        lossPortfolioVO.setCustName("客户名称"); // 客户名称
        lossPortfolioVO.setInvoiceNo("汇总发票号"); // 汇总发票号
        lossPortfolioVO.setBuyerno("买方代码"); // 买方代码
        lossPortfolioVO.setInvoicedate(format.parse("2020-12-14")); // 汇总发票日期
        lossPortfolioVO.setInsureId("106"); // 已投保表id


        List list = new ArrayList<>();
        ClaimProgressDO claimProgressDO = new ClaimProgressDO();
        claimProgressDO.setCaseProgress("案件进展"); // 案件进展
        claimProgressDO.setCaseStatus("案件状态"); // 案件状态
        ClaimProgressDO claimProgressDO1 = new ClaimProgressDO();
        claimProgressDO1.setCaseProgress("案件进展"); // 案件进展
        claimProgressDO1.setCaseStatus("案件状态"); // 案件状态
        list.add(claimProgressDO);
        list.add(claimProgressDO1);
        lossPortfolioVO.setClaimProgressDOList(list);
        Result save = reportLossManagementService.save(lossPortfolioVO);
        System.out.println(JSONObject.toJSONString(save));
    }

    /**
     * 报损管理-台账维护
     */
    @Test
    public void update() {
        LossPortfolioVO lossPortfolioVO = new LossPortfolioVO();
        lossPortfolioVO.setId(13L); // ID
        lossPortfolioVO.setCaseno("RE2012230001"); // 案件编号

        List<String> list = new ArrayList<>();// 删除的ID
        list.add("36");
        List<ClaimProgressDO> claimProgressDOList = new ArrayList<>();
        ClaimProgressDO claimProgressDO = new ClaimProgressDO(); // 修改
        claimProgressDO.setId(30L);
        claimProgressDO.setCaseProgress("进展中"); // 案件进展
        claimProgressDO.setCaseStatus("1"); // 案件状态

        ClaimProgressDO claimProgress = new ClaimProgressDO(); // 新增
        claimProgress.setCaseProgress("进展接受"); // 案件进展
        claimProgress.setCaseStatus("3"); // 案件状态

        //claimProgressDOList.add(claimProgressDO);
        //claimProgressDOList.add(claimProgress);
        lossPortfolioVO.setIds(list); // 删除的ID
        lossPortfolioVO.setClaimProgressDOList(claimProgressDOList); // 台账信息

        Result update = reportLossManagementService.update(lossPortfolioVO);
        System.out.println(JSONObject.toJSONString(update));
    }

    /**
     * 报损管理-删除
     */
    @Test
    public void delete() {
        ClaimProgressDO claimProgressDO = new ClaimProgressDO();
        claimProgressDO.setId(2L);
        claimProgressDO.setCaseno("RE2012140001");
        reportLossManagementService.delete(claimProgressDO);
    }

    /**
     * 报损管理-详细
     */
    @Test
    public void getOne() {
        ClaimDO claimDO = new ClaimDO();
        claimDO.setId(2L);
        claimDO.setCaseno("RE2012140001");
        ClaimDO one = reportLossManagementService.getOne(claimDO);
        System.out.println(JSONObject.toJSONString(one));
    }

    /**
     * 查看已投保保单
     */
    @Test
    public void listInsuredPolicys() {
        InsureDO insureDO = new InsureDO();
        insureDO.setCustName("");
        List<InsureDO> insureDOS = reportLossManagementService.listInsuredPolicys(insureDO);
        System.out.println(JSONObject.toJSONString(insureDOS));
    }

    /**
     * 根据选择的已投保保单ID查询信息
     */
    @Test
    public void getInsure() {
        InsureDO insureDO = new InsureDO();
        insureDO.setId(64L);
        InsureDO insure = reportLossManagementService.getInsure(insureDO);
        //Result insure = reportLossManagementController.getInsure(insureDO);
        System.out.println(JSONObject.toJSONString(insure));
    }

    /**
     * 报损管理-台账维护-台账删除
     */
    @Test
    public void ledgerDelete() {
        ClaimProgressDO claimProgressDO = new ClaimProgressDO();
        claimProgressDO.setId(1L);
        Result result = reportLossManagementService.ledgerDelete(claimProgressDO);
        System.out.println(JSONObject.toJSONString(result));
    }
}
