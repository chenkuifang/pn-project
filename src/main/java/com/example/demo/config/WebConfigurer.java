package com.example.demo.config;

import com.example.demo.interceptor.InjectionAttackInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.interceptor.LoginInterceptor;

/**
 * 配置各个注册器
 *
 * @author QuiFar
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
        registry.addInterceptor(new InjectionAttackInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**")
                .excludePathPatterns("/resetPwd/**");

        super.addInterceptors(registry);
    }
}
