package com.am.adastra.service;

import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserCollectionTest {
    @Autowired
    UserCollectionService userCollectionService;

    @Test
    public void TestSelectById(){
        Long uid = 21L;
        List<UserCollectionSimpleVO> list = userCollectionService.selectById(uid);
        for (int i = 0; i < list.size(); i++) {
            log.info(list.get(i).toString());
        }

    }


}
