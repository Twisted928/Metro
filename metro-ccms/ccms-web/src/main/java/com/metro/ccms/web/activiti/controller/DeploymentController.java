package com.metro.ccms.web.activiti.controller;

import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.web.activiti.query.DeployQuery;
import com.metro.ccms.web.activiti.vo.DefinitionVO;
import io.swagger.models.auth.In;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@RequestMapping({"/activiti/deploy"})
@Controller
public class DeploymentController extends BaseController {

    @Autowired
    private RepositoryService repositoryService;

    public DeploymentController() {
    }


    /**
     * 获取流程发布历史
     * @param query
     * @return
     */
    @ResponseBody
    @PostMapping({"/list"})
    public List<DefinitionVO> list(@RequestBody DeployQuery query) {

        //List<ProcessDefinition> list = this.repositoryService.createProcessDefinitionQuery().listPage((int)page.getCurrent(), (int)page.getSize());
        List<ProcessDefinition> list = this.repositoryService.createProcessDefinitionQuery().processDefinitionResourceNameLike(query.getKey() + "%").orderByProcessDefinitionId().desc().list();
        List<DefinitionVO> newList = new ArrayList();
        Iterator var7 = list.iterator();


        while (var7.hasNext()) {
            ProcessDefinition definition = (ProcessDefinition) var7.next();

            Deployment deployment = this.repositoryService.createDeploymentQuery().deploymentId(definition.getDeploymentId()).singleResult();
            newList.add(new DefinitionVO(definition, deployment));
        }
        if (newList!=null && newList.size()>0){
            Collections.sort(newList, new Comparator<DefinitionVO>() {
                @Override
                public int compare(DefinitionVO o1, DefinitionVO o2) {
                    int diff = Integer.valueOf(o1.getDeploymentId()) - Integer.valueOf(o2.getDeploymentId());
                    if (diff > 0) {
                        return 1;
                    } else if (diff < 0) {
                        return -1;
                    }
                    return 0;
                }
            });
        }
        return newList;
    }
}
