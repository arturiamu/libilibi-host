package com.am.libilibi.controller;

import com.am.libilibi.utils.VideosPool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/general")
    public Result getGeneral(String type, int ps) {
        List<GeneralVideo> data = videosPool.getGeneralVideoByType(type, ps);
        if (data.isEmpty()) {
            return new Result("error", 400, null);
        }
        return new Result("ok", 0, data);
    }

    @RequestMapping("/recommend")
    public Result refresh(int ps) {
        List<GeneralVideo> data = videosPool.getGeneralVideoByReco(ps);
        if (data.isEmpty()) {
            return new Result("error", 400, null);
        }
        return new Result("ok", 0, data);
    }
}
