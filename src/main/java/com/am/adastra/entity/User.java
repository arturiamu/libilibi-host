package com.am.adastra.entity;

import com.am.adastra.entity.param.ValidationRules;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

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
public class User implements Serializable {
    @ApiModelProperty(value = "用户id",name = "id",example = "1")
    private Long id;

    @NotEmpty(groups = ValidationRules.register.class, message = "用户名不能为空！")
    @ApiModelProperty(value = "用户名",name = "username",example = "arturiamu")
    private String username;

    @NotEmpty(groups = ValidationRules.register.class, message = "密码不能为空！")
    @Size(groups = ValidationRules.register.class, min = 8, max = 16, message = "密码长度必须大于等于8，小于等于16！")
    @NotEmpty(groups = ValidationRules.login.class, message = "密码不能为空！")
    @Size(groups = ValidationRules.login.class, min = 8, max = 16, message = "密码长度必须大于等于8，小于等于16！")
    @ApiModelProperty(value = "用户密码",name = "password",example = "123456789")
    private String password;

    @NotEmpty(groups = ValidationRules.login.class, message = "账号不能为空！")
    @NotEmpty(groups = ValidationRules.register.class, message = "账号不能为空！")
    @ApiModelProperty(value = "用户账号",name = "account",example = "15911245016")
    private String account;

    @ApiModelProperty(value = "用户兴趣分区",name = "items",example = "items")
    private Item[] items;

    @ApiModelProperty(value = "用户状态",name = "state",example = "normal")
    private String state;
}
