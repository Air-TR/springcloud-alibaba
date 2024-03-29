package com.tr.auth.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: TR
 */
@Data
public class RegisterDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
