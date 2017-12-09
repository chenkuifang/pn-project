package com.example.demo.common.util;

import javax.servlet.http.HttpSession;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;

/**
 * 系统上下文工具类
 *
 * @author QuiFar
 * @version V1.0
 * @date 2017年12月1日 下午19:28:35
 */
public class WebContextUtils {

    private HttpSession session;

    private WebContextUtils() {
    }

    public WebContextUtils(HttpSession session) {
        this.session = session;
    }

    /**
     * 获取当前用户信息(session数据)
     */
    public static WebContext getCurrentUser(HttpSession session) {
        return (WebContext) session.getAttribute(Constants.SESSION_USER);
    }

    /**
     * 获取当前用户Id(session数据)
     */
    public static int getCurrentUserId(HttpSession session) {
        WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
        return webContext.getUserId();
    }

    /**
     * 获取当前用户名(session数据)
     */
    public static String getCurrentUserName(HttpSession session) {
        WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
        return webContext.getUserName();
    }

    /**
     * 获取当前用户角色Id(session数据)
     */
    public static int getCurrentRoleId(HttpSession session) {
        WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
        return webContext.getRoleId();
    }

// 以下是 12.8修改
//    /**
//     * 获取当前用户信息(session数据)
//     */
//    public static WebContext getCurrentUser(HttpSession session) {
//        return (WebContext) session.getAttribute(Constants.SESSION_USER);
//    }
//
//    /**
//     * 获取当前用户Id(session数据)
//     */
//    public static int getCurrentUserId(HttpSession session) {
//        WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
//        return webContext.getUserId();
//    }
//
//    /**
//     * 获取当前用户角色Id(session数据)
//     */
//    public static int getCurrentRoleId(HttpSession session) {
//        WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
//        return webContext.getRoleId();
//    }

}
