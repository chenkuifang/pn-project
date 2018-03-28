package com.example.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Spring定时器（需要配置开启或者Application.java @EnableScheduler）
 *
 * @QuiFar
 */
//@Component
@Slf4j
public class Scheduler {
    @Scheduled(cron = "0 0/1 * * * ?")//每分钟执行一次
    public void task() {
        log.info("每分钟执行一次");
    }

    @Scheduled(fixedRate = 10000)
    public void testTasks() {
        log.info("每10秒执行一次。开始……");
    }

}
