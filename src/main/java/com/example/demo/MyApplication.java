package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.filter.XssFilter;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    /**
     * 把Filter bean 注册到内嵌的Servlet容器中(Servlets 和Listeners的注册方式一样)
     *
     * @return
     */
    @Bean
    FilterRegistrationBean myFilterRegistration() {
        return new FilterRegistrationBean(new XssFilter());
    }
}
