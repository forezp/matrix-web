package io.github.forezp.modules.task.job;

import io.github.forezp.common.util.Application;
import io.github.forezp.modules.system.service.SysLogService;
import io.github.forezp.modules.task.entity.QrtzTriggersHistory;
import io.github.forezp.modules.task.service.QrtzTriggersHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

@Slf4j
public class CleanLogJob extends AbstractBaseJob {

    @Override
    public void doExecute(JobExecutionContext context, QrtzTriggersHistory history) {
        QrtzTriggersHistoryService qrtzTriggersHistoryService = Application.getBean(QrtzTriggersHistoryService.class);
        log.info("清除任务日志");
        qrtzTriggersHistoryService.remove(null);
        SysLogService sysLogService = Application.getBean(SysLogService.class);
        sysLogService.remove(null);
        log.info("清除请求日志");
    }
}
