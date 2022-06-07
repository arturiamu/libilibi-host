package com.am.libilibi.mapper;

import com.am.adastra.entity.User;
import com.am.libilibi.entity.User;
import com.am.libilibi.entity.UserCollection;
import com.am.libilibi.entity.UserHistory;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/7 10:26
 * @Description:
 */
public interface IdMapper {
    //获取用户选择信息
    User getByChoice(int id);

    //获取用户历史信息
    UserHistory getByHistory(int id);

    //获取用户收藏信息
    UserCollection getByCollection(int id);
}
