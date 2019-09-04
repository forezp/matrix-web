package io.github.forezp.modules.personnel.service.impl;

import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import io.github.forezp.modules.activiti.controller.TaichiTaskController;
import io.github.forezp.modules.activiti.vo.form.StartTask;
import io.github.forezp.modules.personnel.entity.PlVacation;
import io.github.forezp.modules.personnel.mapper.PlVacationMapper;
import io.github.forezp.modules.personnel.service.PlVacationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.forezp.modules.personnel.vo.domain.VacationDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author forezp
 * @since 2019-09-02
 */
@Service
@Slf4j
public class PlVacationServiceImpl extends ServiceImpl<PlVacationMapper, PlVacation> implements PlVacationService {

    @Autowired
    PlVacationMapper plVacationMapper;
    @Resource
    TaichiTaskController taskController;
    @Override
    public void statVacation(VacationDomain vacationDomain) {
        PlVacation plVacation=new PlVacation();
        BeanUtils.copyProperties(vacationDomain,plVacation);
        if(plVacationMapper.insert(plVacation)==0){
            throw new AriesException(ErrorCode.INSERT_DATA_EXIST);
        }

        StartTask startTask = new StartTask();
        startTask.setUserId(vacationDomain.getNextUserId());
        startTask.setEntityId(String.valueOf(plVacation.getId()));
        startTask.setProcDefKey(plVacation.getProcDefKey());
        startTask.setTitle(plVacation.getName() + "请假申请");

        RespDTO start = taskController.start(startTask);
        if (null == start || start.code != 0) {
            throw new AriesException(ErrorCode.FAIL);
        }

        log.debug("给业务表 plVacation 赋值 processId");
        plVacation.setProcessId((String) start.data);
        boolean suc = updateById(plVacation);
        if (!suc) {
            log.error("给业务表 plVacation 赋值 processId 异常 ，抛出异常 回滚上述操作");
            throw new AriesException(ErrorCode.FAIL);
        }
        log.debug("请假申请流程正常开始--哈哈");

    }
}
