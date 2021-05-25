package com.metro.ccms.test.customer;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.customer.controller.BlackListController;
import com.metro.ccms.web.customer.domian.WhitelistmanagementDO;
import com.metro.ccms.web.customer.query.BlackListQuery;
import com.metro.ccms.web.customer.query.WhitelistmanagementQuery;
import com.metro.ccms.web.customer.service.BlackListService;
import com.metro.ccms.web.customer.service.WhitelistmanagementService;
import com.metro.ccms.web.customer.vo.WhitelistmanagementVO;
import com.metro.ccms.web.customer.vo.WhitelistmanagementqdVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *<p>
 *  黑名单单元测试
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/3
 * @desc
 **/
public class BlackListServiceTest extends BaseTest {

    @Autowired
    private BlackListService blackListService;
    @Autowired
    private WhitelistmanagementService whitelistmanagementService;
    @Autowired
    private BlackListController blackListController;

/**
* @Param 黑名单查询
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void test(){
        BlackListQuery blackListQuery=new BlackListQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        blackListQuery.setCustcode("123456");
        blackListQuery.setCustname("test");
        blackListQuery.setStatus("1");
//        blackListQuery.setValidfrom(formatter.format(date));
//        blackListQuery.setValidto(formatter.format(date));
        blackListService.pagesells(blackListQuery);
    }
/**
* @Param 白名单查询
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void testb(){
        WhitelistmanagementQuery whitelistmanagementQuery=new WhitelistmanagementQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        whitelistmanagementQuery.setApplicationno("10001");
        whitelistmanagementQuery.setCustcode("12345");
        whitelistmanagementQuery.setCustname("test");
        whitelistmanagementQuery.setApprovestatus("1");
        List<WhitelistmanagementqdVO> list=whitelistmanagementService.pagesells(whitelistmanagementQuery);
        System.out.println(list);
    }
/**
* @Param 白名单查询详情
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void seldetails(){
        WhitelistmanagementQuery whitelistmanagementQuery=new WhitelistmanagementQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        whitelistmanagementQuery.setId("14");
        WhitelistmanagementVO list=whitelistmanagementService.seldetails(whitelistmanagementQuery);
        System.out.println(list);
    }
/**
* @Param 白名单新增
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void savemonitoring(){
        WhitelistmanagementDO whitelistmanagementQuery=new WhitelistmanagementDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        whitelistmanagementQuery.setApplicationno("10001");
        whitelistmanagementQuery.setCustcode("12345");
        whitelistmanagementQuery.setCustname("test");
        whitelistmanagementQuery.setReason("测试原因");
        whitelistmanagementQuery.setValidfrom(date);
        whitelistmanagementQuery.setValidto(date);
        whitelistmanagementQuery.setApprovestatus("1");
        whitelistmanagementQuery.setApplicant("测试人员");
        whitelistmanagementQuery.setApplytime(formatter.format(date));
        Result result=whitelistmanagementService.savemonitoring(whitelistmanagementQuery);
        System.out.println(result);
    }
    /**
     * @Param 白名单修改
     * @description
     * @author JiXiang.Song
     * @date 2020/12/3 16:26
     * @return
     * @throws
     */
    @Test
    public void updmonitoring(){
        WhitelistmanagementDO whitelistmanagementQuery=new WhitelistmanagementDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        whitelistmanagementQuery.setId("6");
        whitelistmanagementQuery.setApplicationno("100002548");
        whitelistmanagementQuery.setCustcode("12345");
        whitelistmanagementQuery.setCustname("test");
        whitelistmanagementQuery.setReason("测试修改");
        whitelistmanagementQuery.setValidfrom(date);
        whitelistmanagementQuery.setValidto(date);
        whitelistmanagementQuery.setApprovestatus("1");
        whitelistmanagementQuery.setApplicant("测试修改人员");
        whitelistmanagementQuery.setApplytime(formatter.format(date));
        whitelistmanagementQuery.setAttachmentname("测试附件");
        whitelistmanagementQuery.setAttachmenturl("测试URL");
        whitelistmanagementService.updmonitoring(whitelistmanagementQuery);
    }
/**
* @Param 白名单审批
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void updatemonitoring() throws ParseException {
        WhitelistmanagementDO whitelistmanagementQuery=new WhitelistmanagementDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        whitelistmanagementQuery.setId("63");
        whitelistmanagementQuery.setApprovestatus("2");
        whitelistmanagementQuery.setCustcode("C910015580");
        whitelistmanagementQuery.setCustname("陕西黄马甲物流配送股份有限公司");
        whitelistmanagementQuery.setTaskId("157504");
        whitelistmanagementQuery.setApplicationno("WL2101190063");
        whitelistmanagementQuery.setInstanceid("142501");
        whitelistmanagementQuery.setValidfrom(formatter.parse("2021-01-19"));
        whitelistmanagementQuery.setValidto(formatter.parse("2021-01-20"));
        Result Result=whitelistmanagementService.updatemonitoring(whitelistmanagementQuery);
        System.out.println("///////////////////"+Result);
    }
/**
* @Param 白名单撤销
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void deletemonitoring(){
        WhitelistmanagementDO whitelistmanagementQuery=new WhitelistmanagementDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        whitelistmanagementQuery.setId("7");
        whitelistmanagementQuery.setApprovestatus("4");
        whitelistmanagementService.deletemonitoring(whitelistmanagementQuery);
    }
/**
* @Param 白名单移除
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void remove(){
        WhitelistmanagementDO whitelistmanagementQuery=new WhitelistmanagementDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        whitelistmanagementQuery.setId("11");
        whitelistmanagementQuery.setReason("测试移出原因");
        whitelistmanagementQuery.setCustcode("1234");
        whitelistmanagementQuery.setCustname("test01");
        whitelistmanagementQuery.setValidfrom(date);
        whitelistmanagementQuery.setValidto(getNextWeekMonday(date));
        Result Result=whitelistmanagementService.remove(whitelistmanagementQuery);
        System.out.println("----------------"+Result);
    }

/**
 * @Param 获取下周时间
 * @description
 * @author JiXiang.Song
 * @date 2020/12/21
 * @return
 * @throws
 */
    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }
    /**
    * @Param 获取当前周时间
    * @description
    * @author JiXiang.Song
    * @date 2020/12/21 9:10
    * @return
    * @throws
    */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }
}
