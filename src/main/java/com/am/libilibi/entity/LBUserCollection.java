package com.am.libilibi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LBUserCollection {
    private Integer id;

    @NotNull(message = "用户id不能为空！")
    private Integer userId ;

    @NotNull(message = "视频id不能为空！")
    private Integer collectionVideoId;

    //    yyyy表示年  MM代表月份   dd代表日   HH代表小时   mm代表分钟  ss代表秒
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date collectionTime;//收藏时间

    private String category;//收藏分类

}
