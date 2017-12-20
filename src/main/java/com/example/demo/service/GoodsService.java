package com.example.demo.service;

import com.example.demo.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

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
     *
     * @param goodsNum 商品编码
     * @return true : 商品编码有效； false : 商品编码无效
     */
    boolean checkGoodsNum(String goodsNum);

}
