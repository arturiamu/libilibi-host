package com.am.adastra.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCategorySimpleVO implements Serializable {

    private String categoryName;//收藏夹名字

    private String remarks;//备注
}
