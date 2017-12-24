package com.example.demo.pay;

import com.example.demo.common.JsonResult;
import com.example.demo.entity.Order;

/**
 * 支付前接口
 *
 * @author QuiFar
 */
public interface PayBefore {

    /**
     * 解析商品信息
     *
     * @param order
     * @return
     */
    JsonResult decodeGoods(Order order);
}
