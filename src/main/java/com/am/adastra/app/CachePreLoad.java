package com.am.adastra.app;

import com.am.adastra.service.UserCollectionService;
import com.am.adastra.service.UserHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * 缓存预热
 * 开机的时候就将这里的东西加载在内存中
 */
@Component
@Slf4j
public class CachePreLoad implements ApplicationRunner {
    @Autowired
    private static UserCollectionService userCollectionService;
    @Autowired
    private static UserHistoryService userHistoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {


        System.out.println("CachePreLoad.run() 缓存预热启动");
        new Thread(new Cate()).start();


    }

    /**
     * 新建一个线程，用来定时更新redis中的的内容
     */
    static class Cate implements Runnable {

        @Override
        public void run() {
            while (true){
                log.debug("准备执行收藏夹缓存预热....");
                userCollectionService.preloadCache();
                log.info("收藏夹缓存预热执行完成....");

                log.debug("准备执行历史记录缓存预热....");
                userHistoryService.preloadCache();
                log.info("历史记录缓存预热执行完成....");

                //程序睡眠10分钟然后重新获取用户的爱好内容
                try {
                    Thread.sleep(1 * 1000 * 60 * 10);
                } catch (InterruptedException ignored) {

                }
            }
        }
    }
}
