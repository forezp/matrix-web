package io.github.forezp.modules.activiti.controller;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.PageUtils;
import io.github.forezp.modules.activiti.service.impl.ProcessService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author: xufei.
 * @createTime: 2017/8/3.
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	ProcessService processService;

	@ApiOperation(value = "分页查询流程实例", notes = "分页查询流程实例")
	@GetMapping("/pagelist")
	public RespDTO getProcessByPage(@RequestParam(required = false) String category,
									@RequestParam int page, @RequestParam int pageSize) {
        PageUtils.check(page, pageSize);
		PageResultsDTO result = processService.getProcessByPage(category, page, pageSize);
		return RespDTO.onSuc(result);
	}

    @ApiOperation(value = "分页查询运行中的实例列表", notes = "分页查询运行中的实例列表")
	@GetMapping("/running/pagelist")
	public RespDTO runningList(@RequestParam(required = false) String procInsId,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) String category,
                               @RequestParam int page, @RequestParam int pageSize) {
        PageUtils.check(page, pageSize);
        PageResultsDTO result = processService.runningList(page, pageSize, procInsId, title, category);
		return RespDTO.onSuc(result);
	}

    @ApiOperation(value = "读取xml/image资源", notes = "resType为xml或者image")
	@GetMapping(value = "/resource")
	public void resourceRead(String procDefId, String proInsId, String resType, HttpServletResponse response) {
	    if ("xml".equals(resType)) {
            response.setContentType("application/xml");
        } else {
	        response.setContentType("image/png");
        }

	    try {
            InputStream resourceAsStream = processService.resourceRead(procDefId, proInsId, resType);
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
	        throw new AriesException(ErrorCode.FAIL, resType + "资源获取失败");
        }
	}

	@ApiOperation(value = "使用流程文件部署", notes = "使用流程文件部署")
	@PostMapping("/deploy")
	public RespDTO deploy(String category, @RequestParam("file") MultipartFile file) {
			String message = processService.deploy(category, file);
		return RespDTO.onSuc(null);
	}

	@ApiOperation(value = "更新分类", notes = "更新分类")
	@PutMapping("/update-category/{procDefId}")
	public RespDTO updateCategory(@PathVariable("procDefId") String procDefId, @RequestParam String category) {
	    if (null == procDefId || null == category) {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
		processService.updateCategory(procDefId, category);
		return RespDTO.onSuc(null);
	}

    @ApiOperation(value = "激活/挂起", notes = "active为激活，suspend为挂起")
	@PutMapping("/{state}")
	public RespDTO updateState(@PathVariable("state") String state, @RequestParam String procDefId) {
        String status = processService.updateState(state, procDefId);
        return RespDTO.onSuc(status);
	}

    @ApiOperation(value = "将流程转为模型", notes = "将流程转为模型")
	@PutMapping(value = "/convert/{procDefId}")
	public RespDTO convertToModel(@PathVariable("procDefId") String procDefId) {
		Model model = processService.convertToModel(procDefId);
		return RespDTO.onSuc(model.getId());
	}

    @ApiOperation(value = "导出图片文件到磁盘", notes = "导出图片文件到磁盘")
	@GetMapping(value = "/export/diagrams")
	public RespDTO exportDiagrams(@RequestParam String exportDir) {
        Optional.ofNullable(exportDir).orElseThrow(() -> new AriesException(ErrorCode.ERROR_ARGS, "export dir is null"));
		List<String> files = processService.exportDiagrams(exportDir);
		return RespDTO.onSuc(files);
	}

    @ApiOperation(value = "根据部署流程id级联删除所有实例", notes = "根据部署流程id级联删除所有实例")
    @DeleteMapping("/deployment/{deploymentId}")
	public RespDTO delete(@PathVariable("deploymentId") String deploymentId) {
	    Optional.ofNullable(deploymentId).orElseThrow(() -> new AriesException(ErrorCode.ERROR_ARGS, "deploymentId is null"));
		processService.deleteDeployment(deploymentId);
		return RespDTO.onSuc(deploymentId);
	}

    @ApiOperation(value = "根据实例id删除流程实例", notes = "根据实例id删除流程实例")
	@DeleteMapping("/process-instance/{procInsId}")
	public RespDTO deleteProcIns(@PathVariable("procInsId") String procInsId, @RequestParam String reason) {
		if (null == procInsId || StringUtils.isBlank(reason)){
			throw new AriesException(ErrorCode.ERROR_ARGS, "procInsId or reason missing");
		}
        processService.deleteProcIns(procInsId, reason);
		return RespDTO.onSuc(procInsId);
	}

	@ApiOperation(value = "根据流程id获取流程评论")
    @GetMapping("/comment/{processIds}")
	public RespDTO getCommentByProcessIds(@PathVariable("processIds") String processIds) {
	    if (processIds==null) {
	        throw new AriesException(ErrorCode.MISSING_ARGS);
        }
		return RespDTO.onSuc(processService.getComment(Arrays.asList(processIds.split(","))));
	}
}
