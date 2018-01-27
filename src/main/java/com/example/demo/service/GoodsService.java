package com.example.demo.service;

import com.example.demo.entity.Goods;

import java.util.List;
import java.util.Map;

/**
 * 商品信息服务层接口
 *
 * @author QuiFar
 */
public interface GoodsService {
    /**
     * 根据主键删除商品信息
     *
     * @param id 主键
     * @return
     */
    int remove(Integer id);

    /**
     * 根据主键数据批量删除
     *
     * @param ids 主键
     * @return
     */
    int removeBatch(String[] ids);

    /**
     * 根据主键ID更新,对象必须包括ID值
     *
     * @param goods
     * @return
     */
    int update(Goods goods);

    /**
     * 修改商品库存和销量
     *
     * @param goodsNum 商品编码
     * @param amount   销量
     * @return 返回修改成功行数
     */
    int updateStockAndSaleCount(String goodsNum, int amount);

    /**
     * 新增
     *
     * @param goods
     * @return
     */
    int add(Goods goods);

    /**
     * 根据主键获取商品信息
     *
     * @param id
     * @return
     */
    Goods get(Integer id);

    /**
     * 根据商品编码获取商品信息
     *
     * @param goodsNum
     * @return
     */
    Goods get(String goodsNum);

    /**
     * 根据商品编码获取可下单的商品
     *
     * @param goodsNum 商品编码
     * @return 返回null表示获取不到可下单的商品
     */
    Goods getCanCreateOrder(String goodsNum);

    /**
     * 根据条件获取菜单列表(非外链)
     *
     * @param whereSql 不为空 则根据该条件过滤
     * @param orderSql 不为空 则根据该条件排序
     * @return
     */
    List<Goods> list(Map<String, Object> params);

    /**
     * 根据商品ID数组获取列表
     *
     * @return
     */
    List<Goods> listByIds(Integer[] ids);

    /**
     * 根据条件获取菜单列表(分页)
     *
     * @param 1.过滤条件、2.分页参数必须包含page,limit
     * @return
     */
    List<Goods> listPage(Map<String, Object> params);

    /**
     * 根据条件获取菜单列表总行数(一般分页用)
     *
     * @param params
     * @return
     */
    Integer countPage(Map<String, Object> params);

    /**
     * 校验商品编码是否有效，商品编码唯一性
     * 1.新增时候校验，Id值为null，用法：checkGoodsNum("123456","");
     * 2.修改时候校验，用法: checkGoodsNum("123456","10001");
     *
     * @param goodsNum 商品编码
     * @param id       商品ID
     * @return true : 商品编码有效； false : 商品编码无效
     */
    boolean checkGoodsNum(String goodsNum, Integer id);

}
