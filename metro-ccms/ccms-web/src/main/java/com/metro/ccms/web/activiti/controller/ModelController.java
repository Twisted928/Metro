package com.metro.ccms.web.activiti.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.entity.SysRole;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.web.activiti.config.CustomBpmnJsonConverter;
import com.metro.ccms.web.activiti.config.CustomUserTaskJsonConverter;
import com.metro.ccms.web.activiti.domain.ActBusinessDO;
import com.metro.ccms.web.activiti.domain.ActModelRuleDO;
import com.metro.ccms.web.activiti.query.ModelQuery;
import com.metro.ccms.web.activiti.service.ModelService;
import com.metro.ccms.web.activiti.service.impl.ModelServiceImpl;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import static com.metro.ccms.common.utils.SecurityUtils.getUsername;

@RequestMapping("/activiti/model")
@Controller
public class ModelController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelService modelService;

    public ModelController() {
    }

    /**
     * 分页获取流程信息
     * @param query
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    public TableDataInfo list(ModelQuery query) {
        startPage();
        List<ActBusinessDO> list=modelService.getBusinessList(query);
        return getDataTable(list);
    }

    /**
     * 新增流程场景
     * @param actBusinessDO
     * @return
     */
    @PostMapping("/saveActBusiness")
    @ResponseBody
    public Result saveActBusiness(@RequestBody ActBusinessDO actBusinessDO){
        return modelService.saveActBusiness(actBusinessDO);
    }

    /**
     * 新增流程
     * @param data
     * @return
     * @throws IOException
     */
    @PostMapping("/create")
    @ResponseBody
    public Result createOrUpdate(@RequestBody JSONObject data) throws IOException {
        String name=data.getString("name");
        String bkey=data.getString("bkey");
        String bname=data.getString("bname");

        Model model = this.repositoryService.newModel();
        ObjectNode modelNode = this.objectMapper.createObjectNode();
        modelNode.put("name", name);
        modelNode.put("description", "");
        modelNode.put("revision", 1);
        modelNode.put("createUser",getUsername());
        modelNode.put("updateUser",getUsername());
        model.setName(name);
        model.setKey(bkey);
        model.setMetaInfo(modelNode.toString());
        model.setCategory(bname);
        this.repositoryService.saveModel(model);

        ActBusinessDO actBusinessDO = new ActBusinessDO(bkey, model.getId());
        this.modelService.updateBusinessMid(actBusinessDO);

        this.createObjectNode(model.getId());

        return Result.success(model.getId());
    }

    private void createObjectNode(String modelId) {
        ObjectNode editorNode = this.objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = this.objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);

        try {
            this.repositoryService.addModelEditorSource(modelId, editorNode.toString().getBytes("utf-8"));
        } catch (Exception var5) {
            logger.info("创建模型时完善ModelEditorSource服务异常：{}", var5);
        }

        logger.info("创建模型完善ModelEditorSource结束");
    }

    @GetMapping("/editor")
    public String editor() {
        return "modeler";
    }

    /**
     * 流程发布/流程部署
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping("/publish")
    public Result publish(@RequestBody JSONObject data) {
        String modelId=data.getString("modelId");
        logger.info("流程部署入参modelId：{}", modelId);

        try {
            Model modelData = this.repositoryService.getModel(modelId);
            byte[] bytes = this.repositoryService.getModelEditorSource(modelData.getId());
            if (bytes == null) {
                logger.info("部署ID:{}的模型数据为空，请先设计流程并成功保存，再进行发布", modelId);
                return Result.error("部署的模型数据为空，请先设计流程并成功保存，再进行发布");
            } else {
                CustomBpmnJsonConverter.getConvertersToBpmnMap().put("UserTask", CustomUserTaskJsonConverter.class);
                JsonNode modelNode = (new ObjectMapper()).readTree(bytes);
                BpmnModel model = (new BpmnJsonConverter()).convertToBpmnModel(modelNode);
                DeploymentBuilder deploymentBuilder = this.repositoryService.createDeployment();
                Deployment deployment = deploymentBuilder.name(modelData.getName()).addBpmnModel(modelData.getKey() + ".bpmn20.xml", model).tenantId(getUsername()).deploy();
                modelData.setDeploymentId(deployment.getId());
                this.repositoryService.saveModel(modelData);
                return Result.success();
            }
        } catch (Exception var8) {
            logger.info("部署modelId:{}模型服务异常：{}", modelId, var8);
            return Result.error();
        }
    }

    /**
     * 获取流程场景下拉列表
     * @return
     */
    @GetMapping("/getBusiness")
    @ResponseBody
    public List<ActBusinessDO> getBusiness() {
        return this.modelService.getBusiness();
    }

    /**
     * 获取规则信息
     * @param mid
     * @return
     */
    @GetMapping("/getModelRule")
    @ResponseBody
    public List<ActModelRuleDO> getModelRule(String mid) {
        return this.modelService.getModelRule(mid);
    }

    /**
     * 保存规则信息
     * @param list
     * @return
     */
    @PostMapping("/saveModelRule")
    @ResponseBody
    public Result saveModelRule(@RequestBody List<ActModelRuleDO> list) {
        return this.modelService.saveModelRule(list);
    }

    /**
     * 获取角色信息
     * @return
     */
    @GetMapping("/getRole")
    @ResponseBody
    public List<SysRole> getRole() {
        return this.modelService.getRole();
    }
}