package com.am.adastra.entity;

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
public class DBVideo {
    private long aid;
    private int pid;
    private int tid;
    private String tname;
    private String pic;
    private String title;
    private String desc;
    private int view;
    private int danmaku;
    private int reply;
    private int favorite;
    private int coin;
    private int share;
    private int like;
}
