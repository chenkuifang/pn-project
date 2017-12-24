package com.example.demo.pay.impl;

import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import com.example.demo.pay.PayBefore;
import com.example.demo.service.CommonService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 支付前接口实现类
 *
 * @author QuiFar
 */
@Component
public class PayBeforeImpl implements PayBefore {
    @Autowired
    private GoodsService goodsServices;
    @Autowired
    private CommonService commonService;
    @Autowired
    private OrderService orderService;

    // 类锁
    private static final Object lock = new Object();

    /**
     * 订单创建
     *
     * @param order
     * @return
     */
    @Transactional
    public JsonResult decodeGoods(Order order) {
        // 订单前缀
        String orderPrefix = "PN" + new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

        String goodsNum = order.getGoodsNum();
        int amount = order.getAmount();

        if (StringUtils.isBlank(goodsNum)) {
            return JsonResultUtils.jsonResult("商品信息为空！");
        }

        if (StringUtils.isBlank(order.getBuyerName())) {
            return JsonResultUtils.jsonResult("收货人信息为空！");
        }

        if (StringUtils.isBlank(order.getBuyerPhone())) {
            return JsonResultUtils.jsonResult("收货手机号码为空！");
        }

        if (StringUtils.isBlank(order.getBuyerAddress())) {
            return JsonResultUtils.jsonResult("收货地址为空！");
        }

        synchronized (lock) {
            // 获取商品信息(如果服务器连接数据库延迟，该怎么办？)
            Goods goods = goodsServices.get(goodsNum);
            if (goods.getStatus() != 1) {
                return JsonResultUtils.jsonResult("该商品已下架！");
            }

            if (amount <= 0) {
                return JsonResultUtils.jsonResult("购买数量错误！");
            }

            if (amount > goods.getStock()) {
                return JsonResultUtils.jsonResult("商品库存不足！");
            }

            // 获取订单编码
            int orderId = commonService.getTableNewId("pn_order", "order_id", 1000001);

            // 获取订单号
            String orderSid = orderService.getNewSid(orderPrefix);
            if (StringUtils.isBlank(orderSid)) {
                return JsonResultUtils.jsonResult("订单号生成失败！");
            }

            // 1.生成订单主表信息
            order.setOrderId(orderId);
            order.setOrderSid(orderSid);
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            order.setStatus(0);
            orderService.add(order);

            // 2.商品减库存
            goodsServices.updateStockAndSaleCount(goodsNum, amount);

        }

        return JsonResultUtils.jsonResult("订单创建成功");
    }
}
