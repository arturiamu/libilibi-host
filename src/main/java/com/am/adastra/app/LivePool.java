package com.am.adastra.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/25 14:00
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Component
@Slf4j
public class LivePool implements ApplicationRunner {

    private static final String JSON_BIRD_V1 = "https://bird.ioliu.cn/v1";
    private static final String JSON_BIRD_V2 = "https://bird.ioliu.cn/v2";
    private static final int live_update = 60;
    private static final String BL_URL = "https://api.live.bilibili.com/xlive/web-interface/v1/webMain/getMoreRecList?platform=web";
    private static final List<String> LIVE_POOL = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.warn("init live pool");
        Thread thread = new Thread(new Task());
        thread.setDaemon(true);
        thread.start();
        log.warn("end live pool");
    }

    public List<String> getLive(int ps) {
        if (LIVE_POOL.size() == 0) {
            return null;
        }
        if (ps > LIVE_POOL.size()) {
            ps = LIVE_POOL.size();
        } else if (ps <= 0) {
            return null;
        }
        List<String> list = new ArrayList<>();

        Random random = new Random();
        int st = random.nextInt(LIVE_POOL.size()) - ps;
        if (st < 0) {
            st = 0;
        }
        for (int i = st; i < st + ps; i++) {
            list.add(LIVE_POOL.get(i));
        }
        return list;
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            log.warn("start runnable");
            while (true) {
                for (int cnt = 0; cnt < 50; cnt++) {
                    try {
                        String str = HttpClient.client(JSON_BIRD_V2 + "?url=" + BL_URL, HttpMethod.GET, null);
                        JSONObject jsonObject = JSON.parseObject(str);
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONArray recommend_room_list = data.getJSONArray("recommend_room_list");
                        if (recommend_room_list.size() > 0) {
                            Set<String> temp = new HashSet<>();
                            for (int i = 0; i < recommend_room_list.size(); i++) {
                                JSONObject object = recommend_room_list.getJSONObject(i);
                                String roomid = object.getString("roomid");
                                temp.add(roomid);
                            }
                            LIVE_POOL.clear();
                            LIVE_POOL.addAll(temp);
                        } else {
                            log.warn("json error");
                            break;
                        }
                    } catch (Exception e) {
                        log.warn("http error");
                        e.printStackTrace();
                        try {
                            int i = new Random().nextInt(50) + 10;
                            Thread.sleep(i * 60 * 1000);
                        } catch (InterruptedException ignored) {

                        }
                    }
                }
                try {
                    Thread.sleep((long) live_update * 60 * 1000);
                } catch (InterruptedException ignored) {

                }
            }
        }
    }

    public int getSize() {
        return LIVE_POOL.size();
    }
}
