package com.metro.ccms.test.system;

import com.alibaba.fastjson.JSON;
import com.metro.ccms.common.core.domain.TreeSelect;
import com.metro.ccms.common.core.domain.entity.SysBasicData;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.system.domain.SysBasicExchange;
import com.metro.ccms.system.service.ISysBasicDataService;
import com.metro.ccms.system.service.ISysBasicExchangeService;
import com.metro.ccms.system.service.ISysNoticeService;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.system.controller.SysBasicExchangeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 13:38 2020/12/15
 * @Modified By:
 */
public class SysBasicDataDOAndExchangeServiceTest extends BaseTest {

    @Autowired
    private ISysBasicDataService iSysBasicDataService;
    @Autowired
    private ISysBasicExchangeService iSysBasicExchangeService;
    @Autowired
    private SysBasicExchangeController sysBasicExchangeController;
    @Autowired
    private ISysNoticeService iSysNoticeService;



    @Test
    public void treeTest(){
        List<TreeSelect> tree=iSysBasicDataService.buildDeptTreeSelect(iSysBasicDataService.getDataList(new SysBasicData()));
        System.out.println(JSON.toJSONString(tree));
    }

    @Test
    public void saveExchange(){
//        SysBasicExchange exchange=new SysBasicExchange();
//        exchange.setMonthDate(new Date());
//        exchange.setCurrency("RMB");
//        exchange.setCurrencyDh("XEU");
//        iSysBasicExchangeService.saveExchange(exchange);
        System.out.println(JSON.toJSONString(sysBasicExchangeController.getExchangeList(new SysBasicExchange())));
    }

    @Test
    public void nickNametest(){
        List<Long> dept=new ArrayList<>();
        dept.add(1L);
        List<Long> role=new ArrayList<>();
        role.add(1L);
        List<String> list=iSysNoticeService.getNoticeNickName(dept,role);
        System.out.println(JSON.toJSONString(list));
    }
}
