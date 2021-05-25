package com.metro.ccms.test.credit;

import com.metro.ccms.test.BaseTest;
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
 *  授信管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/9
 * @desc
 **/
public class CreditModuleServiceTest extends BaseTest {

    @Autowired
    private CreditManagementService creditManagementService;

/**
* @Param 授信管理-查询
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void test(){
        CreditManagementQuery blackListQuery=new CreditManagementQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setCustcode("123456");
        blackListQuery.setStatus("1");
        List<CreditManagementVO> list=creditManagementService.pagesel(blackListQuery);
        System.out.println("-----------"+list);
    }
/**
* @Param 授信管理-历史
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void history(){
        CreditManagementQuery blackListQuery=new CreditManagementQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setCustcode("123456");
        blackListQuery.setStatus("1");
        List<CreditManagementVO> list=creditManagementService.history(blackListQuery);
        System.out.println("-----------"+list);
    }
/**
* @Param 授信管理-卡号查询
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void pagecard(){
        CarditManagementQuery blackListQuery=new CarditManagementQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setCustcode("123456");
        blackListQuery.setStatus("1");
        blackListQuery.setDepartcode("10000");
        List<CarditManagementVO> list=creditManagementService.pagecard(blackListQuery);
        System.out.println("-----------"+list);
    }

}
