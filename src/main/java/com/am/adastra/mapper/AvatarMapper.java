package com.am.adastra.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    List<String> getAllDefault();

    String getByUid(Long uid);

    int updateAvatar(Long uid, String url);
}
