package com.am.adastra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollection {
    private Integer id;

    //    @NotNull(message = "用户id不能为空！")
    private Integer userId ;

    private Integer collectionVideoId;//视频的大分类id

    @NotNull(message = "视频id不能为空！")
    private Integer videoId;//视频的具体id

    //    yyyy表示年  MM代表月份   dd代表日   HH代表小时   mm代表分钟  ss代表秒
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date collectionTime;//收藏时间

    private String category;//收藏分类

    private String state;//当前信息的状态（显示或者不显示）
}
