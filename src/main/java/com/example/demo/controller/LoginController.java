package com.example.demo.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.demo.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;
import com.example.demo.common.util.MDUtils;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/**
 * 登陆控制类
 *
 * @author QuiFar
 * @version V1.0
 */
@Controller
public class LoginController {

    @Autowired
    private UserService UserService;

    /**
     * 获取登陆初始化页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登陆校验
     *
     * @param userName
     * @param password
     * @return code 100 成功，101 失败
     */
    @PostMapping("/loginPost")
    @ResponseBody
    public R loginPost(HttpSession session, @RequestParam("userName") String userName,
                       @RequestParam("password") final String password) {
        R r = R.getInstance();

        String code = Constants.FAIL_CODE;
        String msg = "用户名或密码错误!";
        User user = UserService.getByUserName(userName);

        if (user != null) {
            String md5Str = "";
            try {
                md5Str = MDUtils.encodeMD5(password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (user.getPassword().equals(md5Str)) {
                code = Constants.SUCCESS_CODE;
                msg = Constants.SUCCESS_DESCRIPTION;

                // 存放user相关信息进入session
                WebContext webContext = new WebContext();
                webContext.setUserId(user.getId());
                webContext.setUserName(user.getUserName());
                webContext.setUserNike(user.getUserNike());
                webContext.setRoleId(user.getRoleId());
                session.setAttribute(Constants.SESSION_USER, webContext);
            }
        }

        // 结果返回
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut(HttpSession session) {
        // 清除session
        Enumeration<String> em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement());
        }
        return "redirect:/login";
    }

    /**
     * 修改密码初始化视图
     *
     * @return
     */
    @GetMapping("/changePwd")
    public String changePwd(HttpSession session, Model model) {
        WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
        model.addAttribute("userName", webContext.getUserName());
        model.addAttribute("result", "");
        return "user/changePwd";
    }

    /**
     * 获取修改密码操作
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/changePwdPost")
    public String changePwdPost(@RequestParam String userName, @RequestParam String oldPwd, @RequestParam String newPwd,
                                Model model) throws Exception {
        String result = "";
        String viewName = "user/changePwd";

        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd)) {
            result = "密码不能为空！";
            model.addAttribute("result", result);
            return viewName;
        }

        // 根据用户名获取用户信息
        User user = UserService.getByUserName(userName);
        String encodePwd = MDUtils.encodeMD5(oldPwd);
        if (user != null && user.getPassword().equals(encodePwd)) {
            User newUser = new User();
            newUser.setId(user.getId());
            newUser.setPassword(MDUtils.encodeMD5(newPwd));
            int flag = UserService.update(newUser);
            result = flag >= 1 ? "修改成功" : "修改失败";
        } else {
            result = "用户名或密码错误,请重新输入！";
        }
        model.addAttribute("userName", userName);
        model.addAttribute("result", result);
        return viewName;
    }
}
