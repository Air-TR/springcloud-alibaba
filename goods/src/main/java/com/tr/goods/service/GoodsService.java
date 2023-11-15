package com.tr.goods.service;

import com.tr.goods.entity.Goods;

import java.util.List;

public interface GoodsService {

    Goods save(Goods goods);

    Goods findById(Integer id);

    void deleteById(Integer id);

    List<Goods> findAll();

}
