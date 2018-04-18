package com.example.demo.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对某个方法实现不拦截的词
 * <p>该注解写在Controller下的方法上</p>
 * <pre>
 *     // 表示该请求,不会进行注入攻击拦截处理
 *     <code>@</code>RequestMapping( "demo" )
 *     <code>@</code>NotIntercept
 *     public ResponseEntity< String > demo () {
 *          return ResponseEntity.ok();
 *     }
 *
 *     // 表示该请求,进行注入攻击拦截处理时,如果请求参数中包含了 "update" 或者 "exec",那么对此进行忽略,排除这些关键字符
 *     <code>@</code>RequestMapping( "demo" )
 *     <code>@</code>NotIntercept( { "update" , "exec" } )
 *     public ResponseEntity< String > demo () {
 *          return ResponseEntity.ok();
 *     }
 *
 *
 *  * </pre>
 *
 * @author QuiFar
 * @version V1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface NotIntercept {

    @AliasFor("ignoreStrings")
    String[] value() default {};

    @AliasFor("value")
    String[] ignoreStrings() default {};

}
