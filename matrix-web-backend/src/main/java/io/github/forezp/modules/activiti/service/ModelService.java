package io.github.forezp.modules.activiti.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author: xufei.
 * @createTime: 2017/8/2.
 */
@Service
public class ModelService {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ObjectMapper objectMapper;

    public PageResultsDTO<Model> getMadelByPage(Integer page, Integer pageSize, String category) {
        PageResultsDTO result = new PageResultsDTO(page, pageSize);
        ModelQuery modelQuery = repositoryService.createModelQuery().latestVersion().orderByLastUpdateTime().desc();
        if (StringUtils.isNotBlank(category)) {
            modelQuery.modelCategory(category);
        }
        result.setTotalCount(modelQuery.count());
        result.setList(modelQuery.listPage(result.offset, result.pageSize));
        return result;
    }

    @Transactional(readOnly = false)
    public Model create(String name, String key, String desc, String category) {
        try {
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode properties = objectMapper.createObjectNode();
            properties.put("process_author", "taichi");
            editorNode.put("properties", properties);
            ObjectNode stencilset = objectMapper.createObjectNode();
            stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilset);

            Model model = repositoryService.newModel();
            desc = StringUtils.defaultString(desc);
            model.setKey(StringUtils.defaultString(key));
            model.setName(name);
            model.setCategory(category);
            model.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(model.getKey()).count()+1)));

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, model.getVersion());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, desc);
            model.setMetaInfo(modelObjectNode.toString());

            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes("utf-8"));
            return model;
        } catch (UnsupportedEncodingException e) {
            throw new AriesException(ErrorCode.FAIL, "modelService create model fail");
        }
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        repositoryService.deleteModel(id);
    }

    @Transactional(readOnly = false)
    public void updateCategory(String id, String category) {
        Model modelData = repositoryService.getModel(id);
        modelData.setCategory(category);
        repositoryService.saveModel(modelData);
    }

    @Transactional(readOnly = false)
    public String deploy(String id) {
        String message = "";
        try {
            // 获取模型
            Model model = repositoryService.getModel(id);
            ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(model.getId()));
            BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);

//            byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
            String processName = model.getName();
            if (!StringUtils.endsWith(processName, ".bpmn20.xml")){
                processName += ".bpmn20.xml";
            }
            // 部署流程
            Deployment deployment = repositoryService.createDeployment().name(model.getName())
//                    .addString(processName, new String(bpmnBytes))
//                    .deploy();
                    .addBpmnModel(processName, bpmnModel)
                    .deploy();

            // 设置流程分类
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
            if (list.size() == 0){
                throw new AriesException(ErrorCode.MODEL_NOT_EXIST);
            }
            list.stream().forEach(processDefinition -> {
                        repositoryService.setProcessDefinitionCategory(processDefinition.getId(), model.getCategory());
//                        ObjectNode modelNode = null;
//                        try {
//                            modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
//                            JsonNode jsonNode = modelNode.get(ModelDataJsonConstants.MODEL_DESCRIPTION);
//                            ProcessDefinitionEntity processDefinition1 = (ProcessDefinitionEntity) processDefinition;
//                            processDefinition1.setDescription(jsonNode.asText());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
            });
        } catch (Exception e) {
            throw new AriesException(ErrorCode.FAIL, "model service deploy model fail");
        }
        return message;
    }

//    public void export(String id, HttpServletResponse response) {
//        try {
//            org.activiti.engine.repository.Model modelData = repositoryService.getModel(id);
//            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
//            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
//            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
//            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
//            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
//
//            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
//            IOUtils.copy(in, response.getOutputStream());
//            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
//            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
//            response.flushBuffer();
//        } catch (Exception e) {
//            throw new ActivitiException("导出model的xml文件失败，模型ID="+id, e);
//        }
//
//    }
}
