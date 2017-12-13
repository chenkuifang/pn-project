package com.example.demo.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger Logger = LoggerFactory.getLogger(LoginInterceptor.class);

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
			if (Objects.isNull(obj) || !(obj instanceof WebContext) || ((WebContext) obj).getUserId() == null) {
				WebContextUtils.clear();
				response.sendRedirect(request.getContextPath() + "/login.html");
				return false;
			}

			Logger.info("当前sesseionId:" + session.getId());

			// 为web上下文赋值 (保持session和内存的session有效期一致)
			if (Objects.isNull(WebContextUtils.getSession())) {
				WebContextUtils.setSession(session);
			}
			Logger.info("赋值后esseionId:" + WebContextUtils.getCurrentUserName());
		}

		return true;
	}

	/**
	 * controller 执行之后，且页面渲染之前调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// response.setContentType("text/html;charset=utf-8");
		// PrintWriter out=response.getWriter();
		// out.println("<html><head></head><body>1111</body></html>");
		// System.err.println(modelAndView.getStatus());

		super.postHandle(request, response, handler, modelAndView);

	}

	/**
	 * 页面渲染之后调用，一般用于资源清理操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (!Objects.isNull(ex)) {
			// ex.printStackTrace();
		}
		super.afterCompletion(request, response, handler, ex);
	}

}
