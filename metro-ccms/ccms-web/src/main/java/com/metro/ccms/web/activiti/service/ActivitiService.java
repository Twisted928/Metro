package com.metro.ccms.web.activiti.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.activiti.domain.StartDO;
import com.metro.ccms.web.activiti.domain.TaskDO;
import com.metro.ccms.web.activiti.vo.TaskVO;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 审批流服务接口
 */
public interface ActivitiService {

    /**
     * 发起流程
     * @param startDO
     * @return 返回结果包含流程ID
     */
    Result startProcess(StartDO startDO);

    /**
     * 审批任务
     * 如果当前审批节点和下一审批节点是同一个人话，则直接审批过
     * taskDo.end该字段为true，审批完成后会结束审批流程
     *
     * @param taskDO
     * @return
     */
    Result completeTask(TaskDO taskDO);

    /**
     * 分页获取用户待办结合
     * @param username
     * @param page 页数
     * @param pagesize 每页数量
     * @return
     */
    List<TaskVO> getTodoList(String username, int page, int pagesize);

    /**
     * 分页获取用户已办结合
     * @param username
     * @param page 页数
     * @param pagesize 每页数量
     * @return
     */
    List<TaskVO> getHistoryTaskByUser(String username, int page, int pagesize);

    /**
     * 判断流程是否结束
     * @param instanceId
     * @return boolean
     */
    boolean isEnded(String instanceId);

    /**
     * 根据流程实例ID获取当前任务
     * @param instanceId
     * @return
     */
    List<TaskVO> getProcessActive(String instanceId);

    /**
     * 添加候选人
     * @param taskId
     * @param userId
     * @return
     */
    Result addCandidateUser(String taskId,String userId);

    /**
     * 删除候选人
     * @param taskId
     * @param userId
     * @return
     */
    Result deleteCandidateUser(String taskId,String userId);

    /**
     * 流程撤销
     * @param instanceId
     * @return
     */
    Result rollback(String instanceId);

    /**
     * 根据用户名获取此用户所有待办数量
     * @param userName
     * @return
     */
    int getCountToDo(String userName);

    /**
     * 根据用户名获取此用户所有已办数量
     * @param userName
     * @return
     */
    int getCountHis(String userName);

    /**
     * 判断第一个节点是否能获取到待办人
     * @param modelId
     * @param deptCodes
     * @return
     */
    Result getFirstUserTask(String modelId,List<String> deptCodes);

    /**
     * 判断下一个节点是否有待办人
     * @param instanceId
     * @return
     */
    Result getNextUserTask(String instanceId,List<String> deptCodes);
}