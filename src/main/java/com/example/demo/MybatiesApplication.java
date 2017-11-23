package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.XssFilter;

@SpringBootApplication
public class MybatiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatiesApplication.class, args);
	}

	/**
	 * 把Filter bean 注册到内嵌的Servlet容器中(Servlets 和Listeners的注册方式一样)
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean myFilterRegistration() {
		return new FilterRegistrationBean(new XssFilter());
	}
	
}
