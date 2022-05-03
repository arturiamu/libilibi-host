package com.am.libilibi.controller;

import com.am.libilibi.entity.Video;
import com.am.libilibi.mapper.VideoMapper;
import com.am.libilibi.utils.APIList;
import com.am.libilibi.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/4/29 16:01
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
public class VideoController {
    private static final Random random = new Random();

    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("/video/refresh")
    public List<Video> refresh() {
        int s = random.nextInt(100);
        return videoMapper.refresh(s, s + 25);
    }

    @RequestMapping("/search/{keyword}")
    public String search(@PathVariable String keyword) {
        return HttpUtils.httpRequest(APIList.SEARCH + keyword, "GET", null);
    }

    @RequestMapping("/video/{type}")
    public String getVideo(@PathVariable String type) {
        switch (type) {
            case "recommend": {
                return HttpUtils.httpRequest(APIList.RECOMMEND, "GET", null);
            }
            case "animation": {
                return HttpUtils.httpRequest(APIList.ANIMATION, "GET", null);
            }
            case "music": {
                return HttpUtils.httpRequest(APIList.MUSIC, "GET", null);
            }
            case "game": {
                return HttpUtils.httpRequest(APIList.GAME, "GET", null);
            }
            case "entertainment": {
                return HttpUtils.httpRequest(APIList.ENTERTAINMENT, "GET", null);
            }
            case "knowledge": {
                return HttpUtils.httpRequest(APIList.KNOWLEDGE, "GET", null);
            }
            case "original": {
                return HttpUtils.httpRequest(APIList.ORIGINAL, "GET", null);
            }
            case "dance": {
                return HttpUtils.httpRequest(APIList.DANCE, "GET", null);
            }
            case "technology": {
                return HttpUtils.httpRequest(APIList.TECHNOLOGY, "GET", null);
            }
            case "sports": {
                return HttpUtils.httpRequest(APIList.SPORTS, "GET", null);
            }
            case "car": {
                return HttpUtils.httpRequest(APIList.CAR, "GET", null);
            }
            case "life": {
                return HttpUtils.httpRequest(APIList.LIFE, "GET", null);
            }
            case "delicacy": {
                return HttpUtils.httpRequest(APIList.DELICACY, "GET", null);
            }
            case "animal": {
                return HttpUtils.httpRequest(APIList.ANIMAL, "GET", null);
            }
            case "ghost": {
                return HttpUtils.httpRequest(APIList.GHOST, "GET", null);
            }
            case "fashion": {
                return HttpUtils.httpRequest(APIList.FASHION, "GET", null);
            }
            case "news": {
                return HttpUtils.httpRequest(APIList.NEWS, "GET", null);
            }
        }
        return null;
    }
}
