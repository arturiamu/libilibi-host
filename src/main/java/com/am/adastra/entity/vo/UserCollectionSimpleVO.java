package com.am.adastra.entity.vo;

import com.am.adastra.entity.Video;
import lombok.Data;

import java.util.Date;
import java.util.List;

/*
* 用户查询自己的收藏夹的时候返回的数据
* */
@Data
public class UserCollectionSimpleVO {
    private long id;//该条收藏的id
    private String categoryName;//收藏夹分类
    private Integer pid;//视频大分类PID
    private long aid;//视频aID
    private Date createTime;//收藏视频的时间()

    private List<Video> video;//视频信息

}
