package com.am.libilibi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/9 19:02
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LBUser {

    private int id;

    @NotEmpty(message = "用户名不能为空！")
    private String username;

    @NotEmpty(message = "用户名不能为空！")
    @Size(max = 16, min = 6, message = "密码长度不符合要求8！")
    private String password;
}
