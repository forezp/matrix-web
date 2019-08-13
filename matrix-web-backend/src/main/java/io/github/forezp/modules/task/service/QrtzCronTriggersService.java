package io.github.forezp.modules.task.service;

import io.github.forezp.common.dto.PageResultsDTO;
import io.github.forezp.modules.task.entity.QrtzCronTriggers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author forezp
 * @since 2019-08-09
 */
public interface QrtzCronTriggersService extends IService<QrtzCronTriggers> {
    void addTask(String taskName, String taskClassName, String taskGroupId, String cronExpression) throws Exception;

    void pauseTask(String taskClassName, String taskGroupId) throws Exception;

    void resumeTask(String taskClassName, String taskGroupId) throws Exception;

    void updateTask(String taskName,String taskClassName, String taskGroupId, String cronExpression) throws Exception;

    void deletTask(String taskClassName, String taskGroupId) throws Exception;

    PageResultsDTO getTriggersPage(int page, int pageSize, String groupId, String triggerName);


}
