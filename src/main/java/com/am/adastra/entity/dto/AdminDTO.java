package com.am.adastra.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminDTO {

    @ApiModelProperty(value = "管理员id",name = "id",example = "1")
    private Long id;
    @ApiModelProperty(value = "管理员用户名",name = "username",example = "admin")
    private String username;//用户名
    @ApiModelProperty(value = "管理员昵称",name = "nickname",example = "阿木木")
    private String nickname;//昵称
    @ApiModelProperty(value = "管理员头像",name = "avatar",example = "https://guli-imge.oss-cn-hangzhou.aliyuncs.com/edu/2022/06/22779838f256794b348cadd0a3bf4b229bfile.png")
    private String avatar;//头像

}
