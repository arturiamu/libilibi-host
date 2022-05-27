package com.am.adastra.entity;

import com.am.adastra.controller.param.ValidationRules;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 10:56
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class User {
    private Integer id;

    @NotEmpty(message = "用户名不能为空！")
    private String username;

    @NotEmpty(message = "密码不能为空！")
    @Size(min = 6, max = 16, message = "密码长度必须大于等于8，小于等于16！")
    private String password;

    @NotNull(groups = ValidationRules.register.class, message = "手机号不能为空")
    @Size(groups = ValidationRules.register.class, message = "手机号格式不正确", max = 11, min = 11)
    private String phone;

    String[] item;
}
