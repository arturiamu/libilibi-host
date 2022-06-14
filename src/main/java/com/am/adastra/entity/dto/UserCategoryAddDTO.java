package com.am.adastra.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/*
* 用户前端传入的格式
* */
@Data
public class UserCategoryAddDTO implements Serializable {
    private Integer id;

    private long uid;

    @NotNull(message = "收藏夹名字不能为空！")
    private String categoryName;//收藏夹名字

}
