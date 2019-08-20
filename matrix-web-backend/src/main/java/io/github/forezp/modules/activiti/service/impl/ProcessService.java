package io.github.forezp.modules.activiti.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.ActivitiUtils;
import io.github.forezp.modules.activiti.vo.dto.ProcessDTO;
import io.github.forezp.modules.activiti.vo.dto.RunningProcessDTO;
import io.netty.util.internal.ObjectUtil;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

/**
 * @author: xufei.
 * @createTime: 2017/8/3.
 */
@Service
public class ProcessService {

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private HistoryService historyService;

	public PageResultsDTO getProcessByPage(String category, int page, int pageSize) {
		PageResultsDTO<ProcessDTO> result = new PageResultsDTO(page, pageSize);
	    ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().latestVersion();
        if (StringUtils.isNotBlank(category)){
            query.processDefinitionCategory(category);
        }
		result.setTotalCount(query.count());
		query.listPage(result.offset, result.pageSize)
				.stream()
                .forEach(processDefinition -> {
					Deployment deployment = repositoryService.createDeploymentQuery()
							.deploymentId(processDefinition.getDeploymentId()).singleResult();
					result.list.add(ActivitiUtils.toProcessDTO(processDefinition, deployment));
		});
		result.list = result.list
                .stream()
                .sorted(Comparator.comparing(ProcessDTO::getDeploymentTime).reversed())
                .collect(Collectors.toList());
		return result;
	}

	public PageResultsDTO runningList(int page, int pageSize, String procInsId, String title, String category) {
		PageResultsDTO result = new PageResultsDTO<>(page, pageSize);
	    ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery().includeProcessVariables();
	    if (StringUtils.isNotBlank(procInsId)){
		    processInstanceQuery.processInstanceId(procInsId);
	    }
	    if (StringUtils.isNotBlank(category)){
		    processInstanceQuery.processDefinitionCategory(category);
	    }
        if (StringUtils.isNotBlank(category)){
            processInstanceQuery.processDefinitionCategory(category);
        }
        if (StringUtils.isNotBlank(title)){
            processInstanceQuery.variableValueLikeIgnoreCase("title", "%" + title + "%");
        }
	    result.setTotalCount(processInstanceQuery.count());
        List<RunningProcessDTO> collect = processInstanceQuery.listPage(result.offset, result.pageSize)
                .stream()
                .map(processInstance -> {
                    RunningProcessDTO dto = new RunningProcessDTO();
                    dto.id = processInstance.getId();
                    dto.processInstanceId = processInstance.getProcessInstanceId();
                    dto.processDefinitionId = processInstance.getProcessDefinitionId();
                    dto.activityId = processInstance.getActivityId();
                    dto.suspended = processInstance.isSuspended();
                    dto.processDefinitionName = processInstance.getProcessDefinitionName();
                    dto.title = (String) processInstance.getProcessVariables().get("title");
                    dto.startBy = historyService.createHistoricProcessInstanceQuery()
							.processInstanceId(processInstance.getId()).singleResult().getStartUserId();
                    dto.activityName = historyService.createHistoricActivityInstanceQuery()
							.processInstanceId(processInstance.getId()).activityId(processInstance.getActivityId())
							.list().get(0).getActivityName();
                    return dto;
                })
                .collect(Collectors.toList());
        result.setList(collect);
		return result;
	}


	public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception {

		if (StringUtils.isBlank(procDefId)){
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId).singleResult();
			procDefId = processInstance.getProcessDefinitionId();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();

		String resourceName = "";
		if (resType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}

		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		return resourceAsStream;
	}

