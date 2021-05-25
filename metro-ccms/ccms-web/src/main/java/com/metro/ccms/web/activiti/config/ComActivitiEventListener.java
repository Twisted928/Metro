package com.metro.ccms.web.activiti.config;

import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.common.utils.spring.SpringUtils;
import com.metro.ccms.web.activiti.domain.AutherRoleDO;
import com.metro.ccms.web.utils.mapper.CommonUtilsMapper;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：yuanjh
 * @date ：Created in 2020/12/03
 * @description：审批流监听类
 * @modified By：
 * @version: 1.0
 */
@Component
public class ComActivitiEventListener implements ActivitiEventListener, Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComActivitiEventListener.class);


    @Override
    public void onEvent(ActivitiEvent activitiEvent) {

        if (ActivitiEventType.TASK_CREATED.equals(activitiEvent.getType())) {
            LOGGER.info("TASK_CREATED Listener");
            RuntimeService runtimeService = SpringUtils.getBean(RuntimeService.class);
            CommonUtilsMapper commonUtilsMapper = SpringUtils.getBean(CommonUtilsMapper.class);

            ActivitiEntityEventImpl activitiEntityEvent = (ActivitiEntityEventImpl) activitiEvent;
            TaskEntityImpl taskEntity = (TaskEntityImpl) activitiEntityEvent.getEntity();
            String groupId = taskEntity.getIdentityLinks().get(0).getGroupId();
//            Map<String, VariableInstance> variableInstances = runtimeService.getVariableInstances(taskEntity.getProcessInstanceId());
            //获取到流程参数中的部门编码
//            List<String> deptCodes = (List<String>)variableInstances.get("deptCode").getCachedValue();

            List<String> deptCodes = (List<String>)runtimeService.getVariable(taskEntity.getProcessInstanceId(),"deptCode");

            if (StringUtils.isBlank(groupId)) {
                throw new RuntimeException("审批流找不到审批角色");
            }

            if (deptCodes==null || deptCodes.size()==0) {
                throw new RuntimeException("部门找不到");
            }

            //待办人
            this.common(deptCodes,groupId,commonUtilsMapper,taskEntity);

            //将角色id 放到变量里
            runtimeService.setVariable(taskEntity.getProcessInstanceId(), "roleId", groupId);
        }
    }

    /**
     * 设置任务待办人
     * @param deptCodes
     * @param groupId
     * @param commonUtilsMapper
     * @param taskEntity
     */
    private void common(List<String> deptCodes,String groupId,CommonUtilsMapper commonUtilsMapper,TaskEntityImpl taskEntity) {
        List<String> userIds = commonUtilsMapper.getCandidateUser(Long.parseLong(groupId), deptCodes);
        //角色授权
        List<AutherRoleDO> autherRoleDOList= commonUtilsMapper.getAutherRole(groupId,new Date());
        if (autherRoleDOList!=null && autherRoleDOList.size()>0){
            for (AutherRoleDO autherRoleDO:autherRoleDOList){
                userIds.add(autherRoleDO.getReceiveId().toString());
            }
        }

        //设置候选人ID
        for (String userId : userIds) {
            taskEntity.addCandidateUser(userId);
        }
    }

    private String getDateDay(Integer num){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastMonth = Calendar.getInstance();
        lastMonth.setTime(new Date());
        lastMonth.add(Calendar.DATE, num);
        String day=sdf.format(lastMonth.getTime());
        return day;
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
