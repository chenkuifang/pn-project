package com.example.demo;

import com.example.demo.filter.XssFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
//@EnableScheduling 开启spring任务调度
public class MyApplication {

    public static void main(String[] args) {
        // 返回一个上下文
        SpringApplication.run(MyApplication.class, args);
    }
//
//    @Bean
//    StringRedisTemplate stringRedisTemplate() {
//        return new StringRedisTemplate();
//    }
}