	@Transactional(readOnly = false)
	public String deploy(String category, MultipartFile file) {

		String message = "";

		String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName)){
            throw new AriesException(ErrorCode.FILE_NAME_NOT_EXIST);
        }

		try {
			InputStream fileInputStream = file.getInputStream();
			Deployment deployment;
			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
			} else if (extension.equals("png")) {
				deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			} else if (fileName.indexOf("bpmn20.xml") != -1) {
				deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			} else if (extension.equals("bpmn")) {
				String baseName = FilenameUtils.getBaseName(fileName);
				deployment = repositoryService.createDeployment().addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
			} else {
				throw new AriesException(ErrorCode.FILE_PATTERN_NOT_SUPPORTED);
			}

			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

            if (list.size() == 0){
                throw new AriesException(ErrorCode.MODEL_NOT_EXIST);
            }

			// 设置流程分类
            list.stream().forEach(processDefinition ->
                repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category));
		} catch (Exception e) {
			throw new AriesException(ErrorCode.FAIL, "processService deploy failed");
		}
		return message;
	}

	@Transactional(readOnly = false)
	public void updateCategory(String procDefId, String category) {
		repositoryService.setProcessDefinitionCategory(procDefId, category);
	}

	@Transactional(readOnly = false)
	public String updateState(String state, String procDefId) {
		if (state.equals("active")) {
			repositoryService.activateProcessDefinitionById(procDefId, true, null);
		} else if (state.equals("suspend")) {
			repositoryService.suspendProcessDefinitionById(procDefId, true, null);
		}
		return state;
	}

	@Transactional(readOnly = false)
	public Model convertToModel(String procDefId) {
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
            InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                    processDefinition.getResourceName());
            XMLInputFactory xif = XMLInputFactory.newInstance();
            InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
            XMLStreamReader xtr = xif.createXMLStreamReader(in);
            BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

            BpmnJsonConverter converter = new BpmnJsonConverter();
            ObjectNode modelNode = converter.convertToJson(bpmnModel);
			Model model = repositoryService.newModel();
            model.setKey(processDefinition.getKey());
            model.setName(processDefinition.getResourceName());
            model.setCategory(processDefinition.getCategory());
            model.setDeploymentId(processDefinition.getDeploymentId());
            model.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(model.getKey()).count()+1)));

            ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, model.getVersion());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
            model.setMetaInfo(modelObjectNode.toString());

            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), modelNode.toString().getBytes("utf-8"));
			return model;
        } catch (Exception e) {
            throw new AriesException(ErrorCode.FAIL, "processService convert to model fail");
        }
	}

	public List<String> exportDiagrams(String exportDir) {
		List<String> files = new ArrayList<String>();
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();

        list.stream().forEach(processDefinition -> {
            try {
                String diagramResourceName = processDefinition.getDiagramResourceName();
                String key = processDefinition.getKey();
                int version = processDefinition.getVersion();
                String diagramPath = "";

                InputStream resourceAsStream =
                        repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
                byte[] b = new byte[resourceAsStream.available()];

                resourceAsStream.read(b, 0, b.length);

                String diagramDir = exportDir + "/" + key + "/" + version;
                File diagramDirFile = new File(diagramDir);
                if (!diagramDirFile.exists()) {
                    diagramDirFile.mkdirs();
                }
                diagramPath = diagramDir + "/" + diagramResourceName;
                File file = new File(diagramPath);

                // 文件存在退出
                if (file.exists()) {
                    files.add(diagramPath);
                } else {
                    file.createNewFile();
                    FileUtils.writeByteArrayToFile(file, b, true);
                    files.add(diagramPath);
                }
            } catch (Exception e) {
                throw new AriesException(ErrorCode.FAIL, "processService export diagrams fail!");
            }
        });
		return files;
	}

    // 删除部署的流程，级联删除流程实例
	@Transactional(readOnly = false)
	public void deleteDeployment(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}

	@Transactional(readOnly = false)
	public void deleteProcIns(String procInsId, String deleteReason) {
		runtimeService.deleteProcessInstance(procInsId, deleteReason);
	}

	private String getComment(String processId) {
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processId).includeProcessVariables().singleResult();
        Map<String, Object> variables = processInstance.getProcessVariables();
        if (variables!=null) {
            return (String) variables.get("comment");
        }
        return null;
	}

	public Map getComment(List<String> processIds) {
        Map<String, String> collect = new HashMap<>();
	    processIds.stream()
                .forEach(processId -> collect.put(processId, getComment(processId)));
        return collect;
	}
}
