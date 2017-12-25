package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.JsonResult;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.pay.PayBefore;
import com.example.demo.service.OrderService;

/**
 * 商品服务层接口实现
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PayBefore payBefore;

    @Override
    public int update(Order order) {
        return orderMapper.update(order);
    }

    @Override
    public int add(Order order) {
        return orderMapper.add(order);
    }

    @Override
    public JsonResult createOrderOpt(Order order) {

        //1. 支付前

        //2.支付中

        //3.支付完成

        return payBefore.decodeGoods(order);

    }

    @Override
    public Order get(Integer id) {
        return orderMapper.get(id);
    }

    @Override
    public String getNewSid(String orderPrefix) {
        // 根据订单前缀获取最新订单号
        Map<String, String> params = new HashMap<>();
        params.put("orderPrefix", orderPrefix);
        String lastOrderSid = orderMapper.getLastSid(params);

        if (StringUtils.isBlank(lastOrderSid)) {
            return orderPrefix + "10001";
        }

        int intValue = Integer.parseInt(lastOrderSid.substring(lastOrderSid.length() - 5));
        return orderPrefix + String.valueOf(intValue + 1);
    }

    @Override
    public List<Order> list(Map<String, Object> params) {
        return orderMapper.list(params);
    }

    @Override
    public List<Order> listByIds(Integer[] ids) {
        return null;
    }

    @Override
    public List<Order> listPage(Map<String, Object> params) {
        return orderMapper.listPage(params);
    }

    @Override
    public Integer countPage(Map<String, Object> params) {
        return orderMapper.countPage(params);
    }

}
