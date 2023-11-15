package com.tr.auth.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: TR
 */
@Data
public class LoginDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
