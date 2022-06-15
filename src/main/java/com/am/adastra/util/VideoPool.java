package com.am.adastra.util;

import com.am.adastra.entity.Item;
import com.am.adastra.entity.Video;
import com.am.adastra.mapper.ItemMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.VideoService;
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
    public VideoService videoService;

    @Resource
    public ItemMapper itemMapper;

    @Resource
    public UserMapper userMapper;

    private static final List<List<Video>> VIDEO_POOL = new ArrayList<>();
    private static final Map<Integer, Integer> PID_INDEX = new HashMap<>();
    private static final List<Item> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        log.info("init video pool");
        that = this;
        that.itemMapper = this.itemMapper;
        that.userMapper = this.userMapper;
        that.videoService = this.videoService;
    }

    public static void run() {
        log.info("start load videos...");
        long st = System.currentTimeMillis();
        items.addAll(that.itemMapper.getAll());
        for (int i = 0; i < items.size(); i++) {
            PID_INDEX.put(items.get(i).getPid(), i);
        }
        int total = 0;
        for (Item item : items) {
            List<Video> videoList = that.videoService.getByPId(item.getPid());
            log.info("{} size {}", item, videoList.size());
            total += videoList.size();
            VIDEO_POOL.add(videoList);
        }
        log.info("total time:{}", (System.currentTimeMillis() - st) / 1000);
        log.info("total videos:{}", total);
        log.info("end load videos...");
    }

    public static int indexPid(int pid) {
        return PID_INDEX.get(pid);
    }

    public static List<Video> getPidVideo(int pid, int ps) {
        Set<Video> res = new HashSet<>();
        Random random = new Random();
        int pidIdx = indexPid(pid);
        int st = 0;
        if (ps < VIDEO_POOL.get(pidIdx).size()) {
            st = random.nextInt(VIDEO_POOL.get(pidIdx).size()) - ps;
        }
        if (st < 0) {
            st = 0;
        }
        while (ps-- > 0) {
            res.add(VIDEO_POOL.get(pidIdx).get(st++));
        }
        return new ArrayList<>(res);
    }
}
