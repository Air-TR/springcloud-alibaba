package com.tr.auth.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: TR
 */
@Data
public class RoleUpdateDto {

    @NotNull
    private Integer id;
    private String rolename;
    private String description;

}
