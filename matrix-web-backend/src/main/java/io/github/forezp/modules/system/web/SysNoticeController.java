package io.github.forezp.modules.system.web;

import io.github.forezp.common.dto.RespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class SysNoticeController {

    @GetMapping("/list")
    public RespDTO list() {
        return RespDTO.onSuc(null);
    }
}
