package com.am.libilibi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/4 18:55
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralVideo {
    private String bvid;
    private String pic;
    private String title;
    private String tid;
    private String duration;  // 时长
    private String desc;
    private Stat stat = new Stat();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Stat {
        private String aid;
        private String view;
        private String danmaku;
        private String reply;  // 评论数
        private String favorite;  // 收藏数
        private String coin;
        private String share;  // 分享数
        private String like;  // 获赞数
    }
}
