package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Spring定时器（需要配置开启或者Application.java @EnableScheduler）
 *
 * @QuiFar
 */
@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 0/1 * * * ?")//每分钟执行一次
    public void task() {
        logger.info("每分钟执行一次");
    }

    @Scheduled(fixedRate = 10000)
    public void testTasks() {
        logger.info("每10秒执行一次。开始……");
    }

}
