package com.example.demo.service.impl;

import com.example.demo.entity.Goods;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 商品服务层接口实现
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int remove(Integer id) {
        return goodsMapper.remove(id);
    }

    @Override
    public int removeBatch(String[] ids) {
        return goodsMapper.removeBatch(ids);
    }

    @Override
    public int update(Goods goods) {
        return goodsMapper.update(goods);
    }

    @Override
    public int add(Goods goods) {
        return goodsMapper.add(goods);
    }

    @Override
    public Goods get(Integer id) {
        return goodsMapper.get(id);
    }

    @Override
    public List<Goods> list(Map<String, Object> params) {
        return goodsMapper.list(params);
    }

    @Override
    public List<Goods> listByIds(Integer[] ids) {
        return goodsMapper.listByIds(ids);
    }

    @Override
    public List<Goods> listPage(Map<String, Object> params) {
        return goodsMapper.listPage(params);
    }

    @Override
    public Integer countPage(Map<String, Object> params) {
        return goodsMapper.countPage(params);
    }

    @Override
    public boolean checkGoodsNum(String goodsNum, Integer id) {
        Goods goods = goodsMapper.getByGoodsNum(goodsNum);
        if (Objects.isNull(goods)) {
            return true;
        }

        // update时的校验
        if (!Objects.isNull(id))
            if (id.equals(goods.getId()))
                return true;

        return false;
    }
}
