package com.am.libilibi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/4/29 15:25
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    private String vid;
    private String uid;
    private String name;
    private String tittle;
    private String type;
    private String img;
    private Date r_time;
    private int view;
    private int barrage;
    private int like;
    private int coin;
    private int collect;
    private int share;
    private int comment;
    private Date c_time;
    private double hot;
}
