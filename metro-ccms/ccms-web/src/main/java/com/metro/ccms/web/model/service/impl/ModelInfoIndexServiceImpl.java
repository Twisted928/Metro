package com.metro.ccms.web.model.service.impl;


import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.web.model.domain.ModelInfoIndexDO;
import com.metro.ccms.web.model.domain.ModelInfoIndexItemDO;
import com.metro.ccms.web.model.mapper.ModelInfoIndexItemMapper;
import com.metro.ccms.web.model.mapper.ModelInfoIndexMapper;
import com.metro.ccms.web.model.service.ModelInfoIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
public class ModelInfoIndexServiceImpl implements ModelInfoIndexService {

    @Autowired
    private ModelInfoIndexMapper modelInfoIndexMapper;
    @Autowired
    private ModelInfoIndexItemMapper modelInfoIndexItemMapper;


    /**
     * 根据id获取模型指标关系表信息
     * @param id
     * @return
     */
    @Override
    public ModelInfoIndexDO get(Long id) {
        return modelInfoIndexMapper.get(id);
    }

    /**
     * 根据模型id查询模型下的指标信息
     * @param modId
     * @return
     */
    @Override
    public List<ModelInfoIndexDO> list(Long modId) {
        return modelInfoIndexMapper.list(modId);
    }

    /**
     * 保存模型指标元素表
     * @param modelInfoIndex
     * @param itemList
     * @return
     */
    @Transactional
    @Override
    public Result save(ModelInfoIndexDO modelInfoIndex, List<ModelInfoIndexItemDO> itemList) {
        if(modelInfoIndex==null){
            return Result.error("指标信息不能为空！");
        }

        if(modelInfoIndex.getModelId()==null ||modelInfoIndex.getIndexId()==null){
            return Result.error("模型信息不能为空！");
        }

        if (modelInfoIndex.getWeight()==null){
            return Result.error("权重信息不能为空！");
        }

        if(modelInfoIndex.getStandrad()==null){
            return Result.error("标准分信息不能为空！");
        }

        //检验权重不能大于1
        BigDecimal weight=modelInfoIndexMapper.getSumWeightByModelId(modelInfoIndex.getModelId());
        if (weight.add(modelInfoIndex.getWeight()).compareTo(new BigDecimal(1))==1){
            return Result.error("权重不能大于1");
        }

        //校验是否为连续闭合区间
        if (itemList!=null && itemList.size()>0){
            if (itemList.get(0).getLowerValue().compareTo(BigDecimal.ZERO)!=0) {
                return Result.error("不是以0开头，该区间为不连续区间!");
            }
            if (itemList.get(itemList.size()-1).getUpperValue().compareTo(itemList.get(itemList.size()-1).getLowerValue())!=1) {
                return Result.error("最后区间的最大值小于小于最小值，该区间为不连续区间!");
            }
            String[][] str=new String[][]{};
            for (int i=0;i<itemList.size()-1;i++){
                String upper=str[i][1]=itemList.get(i).getUpperValue().toString();
                String lower=str[i+1][0]=itemList.get(i).getLowerValue().toString();
                if (!StringUtils.equals(upper,lower)){
                    return Result.error("该区间为不连续区间!");
                }
            }

            for (int i=0;i<itemList.size()-1;i++){
                int up=itemList.get(i).getIncludeRange();
                int down=itemList.get(i+1).getIncludeRange();
                if (up==1 && down==4){
                    return Result.error("该区间为不连续区间!");
                }
                if (up==2 && down==3){
                    return Result.error("该区间为不连续区间!");
                }
                if (up==3 && (down==1 || down==4)){
                    return Result.error("该区间为不连续区间!");
                }
                if (up==4 && (down==2 || down==3)){
                    return Result.error("该区间为不连续区间!");
                }
            }
        }

        if(modelInfoIndex.getId()==null) {
            modelInfoIndex.setCreatedBy(SecurityUtils.getLoginUser().getUsername());
            modelInfoIndexMapper.save(modelInfoIndex);
            if (itemList!=null && itemList.size()>0){
                //保存指标项目
                for (ModelInfoIndexItemDO item:itemList){
                    item.setModIndexId(modelInfoIndex.getId());
                    //modelInfoIndexItemMapper.save(item);
                }
                modelInfoIndexItemMapper.batchSave(itemList);
            }
        }else{
            modelInfoIndex.setUpdatedBy(SecurityUtils.getLoginUser().getUsername());
            modelInfoIndex.setUpdateTime(new Date());
            modelInfoIndexMapper.update(modelInfoIndex);
            //保存指标项目
            modelInfoIndexItemMapper.remove(modelInfoIndex.getId());
            if (itemList!=null && itemList.size()>0){
                for (ModelInfoIndexItemDO item:itemList){
                    item.setModIndexId(modelInfoIndex.getId());
                    //modelInfoIndexItemMapper.save(item);
                }
                modelInfoIndexItemMapper.batchSave(itemList);
            }
        }
        return Result.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Result remove(Long id) {
        modelInfoIndexMapper.remove(id);
        return Result.success();
    }


}
