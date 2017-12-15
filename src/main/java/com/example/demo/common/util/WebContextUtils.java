package com.example.demo.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;

/**
 * 系统上下文工具类
 *
 * @author QuiFar
 * @version V1.0
 */
public class WebContextUtils {

    // 为解决并发问题把session存在到ThreadLocal中
    private static ThreadLocal<HttpSession> session = new ThreadLocal<>();

    private WebContextUtils() {
    }

    /**
     * 设置sesion,此方法在LoginInterceptor中调用赋值
     *
     * @param session
     * @see #preHandle(HttpServletRequest request, HttpServletResponse response,
     * Object handler)
     */
    public static void setSession(HttpSession session) {
        WebContextUtils.session.set(session);
    }

    /***
     * 返回session
     *
     * @return
     */
    public static HttpSession getSession() {
        Assert.notNull(session, "session is must not be null");
        return session.get();
    }

    /**
     * 清空session对象
     */
    public static void clear() {
        session.remove();
    }

    /**
     * 获取当前用户信息(session数据)
     */
    public static WebContext getCurrentUser() {
        return (WebContext) getSession().getAttribute(Constants.SESSION_USER);
    }

    /**
     * 获取当前用户Id(session数据)
     */
    public static int getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    /**
     * 获取当前用户名(session数据)
     */
    public static String getCurrentUserName() {
        return getCurrentUser().getUserName();
    }

    /**
     * 获取当前用户角色Id(session数据)
     */
    public static int getCurrentRoleId() {
        return getCurrentUser().getRoleId();
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
