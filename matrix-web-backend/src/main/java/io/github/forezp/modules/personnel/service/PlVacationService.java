package io.github.forezp.modules.personnel.service;

import io.github.forezp.modules.personnel.entity.PlVacation;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.forezp.modules.personnel.vo.domain.VacationDomain;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author forezp
 * @since 2019-09-02
 */
public interface PlVacationService extends IService<PlVacation> {

    void statVacation(VacationDomain vacationDomain);
}
