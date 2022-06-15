package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.Video;
import com.am.adastra.service.UserInterestRecommendService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * 用户推荐
 * */
@Slf4j
@RestController
@RequestMapping("/interest")
public class UserInterestRecommendController {
    @Autowired
    private UserService userService;
    @Qualifier("userInterestRecommendServiceImpl")
    @Autowired(required = false)
    private UserInterestRecommendService interestService;

    @GetMapping("/{ps}")
    public Result<List<Video>> interest(@PathVariable int ps, HttpServletRequest request) {
        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        long userId = user.getId();
        log.info("当前的用户id:{}", userId);

        return interestService.list(userId, ps);

    }
}
