package com.example.demo.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;
import com.example.demo.common.util.WebContextUtils;

/**
 * 登陆拦截器
 * <p>
 * 需要注意的是：该拦截器不会拦截静态资源
 * </p>
 *
 * @author QuiFar
 * @version V1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	/**
	 * controller 执行之前调用,返回true 往下执行,否则不往下执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getServletPath();
		if (path.matches(Constants.NO_INTERCEPTOR_PATH)) {
			return true;
		} else {
			HttpSession session = request.getSession();

			Object obj = session.getAttribute(Constants.SESSION_USER);
			if (Objects.isNull(obj)) {
				response.sendRedirect(request.getContextPath() + "/login.html");
				return false;
			}
			// 二次校验当前登录信息
			WebContext webContext = (WebContext) obj;
			if (Objects.isNull(webContext.getUserId())) {
				response.sendRedirect(request.getContextPath() + "/login.html");
				return false;
			}

			// 为web上下文赋值 (避免WebContextUtils.getSession的session非空，但无效，所以每次都赋值)
			WebContextUtils.setSession(session);

		}

		return true;
	}

	/**
	 * controller 执行之后，且页面渲染之前调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 页面渲染之后调用，一般用于资源清理操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (!Objects.isNull(ex)) {
			System.err.println("服务器响应出现异常");
		}
		super.afterCompletion(request, response, handler, ex);
	}

}
