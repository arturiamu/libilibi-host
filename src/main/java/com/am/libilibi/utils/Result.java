package com.am.libilibi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.am.libilibi.entity.GeneralVideo;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/1 17:48
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String message;
    private int status_code;
    private List<GeneralVideo> data;
}
