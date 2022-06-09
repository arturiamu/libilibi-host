package com.am.adastra.service.impl;

import com.am.adastra.entity.User;
import com.am.adastra.entity.UserCollection;
import com.am.adastra.entity.UserHistory;
import com.am.adastra.mapper.UserCollectionMapper;
import com.am.adastra.mapper.UserHistoryMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.InterestService;
import com.am.adastra.service.UserCollectionService;
import com.am.adastra.service.UserHistoryService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Interest.InterestRecommendation;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class InterestServiceImpl implements InterestService {
    @Autowired(required = false)
    private UserHistoryMapper userHistoryMapper;
    @Autowired(required = false)
    private UserCollectionMapper userCollectionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private InterestRecommendation recommendation;//兴趣推荐的自动装配


    /*
     * 通过用户id查询用户的兴趣推荐
     * */
    @Override
    public Result<ArrayList<Integer>> list(Integer userId) {
        //2.获取当前用户的关看历史和收藏夹
        //2.1获取当前用户的历史记录
        List<UserHistory> historyList = userHistoryMapper.selectById(userId);
        log.info("当前用户的历史记录 ：{}", historyList);
        //2.2获取当前用户的收藏夹记录
        List<UserCollection> collectionList = userCollectionMapper.selectById(userId);
        log.info("当前用户的收藏夹记录 ：{}", collectionList);
        //3.获取所有人的观看历史和收藏夹记录
        //3.1获取所有用户的信息（为了遍历获取用户的历史记录和收藏）
        List<User> AllUserList = userMapper.list();
        //3.2遍历获取所有用户的历史信息和收藏记录
        List<List<UserHistory>> AllHistoryList = new ArrayList<>();
        List<List<UserCollection>> AllCollectionList = new ArrayList<>();
        for (User user : AllUserList) {
            //匹配到当前用户就跳出当前循环
            if (userId == user.getId()) {
                continue;
            }
            //匹配到不是当前用户就进入
//            如果当前用户的历史记录和收藏记录都为空就跳出本层循环
            List<UserHistory> otherUserHistory = userHistoryMapper.selectById(user.getId());
            List<UserCollection> otherUserCollection = userCollectionMapper.selectById(user.getId());
            if (otherUserCollection == null && otherUserHistory == null) {
                continue;
            }
            AllHistoryList.add(otherUserHistory);
            AllCollectionList.add(otherUserCollection);
        }

        //4.将当前用户信息和所有的用户用户信息给算法
        /*
         *   historyList当前用户的历史记录
         *   collectionList当前用户的收藏夹记录
         *   AllHistoryList所有用户的信息
         *   AllCollectionList所有用户的收藏记录
         * */
        List<Integer> video = recommendation.ProcessingData(historyList, collectionList, AllHistoryList, AllCollectionList);

        log.info("通过算法推荐的视频id ：{}", video);

        return null;
    }
}
