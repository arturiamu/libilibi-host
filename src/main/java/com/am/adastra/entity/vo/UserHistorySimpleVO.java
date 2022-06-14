package com.am.adastra.entity.vo;


import com.am.adastra.entity.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserHistorySimpleVO implements Serializable {
    private int id;
    private Integer pid;//视频的大分类id
    private Integer aid;//当前视频具体id
    //    yyyy表示年  MM代表月份   dd代表日   HH代表小时   mm代表分钟  ss代表秒
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;//浏览时间时间

    private Video videoList;

}
