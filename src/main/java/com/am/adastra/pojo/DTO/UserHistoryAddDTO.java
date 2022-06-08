package com.am.adastra.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
/*
 * 外部数据进入后台用DTO
 * */
@Data
public class UserHistoryAddDTO implements Serializable {
    private int id;
    //    @NotNull(message = "用户id不能为空！")
    private Integer userId;

    private Integer historyVideoId;//视频的大分类id

    @NotNull(message = "视频id不能为空！")
    private Integer VideoId;

    //    yyyy表示年  MM代表月份   dd代表日   HH代表小时   mm代表分钟  ss代表秒
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date time;//浏览时间时间

    private String state;//当前信息的状态（显示或者不显示）
}
