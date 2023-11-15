package com.tr.goods.controller;

import com.tr.goods.entity.Goods;
import com.tr.goods.service.GoodsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: TR
 */
@Api(tags = "商品")
@RestController
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @PostMapping("/goods/save")
    public Goods save(@RequestBody Goods goods) {
        return goodsService.save(goods);
    }

    @GetMapping("/goods/{id}")
    public Goods findById(@PathVariable Integer id) {
        return goodsService.findById(id);
    }

    @DeleteMapping("/goods/{id}")
    public void deleteById(@PathVariable Integer id) {
        goodsService.deleteById(id);
    }

    @GetMapping("/goods/all")
    public List<Goods> findAll() {
        return goodsService.findAll();
    }

}
