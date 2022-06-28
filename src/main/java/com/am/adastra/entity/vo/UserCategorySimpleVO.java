package com.am.adastra.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserCategorySimpleVO implements Serializable {

    @ApiModelProperty(value = "收藏夹名字",name = "categoryName",example = "收藏夹1")
    private String categoryName;//收藏夹名字

    @ApiModelProperty(value = "收藏夹备注",name = "remarks",example = "学习资料")
    private String remarks;//备注
}
