package com.example.demo.interceptor;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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

    //private static final Logger Logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * controller 执行之前调用,返回true 往下执行,否则不往下执行
     * <p>
     * note:这里的handler拿不到真正执行方法的实际参数(args)
     * 注意filter、interceptor、aop方式的方法对比
     *
     * @param request  原始请求
     * @param response 原始响应
     * @param handler  拦击后真正执行的方法处理
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String path = request.getServletPath();
        System.err.println(path);
        if (path.matches(Constants.NO_INTERCEPTOR_PATH)) {
            return true;
        } else {
            HttpSession session = request.getSession();

            Object obj = session.getAttribute(Constants.SESSION_USER);
            if (Objects.isNull(obj) || !(obj instanceof WebContext) || Objects.isNull(((WebContext) obj).getUserId())) {
                response.sendRedirect(request.getContextPath() + "/login.html");
                return false;
            }

            // 这样维护一个本地对象，获取session方便，但每个线程都要维护该对象,但还好HttpSession的实现类不是final的，这个利和弊由开发者决定
            WebContextUtils.setSession(session);

            ServletRequestAttributes attributes = new ServletRequestAttributes(request);
            request.setAttribute("myWebContent", "你好，quifar");
            LocaleContextHolder.setLocale(request.getLocale());
            RequestContextHolder.setRequestAttributes(attributes);

            RequestContextHolder.setRequestAttributes(attributes);
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
