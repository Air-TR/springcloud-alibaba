package com.tr.auth.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: TR
 */
@Data
public class UserUpdateDto {

    @NotNull
    private Integer id;
    private String nickname;
    private String realname;
    private Integer age;
    private String email;
    private String address;
    private String idcard;

}
