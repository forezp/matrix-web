package io.github.forezp.modules.personnel.controller;

import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.modules.personnel.vo.domain.VacationDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vacation")
@Slf4j
public class VacationController {


    @PostMapping("")
    public RespDTO postVacation(@RequestBody VacationDomain vacationDomain) {

        return RespDTO.onSuc(null);

    }
}
