package com.metro.ccms.web.model.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.domain.ModelInfoDO;
import com.metro.ccms.web.model.domain.ModelInfoIndexDO;
import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;
import com.metro.ccms.web.model.mapper.ModelIndexMapper;
import com.metro.ccms.web.model.mapper.ModelInfoIndexItemMapper;
import com.metro.ccms.web.model.mapper.ModelInfoIndexMapper;
import com.metro.ccms.web.model.mapper.ModelInfoMapper;
import com.metro.ccms.web.model.query.ModelQuery;
import com.metro.ccms.web.model.service.ModelInfoIndexService;
import com.metro.ccms.web.model.service.ModelInfoService;
import com.metro.ccms.web.model.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ModelInfoServiceImpl implements ModelInfoService {


    @Autowired
    private ModelInfoMapper modelInfoMapper;
    @Autowired
    private ModelIndexMapper modelIndexMapper;
    @Autowired
    private ModelInfoIndexMapper modelInfoIndexMapper;
    @Autowired
    private ModelInfoIndexItemMapper modelInfoIndexItemMapper;
    @Autowired
    private ModelInfoIndexService modelInfoIndexService;


    /**
     * 根据id获取模型信息
     * @param id
     * @return
     */
    @Override
    public ModelInfoDO get(Long id) {
        return modelInfoMapper.get(id);
    }

    /**
     * 查询模型信息列表
     * @param query
     * @return
     */
    @Override
    public List<ModelInfoDO> list(ModelQuery query) {
        return modelInfoMapper.list(query);
    }

    /**
     * 保存模型信息
     * @param modelInfo
     * @return
     */
    @Override
    public Result save(ModelInfoDO modelInfo) {

        if (modelInfo == null) {
            return Result.error("模型信息不能为空！");
        }
        if (StringUtils.isBlank(modelInfo.getName())) {
            return Result.error("模型名称不能为空！");
        }

//        if (StringUtils.isBlank(modelInfo.getScope())) {
//            return Result.error("模型范围不能为空！");
//        }
        modelInfo.setCreatedBy(SecurityUtils.getLoginUser().getUsername());
        modelInfoMapper.save(modelInfo);
        return Result.success();
    }

    /**
     * 修改模型信息
     * @param modelInfo
     * @return
     */
    @Override
    public Result update(ModelInfoDO modelInfo) {
        if (modelInfo == null) {
            return Result.error("模型信息不能为空！");
        }
        if (modelInfo.getId() == null) {
            return Result.error("模型编码不能为空！");
        }
        if (StringUtils.isBlank(modelInfo.getName())) {
            return Result.error("模型名称不能为空！");
        }
        modelInfo.setUpdateTime(new Date());
        modelInfo.setUpdatedBy(SecurityUtils.getLoginUser().getUsername());
        modelInfoMapper.update(modelInfo);
        return Result.success();
    }

    /**
     * 根据id删除模型信息
     * @param id
     * @return
     */
    @Override
    public Result remove(Long id) {
        if (id == null) {
            return Result.error("模型编码不能为空！");
        }
        ModelInfoDO modelInfoDO = modelInfoMapper.get(id);

        if ("1".equals(modelInfoDO.getPublish())) {
            return Result.error("该模型已发布,请撤销发布后删除！");
        }
        modelInfoMapper.remove(id);
        return Result.success();
    }


    /**
     * 发布模型
     * @param id
     * @return
     */
    @Override
    public Result push(Long id) {
        if (id == null) {
            return Result.error("模型编码不能为空！");
        }

        ModelInfoDO modelInfoDO = modelInfoMapper.get(id);

        if ("1".equals(modelInfoDO.getPublish())) {
            return Result.error("该模型已发布！");
        }
        List<ModelInfoIndexDO> modelInfoIndexList = modelInfoIndexService.list(id);
        if (modelInfoIndexList.size()==0){
            return Result.error("请至少维护一个指标后再发布!");
        }
        for (ModelInfoIndexDO modelInfoIndexDO:modelInfoIndexList){
            if ("2".equals(modelInfoIndexDO.getMethod()) && modelInfoIndexItemMapper.getItemByInfoIndexId(modelInfoIndexDO.getId())==0){
                return Result.error("指标:"+modelInfoIndexDO.getIndexName()+"为区间打分法,请至少维护一个区间值再发布!");
            }
        }
        modelInfoDO.setUpdateTime(new Date());
        modelInfoDO.setUpdatedBy(SecurityUtils.getLoginUser().getUsername());
        modelInfoDO.setPublish("1");
        modelInfoMapper.update(modelInfoDO);

        return Result.success();
    }

    /**
     * 撤销发布
     * @param id
     * @return
     */
    @Override
    public Result reBackPush(Long id) {
        if (id == null) {
            return Result.error("模型编码不能为空！");
        }

        ModelInfoDO modelInfoDO = modelInfoMapper.get(id);
        modelInfoDO.setUpdateTime(new Date());
        modelInfoDO.setUpdatedBy(SecurityUtils.getLoginUser().getUsername());
        modelInfoDO.setPublish("0");
        modelInfoMapper.update(modelInfoDO);

        return Result.success();
    }

    /**
     * 根据模型id获取模型、指标、加减分项详细信息
     * @param id
     * @return
     */
    @Override
    public JSONObject getMod(Long id) {

        JSONObject modObj = new JSONObject();

        ModelInfoDO modelInfoDO = modelInfoMapper.get(id);

        modObj.put("modInfo", modelInfoDO);

        List<ModelInfoIndexDO> modelInfoIndexList = modelInfoIndexMapper.list(modelInfoDO.getId());

        JSONArray jsonIndexArray = new JSONArray();
        JSONArray jsonAddAndSubtractArray = new JSONArray();
        Set<Long> indexSet = new HashSet<>();

        List<ModelInfoIndexItemDO> list = modelInfoIndexItemMapper.listByModId(modelInfoDO.getId().toString());

        //如果是有财报模型，则增加报表类型（标准财务数据是指年度财务数据）和财务报表月度数
        if ("1".equals(modelInfoDO.getFinancial())) {
            jsonIndexArray.addAll(this.createFinIndex());
        }


        for (ModelInfoIndexDO modelInfoIndexDO : modelInfoIndexList) {
            JSONObject indexObj = null;

            if ("Z".equals(modelInfoIndexDO.getType())) {
                //加减分校验

                if (StringUtils.isBlank(modelInfoIndexDO.getExpression())) {
                    jsonAddAndSubtractArray.add(this.getAddAndSubtract(modelInfoIndexDO, list));
                } else {
                    //复合指标
                    jsonIndexArray.addAll(this.getCompositeIndex(modelInfoIndexDO, indexSet, list));
                }
                continue;
            }
            //根据表达式来判断单一指标还是复合指标
            if (StringUtils.isBlank(modelInfoIndexDO.getExpression())) {
                //单一指标
                indexObj = this.getSingletonIndex(modelInfoIndexDO, list);
                if (indexSet.add(indexObj.getLong("indexId"))) {
                    jsonIndexArray.add(indexObj);
                }
            } else {
                //复合指标
                jsonIndexArray.addAll(this.getCompositeIndex(modelInfoIndexDO, indexSet, list));
            }


        }

        Collections.sort(jsonIndexArray, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                JSONObject js1 = (JSONObject) o1;
                JSONObject js2 = (JSONObject) o2;

                Integer i1 = js1.getInteger("orderId");
                Integer i2 = js2.getInteger("orderId");

                int diff = i1 - i2;
                if (diff > 0) {
                    return 1;
                } else if (diff < 0) {
                    return -1;
                }
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

        modObj.put("indexList", jsonIndexArray);
        modObj.put("addAndSubtractList", jsonAddAndSubtractArray);

        return modObj;
    }


    /**
     * 组装单一指标
     *
     * @param modelInfoIndexDO
     * @return
     */
    private JSONObject getSingletonIndex(ModelInfoIndexDO modelInfoIndexDO, List<ModelInfoIndexItemDO> list) {

        JSONObject indexObj = new JSONObject();
        List<ModelInfoIndexItemDO> items = null;

        if ("1".equals(modelInfoIndexDO.getMethod())) {
            //逻辑判断 获取指标选择项信息
            items = list.stream().filter(item -> item.getModIndexId().equals(modelInfoIndexDO.getId())).collect(Collectors.toList());
        }

        indexObj.put("id", modelInfoIndexDO.getId());
        indexObj.put("indexId", modelInfoIndexDO.getIndexId());
        indexObj.put("indexName", modelInfoIndexDO.getIndexName());
        indexObj.put("standrad", modelInfoIndexDO.getStandrad());
        indexObj.put("weight", modelInfoIndexDO.getWeight());
        indexObj.put("method", modelInfoIndexDO.getMethod());
        indexObj.put("type", modelInfoIndexDO.getType());
        indexObj.put("remark", modelInfoIndexDO.getRemark());
        indexObj.put("orderId", modelInfoIndexDO.getOrderId());
        indexObj.put("description", modelInfoIndexDO.getDescription());
        indexObj.put("form", "2".equals(modelInfoIndexDO.getMethod()) ? "NmuberInput" : "Select");
        indexObj.put("items", items);

        return indexObj;
    }

    /**
     * 组装复合指标
     *
     * @param modelInfoIndexDO
     * @return
     */
    private JSONArray getCompositeIndex(ModelInfoIndexDO modelInfoIndexDO, Set<Long> indexSet, List<ModelInfoIndexItemDO> itemDOList) {
        JSONArray jsonArray = new JSONArray();
        ModelInfoIndexDO modelInfoIndexDO1 = null;
        //1.获取到指标表达式，解析表达式
        Set<String> list = ModelUtils.splitExperssion(modelInfoIndexDO.getExpression());


        //2.根据表达式名称获取指标
        for (String str : list) {

            ModelIndexDO modelIndexDO = modelIndexMapper.getIndexByName(str);

            if (modelIndexDO != null) {

                modelInfoIndexDO1 = new ModelInfoIndexDO();
                modelInfoIndexDO1.setIndexName(modelIndexDO.getName());
                modelInfoIndexDO1.setIndexId(modelIndexDO.getId());
                modelInfoIndexDO1.setMethod("2");
                modelInfoIndexDO1.setType(modelIndexDO.getType());
                modelInfoIndexDO1.setRemark(modelIndexDO.getRemark());
                modelInfoIndexDO1.setDescription(modelIndexDO.getDescription());
                modelInfoIndexDO1.setOrderId(modelIndexDO.getOrderId());

                if (indexSet.add(modelInfoIndexDO1.getIndexId())) {
                    jsonArray.add(this.getSingletonIndex(modelInfoIndexDO1, itemDOList));
                }

            }
        }

        //3.判断指标是否单一还是复合指标，如果是复合指标重复上述步骤--暂不控制


        return jsonArray;
    }


    /**
     * 组装加减分
     *
     * @param modelInfoIndexDO
     * @return
     */
    private JSONObject getAddAndSubtract(ModelInfoIndexDO modelInfoIndexDO, List<ModelInfoIndexItemDO> itemDOList) {

        return this.getSingletonIndex(modelInfoIndexDO, itemDOList);
    }

    /**
     * 如果是有财报模型，则增加报表类型（标准财务数据是指年度财务数据）和财务报表月度数
     *
     * @return
     */
    private JSONArray createFinIndex() {


        List<ModelInfoIndexItemDO> itemDOList = new ArrayList<>(30);

        for (int i = 1; i <= 12; i++) {
            ModelInfoIndexItemDO item = new ModelInfoIndexItemDO();
            item.setId((long) (100000 + i));
            item.setModIndexId((long)100002);
            item.setGrade(new BigDecimal(i));
            item.setDescription(String.valueOf(i));
            itemDOList.add(item);
        }

        ModelInfoIndexItemDO item1 = new ModelInfoIndexItemDO();
        item1.setId((long)110001);
        item1.setModIndexId((long)100001);
        item1.setGrade(BigDecimal.ONE);
        item1.setDescription("标准");
        itemDOList.add(item1);

        ModelInfoIndexItemDO item2 = new ModelInfoIndexItemDO();
        item2.setId((long)110002);
        item2.setModIndexId((long)100001);
        item2.setGrade(BigDecimal.ZERO);
        item2.setDescription("非标准");
        itemDOList.add(item2);


        JSONArray jsonArray = new JSONArray();

        ModelInfoIndexDO modelInfoIndexDO1 = new ModelInfoIndexDO();
        modelInfoIndexDO1.setIndexName("报表类型");
        modelInfoIndexDO1.setIndexId((long)100001);
        modelInfoIndexDO1.setId((long)100001);
        modelInfoIndexDO1.setMethod("1");
        modelInfoIndexDO1.setType("C");
        modelInfoIndexDO1.setOrderId(0);
        modelInfoIndexDO1.setRemark("标准财务数据是指年度财务数据");
        modelInfoIndexDO1.setDescription("标准财务数据是指年度财务数据");

        jsonArray.add(this.getSingletonIndex(modelInfoIndexDO1, itemDOList));


        ModelInfoIndexDO modelInfoIndexDO2 = new ModelInfoIndexDO();
        modelInfoIndexDO2.setIndexName("财报月度数");
        modelInfoIndexDO2.setIndexId((long)100002);
        modelInfoIndexDO2.setId((long)100002);
        modelInfoIndexDO2.setMethod("1");
        modelInfoIndexDO2.setType("C");
        modelInfoIndexDO2.setOrderId(0);
        modelInfoIndexDO2.setRemark("财务报表月度数");
        modelInfoIndexDO2.setDescription("财务报表月度数");

        jsonArray.add(this.getSingletonIndex(modelInfoIndexDO2, itemDOList));


        return jsonArray;
    }


}
