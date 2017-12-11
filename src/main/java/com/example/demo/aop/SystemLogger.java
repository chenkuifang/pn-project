package com.example.demo.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 零侵入做操作日志记录
 * <p>
 * 如需日志写入文件，在application.properties文件指定即可
 *
 * @author QuiFar
 * @version V1.0
 */
@Aspect
@Component
public class SystemLogger {

    private static final Logger logger = LoggerFactory.getLogger(SystemLogger.class);

    private static long startTime = 0L;

    @Pointcut("execution(* com.example.demo.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 记录下请求内容
        logger.info("请求路径 : " + request.getRequestURL().toString());
        logger.info("请求方法 : " + request.getMethod());
        logger.info("访问IP : " + request.getRemoteAddr());
        logger.info("访问方法 : " + signature.getDeclaringTypeName() + "." + method.getName());
        logger.info("请求方法参数：" + Arrays.toString(joinPoint.getArgs()));

        //当然这里也可以把这些记录插入数据库
    }

    /**
     * 处理完请求，返回内容(切入点后插入)
     *
     * @param ret 方法返回值
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("响应数据 : " + ret);
        logger.info("响应时间 : " + (System.currentTimeMillis() - startTime) + " ms");
    }

}
