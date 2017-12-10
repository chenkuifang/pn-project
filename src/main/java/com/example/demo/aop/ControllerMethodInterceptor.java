package com.example.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonResult;

/**
 * 控制器方法拦截器
 * <p>
 * Advice就是我们插入的代码以何种方式插入 1.Before 切入点插入、After 切入点后插入、Around 环绕插入
 * </p>
 * 
 * @author QuiFar
 * @version V1.0
 */
@Aspect
@Component
public class ControllerMethodInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ControllerMethodInterceptor.class);

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
	 * 切入前点插入
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		// 拦截方法返回值，如果不是String 和JsonResult 抛出异常
		// 当然这一步最好自定义注解方式在runtime的时候就拦截，这样的好处是
		// 不会在生成中出现,在开发中就规范了
		Class<?> clazz = method.getReturnType();
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append(String.class.getName()).append(",");
		sbBuilder.append(JsonResult.class.getName());

		if (!sbBuilder.toString().contains(clazz.getName())) {
			logger.info("控制器返回值不正确,返回值只能包括" + sbBuilder.toString());
			logger.info(Constants.RETURN_VALUE_FAIL_CODE + ":" + Constants.RETURN_VALUE_FAIL_DESCRIPTION);
			throw new Exception(Constants.RETURN_VALUE_FAIL_CODE + ":" + Constants.RETURN_VALUE_FAIL_DESCRIPTION);
		}
	}
}
