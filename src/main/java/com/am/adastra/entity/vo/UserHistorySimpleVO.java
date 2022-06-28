package com.am.adastra.entity.vo;


import com.am.adastra.entity.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserHistorySimpleVO implements Serializable {
    @ApiModelProperty(value = "id",name = "id",example = "1")
    private int id;
    @ApiModelProperty(value = "用户id",name = "uid",example = "1")
    private Long uid;
    @ApiModelProperty(value = "浏览时间",name = "createTime",example = "2022-6-28 13:44:26")
    private LocalDateTime createTime;//浏览时间时间
    @ApiModelProperty(value = "视频",name = "video",example = "video")
    private Video video;

}
