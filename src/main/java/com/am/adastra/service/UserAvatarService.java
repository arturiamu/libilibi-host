package com.am.adastra.service;

import org.springframework.stereotype.Service;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 21:14
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Service
public interface UserAvatarService {
    int addAvatar(Long uid, String url);

    String getByUid(Long uid);

    int updateAvatar(Long uid, String url);
}
