package com.am.adastra.repository.Impl;

import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import com.am.adastra.repository.UserCollectionRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserCollectionRedisRepositoryImpl implements UserCollectionRedisRepository {

    @Autowired(required = false)
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 将单条收藏数据传入到缓存中
     */
    @Override
    public void save(List<UserCollectionSimpleVO> collectionSimpleVOS) {
        if (collectionSimpleVOS.size() == 0)return;
        String key = KEY_COLLECTION +collectionSimpleVOS.get(0).getUid();
        redisTemplate.opsForValue().set(key, (Serializable) collectionSimpleVOS);
    }

    /**
     * 判断是否存在id对应的缓存
     *
     * @param uid
     * @return
     */
    @Override
    public Boolean exists(Long uid) {
        String key = KEY_COLLECTION + uid;
        return redisTemplate.hasKey(key);
    }

    /**
     * * 通过id获取用户的收藏记录
     *
     * @param uid
     * @return
     */
    @Override
    public List<UserCollectionSimpleVO> getCollectionByUid(Long uid) {
        String key = KEY_COLLECTION + uid;
        //从缓存中拿到数据
        Serializable result = redisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        } else {
            List<UserCollectionSimpleVO> Collection = (List<UserCollectionSimpleVO>) result;
            return Collection;
        }
    }

    /**
     * 将缓存中所有用户的收藏记录全部删除
     */
    @Override
    public void deleteAllCollection() {
        //先获取要删除的所有的用户收藏的名称
        Set<String> keys = redisTemplate.keys(KEY_COLLECTION + "*");
        //删除这些keys值中对应的所有的缓存
        redisTemplate.delete(keys);
    }


}
