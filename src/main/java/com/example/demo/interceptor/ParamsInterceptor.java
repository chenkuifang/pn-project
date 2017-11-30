package com.example.demo.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.common.Constants;

/**
 * @Description:参数拦截器类 <br>
 *                     需要注意的是：该拦截器不会拦截静态资源 <br>
 *                     该类提供页面请求参数的拦截 <br>
 *                     例如：拦截参数含有limit 和 page 的，增加一个分页的必须参数offset
 * @author QuiFar
 * @date 2017年11月30日 下午20:01:32
 * @version V1.0
 */
public class ParamsInterceptor extends HandlerInterceptorAdapter {

	/**
	 * controller 执行之前调用,返回true 往下执行,否则不往下执行(操作失败)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.getServletContext().setAttribute("aa", "aa");
		//System.err.println(request.getServletContext().getAttribute("aa"));
		String path = request.getServletPath();
		//System.err.println("请求地址：" + path);
		if (path.matches(Constants.PAGE_INTERCEPTOR_PATH)) {
			// 获取请求的所有参数名称
			Enumeration<String> e = request.getParameterNames();
			int flag = 0;
			for (; e.hasMoreElements();) {
				if (e.nextElement().equals("page")) {
					flag += 1;
				}
				if (e.nextElement().equals("limit")) {
					flag += 1;
				}
			}
			// 同时包含 limit ,page 参数
			if (flag == 2) {
				//double page = Double.parseDouble(request.getParameter("page"));
				//double limit = Double.parseDouble(request.getParameter("limit"));
			}
		}

		return true;
	}
}
