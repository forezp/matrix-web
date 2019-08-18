package io.github.forezp.modules.activiti.controller;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.common.util.PageUtils;
import io.github.forezp.modules.activiti.service.ActModelCategoryService;
import io.github.forezp.modules.task.service.QrtzTriggersGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-08-16
 */
@RestController
@RequestMapping("/model-category")
public class ActModelCategoryController {

    @Autowired
    ActModelCategoryService actModelCategoryService;

    @PostMapping("")
    public RespDTO addCategory(@RequestParam String categoryId, @RequestParam String categoryName, @RequestParam String pCategoryId) {
        if (actModelCategoryService.addModelCategory(categoryId, categoryName, pCategoryId)) {
            return RespDTO.onSuc(null);
        } else {
            throw new AriesException(ErrorCode.INSERT_DATA_FAIL);
        }
    }

    @PutMapping("")
    public RespDTO updateCategory(@RequestParam Long id, @RequestParam String categoryName) {
        if (actModelCategoryService.updateModelCategory(id, categoryName)) {
            return RespDTO.onSuc(null);
        } else {
            throw new AriesException(ErrorCode.UPDATE_DATA_FAIL);
        }
    }

    @GetMapping("/pagelist")
    public RespDTO getCategoryPagelist(@RequestParam int page, @RequestParam int pageSize
            , @RequestParam(required = false) String categoryId
            , @RequestParam(required = false) String categoryName
            , @RequestParam(required = false) String pCategoryId) {
        PageUtils.check(page, pageSize);
        PageResultsDTO resultsDTO = actModelCategoryService.selectPagelist(page, pageSize, categoryId, categoryName, pCategoryId);
        return RespDTO.onSuc(resultsDTO);
    }
}
