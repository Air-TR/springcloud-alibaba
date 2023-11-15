package com.tr.goods.entity;

import com.tr.goods.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Author: TR
 */
@Data
@Entity
public class Goods extends BaseEntity implements Serializable {

    private String name;
    private Double price;
    private Integer inventory;

}
