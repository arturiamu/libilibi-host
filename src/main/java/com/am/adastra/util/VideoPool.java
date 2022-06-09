package com.am.adastra.util;

import com.am.adastra.entity.Item;
import com.am.adastra.entity.Video;
import com.am.adastra.mapper.ItemMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.mapper.VideoMapper;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        log.info("init video pool");
        that = this;
        that.videoMapper = this.videoMapper;
        that.itemMapper = this.itemMapper;
        that.userMapper = this.userMapper;
    }

    public static void run() {
        log.info("start load videos...");
        long st = System.currentTimeMillis();
        List<Item> items = that.itemMapper.getAll();
        for (int i = 0; i < items.size(); i++) {
            PID_INDEX.put(items.get(i).getPid(), i);
        }
        int total = 0;
        for (Item item : items) {
            List<Video> videoList = that.videoMapper.getByPId(item.getPid());
            log.info("{} size {}", item, videoList.size());
            total += videoList.size();
            VIDEO_POOL.add(videoList);
        }
        System.out.println((System.currentTimeMillis() - st) / 1000);
        System.out.println(total);
        System.out.println("end load videos...");
        log.info("end load videos...");
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
