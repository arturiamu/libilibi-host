package com.am.adastra.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import netscape.javascript.JSObject;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/31 9:17
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDB {
    private Integer id;
    private String username;
    private String password;
    private String account;
    private String jsItems;
    private String state;
}
