package com.am.adastra.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
public class AdminVO {

    @ApiModelProperty(value = "管理员id", name = "id", example = "1")
    private Long id;
    @ApiModelProperty(value = "管理员用户名",name = "username",example = "admin")
    private String username;
    @ApiModelProperty(value = "管理员密码", name = "password", example = "123456")
    private String password;
    @ApiModelProperty(value = "管理员昵称",name = "nickname",example = "阿木木")
    private String nickname;//昵称
    @ApiModelProperty(value = "管理员头像", name = "avatar", example = "https://guli-imge.oss-cn-hangzhou.aliyuncs.com/edu/2022/06/22779838f256794b348cadd0a3bf4b229bfile.png")
    private String avatar;//头像
}
