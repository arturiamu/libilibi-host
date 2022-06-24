package com.am.adastra.repository;

import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* 将用户的收藏信息添加到缓存中
* */
@Repository
public interface UserCollectionRedisRepository {
    /**
     * 收藏信息的数据前缀
     */
    String KEY_COLLECTION = "collection:item:";

    /**
     * 收藏信息的列表的数据前缀
     */
    String KEY_COLLECTION_LIST = "collection:list";

    /**
     * 将单条收藏数据传入到缓存中
     */
    void save(List<UserCollectionSimpleVO> collectionSimpleVOS);

    /**
     * 判断是否存在id对应的缓存
     * @param uid
     * @return
     */
    Boolean exists(Long uid);

    /**
     * 通过id获取用户的收藏记录
     * @return
     */
    List<UserCollectionSimpleVO>  getCollectionByUid(Long uid);


    /**
     * 将缓存中所有用户的收藏记录全部删除
     */
    void deleteAllCollection();
}
