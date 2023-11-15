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
public class User extends BaseEntity implements Serializable {

    @Column(length = 32, nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(length = 32)
    private String nickname;
    @Column(length = 32)
    private String realname;
    private Integer age;
    @Column(length = 32)
    private String phone;
    @Column(length = 32)
    private String email;
    private String address;
    /** 身份证 */
    @Column(length = 32)
    private String idcard;

}
