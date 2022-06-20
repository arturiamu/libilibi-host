package com.am.adastra.service;

import com.am.adastra.entity.Video;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserHistoryTest {
    @Autowired
    UserHistoryService userHistoryService;

    @Test
    public void getAllTest(){
        long uid = 11;
        List<Video> videoList = userHistoryService.getAll(uid);
        for (int i = 0; i < videoList.size(); i++) {
            log.info(videoList.get(i).toString());
        }

    }
}
