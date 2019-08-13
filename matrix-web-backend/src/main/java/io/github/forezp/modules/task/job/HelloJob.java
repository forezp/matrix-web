package io.github.forezp.modules.task.job;

import java.util.Date;
import io.github.forezp.modules.task.entity.QrtzTriggersHistory;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

@Slf4j
public class HelloJob extends AbstractBaseJob {

    @Override
    public void doExecute(JobExecutionContext context, QrtzTriggersHistory history) {
        String result = "Hello Job执行时间: " + new Date();
        log.info(result);
        history.setParams("1");
        history.setResult(result);
    }
}