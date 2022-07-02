package com.am.adastra.service.impl;

import com.am.adastra.entity.User;
import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserHistorySimpleVO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.mapper.UserHistoryMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.repository.UserHistoryRedisRepository;
import com.am.adastra.service.UserHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 13:43
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Component
@Slf4j
public class UserHistoryServiceImpl implements UserHistoryService {
    @Autowired
    UserMapper userMapper;
    @Resource
    UserHistoryMapper userHistoryMapper;
    @Autowired
    UserHistoryRedisRepository userHistoryRedisRepository;

    /**
     * 通过用户id获取该用户所有的历史记录
     *
     * @param uid
     * @return
     */
    public List<Video> getAll(Long uid) {
        //1.先判断该用户的历史记录是否在缓存中
        Boolean exists = userHistoryRedisRepository.exists(uid);
        if (exists) {
            //用户的历史在缓存中
            //查询用户的历史记录，然后返回
            List<Video> videoList = userHistoryRedisRepository.getHistoryByUid(uid);
            return videoList;
        }
        //2.历史记录不在缓存中，我们直接从mapper中调用方法获取，然后添加到缓存中
        List<Video> userHistory = userHistoryMapper.getAll(uid);
        userHistoryRedisRepository.save(uid, userHistory);
        return userHistory;
    }

    @Override
    public List<Video> getLimitByDate(Long uid, Date date) {
        return userHistoryMapper.getLimitByDate(uid, date);
    }

    @Override
    public List<UserHistorySimpleVO> getLimit(Long uid, Integer ps) {
        if (ps > 100) {
            ps = 100;
        }
        return userHistoryMapper.getLimit(uid, ps);
    }

    @Override
    public boolean add(VideoOperateDTO videoOperateDTO) {
        return userHistoryMapper.add(videoOperateDTO) == 1;
    }

    @Override
    public boolean del(Long id) {
        return userHistoryMapper.del(id) == 1;
    }

    @Override
    public boolean clear(Long uid) {
        return userHistoryMapper.clear(uid) >= 0;
    }

    @Override
    public boolean fakeDel(Long id) {
        return userHistoryMapper.fakeDel(id) >= 0;
    }

    @Override
    public boolean fakeClear(Long uid) {
        return userHistoryMapper.fakeClear(uid) >= 0;
    }

    /**
     * 预热类别数据的缓存
     * 将用户的收藏记录预加载到缓存中
     */
    @Override
    public void preloadCache() {
        //1.删除缓存中所有的用户的历史记录信息
        log.info("删除缓存中所有的用户的历史记录信息");
        userHistoryRedisRepository.deleteAllHistory();
        //2.从数据库中查询出用户的收藏夹记录，添加到缓存中
        //2.1查询出所有用户
        List<UserVO> userList = userMapper.list();
        log.info("缓存预热,将所有用户历史添加到缓存中 ");
        //2.2通过所有用户的uid将他们的收藏记录写入到缓存中
        for (UserVO user : userList) {
            Long uid = user.getId();
            log.info("当前用户id:{}", uid);
            List<Video> userHistory = userHistoryMapper.getAll(uid);
            userHistoryRedisRepository.save(user.getId(), userHistory);
        }
        log.info("添加用户历史到缓存完成");
    }
}
