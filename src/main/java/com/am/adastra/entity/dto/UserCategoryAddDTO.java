package com.am.adastra.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
 * 用户前端传入的格式
 * */
@Data
public class UserCategoryAddDTO implements Serializable {
    @ApiModelProperty(value = "收藏夹id", name = "id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "用户id", name = "uid", example = "1")
    private Long uid;

    @ApiModelProperty(value = "收藏夹名字", name = "categoryName", example = "收藏夹1")
    @NotNull(message = "收藏夹名字不能为空！")
    private String categoryName;//收藏夹名字

    @ApiModelProperty(value = "收藏夹描述", name = "remarks", example = "精品资源")
    private String remarks;

}
