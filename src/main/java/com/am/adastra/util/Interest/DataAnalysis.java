package com.am.adastra.util.Interest;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Component
public class DataAnalysis {
    @Autowired
    private CosineSimilarity cosineSimilarity;//计算两个用户的相似性

    static int historyValue = 1; //用户历史的价值
    static int collectionValue = 2;  //用户收藏的价值
    static int people = 5;//用户相似度排序后 ， 需要找出前多少名
    private static int videNum = 3;//找出用户喜欢的视频类型后，推介几个视频类型给他
    static double similarity = 0.1;//和当前用户相似度大于多少才参与排序

    static int classify = 21;//20种影视分区
    static HashMap<Integer, Integer> pidMap = new HashMap<>();


    /*
      对数据分析进行推荐
     */
    public List<Integer> recommend(HashMap<Integer, Integer> pidMap, List<Video> userHistory,
                                   List<UserCollectionSimpleVO> userCollection,
                                   List<List<Video>> allUserHistory,
                                   List<List<UserCollectionSimpleVO>> allUserCollection) {
        //1.从请求参数中得到pid和数组下标的映射关系
        this.pidMap = pidMap;


        //2.获取当前用户的用历史记录和收藏记录对应的分值
        double[] USER = new double[classify];
        for (Video history : userHistory) {
            USER[DataAnalysis.pidMap.get(history.getPid())] += historyValue;
        }
        for (UserCollectionSimpleVO co : userCollection) {
            List<Video> videoList = co.getVideo();
            for (int i = 0; i < videoList.size(); i++) {
                USER[DataAnalysis.pidMap.get(videoList.get(i).getPid())] += collectionValue;
            }

        }

        //3.获取所有的用户的用历史记录和收藏记录对应的分值
        ArrayList<double[]> list = new ArrayList<>();
        for (int i = 0; i < allUserCollection.size(); i++) {
            double[] otherUser = new double[classify];
            for (Video video : allUserHistory.get(i)) {
                otherUser[DataAnalysis.pidMap.get(video.getPid())] += historyValue;
            }
            for (UserCollectionSimpleVO co : allUserCollection.get(i)) {
                List<Video> videoList = co.getVideo();
                for (int j = 0; j < videoList.size(); j++) {
                    otherUser[DataAnalysis.pidMap.get(videoList.get(j).getPid())] += collectionValue;
                }
            }
            list.add(otherUser);
        }


//        4.计算USER和list之间，两两之间的相似度
        double[] num = new double[list.size()];
        for (int i = 0; i < num.length; i++) {
            num[i] = cosineSimilarity.check(USER, list.get(i));
        }

//        5.对用户相似度排序，找到相似度排行榜的前5名，并且相似度大于0.5
        ArrayList<Integer> P = new ArrayList<>();//前5名的用户id
        double[] copyNum = Arrays.copyOf(num, num.length);
        for (int i = 0; i < people && i < copyNum.length; i++) {
            int n = 0;
            double max = -1;
            for (int k = 0; k < copyNum.length; k++) {
                if (copyNum[k] > max && copyNum[k] > similarity) {
                    max = copyNum[k];
                    n = k;
                }
            }
            //找到了第i大的数据
            if (max == -1) break;//没有可用值的时候
            P.add(n);
            copyNum[n] = 0;
        }
//        6.计算出推荐给用户的视频id
        double[] recommend = new double[USER.length];
        for (int i = 0; i < USER.length; i++) {
            for (int j = 0; j < P.size(); j++) {
                recommend[i] += (list.get(P.get(j))[i] * num[P.get(j)]);
            }
        }

        //7.将视频排序，推荐前几名的视频id给他
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < videNum; i++) {
            double maxRecommend = 0;
            int number = -1;
            for (int j = 0; j < recommend.length; j++) {
                if (recommend[j] > maxRecommend) {
                    maxRecommend = recommend[j];
                    number = j;
                }
            }
            if (number != -1) {
                ans.add(number);
                recommend[number] = 0;
            }
        }
        return ans;
    }
}
