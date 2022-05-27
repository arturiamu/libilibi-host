package com.am.adastra.controller;

import com.am.adastra.entity.DBVideo;
import com.am.adastra.util.Result;
import com.am.adastra.util.VideoPool;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/26 10:58
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController()
@RequestMapping("/video")
public class VideoController {

    @GetMapping("/test/{ps}")
    public List<DBVideo> test(@PathVariable int ps) {
        return VideoPool.getPidVideo(1, ps);
    }

    @GetMapping("/pid/{pid}/{ps}")
    public Result<List<DBVideo>> getPidVideo(@PathVariable int pid, @PathVariable int ps) {
        Result<List<DBVideo>> result = new Result<>();
        List<DBVideo> getVideos = VideoPool.getPidVideo(pid, ps);
//        result.setResultSuccess(getVideos);
        return result;
    }

    @GetMapping("/personalized/recommend/{ps}")
    public List<DBVideo> recommend(@PathVariable int ps) {
        return VideoPool.getPidVideo(1, ps);
    }

    @GetMapping("/general/{tid}/{ps}")
    public List<DBVideo> general(@PathVariable int tid, @PathVariable int ps) {
        return VideoPool.getPidVideo(1, ps);
    }

    @GetMapping("/search/{keyword}/{ps}")
    public List<DBVideo> search(@PathVariable String keyword, @PathVariable int ps) {
        return VideoPool.getPidVideo(1, ps);
    }

}
