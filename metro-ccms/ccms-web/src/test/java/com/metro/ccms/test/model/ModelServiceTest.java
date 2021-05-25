package com.metro.ccms.test.model;

import com.alibaba.fastjson.JSON;
import com.metro.ccms.framework.web.domain.server.Sys;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.service.ISysFileService;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;
import com.metro.ccms.web.model.service.ModelIndexService;
import com.metro.ccms.web.utils.CommonUtils;
import com.metro.ccms.web.utils.domain.BasicDataDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 14:26 2020/12/1
 * @Modified By:
 */
public class ModelServiceTest extends BaseTest {

    @Autowired
    private ModelIndexService modelIndexService;
    @Autowired
    private ISysFileService iSysFileService;
    @Autowired
    private CommonUtils commonUtils;


    @Test
    public void test(){
        ModelIndexDO modelIndex=new ModelIndexDO();
        modelIndexService.save(modelIndex);
    }

    @Test
    public void getFileList(){
        String applicationNo=commonUtils.getApplicationNo(111L,1);
        List<BasicDataDO> list1=commonUtils.getBasicDataByCType("BusinessType");
        List<SysBasicFile> list=iSysFileService.getFileList("111","1");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testStringTwo(){
        List<ModelInfoIndexItemDO> itemList=new ArrayList<>();
        int a=5;
        int low=0;
        int upper=10;
        for (int i=0;i<a;i++){
            ModelInfoIndexItemDO itDo=new ModelInfoIndexItemDO();
            itDo.setLowerValue(new BigDecimal(low));
            itDo.setUpperValue(new BigDecimal(upper));
            itemList.add(itDo);
            low=low+10;
            upper=upper+10;
        }

        String[][] str=new String[][]{};
        for (int i=0;i<itemList.size();i++){
            str[i][0]=itemList.get(i).getLowerValue().toString();
            str[i][1]=itemList.get(i).getUpperValue().toString();
            System.out.println("======"+str[i][1]);
        }
    }
}
