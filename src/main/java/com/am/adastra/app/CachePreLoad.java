package com.am.adastra.app;

import com.am.adastra.service.UserCollectionService;
import com.am.adastra.service.UserHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private static final int cache_update = 10;
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

        log.warn("CachePreLoad.run() 缓存预热启动");
        Thread thread = new Thread(new Cate());
        thread.setDaemon(true);//设置为守护线程
        thread.start();
    }

    /**
     * 新建一个线程，用来定时更新redis中的的内容
     */
    static class Cate implements Runnable {

        @Override
        public void run() {
            log.warn("thread sleep:{}",cache_update);
            while (true) {
                log.info("start load cache");
                log.info("准备执行收藏夹缓存预热....");
                that.userCollectionService.preloadCache();
                log.info("收藏夹缓存预热执行完成....");
                log.info("准备执行历史记录缓存预热....");
                that.userHistoryService.preloadCache();
                log.info("历史记录缓存预热执行完成....");
                log.info("end load cache");
                VideoPool.CACHE = true;
                try {
                    Thread.sleep(1000L * 60 * cache_update);
                    log.info("thread sleep:{}",1000L * 60 * cache_update);
                } catch (InterruptedException ignored) {

                }
            }
        }
    }
}
