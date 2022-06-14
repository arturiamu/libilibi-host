package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
 * 用户推荐
 * */
@Slf4j
@RestController
@RequestMapping("/interest")
public class UserInterestRecommend {
    @Autowired
    private UserService userService;

    @GetMapping
    public Result<Void> interest(HttpServletRequest request) {
        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Integer userId = user.getId();
        log.info("当前的用户id为" + userId);

        return null;


    }
}
