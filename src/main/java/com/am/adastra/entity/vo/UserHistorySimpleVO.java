package com.am.adastra.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserHistorySimpleVO implements Serializable {
    private int id;

    private Integer historyVideoId;//视频的大分类id

    private Integer VideoId;

    //    yyyy表示年  MM代表月份   dd代表日   HH代表小时   mm代表分钟  ss代表秒
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date time;//浏览时间时间

}
