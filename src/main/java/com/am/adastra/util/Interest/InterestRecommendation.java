//package com.am.adastra.util.Interest;
//
//
//import com.am.adastra.entity.Item;
//import com.am.adastra.entity.Video;
//import com.am.adastra.entity.vo.UserCollectionSimpleVO;
//import com.am.adastra.mapper.ItemMapper;
//import io.swagger.models.auth.In;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//@Component
//@Slf4j
//public class InterestRecommendation {
//    @Autowired
//    private ItemMapper itemMapper;
//
//    static int historyValue = 1; //用户历史的价值
//    static int collectionValue = 2;  //用户收藏的价值
//    static int people = 5;//用户相似度排序后 ， 需要找出前多少名
//    private static int videNum = 3;//找出用户喜欢的视频类型后，推介几个视频类型给他
//    static double similarity = 0.1;//和当前用户相似度大于多少才参与排序
//    static double bottom = 0.2;//分数低于多少看作0
//    static int classify = 21;//20种影视分区
//    static HashMap<Integer, Integer> pidMap;
//
//     /** 处理数据的方法
//      *     number 前端需要多少条数据
//     *   userHistory当前用户的历史记录
//     *   userCollection当前用户的收藏夹记录
//     *   allUserHistory 所有用户的历史记录
//     *   allUserCollection所有用户的收藏记录
//     * */
//
//    public List<Integer> ProcessingData(int number,
//                                        List<Video> userHistory ,
//                                        List<UserCollectionSimpleVO> userCollection ,
//                                        List<List<Video>> allUserHistory ,
//                                        List<List<UserCollectionSimpleVO>> allUserCollection) {
//
//        System.out.println("当前用户的历史记录" + userHistory);
//        System.out.println("当前用户的收藏记录" + userCollection);
//        log.info("所有用户的历史记录");
//        for (List<Video> list : allUserHistory) {
//            System.out.println(list);
//        }
//        log.info("所有用户的收藏记录");
//        for (List<UserCollectionSimpleVO> list : allUserCollection) {
//            System.out.println(list);
//        }
//
////        1.得到所有的视频大分类id,将所有的视频pid作为键，数组下标作为值，映射到哈希表中
//        List<Item> allItem = itemMapper.getAll();
//        classify = allItem.size();
//        for (int i = 0; i < classify; i++) {
//            pidMap.put(allItem.get(i).getPid(),i);
//        }
//
//
//        //2.获取当前用户的用历史记录和收藏记录对应的分值
//        double[] USER = new double[classify];
//        for (Video history : userHistory) {
//            USER[pidMap.get(history.getPid())] += historyValue;
//        }
//        for (UserCollectionSimpleVO co : userCollection) {
//            USER[pidMap.get(co.getPid())] += collectionValue;
//        }
//
//        //3.获取所有的用户的用历史记录和收藏记录对应的分值
//        ArrayList<double[]> list = new ArrayList<>();
//
//      /*  for (int i = 0; i < allUserHistory.size(); i++) {
////            遇到当前用户就跳出本次循环
////            if (xxx){}
//            double[] otherUser = new double[classify];
//            for (UserHistory history : allHistoryList.get(i)) {
//                otherUser[history.getHistoryVideoId()] += historyValue;
//            }
//            for (UserCollection CollectionList : allCollectionList.get(i)) {
//                otherUser[CollectionList.getCollectionVideoId()] += collectionValue;
//            }
//            list.add(otherUser);
//        }*/
//
//        //3.数据分析返回最终结果
//        return recommend(USER, list);
//
//    }
//
//
//      /*
//      对数据分析进行推荐
//      */
//
//
//
//
//    public List<Integer> recommend(double[] USER, ArrayList<double[]> list) {
//        //1.获取用户对应视频的分数
////        double[] USER = {0.3,1.2,0.1,0,0};
////        ArrayList<double[]> list = new ArrayList<>();
//        double[] user0 = {0.3,1.2,0.1,7,9};
//        double[] user1 = {0.1,2,0,1,0};
//
//        list.add(user0);
//        list.add(user1);
//
//
////        2.计算USER和user1~3之间，两两之间的相似度
//        double[] num = new double[list.size()];
//        for (int i = 0; i < num.length; i++) {
//            num[i] = check(USER, list.get(i));
////            System.out.println(num[i]);
//        }
//
////        3.对用户相似度排序，找到相似度排行榜的前5名，并且相似度大于0.5
//        ArrayList<Integer> P = new ArrayList<>();//前5名的用户id
//        double[] copyNum = Arrays.copyOf(num, num.length);
//        for (int i = 0; i < people && i < copyNum.length; i++) {
//            int n = 0;
//            double max = -1;
//            for (int k = 0; k < copyNum.length; k++) {
//                if (copyNum[k] > max && copyNum[k] > similarity) {
//                    max = copyNum[k];
//                    n = k;
//                }
//            }
//            //找到了第i大的数据
//            if (max == -1) break;//没有可用值的时候
//            P.add(n);
//            copyNum[n] = 0;
//        }
//        System.out.println("前几名的用户id" + P);
//
////        4.计算出推荐给用户的视频id
//        double[] recommend = new double[USER.length];
//        for (int i = 0; i < USER.length; i++) {
//            for (int j = 0; j < P.size(); j++) {
//                recommend[i] += (list.get(P.get(j))[i] * num[P.get(j)]);
//            }
//            System.out.println(recommend[i]);
//        }
//
//        //5.将视频排序，推荐前几名的视频id给他
//        List<Integer> ans = new ArrayList<>();
//        for (int i = 0; i < videNum; i++) {
//            double maxRecommend = -1;
//            int number = -1;
//            for (int j = 0; j < recommend.length; j++) {
//                if (recommend[j] > maxRecommend) {
//                    maxRecommend = recommend[j];
//                    number = j;
//                }
//            }
//            if (number != -1) {
//                ans.add(number);
//                recommend[number] = 0;
//            }
//        }
//
//
//        return ans;
//    }
//
//
//    /* 计算两个用户的相似性*/
//
//
//    static double check(double[] w, double[] v) {
//        //1.过滤无效数据（将小于等于2的位置看作0，将等于0的位置忽略）
//        //同时也将原数组中的无效数据剔除
//        ArrayList<Double> copyW = new ArrayList<>();
//        ArrayList<Double> copyV = new ArrayList<>();
//        ArrayList<Double> list = new ArrayList<>();
//        for (int i = 0; i < w.length; i++) {
//            if (w[i] == 0 || v[i] == 0) {
//                continue;
//            }
//            if (w[i] <= bottom) {
//                copyW.add((double) 0);
//            } else {
//                copyW.add(w[i]);
//            }
//            if (v[i] <= bottom) {
//                copyV.add((double) 0);
//            } else {
//                copyV.add(v[i]);
//            }
//            w[i] = copyW.get(copyW.size() - 1);
//            v[i] = copyV.get(copyV.size() - 1);
//        }
//
//        //2.利用余弦相似度公式计算两个用户之间的相似度
//        double ans = 0;
//        double ans1 = 0;
//        double ans2 = 0;
//        double ans3 = 0;
//        for (int i = 0; i < copyV.size(); i++) {
//            ans1 += (copyV.get(i) * copyW.get(i));
//            ans2 += Math.pow(copyV.get(i), 2);
//            ans3 += Math.pow(copyW.get(i), 2);
//        }
//        ans = ans1 / (Math.sqrt(ans2) * Math.sqrt(ans3));
//        return ans;
//    }
//
//
//}
