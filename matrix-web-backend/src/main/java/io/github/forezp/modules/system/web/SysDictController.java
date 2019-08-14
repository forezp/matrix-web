package io.github.forezp.modules.system.web;


import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.modules.system.entity.SysDict;
import io.github.forezp.modules.system.service.SysDictService;
import io.github.forezp.modules.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author forezp
 * @since 2019-08-13
 */
@RestController
@RequestMapping("/sys-dict")
public class SysDictController {
    @Autowired
    SysDictService sysDictService;

    @PostMapping("")
    public RespDTO createDictType(@RequestBody SysDict sysDict) {
        sysDictService.addDict(sysDict);
        return RespDTO.onSuc(null);
    }

    @PutMapping("/{id}")
    public RespDTO updateDictType(@PathVariable(value = "id") Long id, @RequestBody SysDict sysDict) {
        sysDictService.updateDict(id, sysDict);
        return RespDTO.onSuc(null);
    }

    @DeleteMapping("/{id}")
    public RespDTO deletDiceType(@PathVariable(value = "id") Long id) {
        sysDictService.deleteDict(id);
        return RespDTO.onSuc(null);
    }

    @GetMapping("/pagelist")
    public RespDTO selectPageSysLog(@RequestParam Integer page, @RequestParam Integer pageSize,
                                    @RequestParam(required = false) String codeId,
                                    @RequestParam(required = false) String codeName,
                                    @RequestParam(required = false) String typeId,
                                    @RequestParam(required = false) String typeName) {

        PageResultsDTO resultsDTO = sysDictService.selectPage(page, pageSize, codeId, codeName, typeId, typeName);
        return RespDTO.onSuc(resultsDTO);
    }
}
