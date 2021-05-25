package com.metro.ccms.web.model.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.web.model.domain.*;
import com.metro.ccms.web.model.mapper.*;
import com.metro.ccms.web.model.utils.JexlEngineUtils;
import com.metro.ccms.web.model.utils.ModelUtils;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ModelInfoResultServiceImpl {
    @Autowired
    private ModelInfoMapper modelInfoMapper;
    @Autowired
    private ModelIndexMapper modelIndexMapper;
    @Autowired
    private ModelInfoIndexMapper modelInfoIndexMapper;
    @Autowired
    private ModelInfoIndexItemMapper modelInfoIndexItemMapper;
    @Autowired
    private ModelResultMainMapper modelResultMainMapper;
    @Autowired
    private ModelResultSubMapper modelResultSubMapper;

    /**
     * 获取当前打分卡结果
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public JSONObject getCurrentResult(ModelResultMainDO mainDO, Map<String, String> paramMap) {
        JSONObject modObj = new JSONObject();

        ModelInfoDO modelInfoDO = modelInfoMapper.get(mainDO.getModId());

        List<ModelInfoIndexDO> modelInfoIndexList = modelInfoIndexMapper.list(modelInfoDO.getId());

        List<ModelResultSubDO> subList = new ArrayList<>(100);
        List<ModelResultSubDO> addSubtractList = new ArrayList<>(50);
        Set<Long> indexSet = new HashSet<>();

        for (ModelInfoIndexDO modelInfoIndexDO : modelInfoIndexList) {
            ModelResultSubDO subDO = null;

            //加减分校验
            if ("Z".equals(modelInfoIndexDO.getType())) {
                if (StringUtils.isBlank(modelInfoIndexDO.getExpression())) {
                    addSubtractList.add(this.getAddAndSubtract(modelInfoIndexDO, paramMap));
                } else {
                    List<ModelResultSubDO> compositeIndex = this.getCompositeIndex(modelInfoIndexDO, indexSet, paramMap);
                    for (ModelResultSubDO index : compositeIndex) {
                        if ("Z".equals(index.getType())) {
                            addSubtractList.add(index);
                            continue;
                        }
                        subList.add(index);
                    }
                }
                continue;
            }

            //根据表达式来判断单一指标还是复合指标
            if (StringUtils.isBlank(modelInfoIndexDO.getExpression())) {
                //单一指标
                subDO = this.getSingletonIndex(modelInfoIndexDO, paramMap);
                if (indexSet.add(subDO.getIndexId())) {
                    subList.add(subDO);
                }
            } else {
                //复合指标
                subList.addAll(this.getCompositeIndex(modelInfoIndexDO, indexSet, paramMap));
            }
        }

        //以上指标都组装计算完毕，下面进行合计
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalAddSub = BigDecimal.ZERO;

        for (ModelResultSubDO subDO : subList) {
            total = total.add(subDO.getScore());
        }
        for (ModelResultSubDO subDO : addSubtractList) {
            totalAddSub = totalAddSub.add(subDO.getScore());
        }

        if ("1".equals(modelInfoDO.getFinancial())) {
            //增加财务指标
            ModelResultSubDO subCBType = new ModelResultSubDO();
            subCBType.setIndexName("报表类型");
            subCBType.setScore(BigDecimal.ZERO);
            subCBType.setIndexId((long)100001);
            subCBType.setNativeValue(paramMap.get("100001"));
            subCBType.setStandard(0);
            subCBType.setWeight(BigDecimal.ZERO);
            subCBType.setType("C");
            subCBType.setOrderId(0);
            subList.add(subCBType);

            ModelResultSubDO subCBMonth = new ModelResultSubDO();
            subCBMonth.setIndexName("财报月度数");
            subCBMonth.setScore(BigDecimal.ZERO);
            subCBMonth.setIndexId((long)100002);
            subCBMonth.setNativeValue(paramMap.get("100002"));
            subCBMonth.setStandard(0);
            subCBMonth.setWeight(BigDecimal.ZERO);
            subCBMonth.setType("C");
            subCBMonth.setOrderId(0);
            subList.add(subCBMonth);
        }

        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);

        mainDO.setCreatedBy(SecurityUtils.getLoginUser().getUsername());
        mainDO.setCreateTime(new Date());
        //mainDO.setModId(modId);
        mainDO.setScore(total.add(totalAddSub));
        //mainDO.setCustId(999);
        mainDO.setModName(modelInfoDO.getName());
        mainDO.setAddSubScore(totalAddSub);
        mainDO.setIndexScore(total);


        //对结果进行排序
        Collections.sort(subList, (o1, o2) -> {
            if (o1.getType().compareTo(o2.getType()) == 0) {
                return o1.getOrderId() - o2.getOrderId();
            }
            return o1.getType().compareTo(o2.getType());
        });

        modObj.put("result", mainDO);
        modObj.put("subList", subList);
        modObj.put("addSubtractList", addSubtractList);


        modelResultMainMapper.save(mainDO);

        for (ModelResultSubDO subDO : subList) {
            subDO.setMainId(mainDO.getId());
        }
        modelResultSubMapper.batchSave(subList);

        for (ModelResultSubDO subDO : addSubtractList) {
            subDO.setMainId(mainDO.getId());
        }
        modelResultSubMapper.batchSave(addSubtractList);

        return modObj;
    }

    /**
     * 组装单一指标
     *
     * @param modelInfoIndexDO
     * @return
     */
    private ModelResultSubDO getSingletonIndex(ModelInfoIndexDO modelInfoIndexDO, Map<String, String> param) {

        ModelResultSubDO subDO = createSubDo(modelInfoIndexDO, param);

        if (subDO.getNativeValue() == null || subDO.getValue() == null) {
            subDO.setException("指标值为空！");
            return subDO;
        }

        if (subDO.getIndexId() == 10) {
            //成立年限
            int clYear = subDO.getValue().intValue();
            int year = LocalDate.now().getYear() - clYear;
            if (year < 0) {
                subDO.setValue(BigDecimal.ZERO);
            } else {
                subDO.setValue(new BigDecimal(year));
            }
        }


        if ("2".equals(modelInfoIndexDO.getMethod())) {
            //区间打分
            setIntervalMethod(modelInfoIndexDO.getId(), subDO);
            if (StringUtils.isNotBlank(subDO.getException())) {
                return subDO;
            }
        }

        //计算得分公式   指标分数*权重/标准值

        BigDecimal weight = subDO.getWeight();
        BigDecimal standard = new BigDecimal(subDO.getStandard());
        BigDecimal score = subDO.getValue().multiply(weight).divide(standard, 4, BigDecimal.ROUND_HALF_UP);
        subDO.setScore(score);

        return subDO;
    }

    /**
     * 组装复合指标
     *
     * @param modelInfoIndexDO
     * @return
     */
    private List<ModelResultSubDO> getCompositeIndex(ModelInfoIndexDO modelInfoIndexDO, Set<Long> indexSet, Map<String, String> param) {
        List<ModelResultSubDO> subList = new ArrayList<>(10);
        //1.获取到指标表达式，解析表达式
        String cbtype = param.get("100001");
        BigDecimal cbMonth = StringUtils.toBigdecimal(param.get("100002"));
        Set<String> list = ModelUtils.splitExperssion(modelInfoIndexDO.getExpression());
        String expression = modelInfoIndexDO.getExpression();
        JexlContext map = new MapContext();
        int temp = 0;
        List<String> indexValueNull = new ArrayList<>(list.size());

        ModelResultSubDO subDO = createSubDo(modelInfoIndexDO, param);
        subDO.setScore(BigDecimal.ZERO);
        if (subDO.getValue() == null) {
            subDO.setValue(BigDecimal.ZERO);
        }


        try {

            //2.根据表达式名称获取指标
            for (String indexName : list) {

                ModelIndexDO modelIndexDO = modelIndexMapper.getIndexByName(indexName);

                if (modelIndexDO != null) {

                    ModelResultSubDO subDO1 = createSubDo(modelIndexDO, param);
                    BigDecimal val = subDO1.getValue();

                    if (val == null) {
                        indexValueNull.add(indexName);
                    }

                    if (indexSet.add(modelIndexDO.getId())) {
                        subList.add(subDO1);
                    }
                    //如果该指标为非标准财务数据，则进行计算
                    if ("0".equals(cbtype) && "N".equals(modelIndexDO.getFinStandrad())) {
                        //获取到公式计算自身值
                        String subExpression = modelIndexDO.getExpression();
                        JexlContext submap = new MapContext();
                        submap.set("value", val);
                        submap.set("month", cbMonth == null ? BigDecimal.ZERO : cbMonth);

                        String subVal = JexlEngineUtils.createExpression(subExpression).evaluate(submap).toString();
                        val = new BigDecimal(subVal).setScale(2, BigDecimal.ROUND_HALF_UP);
                        subDO1.setValue(val);
                    }

                    String index = "i" + ++temp;
                    map.set(index, val);
                    String reIndexName = "[" + indexName + "]";
                    expression = expression.replace(reIndexName, index);

                }
            }


            //非标准财务指标需要进行计算
            subList.add(subDO);

            if ("0".equals(cbtype) && "N".equals(modelInfoIndexDO.getFinStandrad())) {
                //获取到公式计算自身值
                JexlContext submap = new MapContext();
                BigDecimal val = subDO.getValue();
                submap.set("value", val);
                submap.set("month", cbMonth == null ? BigDecimal.ZERO : cbMonth);
                String subVal = JexlEngineUtils.createExpression(expression).evaluate(submap).toString();
                val = new BigDecimal(subVal).setScale(2, BigDecimal.ROUND_HALF_UP);
                subDO.setValue(val);
                indexSet.add(subDO.getIndexId());
                //param.put(subDO.getIndexId().toString(), StringUtils.isBlank(subDO.getNativeValue()) ? "" : subVal);
            } else if ("1".equals(cbtype) && "N".equals(modelInfoIndexDO.getFinStandrad())) {
                //标准财务指标
                indexSet.add(subDO.getIndexId());
            } else {

                if (list.size() == 0 || list.size() != temp) {
                    subDO.setException("指标公式不合规！");
                    return subList;
                }

                if (indexValueNull.size() == list.size() || (indexValueNull.size() > 0 && expression.indexOf("if") > 0)) {
                    StringBuilder sbu = new StringBuilder();
                    for (String s : indexValueNull) {
                        sbu.append(s).append("、");
                    }
                    sbu.append("没有维护，无法计算分数");
                    subDO.setException(sbu.toString());
                    return subList;
                }


                expression = expression.replace("[", "").replace("]", "");


                String val = JexlEngineUtils.createExpression(expression).evaluate(map).toString();
                BigDecimal bigDecimal = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP);
                subDO.setValue(bigDecimal);
                subDO.setNativeValue(bigDecimal.toPlainString());
                //计算获取区间打分所在的分数

            }

            setIntervalMethod(modelInfoIndexDO.getId(), subDO);
            if (StringUtils.isNotBlank(subDO.getException())) {
                return subList;
            }

        } catch (Exception ex) {
            subDO.setException("公式计算错误！");
        }


        //计算指标最终得分
        String nativeValue = subDO.getNativeValue();
        BigDecimal weight = subDO.getWeight();
        BigDecimal standard = new BigDecimal(subDO.getStandard());
        BigDecimal score = BigDecimal.ZERO;

        if ("Z".equals(subDO.getType())) {
            //如果是加减分，不需要计算最终得分，只需基础分数可以
            subDO.setScore(subDO.getValue());
            return subList;
        }

        if (StringUtils.isNotBlank(nativeValue)) {
            score = subDO.getValue().multiply(weight).divide(standard, 4, BigDecimal.ROUND_HALF_UP);
        } else {
            subDO.setValue(BigDecimal.ZERO);
        }

        subDO.setScore(score);
        return subList;
    }

    /**
     * 组装加减分
     *
     * @param modelInfoIndexDO
     * @return
     */
    private ModelResultSubDO getAddAndSubtract(ModelInfoIndexDO modelInfoIndexDO, Map<String, String> param) {
        ModelResultSubDO subDO = createSubDo(modelInfoIndexDO, param);

        if (subDO.getValue() == null) {
            subDO.setException("指标值为空！");
            return subDO;
        }

        //计算得分
        subDO.setScore(subDO.getValue());

        return subDO;
    }

    /**
     * 获取区间打分值
     *
     * @param modIndexId
     * @param subDO
     * @return
     */
    private void setIntervalMethod(Long modIndexId, ModelResultSubDO subDO) {

        List<ModelInfoIndexItemDO> list = modelInfoIndexItemMapper.list(modIndexId);

        if (list == null) {
            subDO.setException("请检查模型指标信息是否正确！");
            return;
        }

        list = list.stream().filter(item -> ModelUtils.isInterval(subDO.getValue(), item.getLowerValue(), item.getUpperValue(), item.getIncludeRange())).collect(Collectors.toList());

        if (list.size() != 1) {
            subDO.setException("请检查模型指标信息是否正确！");
            return;
        }

        ModelInfoIndexItemDO item = list.get(0);
        subDO.setValue(item.getGrade());
    }

    private ModelResultSubDO createSubDo(ModelIndexDO modelIndexDO, Map<String, String> param) {

        ModelResultSubDO subDO = new ModelResultSubDO();
        String nativeValue = param.get(modelIndexDO.getId().toString());
        BigDecimal value = StringUtils.toBigdecimal(nativeValue);

        subDO.setIndexId(modelIndexDO.getId());
        subDO.setIndexName(modelIndexDO.getName());
        subDO.setStandard(0);
        subDO.setWeight(BigDecimal.ZERO);
        subDO.setMethod(modelIndexDO.getMethod());
        subDO.setType(modelIndexDO.getType());
        subDO.setRemark(modelIndexDO.getRemark());
        subDO.setDescription(modelIndexDO.getDescription());
        subDO.setNativeValue(nativeValue);
        subDO.setScore(BigDecimal.ZERO);
        subDO.setValue(value);
        subDO.setExpression(modelIndexDO.getExpression());
        subDO.setOrderId(modelIndexDO.getOrderId());
        subDO.setException("公式内指标，不计算！");

        return subDO;
    }

    private ModelResultSubDO createSubDo(ModelInfoIndexDO modelInfoIndexDO, Map<String, String> param) {

        ModelResultSubDO subDO = new ModelResultSubDO();

        //获取指标值
        String nativeValue = param.get(modelInfoIndexDO.getIndexId().toString());
        BigDecimal value = StringUtils.toBigdecimal(nativeValue);

        subDO.setIndexId(modelInfoIndexDO.getIndexId());
        subDO.setIndexName(modelInfoIndexDO.getIndexName());
        subDO.setStandard(modelInfoIndexDO.getStandrad());
        subDO.setWeight(modelInfoIndexDO.getWeight());
        subDO.setMethod(modelInfoIndexDO.getMethod());
        subDO.setType(modelInfoIndexDO.getType());
        subDO.setRemark(modelInfoIndexDO.getRemark());
        subDO.setDescription(modelInfoIndexDO.getDescription());
        subDO.setNativeValue(nativeValue);
        subDO.setScore(BigDecimal.ZERO);
        subDO.setExpression(modelInfoIndexDO.getExpression());
        subDO.setValue(value);
        subDO.setOrderId(modelInfoIndexDO.getOrderId());

        return subDO;
    }


    /**
     * 获取最后一次打分记录
     *
     * @param documentNo
     * @return
     */
    public JSONObject getLastResult(String documentNo) {

        JSONObject result = new JSONObject();

        ModelResultMainDO lastResult = modelResultMainMapper.getLastResult(documentNo);

        if (lastResult == null) {
            return result;
        }

        List<ModelResultSubDO> modelResultSubDOS = modelResultSubMapper.get(lastResult.getId());

        result.put("result", lastResult);
        result.put("resultSub", modelResultSubDOS);
        return result;
    }


}
