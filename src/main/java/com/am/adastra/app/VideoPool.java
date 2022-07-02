package com.am.adastra.app;

import com.am.adastra.entity.Item;
import com.am.adastra.entity.Video;
import com.am.adastra.mapper.AvatarMapper;
import com.am.adastra.mapper.ItemMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class VideoPool implements ApplicationRunner {
    public static VideoPool that;

    @Resource
    public VideoService videoService;

    @Resource
    public ItemMapper itemMapper;

    @Resource
    public UserMapper userMapper;

    @Resource
    public AvatarMapper avatarMapper;

    private static final List<List<Video>> VIDEO_POOL = new ArrayList<>();
    private static final Map<Integer, Integer> PID_INDEX = new HashMap<>();
    public static final List<Item> items = new ArrayList<>();
    public static final List<String> DEFAULT_AVATAR = new ArrayList<>();
    public static boolean CACHE = false;

    @PostConstruct
    public void init() {
        log.warn("init video pool");
        that = this;
        that.itemMapper = this.itemMapper;
        that.userMapper = this.userMapper;
        that.videoService = this.videoService;
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

    public static List<Video> getRandom(int ps) {
        List<Video> list = new ArrayList<>();
        Random random = new Random();
        while (ps-- > 0) {
            int idx = random.nextInt(items.size());
            int cnt = random.nextInt(VIDEO_POOL.get(idx).size());
            list.add(VIDEO_POOL.get(idx).get(cnt));
        }
        return list;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.warn("start load videos...");
        long st = System.currentTimeMillis();
        items.addAll(that.itemMapper.getAll());
        for (int i = 0; i < items.size(); i++) {
            PID_INDEX.put(items.get(i).getPid(), i);
        }
        log.warn("total items:{}",items.size());
        int total = 0;
        for (Item item : items) {
            List<Video> videoList = that.videoService.getByPId(item.getPid());
            log.warn("{} size {}", item, videoList.size());
            total += videoList.size();
            VIDEO_POOL.add(videoList);
        }
        log.warn("total time:{}", (System.currentTimeMillis() - st) / 1000);
        log.warn("total videos:{}", total);
        log.warn("end load videos...");
        List<String> allDefault = avatarMapper.getAllDefault();
        DEFAULT_AVATAR.addAll(allDefault);
    }
}
