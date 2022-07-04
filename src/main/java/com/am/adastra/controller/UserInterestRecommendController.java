package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.Video;
import com.am.adastra.service.UserInterestRecommendService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.am.adastra.app.VideoPool;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/*
 * 用户推荐
 * */
@Slf4j
@RestController
@Api(tags = "个性化推荐模块")
@RequestMapping("/interest")
public class UserInterestRecommendController {
    @Autowired
    private UserService userService;
    @Qualifier("userInterestRecommendServiceImpl")
    @Autowired(required = false)
    private UserInterestRecommendService interestService;

    @ApiOperation("获取个性化推荐")
    @ApiOperationSupport(order = 5)
    @GetMapping("/{ps}")
    public Result<List<Video>> interest(@PathVariable int ps, HttpServletRequest request, HttpServletResponse response) {
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        Result<List<Video>> result = new Result<>();
        if (sessionUser == null || !VideoPool.CACHE) {
            log.info("用户未登录");
            List<Video> videoList = VideoPool.getRandom(ps);
            result.setSuccess(videoList);
            return result;
        }
        log.info("当前的用户id:{}", sessionUser.getId());
        return interestService.list(sessionUser.getId(), ps);
    }
}
