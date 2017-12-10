package com.example.demo.common.util;

import org.springframework.context.ApplicationContext;

/**
 * @Description:
 * @author QuiFar
 * @date 2017年12月9日 下午3:36:12
 * @version V1.0
 */
public class SpringContextUtils {
	private static ApplicationContext applicationContext;

	// 获取上下文
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	// 设置上下文
	public static void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtils.applicationContext = applicationContext;
	}

	// 通过名字获取上下文中的bean
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	// 通过类型获取上下文中的bean
	public static Object getBean(Class<?> requiredType) {
		return applicationContext.getBean(requiredType);
	}
}
