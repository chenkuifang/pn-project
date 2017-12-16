package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("order/list");
        return mv;
    }
}
