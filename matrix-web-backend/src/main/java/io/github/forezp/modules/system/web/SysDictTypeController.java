package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.modules.system.entity.SysDictType;
import io.github.forezp.modules.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
@RestController
@RequestMapping("/sys-dict-type")
public class SysDictTypeController {


    @Autowired
    SysDictTypeService sysDictTypeService;

    @PostMapping("")
    public RespDTO createDictType(@RequestParam String typeId, @RequestParam String typeName
            , @RequestParam String typeDescribe, @RequestParam String remarks) {
        sysDictTypeService.addDictType(typeId, typeName, typeDescribe, typeDescribe);
        return RespDTO.onSuc(null);
    }

    @PutMapping("")
    public RespDTO updateDictType(@RequestParam Long id, @RequestParam String typeName
            , @RequestParam String typeDescribe, @RequestParam String remarks) {
        sysDictTypeService.editDictType(id, typeName, typeDescribe, typeDescribe);
        return RespDTO.onSuc(null);
    }

    @DeleteMapping("/{id}")
    public RespDTO deletDiceType(@PathVariable(value = "id") Long id) {
        sysDictTypeService.deleteDictType(id);
        return RespDTO.onSuc(null);
    }

    @GetMapping("/pagelist")
    public RespDTO selectPageSysLog(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam(required = false) String typeId,
                                    @RequestParam(required = false) String typeName) {
        PageResultsDTO resultsDTO = sysDictTypeService.selectPageSysLog(page, pageSize, typeId, typeName);
        return RespDTO.onSuc(resultsDTO);
    }
}
