package com.am.adastra.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 21:11
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
public interface AvatarMapper {
    int addAvatar(Long uid, String url);

    String getByUid(Long uid);

    int updateAvatar(Long uid, String url);
}
