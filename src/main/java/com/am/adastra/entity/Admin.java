package com.am.adastra.entity;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/*
* 管理员实体
* */
@Data
public class Admin {
    private Integer id;

    @NotEmpty(message = "用户名不能为空！")
    private String adminName;

    @NotEmpty(message = "密码不能为空！")
    private String password;
}
