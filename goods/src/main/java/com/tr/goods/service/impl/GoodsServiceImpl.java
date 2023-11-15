package com.tr.goods.service.impl;

import com.tr.goods.entity.Goods;
import com.tr.goods.repository.GoodsRepository;
import com.tr.goods.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: TR
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsRepository goodsRepository;

    @Override
    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public Goods findById(Integer id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }

}
