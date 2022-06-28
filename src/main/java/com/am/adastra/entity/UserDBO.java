package com.am.adastra.entity;

import com.am.adastra.entity.param.ValidationRules;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/31 9:17
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDBO {
    @ApiModelProperty(value = "用户id",name = "id",example = "1")
    private Long id;
    @ApiModelProperty(value = "用户名",name = "username",example = "arturiamu")
    private String username;
    @ApiModelProperty(value = "用户密码",name = "password",example = "123456789")
    @NotEmpty(groups = ValidationRules.register.class, message = "密码不能为空！")
    private String password;
    @ApiModelProperty(value = "用户账号",name = "account",example = "15911245016")
    private String account;
    @ApiModelProperty(value = "用户兴趣分区",name = "items",example = "items")
    private String jsItems;
    @ApiModelProperty(value = "用户状态",name = "state",example = "normal")
    private String state;
    private String createTime;

    public UserDBO(Long id, String username, String password, String account, String jsItems, String state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.account = account;
        this.jsItems = jsItems;
        this.state = state;
    }
}
