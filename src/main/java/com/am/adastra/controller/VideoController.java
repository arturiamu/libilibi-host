package com.am.adastra.controller;

import com.am.adastra.entity.Video;
import com.am.adastra.service.VideoService;
import com.am.adastra.util.Result;
import com.am.adastra.util.VideoPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/26 10:58
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
@RestController()
@RequestMapping("/video")
public class VideoController {

    @Resource
    VideoService videoService;

    @GetMapping("/test/{ps}")
    public List<Video> test(@PathVariable int ps) {
        return VideoPool.getPidVideo(1, ps);
    }

    @GetMapping("/pid/{pid}/{ps}")
    public Result<List<Video>> getByPid(@PathVariable int pid, @PathVariable int ps) {
        Result<List<Video>> result = new Result<>();
        List<Video> getVideos = VideoPool.getPidVideo(pid, ps);
        log.info(String.valueOf(getVideos.size()));
        result.setSuccess(getVideos);
        return result;
    }

    @GetMapping("/search/{keyword}/{offset}/{ps}")
    public List<Video> search(@PathVariable String keyword,@PathVariable int offset, @PathVariable int ps) {
        return videoService.search(keyword,offset,ps);
    }
}
