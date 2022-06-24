package com.am.adastra.repository;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;

import java.util.List;

/**
 * 将用户的所有历史信息添加到缓存中
 */
public interface UserHistoryRedisRepository {
    /**
     * 收藏信息的数据前缀
     */
    String KEY_HISTORY = "history:item:";
    /**
     * 将单条收藏数据传入到缓存中
     */
    void save(Long uid ,List<Video> videoList);

    /**
     * 判断是否存在id对应的缓存
     * @param uid
     * @return
     */
    Boolean exists(Long uid);

    /**
     * 通过id获取用户的历史记录
     * @return
     */
    List<Video>  getHistoryByUid(Long uid);


    /**
     * 将缓存中所有用户的历史记录全部删除
     */
    void deleteAllHistory();
}
