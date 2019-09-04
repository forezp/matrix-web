package io.github.forezp.modules.personnel.controller;

import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.modules.personnel.service.PlVacationService;
import io.github.forezp.modules.personnel.vo.domain.VacationDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vacation")
@Slf4j
public class VacationController {

    @Autowired
    PlVacationService plVacationService;

    @PostMapping("")
    public RespDTO postVacation(@RequestBody VacationDomain vacationDomain) {
        plVacationService.statVacation(vacationDomain);
        return RespDTO.onSuc(null);

    }
}
