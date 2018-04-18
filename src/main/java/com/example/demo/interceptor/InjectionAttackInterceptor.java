package com.example.demo.interceptor;

import com.example.demo.common.util.RequestUtils;
import com.example.demo.interceptor.handler.DefaultInjectionAttackHandler;
import com.example.demo.interceptor.handler.InjectionAttackHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thymeleaf.util.MapUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 注入攻击拦截器
 *
 * @author : QuiFar
 * 
 */
@Slf4j
public class InjectionAttackInterceptor extends HandlerInterceptorAdapter {

	private InjectionAttackHandler injectionAttackHandler = DefaultInjectionAttackHandler.getInstance();

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			log.debug("! ( handler instanceof HandlerMethod )");
			log.debug("handler : {}", handler);
			return true;
		}

		final String parameters = RequestUtils.getRequestParameters(request);

		// 参数注入攻击处理
		if (this.injectionAttackHandler.isInjectionAttack(parameters)) {
			log.debug("参数 {} 被判断为注入攻击", parameters);
			this.injectionAttackHandler.attackHandle(request, response, parameters);
			return false;
		}

		final Map<String, String> decodedUriVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		if (MapUtils.isEmpty(decodedUriVariables)) {
			return true;
		}

		// URI PATH 注入攻击处理
		for (String decodedUriVariable : decodedUriVariables.values()) {
			if (this.injectionAttackHandler.isInjectionAttack(decodedUriVariable)) {
				log.debug("URI {} 被判断为注入攻击", parameters);
				this.injectionAttackHandler.attackHandle(request, response, decodedUriVariable);
				return false;
			}
		}
		return true;
	}
}
