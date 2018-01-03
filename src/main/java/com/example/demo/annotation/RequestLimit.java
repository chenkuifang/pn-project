package com.example.demo.annotation;

/**
 * 限流器
 * 作用于方法上，example:@RequestLimit(count = 10)
 * 表示一分鐘內允許10次訪問
 *
 * @author QuiFar
 * @version V1.0
 **/

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {

    /**
     * 默认允许访问次数为最大次数
     *
     * @return
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，默认为60秒
     *
     * @return
     */
    long time() default 60000;
}
