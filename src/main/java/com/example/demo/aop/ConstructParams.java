package com.example.demo.aop;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.common.Constants;

/**
 * 控制器方法拦截器，该拦截器的作用主要有1.规范控制器方法返回值 2.为listPage请求添加offset参数
 * <p>
 * Advice就是我们插入的代码以何种方式插入 1.Before 切入点插入、After 切入点后插入、Around 环绕插入
 * </p>
 *
 * @author QuiFar
 * @version V1.0
 */
@Aspect
@Component
@Slf4j
public class ConstructParams {

    /**
     * 切入点；拦截controller包下面的所有类中含有@RequestMapping(已包括GETxxxx,POSTxxxx注解)注解的方法。
     * 切入点分为：call和execution等类型
     * <p>
     * execution参数说明,第一个* 表示返回任意值,后面是典型的包名路径，其中可以包含 * 来进行通配，(..)表示任意类型，任意个数的参数
     */
    @Pointcut("execution(* com.example.demo.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void pointcut() {
    }

    /**
     * Around增强处理是功能比较强大的增强处理，它近似于Before增强处理和AfterReturing增强处理的汇总
     * <p>
     * Around增强处理可以决定目标方法在什么时候执行，如何执行，甚至可以完全阻止目标方法的执行
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @SuppressWarnings("unchecked")
    @Around("pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 构造翻页参数
        String methodName = method.getName();
        if (methodName.matches(Constants.PAGE_INTERCEPTOR_PATH)) {
            double page = Double.parseDouble(request.getParameter("page"));
            double limit = Double.parseDouble(request.getParameter("limit"));
            int offset = (int) ((page - 1) * limit);
            for (Object arg : args) {
                if (arg instanceof Map<?, ?>) {
                    // 追加参数
                    ((Map<String, Integer>) arg).put("offset", offset);
                }
            }
        }

        return joinPoint.proceed(args);
    }

    /**
     * 出现异常就会执行该方法
     *
     * @param ex
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrow(Exception ex) {
        log.info("麻蛋出现异常了， " + ex.getMessage());
    }

}
