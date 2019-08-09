package io.github.forezp.modules.task.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JobTest implements BaseJob {
	private static org.slf4j.Logger log = LoggerFactory.getLogger(JobTest.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.error("JobTest 执行时间: " + new Date());
	}
}
