package com.example.demo.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;

/**
 * 系统上下文工具类
 *
 * @author QuiFar
 * @version V1.0
 */
public class WebContextUtils {

	private static HttpSession session;

	private WebContextUtils() {
	}

	/**
	 * 设置sesion,此方法在LoginInterceptor中调用赋值
	 * 
	 * @see #preHandle(HttpServletRequest request, HttpServletResponse response,
	 *      Object handler)
	 * 
	 * @param session
	 */
	public static void setSession(HttpSession session) {
		WebContextUtils.session = session;
	}

	/**
	 * 获取sesion
	 * 
	 * @param session
	 */
	public static HttpSession getSession() {
		return session;
	}

	/**
	 * 获取当前用户信息(session数据)
	 */
	public static WebContext getCurrentUser() {
		return (WebContext) session.getAttribute(Constants.SESSION_USER);
	}

	/**
	 * 获取当前用户Id(session数据)
	 */
	public static int getCurrentUserId() {
		WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
		return webContext.getUserId();
	}

	/**
	 * 获取当前用户名(session数据)
	 */
	public static String getCurrentUserName() {
		WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
		return webContext.getUserName();
	}

	/**
	 * 获取当前用户角色Id(session数据)
	 */
	public static int getCurrentRoleId() {
		WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
		return webContext.getRoleId();
	}

	// 以下是 12.8修改
	// /**
	// * 获取当前用户信息(session数据)
	// */
	// public static WebContext getCurrentUser(HttpSession session) {
	// return (WebContext) session.getAttribute(Constants.SESSION_USER);
	// }
	//
	// /**
	// * 获取当前用户Id(session数据)
	// */
	// public static int getCurrentUserId(HttpSession session) {
	// WebContext webContext = (WebContext)
	// session.getAttribute(Constants.SESSION_USER);
	// return webContext.getUserId();
	// }

	// /**
	// * 获取当前用户名(session数据)
	// */
	// public static String getCurrentUserName(HttpSession session) {
	// WebContext webContext = (WebContext)
	// session.getAttribute(Constants.SESSION_USER);
	// return webContext.getUserName();
	// }
	//
	// /**
	// * 获取当前用户角色Id(session数据)
	// */
	// public static int getCurrentRoleId(HttpSession session) {
	// WebContext webContext = (WebContext)
	// session.getAttribute(Constants.SESSION_USER);
	// return webContext.getRoleId();
	// }

}
