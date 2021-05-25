package com.metro.ccms.web.model.service.impl;


import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoDO;
import com.metro.ccms.web.model.domain.RuleModelIndexInfoitemDO;
import com.metro.ccms.web.model.mapper.RuleModelInfoIndexItemMapper;
import com.metro.ccms.web.model.mapper.RuleModelInfoIndexMapper;
import com.metro.ccms.web.model.service.RuleModelInfoIndexService;
import com.metro.ccms.web.utils.domain.BasicDataDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 *<p>
 *  中间表
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/29
 * @desc
 **/
@Service
public class RuleModelInfoIndexServiceImpl implements RuleModelInfoIndexService {
    private static final Logger logger = LoggerFactory.getLogger(RuleModelInfoIndexServiceImpl.class);

    @Autowired
    private RuleModelInfoIndexMapper ruleModelInfoIndexMapper;
    @Autowired
    private RuleModelInfoIndexItemMapper ruleModelInfoIndexItemMapper;


    /**
     * 根据id获取模型指标关系表信息
     * @param id
     * @return
     */
    @Override
    public RuleModelIndexInfoDO get(Long id) {
        return ruleModelInfoIndexMapper.get(id);
    }

    /**
     * 根据模型id查询模型下的指标信息
     * @param modId
     * @return
     */
    @Override
    public List<RuleModelIndexInfoDO> list(Long modId) {
        return ruleModelInfoIndexMapper.list(modId);
    }
    /**
     * 查询客户类型
     * @param
     * @return
     */
    @Override
    public List<BasicDataDO> listkehu() {
        return ruleModelInfoIndexMapper.listkehu();
    }

    /**
     * 保存模型指标元素表
     * @param modelInfoIndex
     * @param itemList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result save(RuleModelIndexInfoDO modelInfoIndex, List<RuleModelIndexInfoitemDO> itemList) {
        try {
            if(modelInfoIndex==null){
                return Result.error("指标信息不能为空！");
            }

            if(modelInfoIndex.getModelId()==null ||modelInfoIndex.getIndexId()==null){
                return Result.error("模型信息不能为空！");
            }
            if(modelInfoIndex.getId()==null) {
                modelInfoIndex.setCreatedBy(SecurityUtils.getLoginUser().getUsername());
                ruleModelInfoIndexMapper.save(modelInfoIndex);
                //保存指标项目
                for (RuleModelIndexInfoitemDO item:itemList){
                    item.setModIndexId(modelInfoIndex.getId());
                }
                ruleModelInfoIndexItemMapper.batchSave(itemList);
            }else{
                modelInfoIndex.setUpdatedBy(SecurityUtils.getLoginUser().getUsername());
                ruleModelInfoIndexMapper.update(modelInfoIndex);
                //保存指标项目
                ruleModelInfoIndexItemMapper.remove(modelInfoIndex.getId());
                if(itemList.size()>0){
                    for (RuleModelIndexInfoitemDO item:itemList){
                        item.setModIndexId(modelInfoIndex.getId());
                    }
                    ruleModelInfoIndexItemMapper.batchSave(itemList);
                }
            }
        }catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("保存失败");
        }
        return Result.success();
    }

    /**
     * 将异常信息转换为string类型
     * @param e 异常
     * @return string后的异常信息
     */
    public static String getExceptionInfo(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        return baos.toString();
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result remove(Long id) {
        ruleModelInfoIndexMapper.remove(id);
        return Result.success();
    }


}
