package io.github.forezp.modules.activiti.controller;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.PageUtils;
import io.github.forezp.modules.activiti.service.ModelService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author: xufei.
 * @createTime: 2017/8/2.
 */
@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    ModelService modelService;

    @ApiOperation(value = "分页查询流程列表", notes = "分页查询流程列表")
    @GetMapping("/pagelist")
    public RespDTO getModelsByPage(@RequestParam int page, @RequestParam int pageSize, @RequestParam(required = false) String category) {
        PageUtils.check(page, pageSize);
        PageResultsDTO<Model> result = modelService.getMadelByPage(page, pageSize, category);
        return RespDTO.onSuc(result);
    }

    @ApiOperation(value = "新建流程", notes = "新建流程")
    @PostMapping("")
    public RespDTO createModel(@RequestParam String name, @RequestParam String key,
                               @RequestParam String desc, @RequestParam String category) {
        if (null == name || null == key || null == category) {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
        Model model = modelService.create(name, key, desc, category);
        return RespDTO.onSuc("/modeler.html?modelId=" + model.getId());
    }

    @ApiOperation(value = "删除流程", notes = "删除流程")
    @DeleteMapping("")
    public RespDTO delete(String id) {
        Optional.ofNullable(id).orElseThrow(() -> new AriesException(ErrorCode.ERROR_ARGS));
        modelService.delete(id);
        return RespDTO.onSuc(null);
    }

    @ApiOperation(value = "更新流程分类", notes = "更新流程分类")
    @PutMapping("")
    public RespDTO updateCategory(@RequestParam("id") String id, @RequestParam String category) {
        if (null == id || null == category) {
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
        modelService.updateCategory(id, category);
        return RespDTO.onSuc(null);
    }

    @ApiOperation(value = "部署流程", notes = "部署流程")
    @PutMapping("/deploy/{id}")
    public RespDTO deploy(@PathVariable("id") String id) {
        modelService.deploy(id);
        return RespDTO.onSuc(null);
    }

//    @GetMapping(value = "/export/{id}")
//    public void export(@PathVariable("id") String id, HttpServletResponse response) {
//        modelService.export(id, response);
//    }
}