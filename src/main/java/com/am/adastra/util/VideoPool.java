package com.am.adastra.util;

import com.am.adastra.entity.Item;
import com.am.adastra.entity.Video;
import com.am.adastra.mapper.ItemMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.mapper.VideoMapper;
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
    private static VideoPool that;
    @Resource
    public VideoMapper videoMapper;

    @Resource
    public ItemMapper itemMapper;

    @Resource
    public UserMapper userMapper;

    @Resource
    public VideosUtilsRedis videosUtilsRedis;

    private static final List<List<Video>> VIDEO_POOL = new ArrayList<>();
    private static final Map<Integer, Integer> POD_INDEX = new HashMap<>();

    @PostConstruct
    public void init() {
        System.out.println("init video pool");
        that = this;
        that.videoMapper = this.videoMapper;
        that.itemMapper = this.itemMapper;
        that.userMapper = this.userMapper;
    }

    public static void run() {
        System.out.println("start load videos...");
        List<Item> itemList = that.itemMapper.getAll();
        int total = 0;
        for (int i = 0; i < itemList.size(); i++) {
            List<Video> itVideos = that.videoMapper.getByPId(itemList.get(i).getPid());
            POD_INDEX.put(itVideos.get(0).getPid(), i);
            total += itVideos.size();
            System.out.println(itemList.get(i) + " size: " + itVideos.size());
            VIDEO_POOL.add(itVideos);
        }
        System.out.println("total video : " + total);
        System.out.println("end load videos...");

        System.out.println("remove videos...");
        for (List<Video> videoList : VIDEO_POOL) {
            for (Video video : videoList) {
                that.videosUtilsRedis.setVideAll(video);
                System.out.println(total--);
            }
        }
        System.out.println("remove videos done...");
    }

    public static int indexPid(int pid) {
        return POD_INDEX.get(pid);
    }

    public static List<Video> getPidVideo(int pid, int ps) {
        Set<Video> res = new HashSet<>();
        Random random = new Random();
        while (ps-- > 0) {
            int pidIdx = indexPid(pid);
            int vIdx = random.nextInt(VIDEO_POOL.get(pidIdx).size());
            res.add(VIDEO_POOL.get(pidIdx).get(vIdx));
        }
        return new ArrayList<>(res);
    }
}
