package com.example.demo.controller;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import com.example.demo.common.util.StringUtils;
import com.example.demo.common.util.WebContextUtils;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.GoodsService;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;

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

    /**
     * 创建订单
     *
     * @return
     */
    @PostMapping("/createOrder")
    @ResponseBody
    public JsonResult createOrder() {
//并发测试
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
//        for (int i = 0; i < 1000; i++) {
//            final int index = i;
//            fixedThreadPool.execute(new Runnable() {
//                public void run() {
//
//                    // 订单信息构建
//                    Order order = new Order();
//                    order.setGoodsNum("100001");
//                    order.setBuyerName("quifar" + index);
//                    order.setBuyerAddress("广州萝岗区");
//                    order.setBuyerPhone("15817864015");
//                    order.setPrice(new BigDecimal(100.5));
//                    order.setPayPrice(new BigDecimal(80.5));
//                    order.setAmount(1);
//                    // 订单创建者
//                    String threadName = Thread.currentThread().getName();
//                    order.setGoodsName(threadName);
//                    orderService.createOrderOpt(order);
//
//                }
//            });
//
//        }
//
//        return null;

        // 订单信息构建
        Order order = new Order();
        order.setGoodsNum("100001");
        order.setBuyerName(Thread.currentThread().getName());
        order.setBuyerAddress("广州萝岗区");
        order.setBuyerPhone("15817864015");
        order.setPrice(new BigDecimal(100.5));
        order.setPayPrice(new BigDecimal(80.5));
        order.setAmount(1);

        String goodsNum = order.getGoodsNum();

        // 1.校验商品是否有效(包括状态校验、库存校验)
        if (StringUtils.isBlank(goodsNum)) {
            return JsonResultUtils.jsonResult(Constants.FAIL_CODE, "商品编码不能为空!");
        }

        // 检查商品是否可以下单
        Goods goods = goodsService.getCanCreateOrder(goodsNum);
        if (Objects.isNull(goods)) {
            return JsonResultUtils.jsonResult(Constants.FAIL_CODE, "商品已下架或库存为空!");
        }

        // 2.校验当前用户是否有效
        int userId = WebContextUtils.getCurrentUserId();
        User user = userService.get(userId);
        if (Objects.isNull(user)) {
            return JsonResultUtils.jsonResult(Constants.FAIL_CODE, "买家信息无效!");
        }

        // 订单创建者
        order.setCreateId(userId);
        return orderService.createOrderOpt(order);
    }

}
