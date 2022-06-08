package com.am.adastra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCategory {
    private Integer id;

    private Integer userId;
    @NotNull(message = "收藏夹名字不能为空！")
    private String categoryName;//收藏夹名字

    private String remarks;//备注
}
