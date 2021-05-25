package com.metro.ccms.test.model;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.model.domain.RuleModelIndexDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoitemDO;
import com.metro.ccms.web.model.domain.RuleModelInfoDO;
import com.metro.ccms.web.model.query.RuleModelIndexQuery;
import com.metro.ccms.web.model.query.RuleModelInfoQuery;
import com.metro.ccms.web.model.service.RuleModelIndexService;
import com.metro.ccms.web.model.service.RuleModelInfoIndexService;
import com.metro.ccms.web.model.service.RuleModelInfoService;
import com.metro.ccms.web.utils.domain.BasicDataDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *<p>
 *  规则引擎指标、规则引擎管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/25
 * @desc
 **/
public class RuleModelServiceTest extends BaseTest {

    @Autowired
    private RuleModelIndexService ruleModelIndexService;
    @Autowired
    private RuleModelInfoService ruleModelInfoService;
    @Autowired
    private RuleModelInfoIndexService ruleModelInfoIndexService;

    /**
     * 指标查询
     */
    @Test
    public void list(){
        RuleModelIndexQuery ruleModelIndexQuery=new RuleModelIndexQuery();
        ruleModelIndexQuery.setId(72L);
        ruleModelIndexQuery.setName("即时逾期金额");
        ruleModelIndexQuery.setType("A");
        List<RuleModelIndexDO> list=ruleModelIndexService.list(ruleModelIndexQuery);
        System.out.println("-------------"+list);
    }

    /**
     * 指标保存
     */
    @Test
    public void save(){
        RuleModelIndexDO ruleModelIndexDO=new RuleModelIndexDO();
        ruleModelIndexDO.setName("测试指标名称");
        ruleModelIndexDO.setType("C");
        ruleModelIndexDO.setDescription("测试描述");
        ruleModelIndexDO.setRemark("此处测试备注");
        Result Result=ruleModelIndexService.save(ruleModelIndexDO);
        System.out.println("-------------"+Result);
    }
    /**
     * 指标修改
     */
    @Test
    public void update(){
        RuleModelIndexDO ruleModelIndexDO=new RuleModelIndexDO();
        ruleModelIndexDO.setId(78L);
        ruleModelIndexDO.setName("测试指标");
        ruleModelIndexDO.setType("D");
        ruleModelIndexDO.setDescription("测试描述");
        ruleModelIndexDO.setRemark("测试备注");
        Result Result=ruleModelIndexService.update(ruleModelIndexDO);
        System.out.println("-------------"+Result);
    }
    /**
     * 指标删除
     */
    @Test
    public void remove(){
        Result Result=ruleModelIndexService.remove(78L);
        System.out.println("-------------"+Result);
    }
    /**
     * 模型根据id查询
     */
    @Test
    public void get(){
        RuleModelInfoDO Result=ruleModelInfoService.get(1L);
        System.out.println("-------------"+Result);
    }
    /**
     * 模型查询
     */
    @Test
    public void listmoxing(){
        RuleModelInfoQuery ruleModelInfoQuery=new RuleModelInfoQuery();
        ruleModelInfoQuery.setId(1L);
        ruleModelInfoQuery.setName("政府背景类存量客户模型");
        ruleModelInfoQuery.setStatus("1");
        List<RuleModelInfoDO> list=ruleModelInfoService.list(ruleModelInfoQuery);
        System.out.println("-------------"+list);
    }
    /**
     * 模型保存
     */
    @Test
    public void savemoxing(){
        RuleModelInfoDO ruleModelInfoDO=new RuleModelInfoDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        ruleModelInfoDO.setName("政府背景类存量客户模型测试");
        ruleModelInfoDO.setDescription("模型描述");
        ruleModelInfoDO.setExpirydate(date);
        ruleModelInfoDO.setRemark("123");
        Result Result=ruleModelInfoService.save(ruleModelInfoDO);
        System.out.println("-------------"+Result);
    }
    /**
     * 模型添加指标
     */
    @Test
    public void addindex(){
        RuleModelIndexInfoDO ruleModelInfoDO=new RuleModelIndexInfoDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        ruleModelInfoDO.setModelId(10L);
        ruleModelInfoDO.setIndexId(79L);
        Result Result=ruleModelInfoService.addindex(ruleModelInfoDO);
        System.out.println("-------------"+Result);
    }
    /**
     * 模型修改
     */
    @Test
    public void updatemoxing(){
        RuleModelInfoDO ruleModelInfoDO=new RuleModelInfoDO();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        ruleModelInfoDO.setName("政府背景类存量客户模型");
        ruleModelInfoDO.setDescription("修改描述");
        ruleModelInfoDO.setExpirydate(date);
        ruleModelInfoDO.setRemark("456");
        ruleModelInfoDO.setId(10L);
        Result Result=ruleModelInfoService.update(ruleModelInfoDO);
        System.out.println("-------------"+Result);
    }
    /**
     * 模型删除
     */
    @Test
    public void removemoxing(){
        Result Result=ruleModelInfoService.remove(10l);
        System.out.println("-------------"+Result);
    }
    /**
     * 添加指标按钮查询
     */
    @Test
    public void listmox(){
        List<RuleModelIndexInfoDO> list=ruleModelInfoIndexService.list(1l);
        System.out.println("-------------"+list);
    }

    /**
     * 修改按钮查询
     * /rulemodel/modelInfoIndexItem/list
     */

    /**
     * 保存模型指标元素表
     * 修改按钮保存、新增按钮保存
     */
    @Test
    public void savemox(){
        RuleModelIndexInfoDO modelInfoIndex= new RuleModelIndexInfoDO();
        RuleModelIndexInfoitemDO RuleModelIndexInfoitemDO=new RuleModelIndexInfoitemDO();
        List<RuleModelIndexInfoitemDO> ruleModelIndexInfoitemDO=new ArrayList<RuleModelIndexInfoitemDO>();
        RuleModelIndexInfoitemDO.setUpperValue(BigDecimal.valueOf(100));
        RuleModelIndexInfoitemDO.setLowerValue(BigDecimal.valueOf(200));
        RuleModelIndexInfoitemDO.setDescription("备注");
        RuleModelIndexInfoitemDO.setIncludeRange(1);
        ruleModelIndexInfoitemDO.add(0,RuleModelIndexInfoitemDO);
        modelInfoIndex.setId(161L);//中间表ID
        modelInfoIndex.setIndexId(72L);
        modelInfoIndex.setModelId(11L);
        modelInfoIndex.setMethod("1");//打分方式
        modelInfoIndex.setItemList(ruleModelIndexInfoitemDO);
        Result Result=ruleModelInfoIndexService.save(modelInfoIndex, modelInfoIndex.getItemList());
        System.out.println("-------------"+Result);
    }
    /**
     * 根据大类查询
     * /rulemodel/index/list
     */

    /**
     * 中间表删除
     */
    @Test
    public void removemox(){
        Result Result=ruleModelInfoIndexService.remove(161l);
        System.out.println("-------------"+Result);
    }
    /**
     * 查询客户类型
     */
    @Test
    public void listkehu(){
        List<BasicDataDO> modelInfoIndexList = ruleModelInfoIndexService.listkehu();
        System.out.println("-------------"+modelInfoIndexList);
    }

}
