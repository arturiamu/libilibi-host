package com.am.adastra.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/*
* 管理员实体
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private Long id;

    @NotEmpty(message = "用户名不能为空！")
    private String username;

    @NotEmpty(message = "密码不能为空！")
    private String password;
}
