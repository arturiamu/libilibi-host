package com.am.adastra.app;

import com.am.adastra.service.UserCollectionService;
import com.am.adastra.service.UserHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/*
* 缓存预热
* 开机的时候就将这里的东西加载在内存中
* */
@Component
@Slf4j
public class CachePreLoad implements ApplicationRunner {
    @Autowired
    UserCollectionService userCollectionService;
    @Autowired
    UserHistoryService userHistoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("CachePreLoad.run() 缓存预热启动");

        log.debug("准备执行收藏夹缓存预热....");
        userCollectionService.preloadCache();
        log.info("收藏夹缓存预热执行完成....");

        log.debug("准备执行历史记录缓存预热....");
        userHistoryService.preloadCache();
        log.info("历史记录缓存预热执行完成....");
    }
}
