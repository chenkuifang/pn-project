package com.example.demo.controller;

import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 订单控制类
 *
 * @author QuiFar
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 访问首页
     *
     * @return
     */
    @GetMapping("/list")
    public String list() {
        return "order/list";
    }

    /**
     * 根据条件获取角色列表
     *
     * @param params 页面请求参数
     * @return
     */
    @GetMapping("/listPage")
    @ResponseBody
    public JsonResult listPage(@RequestParam Map<String, Object> params) {
        List<Order> list = orderService.listPage(params);
        int countPage = orderService.countPage(params);

        return JsonResultUtils.jsonPageResult(list, countPage);
    }
}
