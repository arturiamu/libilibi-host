package com.am.libilibi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/18 10:10
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LBPlayVideo {
    String aid;
    String tittle;
    String view;
    String danmaku;
    String favorite;
    String like;
    String coin;
    String share;
    String desc;
    ArrayList<LBDisplayVideo> recommends;
}
