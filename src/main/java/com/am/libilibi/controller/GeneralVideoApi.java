package com.am.libilibi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.am.libilibi.entity.GeneralVideo;
import com.am.libilibi.utils.HttpUtils;
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

    @RequestMapping("/general/{type}/{ps}")
    public Result getGeneral(@PathVariable String type, @PathVariable int ps) {
        Result result = new Result();
        List<GeneralVideo> data = videosPool.getGeneralVideoByType(type, ps);
        if (data.isEmpty()) {
            result.setResultFailed();
            return result;
        }
        result.setResultSuccess(data);
        return result;
    }

    @RequestMapping("/live")
    public String live() {
        String live = "https://api.live.bilibili.com/xlive/web-interface/v1/webMain/getMoreRecList?platform=web";
        try {
            String jsStr = HttpUtils.httpRequest(live, "GET", null, null);
            return jsStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/recommend")
    public Result refresh() {
        Result result = new Result();
        List<GeneralVideo> data = videosPool.getGeneralVideoByReco(11);
        if (data.isEmpty()) {
            result.setResultFailed();
            return result;
        }
        result.setResultSuccess(data);
        return result;
    }

    @RequestMapping("/video/{bvid}")
    public Result getDetail(@PathVariable String bvid) {
        String baseUrl = "http://api.bilibili.com/x/web-interface/view?bvid=";
        Result result = new Result();
        try {
            String resp = HttpUtils.httpRequest(baseUrl + bvid, "GET", null, null);
            JSONObject root = JSON.parseObject(resp).getJSONObject("data");
            GeneralVideo video = JSONObject.parseObject(root.toJSONString(), GeneralVideo.class);
            List<GeneralVideo> data = new ArrayList<>();
            data.add(video);
            result.setResultSuccess(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setResultFailed();
        return result;
    }

    @RequestMapping("/search/{keyword}")
    public Result search(@PathVariable String keyword) {
        String baseUrl = "http://api.bilibili.com/x/web-interface/search/all/v2?keyword=";
        Result result = new Result();
        try {
            String resp = HttpUtils.httpRequest(baseUrl + keyword, "GET", null, null);
            JSONObject root = JSON.parseObject(resp).getJSONObject("data");
            JSONArray jsonArray = root.getJSONArray("result");
            JSONArray videos = jsonArray.getJSONObject(jsonArray.size() - 1).getJSONArray("data");
            List<GeneralVideo> data = new ArrayList<>(JSONObject.parseArray(videos.toJSONString(), GeneralVideo.class));
            result.setResultSuccess(data);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.clearProperty("proxyHost");
            System.clearProperty("proxyPort");
        }
        result.setResultFailed();
        return result;
    }
}
