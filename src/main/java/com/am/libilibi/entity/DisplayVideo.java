package com.am.libilibi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/17 15:25
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayVideo {
    String aid;
    String tid;
    String pic;
    String tittle;
    String view;
    String danmaku;
}
