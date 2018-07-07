package com.example.demo.common.util;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 系统上下文工具类
 *
 * @author QuiFar
 * @version V1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebContextUtils {

    // 参考RequestContextHolder做法
    // 为解决并发问题把session存在到ThreadLocal中
    private final static ThreadLocal<HttpSession> session = new ThreadLocal<>();

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
     * 设置sesion,此方法在LoginInterceptor中调用赋值
     *
     * @param session
     * @see #preHandle(HttpServletRequest request, HttpServletResponse response,
     * Object handler)
     */
    public static void setSession(HttpSession session) {
        WebContextUtils.session.set(session);
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

    /**
     * 获取当前用户角色IP(session数据)
     */
    public static String getRemoteAddr() {
        return getCurrentUser().getIp();
    }
}
