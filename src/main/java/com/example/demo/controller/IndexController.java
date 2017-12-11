package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.common.WebContext;
import com.example.demo.common.util.WebContextUtils;

/**
 * @author QuiFar
 * @version V1.0
 * @Description: 首页控制类
 * @date 2017年11月18日 下午4:21:41
 */

@Controller
public class IndexController {

    /**
     * 访问首页
     *
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model) {
        WebContext webContext = WebContextUtils.getCurrentUser();
        model.addAttribute("webContext", webContext);
        return "index";
    }

    /**
     * 主页视图初始化
     *
     * @return
     */
    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
