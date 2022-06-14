package com.am.adastra.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 17:40
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePwdDTO {
    @NotBlank(message = "新密码不能为空")
    @Size(min = 8, max = 16, message = "密码格式不正确")
    private String password;
    @NotBlank(message = "验证码不能为空")
    @Size(min = 6, max = 6, message = "验证码格式不正确")
    private String verCode;
    @NotBlank(message = "账号不能为空")
    private String account;
}
