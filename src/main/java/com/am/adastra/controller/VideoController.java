package com.am.adastra.controller;

import com.am.adastra.app.LivePool;
import com.am.adastra.entity.Video;
import com.am.adastra.service.VideoService;
import com.am.adastra.util.Result;
import com.am.adastra.app.VideoPool;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@Api("视频模块")
public class VideoController {

    @Resource
    LivePool livePool;
    @Resource
    VideoService videoService;

    @ApiOperation("按照分类获取视频")
    @ApiOperationSupport(order = 0)
    @GetMapping("/pid/{pid}/{ps}")
    public Result<List<Video>> getByPid(@PathVariable int pid, @PathVariable int ps) {
        Result<List<Video>> result = new Result<>();
        List<Video> getVideos = VideoPool.getPidVideo(pid, ps);
        log.info(String.valueOf(getVideos.size()));
        result.setSuccess(getVideos);
        return result;
    }

    @ApiOperation("获取搜索视频")
    @ApiOperationSupport(order = 5)
    @GetMapping("/search/{keyword}/{offset}/{ps}")
    public List<Video> search(@PathVariable String keyword, @PathVariable int offset, @PathVariable int ps) {
        return videoService.search(keyword, offset, ps);
    }

    @ApiOperation("获取直播视频")
    @ApiOperationSupport(order = 15)
    @GetMapping("/live/{ps}")
    public Result<List<String>> getLive(@PathVariable int ps) {
        Result<List<String>> result = new Result<>();
        List<String> live = livePool.getLive(ps);
        if(live.size() == 0){
            result.setFail("系统繁忙，请稍后重试");
            return result;
        }
        result.setSuccess(live);
        return result;
    }

    @ApiOperation("获取直播间数量")
    @ApiOperationSupport(order = 20)
    @GetMapping("/live/size")
    public Result<Integer> getLiveSize() {
        Result<Integer> result = new Result<>();
        result.setSuccess(livePool.getSize());
        return result;
    }
}
