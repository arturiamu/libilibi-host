package com.am.adastra.pojo.DTO;

/*
* 用户添加收藏记录传进来的东西
* */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionAddDTO implements Serializable {

    @NotNull(message = "视频id不能为空！")
    private Integer videoId;//视频的具体id

    private String category;//收藏分类

}
