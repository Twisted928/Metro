package com.metro.ccms.web.activiti.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.exception.CustomException;
import com.metro.ccms.common.utils.DateUtils;
import com.metro.ccms.common.utils.StringUtils;
import com.metro.ccms.web.activiti.domain.StartDO;
import com.metro.ccms.web.activiti.domain.TaskDO;
import com.metro.ccms.web.activiti.mapper.ActModelMapper;
import com.metro.ccms.web.activiti.service.ActivitiService;
import com.metro.ccms.web.activiti.vo.TaskVO;
import com.metro.ccms.web.utils.mapper.CommonUtilsMapper;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ExecutionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.*;

/**
 * 审批流服务接口
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiServiceImpl.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private CommonUtilsMapper commonUtilsMapper;

    @Autowired
    private ActModelMapper actModelMapper;

    /**
     * 发起流程
     * @param startDO
     * @return 返回结果包含流程ID
     */
    @Override
    public Result startProcess(StartDO startDO) {
        Map<String, Object> params = null;
        if (startDO == null) {
            return Result.error("流程启动参数不能为空！");
        }
        if (StringUtils.isBlank(startDO.getBusinessId())) {
            return Result.error("业务ID不能为空！");
        }
        if (StringUtils.isBlank(startDO.getBusinessName())) {
            return Result.error("公司名称/卡号名称不能为空！");
        }
        if (StringUtils.isBlank(startDO.getTitle())) {
            return Result.error("流程标题不能为空！");
        }
        if (startDO.getDeptCodes()==null || startDO.getDeptCodes().size()==0) {
            return Result.error("部门ID不能为空！");
        }
        if (StringUtils.isBlank(startDO.getBusinessKey().getValue())) {
            return Result.error("业务KEY不能为空！");
        }
        if (null != startDO.getVariable()) {
            params = startDO.getVariable();
        } else {
            params = new HashMap<>();
        }
        params.put("deptCode", startDO.getDeptCodes());
        params.put("businessId", startDO.getBusinessId());
        params.put("businessName",startDO.getBusinessName());
        params.put("businessKey", startDO.getBusinessKey().getValue());
        params.put("formUrl", startDO.getFormUrl());
        params.put("title", startDO.getTitle());
        params.put("startUser",startDO.getStartUser());
        if (startDO.getPublishUser()!=null && !"".equals(startDO.getPublishUser())){
            params.put("publishUser",startDO.getPublishUser());
        }

        Model model = repositoryService.createModelQuery().modelKey(startDO.getBusinessKey().getValue()).singleResult();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(model.getDeploymentId()).singleResult();

        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), params);
        if (processInstance.getProcessInstanceId()==null || "".equals(processInstance.getProcessInstanceId())){
            return Result.error("发起审批流错误,未返回流程实例ID!");
        }
        return Result.success("操作成功",processInstance.getProcessInstanceId());
    }

    /**
     * 审批任务
     * 如果当前审批节点和下一审批节点是同一个人话，则直接审批过
     * taskDo.end该字段为true，审批完成后会结束审批流程
     *
     * @param taskDO
     * @return
     */
    @Override
    public Result completeTask(TaskDO taskDO) {

        Assert.notNull(taskDO, "任务不能为空！");

        if (StringUtils.isBlank(taskDO.getTaskId())) {
            return Result.error("任务ID为空！");
        }
        if (StringUtils.isBlank(taskDO.getUsername())) {
            return Result.error("用户ID为空！");
        }

        Task task = taskService.createTaskQuery().taskId(taskDO.getTaskId()).singleResult();
        if (task == null) {
            return Result.error("未获取到任务！");
        }

        String instanceId = task.getProcessInstanceId();

        taskService.unclaim(task.getId());
        taskService.claim(task.getId(),taskDO.getUsername());

        if (taskDO.getEnd()) {
            //结束流程
            runtimeService.deleteProcessInstance(instanceId, "业务流程中需要结束流程");
            return Result.success();
        }

        if (taskDO.getVariable() != null) {
            //改变审批流参数
            runtimeService.setVariables(instanceId, taskDO.getVariable());
        }

        taskService.addComment(task.getId(), instanceId, taskDO.getComment());
        taskService.complete(task.getId());

        //寻找当前流程的下一个节点是不是相同的审批人，如果是调用本方法审核通过
        List<Task> nexttask = taskService.createTaskQuery().processInstanceId(instanceId).taskCandidateUser(taskDO.getUsername()).list();
        for (Task nextTask : nexttask) {
            this.completeTask(new TaskDO(nextTask.getId(), taskDO.getUsername(), taskDO.getComment(), false, taskDO.getVariable()));
        }
        return Result.success();
    }

    /**
     * 分页获取用户待办结合
     * @param username
     * @param page 页数
     * @param pagesize 每页数量
     * @return
     */
    @Override
    public List<TaskVO> getTodoList(String username, int page, int pagesize) {
        List<Task> tasksListPage =taskService.createTaskQuery().taskCandidateOrAssigned(username).active()
                .orderByTaskCreateTime().desc().listPage((page-1)*pagesize,pagesize);
        return createTaskVoList(tasksListPage);
    }

    /**
     * 组装待办信息
     * @param tasks
     * @return
     */
    private List<TaskVO> createTaskVoList(List<Task> tasks){
        List<TaskVO> list=new ArrayList<>(tasks.size());

        for (Task task:tasks){
            TaskVO taskVO=new TaskVO();

            Map<String, VariableInstance> variableInstanceMap=runtimeService.getVariableInstances(task.getProcessInstanceId());

            taskVO.setInstanceId(task.getProcessInstanceId());
            taskVO.setTaskId(task.getId());
            taskVO.setCreateTime(DateUtils.convertDateToLDT(task.getCreateTime()));
            taskVO.setTitle(variableInstanceMap.get("title").getTextValue());
            taskVO.setBusinessId(variableInstanceMap.get("businessId").getTextValue());
            taskVO.setFormUrl(variableInstanceMap.get("formUrl").getTextValue());
            taskVO.setRoleId(variableInstanceMap.get("roleId").getTextValue());
            taskVO.setBusinessName(variableInstanceMap.get("businessName").getTextValue());
            list.add(taskVO);

        }

        return list;
    }

    /**
     * 分页获取用户已办结合
     * @param username
     * @param page 页数
     * @param pagesize 每页数量
     * @return
     */
    @Override
    public List<TaskVO> getHistoryTaskByUser(String username, int page, int pagesize){
        List<HistoricTaskInstance>  hisTaskListPage = historyService.createHistoricTaskInstanceQuery().taskAssignee(username).finished().
                orderByHistoricTaskInstanceEndTime().desc().listPage((page-1)*pagesize,pagesize);
        return createHistoryTaskVoList(hisTaskListPage);
    }

    /**
     * 组装已办信息
     * @param tasks
     * @return
     */
    private List<TaskVO> createHistoryTaskVoList(List<HistoricTaskInstance> tasks){
        List<TaskVO> list=new ArrayList<>(tasks.size());
        for (HistoricTaskInstance task:tasks){
            TaskVO taskVO=new TaskVO();
            List<HistoricVariableInstance> hiVariablelist = historyService
                    .createHistoricVariableInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .list();
            taskVO.setInstanceId(task.getProcessInstanceId());
            taskVO.setTaskId(task.getId());
            taskVO.setCreateTime(DateUtils.convertDateToLDT(task.getEndTime()));
            for (HistoricVariableInstance hi:hiVariablelist){
                if ("title".equals(hi.getVariableName())){
                    taskVO.setTitle(hi.getValue().toString());
                }else if("businessId".equals(hi.getVariableName())){
                    taskVO.setBusinessId(hi.getValue().toString());
                }else if("formUrl".equals(hi.getVariableName())){
                    taskVO.setFormUrl(hi.getValue().toString());
                }else if("roleId".equals(hi.getVariableName())){
                    taskVO.setRoleId(hi.getValue().toString());
                }else if("businessName".equals(hi.getVariableName())){
                    taskVO.setBusinessName(hi.getValue().toString());
                }
            }
            list.add(taskVO);
        }
        return list;
    }

    /**
     * 判断流程是否结束
     * @param instanceId
     * @return boolean
     */
    @Override
    public boolean isEnded(String instanceId) {
        boolean ifEnd=false;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        if (processInstance==null){
            ifEnd=true;
        }
        return ifEnd;
    }

    /**
     * 根据流程实例ID获取当前任务
     * @param instanceId
     * @return
     */
    @Override
    public List<TaskVO> getProcessActive(String instanceId) {
        List<TaskVO> taskVOList=null;
        TaskVO taskVO=null;
        Task task=taskService.createTaskQuery().processInstanceId(instanceId).active().singleResult();
        if (task!=null){
            taskVOList=new ArrayList<>();
            List<IdentityLink> linkList=taskService.getIdentityLinksForTask(task.getId());
            if (linkList!=null && linkList.size()>0){
                for (IdentityLink identityLink:linkList){
                    if (identityLink.getUserId()!=null){
                        taskVO=new TaskVO();
                        Map<String, VariableInstance> variableInstanceMap=runtimeService.getVariableInstances(instanceId);
                        taskVO.setInstanceId(instanceId);
                        taskVO.setTaskId(task.getId());
                        taskVO.setCreateTime(DateUtils.convertDateToLDT(task.getCreateTime()));
                        taskVO.setTitle(variableInstanceMap.get("title").getTextValue());
                        taskVO.setBusinessId(variableInstanceMap.get("businessId").getTextValue());
                        taskVO.setFormUrl(variableInstanceMap.get("formUrl").getTextValue());
                        taskVO.setRoleId(variableInstanceMap.get("roleId").getTextValue());
                        taskVO.setUsername(identityLink.getUserId());
                        taskVO.setBusinessName(variableInstanceMap.get("businessName").getTextValue());
                        taskVOList.add(taskVO);
                    }
                }
            }
        }
        return taskVOList;
    }

    /**
     * 添加候选人
     * @param taskId
     * @param userId
     * @return
     */
    @Override
    public Result addCandidateUser(String taskId, String userId) {
        try {
            taskService.addCandidateUser(taskId,userId);
        }catch (Exception e) {
            return Result.error("添加候选人失败!");
        }
        return Result.success();
    }

    /**
     * 删除候选人
     * @param taskId
     * @param userId
     * @return
     */
    @Override
    public Result deleteCandidateUser(String taskId, String userId) {
        try {
            taskService.deleteCandidateUser(taskId,userId);
        }catch (Exception e) {
            return Result.error("添加候选人失败!");
        }
        return Result.success();
    }

    /**
     * 流程撤销
     * @param instanceId
     * @return
     */
    @Override
    public Result rollback(String instanceId){
        try {
            runtimeService.deleteProcessInstance(instanceId,"");
        } catch (Exception e) {
            return Result.error("流程撤销失败!");
        }
        return Result.success();
    }

    /**
     * 根据用户名获取此用户所有待办数量
     * @param userName
     * @return
     */
    @Override
    public int getCountToDo(String userName) {
        int count=(int)taskService.createTaskQuery().taskCandidateOrAssigned(userName).active().count();
        return count;
    }

    /**
     * 根据用户名获取此用户所有已办数量
     * @param userName
     * @return
     */
    @Override
    public int getCountHis(String userName){
        int count=(int)historyService.createHistoricTaskInstanceQuery().taskAssignee(userName).finished().count();
        return count;
    }

    /**
     * 判断第一个节点是否能获取到待办人
     * @param modelId
     * @param deptCodes
     * @return
     */
    @Override
    public Result getFirstUserTask(String modelId,List<String> deptCodes){
        try {
            Model modelData = this.repositoryService.getModel(modelId);
            byte[] bytes = this.repositoryService.getModelEditorSource(modelData.getId());
            JsonNode modelNode = null;
            modelNode = (new ObjectMapper()).readTree(bytes);
            BpmnModel model = (new BpmnJsonConverter()).convertToBpmnModel(modelNode);
            Process process=model.getProcesses().get(0);
            Collection<FlowElement> list=process.getFlowElements();
            Iterator iterator=list.iterator();
            int i=1;
            UserTask userTask=null;
            while (iterator.hasNext()){
                Object object=iterator.next();
                if (i==2){
                    userTask=(UserTask) object;
                    break;
                }
                i++;
            }
            List<String> roles=userTask.getCandidateGroups();
            Result r=checkCandidateUser(roles,deptCodes);
            if (!r.isSuccess()){
                return r;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success();
    }

    private Result checkCandidateUser(List<String> roles,List<String> deptCodes){
        if (roles==null || roles.size()==0){
            return Result.error("审批流第一个节点未设置角色信息!");
        }
        List<String> candidateUser=commonUtilsMapper.getCandidateUser(Long.parseLong(roles.get(0)), deptCodes);
        if (candidateUser==null || candidateUser.size()==0){
            String[] covertedArray = new String[roles.size()];
            covertedArray = roles.toArray(covertedArray);
            List<String> names=actModelMapper.getRoleNameById(covertedArray);
            return Result.error("未获取到下一节点"+names.get(0)+"审批人!");
        }
        return Result.success();
    }

    /**
     * 判断下一个节点是否有待办人
     * @param instanceId
     * @return
     */
    @Override
    public Result getNextUserTask(String instanceId,List<String> deptCodes){
        Task task=taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        if (task==null){
            return Result.error(400,"流程未启动或已执行完成");
        }
        // 取得已提交的任务
        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery()
                .taskId(task.getId()).singleResult();
        //获得流程定义
        ProcessDefinition processDefinition=repositoryService.getProcessDefinition(historicTaskInstance.getProcessDefinitionId());

        //获得当前流程的活动ID
        ExecutionQuery executionQuery =runtimeService.createExecutionQuery();
        Execution execution =executionQuery.executionId(historicTaskInstance.getExecutionId()).singleResult();
        String activityId=execution.getActivityId();
        UserTask userTask =null;
        while (true){
            //根据活动节点获取当前的组件信息
            FlowNode flowNode =getFlowNode(processDefinition.getId(),activityId);
            //获取该流程组件的之后/之前的组件信息
            List<SequenceFlow> sequenceFlowListOutGoing=flowNode.getOutgoingFlows();
//        List<SequenceFlow> sequenceFlowListIncoming=flowNode.getIncomingFlows();

            //获取的下个节点不一定是userTask的任务节点，所以要判断是否是任务节点
            //sequenceFlowListOutGoing数量可能大于1,可以自己做判断,此处只取第一个
            FlowElement flowElement =sequenceFlowListOutGoing.get(0).getTargetFlowElement();
            if (flowElement instanceof UserTask){
                userTask =(UserTask)flowElement;
                break;
            }else {
                //下一节点不是任务userTask的任务节点,所以要获取再下一个节点的信息,直到获取到userTask任务节点信息
                String flowElementId=flowElement.getId();
                activityId=flowElementId;
                continue;
            }
        }
        List<String> roles=userTask.getCandidateGroups();
        Result r=checkCandidateUser(roles,deptCodes);
        if (!r.isSuccess()){
            return r;
        }
        return Result.success();
    }


    /**
     * 根据活动节点和流程定义ID获取该活动节点的组件信息
     * @param processDefinitionId
     * @param activityId
     * @return
     */
    private FlowNode getFlowNode(String processDefinitionId, String activityId){
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityId);
        return flowNode;
    }

}