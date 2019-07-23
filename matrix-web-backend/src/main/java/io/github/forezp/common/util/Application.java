package io.github.forezp.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author: xufei.
 * @createTime: 2017/6/28.
 */
@Component
public class Application implements ApplicationContextAware {

	private static ApplicationContext context;
	private static String curProfile = "default";
	private static Boolean isOAuthProfile = false;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;

		initProfile();

	}

	private void initProfile() {
		Environment environment = context.getBean(Environment.class);
		if (environment == null) {
			return;
		}
		if (environment.getActiveProfiles() == null || environment.getActiveProfiles().length == 0) {
			return;
		}
		curProfile = environment.getActiveProfiles()[0];
		isOAuthProfile = "production".equals(curProfile) ||
				"stage".equals(curProfile);
	}

	public static Object getBean(String bean) {
		return context.getBean(bean);
	}

	public static <T> T getBean(Class<T> type) {
		return context.getBean(type);
	}

	public static boolean isOAuthProfile() {
		return isOAuthProfile;
	}

	public static String getCurProfile() {
		return curProfile;
	}


}
