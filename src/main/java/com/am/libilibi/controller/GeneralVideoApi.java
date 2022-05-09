package com.am.libilibi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.am.libilibi.entity.GeneralVideo;
import com.am.libilibi.entity.LBProxy;
import com.am.libilibi.utils.HttpUtils;
import com.am.libilibi.utils.ProxyPool;
import com.am.libilibi.utils.Result;
import com.am.libilibi.utils.VideosPool;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/4 19:39
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */

@RestController
@RequestMapping("/api")
public class GeneralVideoApi {
    private static final VideosPool videosPool = new VideosPool();

    @RequestMapping("/general/{type}")
    public Result getGeneral(@PathVariable String type) {
        List<GeneralVideo> data = videosPool.getGeneralVideoByType(type, 10);
        if (data.isEmpty()) {
            return new Result("error", 400, null);
        }
        return new Result("ok", 0, data);
    }

    @RequestMapping("/recommend")
    public Result refresh() {
        List<GeneralVideo> data = videosPool.getGeneralVideoByReco(11);
        if (data.isEmpty()) {
            return new Result("error", 400, null);
        }
        return new Result("ok", 0, data);
    }

    @RequestMapping("/video/{bvid}")
    public Result getDetail(@PathVariable String bvid) {
        String baseUrl = "http://api.bilibili.com/x/web-interface/view?bvid=";
        try {
            String resp = HttpUtils.httpRequest(baseUrl + bvid, "GET", null, null);
            JSONObject root = JSON.parseObject(resp).getJSONObject("data");
            GeneralVideo video = JSONObject.parseObject(root.toJSONString(), GeneralVideo.class);
            List<GeneralVideo> data = new ArrayList<>();
            data.add(video);
            return new Result("ok", 0, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result("ok", 0, null);
    }

    @RequestMapping("/search/{keyword}")
    public Result search(@PathVariable String keyword) {
        String baseUrl = "http://api.bilibili.com/x/web-interface/search/all/v2?keyword=";
        LBProxy proxy = ProxyPool.getRandomProxy();
        System.setProperty("proxyHost", proxy.getHost());
        System.setProperty("proxyPort", proxy.getPort());
        try {
            String resp = HttpUtils.httpRequest(baseUrl + keyword, "GET", null, null);
            JSONObject root = JSON.parseObject(resp).getJSONObject("data");
            JSONArray jsonArray = root.getJSONArray("result");
            JSONArray videos = jsonArray.getJSONObject(jsonArray.size() - 1).getJSONArray("data");
            List<GeneralVideo> data = new ArrayList<>(JSONObject.parseArray(videos.toJSONString(), GeneralVideo.class));
            System.out.println(data);
            return new Result("ok", 0, data);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.clearProperty("proxyHost");
            System.clearProperty("proxyPort");
        }
        return new Result("ok", 0, null);
    }
}
