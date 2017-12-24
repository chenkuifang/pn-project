package com.example.demo.service;

import com.example.demo.common.JsonResult;
import com.example.demo.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * 商品信息服务层接口
 *
 * @author QuiFar
 */
public interface OrderService {

    /**
     * 根据主键ID更新,对象必须包括ID值
     *
     * @param order
     * @return
     */
    int update(Order order);

    /**
     * 新增
     *
     * @param order
     * @return
     */
    int add(Order order);

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    JsonResult createOrderOpt(Order order);

    /**
     * 根据主键获取商品信息
     *
     * @param id
     * @return
     */
    Order get(Integer id);

    /**
     * 获取新订单号
     *
     * @param orderPrefix 订单前缀
     * @return
     */
    String getNewSid(String orderPrefix);

    /**
     * 根据条件获取菜单列表(非外链)
     *
     * @param whereSql 不为空 则根据该条件过滤
     * @param orderSql 不为空 则根据该条件排序
     * @return
     */
    List<Order> list(Map<String, Object> params);

    /**
     * 根据商品ID数组获取列表
     *
     * @return
     */
    List<Order> listByIds(Integer[] ids);

    /**
     * 根据条件获取菜单列表(分页)
     *
     * @param 1.过滤条件、2.分页参数必须包含page,limit
     * @return
     */
    List<Order> listPage(Map<String, Object> params);

    /**
     * 根据条件获取菜单列表总行数(一般分页用)
     *
     * @param params
     * @return
     */
    Integer countPage(Map<String, Object> params);

}
