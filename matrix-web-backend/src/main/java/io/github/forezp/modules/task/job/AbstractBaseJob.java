package io.github.forezp.modules.task.job;

import io.github.forezp.common.util.Application;
import io.github.forezp.modules.task.entity.QrtzTriggersHistory;
import io.github.forezp.modules.task.service.QrtzTriggersHistoryService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public abstract class AbstractBaseJob implements BaseJob {

    QrtzTriggersHistoryService qrtzTriggersHistoryService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        if (qrtzTriggersHistoryService == null) {
            qrtzTriggersHistoryService = Application.getBean(QrtzTriggersHistoryService.class);
        }
        QrtzTriggersHistory history = new QrtzTriggersHistory();
        history.setGroupId(context.getTrigger().getJobKey().getGroup());
        history.setTriggerName(context.getTrigger().getJobKey().getName());
        doExecute(context, history);
        qrtzTriggersHistoryService.save(history);
    }

    public abstract void doExecute(JobExecutionContext context, QrtzTriggersHistory history);

}
