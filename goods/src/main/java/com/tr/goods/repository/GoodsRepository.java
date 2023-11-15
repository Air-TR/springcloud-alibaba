package com.tr.goods.repository;

import com.tr.goods.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: TR
 */
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
