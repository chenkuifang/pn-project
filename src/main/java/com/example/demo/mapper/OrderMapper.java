package com.example.demo.mapper;

import com.example.demo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单映射接口
 *
 * @author QuiFar
 * @version V1.0
 */
@Mapper
public interface OrderMapper {

    /**
     * 根据主键ID更新菜单,Menu对象必须包括ID值
     *
     * @param order 需要更新的菜单
     * @return
     */
    Integer update(Order order);

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    Integer add(Order menu);

    /**
     * 根据主键获取订单
     *
     * @param orderId
     * @return
     */
    Order get(Integer orderId);

    /**
     * 根据订单号获取订单
     *
     * @param orderSid
     * @return
     */
    Order get(String orderSid);

    /**
     * 根据条件获取菜单列表(非外链)
     *
     * @param whereSql 不为空 则根据该条件过滤
     * @param orderSql 不为空 则根据该条件排序
     * @return
     */
    List<Order> list(Map<String, Object> params);

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
