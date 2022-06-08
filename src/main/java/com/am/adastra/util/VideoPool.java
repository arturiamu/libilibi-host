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
    private static final Map<Integer, Integer> PID_INDEX = new HashMap<>();

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
        for (Item item : itemList) {
            List<Video> videos = that.videoMapper.getByPId(item.getPid());
            System.out.println(item + " size: " + videos.size());
            for (Video video : videos) {
                that.videosUtilsRedis.setVideAll(video);
            }
        }
//        for (Item item : itemList) {
//            List<Video> list = new ArrayList<>();
//            VIDEO_POOL.add(list);
//        }
//        System.out.println(System.currentTimeMillis());
//        List<Video> videoList = that.videosUtilsRedis.getAllVideo();
//        for (Video video : videoList) {
//            int pid = video.getPid();
//            VIDEO_POOL.get(indexPid(pid)).add(video);
//        }
//        System.out.println(videoList.size());
//        System.out.println(System.currentTimeMillis());
//        System.out.println("end load videos...");
    }

    public static int indexPid(int pid) {
        return PID_INDEX.get(pid);
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
