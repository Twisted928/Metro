package com.metro.ccms.web.activiti.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@RequestMapping({"activiti"})
@Controller
public class ModelerController {
    private static final Logger logger = LoggerFactory.getLogger(ModelerController.class);
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;

    public ModelerController() {
    }

    @RequestMapping({"/"})
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/index");
    }

    @RequestMapping({"index"})
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("modelList", this.repositoryService.createModelQuery().list());
        modelAndView.addObject("deploylList", this.repositoryService.createProcessDefinitionQuery().list());
        modelAndView.addObject("processInstance", this.runtimeService.createProcessInstanceQuery().list());
        modelAndView.addObject("deployl", this.repositoryService.createDeploymentQuery().list());
        return modelAndView;
    }

    @GetMapping({"editor"})
    public String editor() {
        return "modeler";
    }

    @RequestMapping({"/create"})
    public void create(HttpServletResponse response, String name, String key) throws IOException {
        logger.info("创建模型入参name：{},key:{}", name, key);
        Model model = this.repositoryService.newModel();
        ObjectNode modelNode = this.objectMapper.createObjectNode();
        modelNode.put("name", name);
        modelNode.put("description", "");
        modelNode.put("revision", 1);
        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());
        this.repositoryService.saveModel(model);
        this.createObjectNode(model.getId());
        response.sendRedirect("/editor?modelId=" + model.getId());
        logger.info("创建模型结束，返回模型ID：{}", model.getId());
    }

    private void createObjectNode(String modelId) {
        logger.info("创建模型完善ModelEditorSource入参模型ID：{}", modelId);
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

    @ResponseBody
    @RequestMapping({"/publish"})
    public Object publish(String modelId) {
        logger.info("流程部署入参modelId：{}", modelId);
        HashMap map = new HashMap();

        try {
            Model modelData = this.repositoryService.getModel(modelId);
            byte[] bytes = this.repositoryService.getModelEditorSource(modelData.getId());
            if (bytes == null) {
                logger.info("部署ID:{}的模型数据为空，请先设计流程并成功保存，再进行发布", modelId);
                map.put("code", "FAILURE");
                return map;
            }

            JsonNode modelNode = (new ObjectMapper()).readTree(bytes);
            BpmnModel model = (new BpmnJsonConverter()).convertToBpmnModel(modelNode);
            Deployment deployment = this.repositoryService.createDeployment().name(modelData.getName()).addBpmnModel(modelData.getKey() + ".bpmn20.xml", model).deploy();
            modelData.setDeploymentId(deployment.getId());
            this.repositoryService.saveModel(modelData);
            map.put("code", "SUCCESS");
        } catch (Exception var8) {
            logger.info("部署modelId:{}模型服务异常：{}", modelId, var8);
            map.put("code", "FAILURE");
        }

        logger.info("流程部署出参map：{}", map);
        return map;
    }

    @ResponseBody
    @RequestMapping({"/revokePublish"})
    public Object revokePublish(String modelId) {
        logger.info("撤销发布流程入参modelId：{}", modelId);
        Map<String, String> map = new HashMap();
        Model modelData = this.repositoryService.getModel(modelId);
        if (null != modelData) {
            try {
                this.repositoryService.deleteDeployment(modelData.getDeploymentId());
                map.put("code", "SUCCESS");
            } catch (Exception var5) {
                logger.error("撤销已部署流程服务异常：{}", var5);
                map.put("code", "FAILURE");
            }
        }

        logger.info("撤销发布流程出参map：{}", map);
        return map;
    }

    @ResponseBody
    @RequestMapping({"/revokePublish1"})
    public Object revokePublish1(String dploymentId) {
        logger.info("撤销发布流程入参dploymentId：{}", dploymentId);
        Map<String, String> map = new HashMap();
        if (null != dploymentId) {
            try {
                this.repositoryService.deleteDeployment(dploymentId);
                map.put("code", "SUCCESS");
            } catch (Exception var4) {
                logger.error("撤销已部署流程服务异常：{}", var4);
                map.put("code", "FAILURE");
            }
        }

        logger.info("撤销发布流程出参map：{}", map);
        return map;
    }

    @ResponseBody
    @RequestMapping({"/delete"})
    public Object deleteProcessInstance(String modelId) {
        logger.info("删除流程实例入参modelId：{}", modelId);
        Map<String, String> map = new HashMap();
        Model modelData = this.repositoryService.getModel(modelId);
        if (null != modelData) {
            try {
                ProcessInstance pi = (ProcessInstance)this.runtimeService.createProcessInstanceQuery().processDefinitionKey(modelData.getKey()).singleResult();
                if (null != pi) {
                    this.runtimeService.deleteProcessInstance(pi.getId(), "");
                    this.historyService.deleteHistoricProcessInstance(pi.getId());
                }

                map.put("code", "SUCCESS");
            } catch (Exception var5) {
                logger.error("删除流程实例服务异常：{}", var5);
                map.put("code", "FAILURE");
            }
        }

        logger.info("删除流程实例出参map：{}", map);
        return map;
    }

    @RequestMapping({"/startProcess"})
    public void startProcess(HttpServletResponse response, String deployId) throws IOException {
        this.runtimeService.startProcessInstanceById(deployId);
        response.sendRedirect("index");
    }

    @RequestMapping({"/getProcessImg/{deploymentId}"})
    public void getProcessImg(HttpServletResponse response, @PathVariable String deploymentId) throws Exception {
        ProcessDefinition definition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
        InputStream in = this.repositoryService.getResourceAsStream(deploymentId, definition.getDiagramResourceName());
        ServletOutputStream outputStream = response.getOutputStream();
        fileUpload(in, outputStream);
    }

    @RequestMapping({"/getProcessBpmn/{deploymentId}"})
    public void getProcessBpmn(HttpServletResponse response, @PathVariable String deploymentId) throws Exception {
        ProcessDefinition definition = (ProcessDefinition)this.repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
        InputStream in = this.repositoryService.getResourceAsStream(deploymentId, definition.getResourceName());
        ServletOutputStream outputStream = response.getOutputStream();
        fileUpload(in, outputStream);
    }

    public static void fileUpload(InputStream is, OutputStream os) throws Exception {
        byte[] b = new byte[10485760];
        boolean var3 = false;

        while(true) {
            int length = is.read(b);
            if (length < 0) {
                is.close();
                os.close();
                return;
            }

            os.write(b, 0, length);
        }
    }
}
