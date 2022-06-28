package com.am.adastra.entity.vo;

import com.am.adastra.entity.Video;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/*
 * 用户查询自己的收藏夹的时候返回的数据
 * */
@Data
public class UserCollectionSimpleVO {
    @ApiModelProperty(value = "id", name = "id", example = "1")
    private long id;//该条收藏的id
    @ApiModelProperty(value = "收藏夹名字", name = "categoryName", example = "收藏夹1")
    private String categoryName;//收藏夹分类
    @ApiModelProperty(value = "用户id", name = "uid", example = "1")
    private Long uid;//
    @ApiModelProperty(value = "收藏时间", name = "createTime", example = "2022-6-28 13:43:19")
    private Date createTime;//收藏视频的时间()
    @ApiModelProperty(value = "视频", name = "video", example = "video")
    private List<Video> video;//视频信息

}
