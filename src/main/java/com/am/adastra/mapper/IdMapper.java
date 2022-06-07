package com.am.adastra.mapper;

import com.am.libilibi.entity.LBUser;
import com.am.libilibi.entity.LBUserCollection;
import com.am.libilibi.entity.LBUserHistory;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/7 10:26
 * @Description:
 */
public interface IdMapper {
    //获取用户选择信息
    LBUser getByChoice(int id);

    //获取用户历史信息
    LBUserHistory getByHistory(int id);

    //获取用户收藏信息
    LBUserCollection getByCollection(int id);
}
