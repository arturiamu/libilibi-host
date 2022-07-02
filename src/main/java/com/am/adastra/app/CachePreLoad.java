package com.am.adastra.app;

import com.am.adastra.service.UserCollectionService;
import com.am.adastra.service.UserHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;


/**
 * 缓存预热
 * 开机的时候就将这里的东西加载在内存中
 */
@Component
@Slf4j
public class CachePreLoad implements ApplicationRunner {
    @Autowired
    UserCollectionService userCollectionService;
    @Autowired
    UserHistoryService userHistoryService;
    public static CachePreLoad that;

    @PostConstruct
    public void init() {
        log.warn("init video CachePreLoad");
        that = this;
        that.userCollectionService = this.userCollectionService;
        that.userHistoryService = this.userHistoryService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("CachePreLoad.run() 缓存预热启动");
        Thread thread = new Thread(new Cate());
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 新建一个线程，用来定时更新redis中的的内容
     */
    static class Cate implements Runnable {

        @Override
        public void run() {
            while (true) {
                log.warn("start load cache");
                log.warn("准备执行收藏夹缓存预热....");
                that.userCollectionService.preloadCache();
                log.warn("收藏夹缓存预热执行完成....");
                log.warn("准备执行历史记录缓存预热....");
                that.userHistoryService.preloadCache();
                log.warn("历史记录缓存预热执行完成....");
                log.warn("end load cache");
                VideoPool.CACHE = true;
                //线程睡眠10分钟然后重新获取用户的爱好内容
                try {
                    Thread.sleep(1 * 1000 * 60 * 10);
                } catch (InterruptedException ignored) {

                }
            }
        }
    }
}
