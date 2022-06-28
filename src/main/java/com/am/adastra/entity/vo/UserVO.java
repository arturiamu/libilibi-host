package com.am.adastra.entity.vo;

import com.am.adastra.entity.Item;
import com.am.adastra.entity.param.ValidationRules;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserVO implements Serializable {
    @ApiModelProperty(value = "用户id",name = "id",example = "1")
    private Long id;

    //用户名
    @ApiModelProperty(value = "用户名",name = "username",example = "arturiamu")
    private String username;

    //用户手机号或者邮箱
    @ApiModelProperty(value = "账号",name = "account",example = "15911245016")
    private String account;

    @ApiModelProperty(value = "感兴趣分区",name = "items",example = "items")
    private Item[] items;

    //用户注册时间
    @ApiModelProperty(value = "注册时间",name = "createTime",example = "2022-6-28 13:47:47")
    private Date createTime;

    //用户上次修改的时间
    @ApiModelProperty(value = "上次修改时间",name = "updateTime",example = "2022-6-28 13:48:04")
    private Date updateTime;

    //用户状态
    @ApiModelProperty(value = "用户状态",name = "state",example = "正常")
    private String state;

    //备注
    @ApiModelProperty(value = "备注",name = "remarks",example = "正常")
    private String remarks;

}
