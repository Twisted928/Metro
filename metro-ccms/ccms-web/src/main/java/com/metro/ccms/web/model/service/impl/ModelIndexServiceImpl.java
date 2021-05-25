package com.metro.ccms.web.model.service.impl;


import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.web.model.domain.ModelIndexDO;
import com.metro.ccms.web.model.mapper.ModelIndexMapper;
import com.metro.ccms.web.model.query.ModelIndexQuery;
import com.metro.ccms.web.model.service.ModelIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * 指标表服务
 */
@Service
public class ModelIndexServiceImpl implements ModelIndexService {

    @Autowired
    private ModelIndexMapper modelIndexMapper;


    /**
     * 根据id获取指标信息
     * @param id
     * @return
     */
    @Override
    public ModelIndexDO get(Long id) {
        return modelIndexMapper.get(id);
    }

    /**
     * 获取指标列表
     * @param query
     * @return
     */
    @Override
    public List<ModelIndexDO> list(ModelIndexQuery query) {
        return modelIndexMapper.list(query);
    }

    /**
     * 保存指标信息
     * @param modelIndex
     * @return
     */
    @Override
    public Result save(ModelIndexDO modelIndex) {
        if (modelIndex == null) {
            return Result.error("指标信息不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getName())) {
            return Result.error("指标名称不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getType())) {
            return Result.error("指标类型不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getDescription())) {
            return Result.error("指标描述不能为空！");
        }
        modelIndex.setCreatedBy(SecurityUtils.getLoginUser().getUsername());
        int rowNum = modelIndexMapper.save(modelIndex);
        if (rowNum == 0) {
            return Result.error("保存失败");
        }
        return Result.success();
    }

    /**
     * 更新指标信息
     * @param modelIndex
     * @return
     */
    @Override
    public Result update(ModelIndexDO modelIndex) {
        if (modelIndex == null) {
            return Result.error("指标信息不能为空！");
        }
        if(modelIndex.getId()==null){
            return Result.error("指标编码不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getName())) {
            return Result.error("指标名称不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getType())) {
            return Result.error("指标类型不能为空！");
        }
        if (StringUtils.isBlank(modelIndex.getDescription())) {
            return Result.error("指标描述不能为空！");
        }

        modelIndex.setUpdateTime(new Date());
        modelIndex.setUpdatedBy(SecurityUtils.getLoginUser().getUsername());
        int rowNum = modelIndexMapper.update(modelIndex);
        if (rowNum == 0) {
            return Result.error("保存失败");
        }
        return Result.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Result remove(Long id) {
        modelIndexMapper.remove(id);
        return Result.success();
    }

    /**
     * 检查表达式是否合规
     *
     * @param expression
     * @return
     */
    public Result checkExperssion(String expression){
        if(StringUtils.isBlank(expression)){
            return Result.error("公式不能为空！");
        }
        /**
         * 常用公式例如：
         * [资产总额（百万）]/[负债总额（百万）]*100
         * ([资产总额（百万）]/[负债总额（百万）]+300)+[负债总额（百万）]*20
         */

        return Result.success();
    }

}
