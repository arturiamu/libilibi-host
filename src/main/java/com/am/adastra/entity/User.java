package com.am.adastra.entity;

import com.alibaba.fastjson.JSONObject;
import com.am.adastra.controller.param.ValidationRules;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

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
    private Integer id;

    @NotEmpty(groups = ValidationRules.register.class, message = "用户名不能为空！")
    private String username;

    @NotEmpty(groups = ValidationRules.register.class, message = "密码不能为空！")
    @Size(groups = ValidationRules.register.class, min = 8, max = 16, message = "密码长度必须大于等于8，小于等于16！")
    @NotEmpty(groups = ValidationRules.login.class, message = "密码不能为空！")
    @Size(groups = ValidationRules.login.class, min = 8, max = 16, message = "密码长度必须大于等于8，小于等于16！")
    private String password;

    @Pattern(groups = ValidationRules.registerPhone.class, regexp = "^1[3|4|5|7|8|9]\\d{9}$", message = "手机号码格式错误")
    @NotBlank(groups = ValidationRules.registerPhone.class, message = "手机号码不能为空")
    @Pattern(groups = ValidationRules.registerMail.class, regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$", message = "邮箱格式错误")
    @NotBlank(groups = ValidationRules.registerMail.class, message = "邮箱不能为空")
    private String account;

    private Item[] items;

    private String state;
}
