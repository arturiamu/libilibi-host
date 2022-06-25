package com.am.adastra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserDBO {
    private Long id;
    private String username;
    private String password;
    private String account;
    private String jsItems;
    private String state;
    private String createTime;

    public UserDBO(Long id, String username, String password, String account, String jsItems, String state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.account = account;
        this.jsItems = jsItems;
        this.state = state;
    }
}
