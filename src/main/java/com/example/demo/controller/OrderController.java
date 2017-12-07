package com.example.demo.controller;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 订单类
 *
 * @author QuiFar
 * @version V1.0
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    /**
     * 访问首页
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        System.err.println("ss");
        return "order/list";
    }
}
