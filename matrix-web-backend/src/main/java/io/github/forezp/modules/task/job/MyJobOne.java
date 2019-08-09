package io.github.forezp.modules.task.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MyJobOne implements BaseJob {
	private static org.slf4j.Logger log = LoggerFactory.getLogger(MyJobOne.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.error("MyJobOne 执行时间: " + new Date());
	}
}
