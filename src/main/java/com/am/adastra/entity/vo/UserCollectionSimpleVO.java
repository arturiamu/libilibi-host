package com.am.adastra.entity.vo;

import lombok.Data;

import java.util.Date;

/*
* 用户查询自己的收藏夹的时候返回的数据
* */
@Data
public class UserCollectionSimpleVO {
    private Integer id;//该条收藏的id
    private String category;//收藏夹分类
    private Integer videoId;//视频PID
    private Date collectionTime;//收藏视频的时间
}
