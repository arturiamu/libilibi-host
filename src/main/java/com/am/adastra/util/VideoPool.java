package com.am.adastra.util;

import com.am.adastra.entity.DBVideo;
import com.am.adastra.mapper.DBVideoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/25 15:38
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Component
public class VideoPool {
    private static VideoPool videoPool;
    @Resource
    public DBVideoMapper dbVideoMapper;

    private static final List<List<DBVideo>> VIDEO_POOL = new ArrayList<>();
    private static final int[][] items = {
            {1, 24, 25, 47, 210, 86, 27},  // 动画
            {13, 33, 32, 51, 152},  // 番剧
            {167, 153, 168, 169, 195, 170},  // 国创
            {3, 28, 31, 30, 194, 59, 193, 29, 130, 243, 244},  // 音乐
            {129, 20, 198, 199, 200, 154, 156},  // 舞蹈
            {4, 17, 171, 172, 65, 173, 121, 136, 19},  // 游戏
            {36, 201, 124, 228, 207, 208, 209, 229, 122},  // 知识
            {188, 95, 230, 231, 232, 233},  // 科技
            {234, 235, 164, 236, 237, 238, 249},  // 运动
            {223, 176, 224, 245, 225, 240, 226, 227, 246, 247, 248},  // 汽车
            {160, 138, 239, 161, 162, 21, 250},  // 生活
//            {211, 76, 212, 213, 214, 215}, 美食
            {217, 218, 219, 220, 221, 222, 75},  // 动物
            {119, 22, 26, 126, 216, 127},  // 鬼畜
            {155, 157, 158, 159},  // 时尚
            {202, 203, 204, 205, 206},  // 资讯
            {5, 71, 241, 242, 137},  // 娱乐
            {181, 182, 183, 85, 184},  // 影视
            {177, 37, 178, 179, 180},  // 纪录片
            {23, 147, 145, 146, 83},  // 电影
            {11, 185, 187},  // 电视剧
    };

    @PostConstruct
    public void init() {
        System.out.println("init video pool");
        videoPool = this;
        videoPool.dbVideoMapper = this.dbVideoMapper;
    }

    public static void run() {
        System.out.println("start load videos...");
        for (int[] item : items) {
            List<DBVideo> itVideos = videoPool.dbVideoMapper.getByPId(String.valueOf(item[0]));
            VIDEO_POOL.add(itVideos);
        }
        System.out.println("end load videos...");
    }

    public static int indexPid(int pid) {
        int idx = -1;
        for (int[] item : items) {
            if (item[0] == pid)
                return idx + 1;
            idx += 1;
        }
        return idx;
    }

    public static List<DBVideo> getPidVideo(int pid, int ps) {
        Set<DBVideo> res = new HashSet<>();
        Random random = new Random();
        while (ps-- > 0) {
            int pidIdx = indexPid(pid);
            int vIdx = random.nextInt(VIDEO_POOL.get(pidIdx).size());
            res.add(VIDEO_POOL.get(pidIdx).get(vIdx));
        }
        return new ArrayList<>(res);
    }
}
