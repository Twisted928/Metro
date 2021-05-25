package com.metro.ccms.test.earlywarning;

import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.earlywarning.query.WarningEmailQuery;
import com.metro.ccms.web.earlywarning.service.WarningEmailService;
import com.metro.ccms.web.earlywarning.vo.WarningEmailVO;
import com.metro.ccms.web.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *<p>
 *  邮件记录
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/15
 * @desc
 **/
public class EarlyemailServiceTest extends BaseTest {

    @Autowired
    private WarningEmailService warningemailService;
    @Autowired
    private CommonUtils commonUtils;

/**
* @Param 邮件记录查询
* @description
* @author JiXiang.Song
* @date 2020/12/3 16:26
* @return
* @throws
*/
    @Test
    public void pagesel(){
        WarningEmailQuery warningemailQuery=new WarningEmailQuery();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        warningemailQuery.setTitle("标");
        warningemailQuery.setUsername("admin");
        warningemailQuery.setRolename("角色");
        List<WarningEmailVO> list=warningemailService.pagesel(warningemailQuery);
        System.out.println("-----------"+list);
    }


}
