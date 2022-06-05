package com.am.adastra.entity;

import com.am.adastra.controller.param.ValidationRules;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/31 9:50
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParm {
    @Valid
    User user;
    @NotNull(groups = ValidationRules.register.class, message = "验证码不能为空")
    @Size(groups = ValidationRules.register.class, message = "验证码格式不正确", max = 6, min = 6)
    String verCode;
    Long tokenId;
}
