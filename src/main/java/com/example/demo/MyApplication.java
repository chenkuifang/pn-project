package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
