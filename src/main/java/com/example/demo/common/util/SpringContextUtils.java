package com.example.demo.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring 上下文工具类(还未注入值)
 *
 * @author QuiFar
 * @version V1.0
 */
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    // 获取上下文
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 设置上下文
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    // 通过名字获取上下文中的bean
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBeansOfType(clazz);
    }

    // // 通过类型获取上下文中的bean
    // public static Object getBean(Class<?> clazz) {
    // return applicationContext.getBean(clazz);
    // }
}
