package com.am.adastra.service.impl;

import com.am.adastra.entity.User;
import com.am.adastra.entity.Video;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import com.am.adastra.mapper.UserCollectionMapper;
import com.am.adastra.mapper.UserHistoryMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.UserCollectionService;
import com.am.adastra.service.UserInterestRecommendService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class UserInterestRecommendServiceImpl implements UserInterestRecommendService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserHistoryMapper historyMapper;
    @Autowired(required = false)
    UserCollectionMapper collectionMapper;



    @Override
    public Result<List<Video>> list(Long uid,int number) {
//        1.找到该用户的历史记录和收藏记录的大分类pid
        List<Video> userHistory = historyMapper.getAll(uid);
        List<UserCollectionSimpleVO> userCollection = collectionMapper.selectById(uid);
        log.info("当前用户历史" + userHistory);
        log.info("当前用户收藏信息" + userCollection);

//        2.找到所有用户
        List<User> allUser = userMapper.list();

//        3.通过所有用户查询出所有用户的历史信息和收藏记录，取出大分类pid
        List<List<Video>> allUserHistory = new ArrayList<>();
        List<List<UserCollectionSimpleVO>> allUserCollection = new ArrayList<>();
        for (int i = 0; i < allUser.size(); i++) {
            Long id = allUser.get(i).getId();
            allUserHistory.add(historyMapper.getAll(id));
            allUserCollection.add(collectionMapper.selectById(id));
        }

//        4.将得到的数据传入数据处理工具，分析后返回推荐的视频


        return null;
    }
}















