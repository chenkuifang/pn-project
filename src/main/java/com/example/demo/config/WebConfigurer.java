package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.interceptor.LoginInterceptor;

/**
 * @Description: 配置各个注册器
 * @author QuiFar
 * @date 2017年11月23日 下午9:13:48
 * @version V1.0
 */
@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {
	/**
	 * 注册 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
}
