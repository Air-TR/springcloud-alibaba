package com.tr.auth.entity;

import com.tr.auth.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Author: TR
 */
@Data
@Entity
public class Role extends BaseEntity implements Serializable {

    @Column(length = 32, nullable = false, unique = true)
    private String rolename;
    @Column(length = 32, nullable = false)
    private String description;

}
