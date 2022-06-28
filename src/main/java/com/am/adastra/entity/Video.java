package com.am.adastra.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/23 20:15
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @ApiModelProperty(value = "视频id", name = "aid", example = "3581515")
    private Long aid;
    @ApiModelProperty(value = "视频分类id", name = "pid", example = "11")
    private Integer pid;
    @ApiModelProperty(value = "视频类型id", name = "tid", example = "185")
    private Integer tid;
    @ApiModelProperty(value = "视频分类名", name = "tname", example = "国产剧")
    private String tname;
    @ApiModelProperty(value = "视频封面图片链接", name = "pic", example = "http://i1.hdslb.com/bfs/archive/960586894466f2e0fec9a344de8fd6cf8f527050.png")
    private String pic;
    @ApiModelProperty(value = "视频标题", name = "title", example = "【国产】亲爱的【28集全】")
    private String title;
    @ApiModelProperty(value = "视频描述", name = "desc", example = "田文军（黄渤 饰）和鲁晓娟（郝蕾 饰）曾是一对恩爱的夫妻，然而...")
    private String desc;
    @ApiModelProperty(value = "视频播放量", name = "view", example = "40107")
    private int view;
    @ApiModelProperty(value = "视频弹幕数", name = "danmaku", example = "2761")
    private int danmaku;
    @ApiModelProperty(value = "视频评论数", name = "reply", example = "1")
    private int reply;
    @ApiModelProperty(value = "收藏数", name = "favorite", example = "78")
    private int favorite;
    @ApiModelProperty(value = "投币数", name = "coin", example = "25")
    private int coin;
    @ApiModelProperty(value = "分享数", name = "share", example = "5")
    private int share;
    @ApiModelProperty(value = "点赞数", name = "like", example = "3564")
    private int like;
}
