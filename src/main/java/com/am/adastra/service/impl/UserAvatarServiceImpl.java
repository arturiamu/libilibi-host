package com.am.adastra.service.impl;

import com.am.adastra.mapper.AvatarMapper;
import com.am.adastra.service.UserAvatarService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 21:15
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
@Component
public class UserAvatarServiceImpl implements UserAvatarService {
    @Resource
    AvatarMapper avatarMapper;

    @Override
    public int addAvatar(Long uid, String url) {
        return avatarMapper.addAvatar(uid, url);
    }

    @Override
    public List<String> getAllDefault() {
        return avatarMapper.getAllDefault();
    }

    @Override
    public String getByUid(Long uid) {
        return avatarMapper.getByUid(uid);
    }

    @Override
    public int updateAvatar(Long uid, String url) {
        return avatarMapper.updateAvatar(uid, url);
    }
}
