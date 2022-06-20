package com.am.adastra.service;


import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserCollectionService {

    void add(VideoOperateDTO videoOperateDTO);

    /**
     * 通过用户id和用户的收藏夹分类信息得到用户该收藏夹内的收藏信息
     * @param userId
     * @param category
     * @return
     */
    List<UserCollectionSimpleVO> selectByCollection(Long userId, String category);

    /**
     * 通过用户id得到用户所有的收藏记录
     * @param userId
     * @return
     */
    Map<String ,List<UserCollectionSimpleVO>> selectCategory(Long userId);

    /**
     * 通过用户id得到用户所有的收藏夹记录
     * @param uid
     * @return
     */
    List<UserCollectionSimpleVO> selectById(long uid);

    /**
     * 预热类别数据的缓存
     * 将用户的收藏记录预加载到缓存中
     */
    void preloadCache();
}
