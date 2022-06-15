package com.am.adastra.util.Interest;


import org.springframework.stereotype.Component;

import java.util.ArrayList;

/*
* 余弦相似度公式
* 通过计算两个向量的夹角余弦值来得到两个用户的相似度。
*
* 同时对数据进行清洗
* */
@Component
public class CosineSimilarity {

    static double bottom = 0.2;//分数低于多少看作0

    /* 计算两个用户的相似性*/
    static double check(double[] w, double[] v) {
        //1.过滤无效数据（将小于等于2的位置看作0，将等于0的位置忽略）
        //同时也将原数组中的无效数据剔除
        ArrayList<Double> copyW = new ArrayList<>();
        ArrayList<Double> copyV = new ArrayList<>();
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < w.length; i++) {
            if (w[i] == 0 || v[i] == 0) {
                continue;
            }
            if (w[i] <= bottom) {
                copyW.add((double) 0);
            } else {
                copyW.add(w[i]);
            }
            if (v[i] <= bottom) {
                copyV.add((double) 0);
            } else {
                copyV.add(v[i]);
            }
            w[i] = copyW.get(copyW.size() - 1);
            v[i] = copyV.get(copyV.size() - 1);
        }

        //2.利用余弦相似度公式计算两个用户之间的相似度
        double ans = 0;
        double ans1 = 0;
        double ans2 = 0;
        double ans3 = 0;
        for (int i = 0; i < copyV.size(); i++) {
            ans1 += (copyV.get(i) * copyW.get(i));
            ans2 += Math.pow(copyV.get(i), 2);
            ans3 += Math.pow(copyW.get(i), 2);
        }
        ans = ans1 / (Math.sqrt(ans2) * Math.sqrt(ans3));
        return ans;
    }
}
