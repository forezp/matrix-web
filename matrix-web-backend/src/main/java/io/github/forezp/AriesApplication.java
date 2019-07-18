package io.github.forezp;

import io.github.forezp.datasources.DynamicDataSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import java.lang.reflect.Executable;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class AriesApplication extends SpringBootServletInitializer{

	Logger logger= LoggerFactory.getLogger(AriesApplication.class);

		public static void main(String[] args) {
		SpringApplication.run(AriesApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AriesApplication.class);
	}
//	private void log(){
//		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
//				new Runnable() {
//					@Override
//					public void run() {
//						MDC.put("REQUEST_ID", UUID.randomUUID().toString());
//						logger.info(new Random().nextInt(99999)+"");
//						MDC.clear();
//					}
//				}
//		,100,100, TimeUnit.MICROSECONDS);
//
//	}
}
