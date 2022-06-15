package com.am.adastra.util.Interest;


import com.am.adastra.entity.Item;
import com.am.adastra.entity.Video;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import com.am.adastra.mapper.ItemMapper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class InterestRecommendation {
    @Autowired
    private ItemMapper itemMapper;//通过pid随机获取aid

    @Autowired
    private DataAnalysis dataAnalysis;

    static int classify = 21;//20种影视分区
    static HashMap<Integer, Integer> pidMap = new HashMap<>();

     /** 处理数据的方法
      *     number 前端需要多少条数据
     *   userHistory当前用户的历史记录
     *   userCollection当前用户的收藏夹记录
     *   allUserHistory 所有用户的历史记录
     *   allUserCollection所有用户的收藏记录
     * */

    public List<Integer> ProcessingData(int number,
                                        List<Video> userHistory ,
                                        List<UserCollectionSimpleVO> userCollection ,
                                        List<List<Video>> allUserHistory ,
                                        List<List<UserCollectionSimpleVO>> allUserCollection) {
        log.info("当前用户的历史记录 --> " + userHistory);
        log.info("当前用户的收藏记录 --> " + userCollection);
        log.info("所有用户的历史记录 --> " + allUserHistory);
        log.info("所有用户的收藏记录 --> " + allUserCollection);


//        1.得到所有的视频大分类id,将所有的视频pid作为键，数组下标作为值，映射到哈希表中
        List<Item> allItem = itemMapper.getAll();
        log.info("所有的用户基本信息" + allItem.toString());
        classify = allItem.size();
        for (int i = 0; i < classify; i++) {
            pidMap.put(allItem.get(i).getPid(),i);
        }


//        2.得到推荐的大分类pid
        List<Integer> re = dataAnalysis.recommend(pidMap,userHistory, userCollection, allUserHistory, allUserCollection);
        log.info("分析得到的视频推荐大分类pid" + re.toString());

//        3.将得到的视频大分类id转化为具体的视频pid
        log.info("所有的用户基本信息 --> " + allItem.toString());
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < re.size(); i++) {
            ans.add(allItem.get(re.get(i)).getPid());
        }

        //4.数据分析返回最终结果
        return ans;

    }








}
