package com.am.adastra.repository.Impl;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import com.am.adastra.repository.UserHistoryRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserHistoryRedisRepositoryImpl implements UserHistoryRedisRepository {

    @Autowired(required = false)
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 将单条收藏数据传入到缓存中
     */
    @Override
    public void save(Long uid ,List<Video> videoList) {
        String key = KEY_HISTORY + uid;
        redisTemplate.opsForValue().set(key, (Serializable) videoList);
    }

    /**
     * 判断是否存在id对应的缓存
     * @param uid
     * @return
     */
    @Override
    public Boolean exists(Long uid) {
        String key = KEY_HISTORY + uid;
        return redisTemplate.hasKey(key);
    }

    /**
     * 通过用户id拿到用户的历史记录
     * @param uid
     * @return
     */
    @Override
    public List<Video> getHistoryByUid(Long uid) {
        String key = KEY_HISTORY + uid;
        //从缓存中拿到数据
        Serializable result = redisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        } else {
            List<Video> Collection = (List<Video>) result;
            return Collection;
        }
    }


    /**
     * 将缓存中所有用户的历史记录全部删除
     */
    @Override
    public void deleteAllHistory() {
        //先获取要删除的所有的用户收藏的名称
        Set<String> keys = redisTemplate.keys(KEY_HISTORY + "*");
        //删除这些keys值中对应的所有的缓存
        redisTemplate.delete(keys);
    }
}
