package com.metro.ccms.test.contract;

import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.contract.query.ContracttemplateQuery;
import com.metro.ccms.web.contract.service.ContracttemplateService;
import com.metro.ccms.web.contract.vo.ContracttemplateVO;
import com.metro.ccms.web.credit.query.CarditManagementQuery;
import com.metro.ccms.web.credit.query.CreditManagementQuery;
import com.metro.ccms.web.credit.service.CreditManagementService;
import com.metro.ccms.web.credit.vo.CarditManagementVO;
import com.metro.ccms.web.credit.vo.CreditManagementVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *<p>
 *  合同模板
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/18
 * @desc
 **/
public class ContracttemplateServiceTest extends BaseTest {

    @Autowired
    private ContracttemplateService contracttemplateService;

/**
* @Param 查询
* @description
* @author JiXiang.Song
* @date 2020/12/18 13:59
* @return
* @throws
*/
    @Test
    public void test(){
        ContracttemplateQuery blackListQuery=new ContracttemplateQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setStatus("1");
        List<ContracttemplateVO> list=contracttemplateService.pagesel(blackListQuery);
        System.out.println("-----------"+list);
    }


}
