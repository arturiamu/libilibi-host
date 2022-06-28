package com.am.adastra.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/24 21:40
 * @Description:
 */
@Data
public class UserLoginLogVO {
    @ApiModelProperty(value = "id",name = "id",example = "1")
    private Long id;
    @ApiModelProperty(value = "用户id",name = "uid",example = "1")
    private Long uid;
    @ApiModelProperty(value = "登录时间",name = "time",example = "2022-6-28 13:45:32")
    private Date time;
    @ApiModelProperty(value = "登录ip",name = "ip",example = "127.0.0.1")
    private String ip;
}
