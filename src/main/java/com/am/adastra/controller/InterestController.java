package com.am.adastra.controller;


import com.am.adastra.entity.User;
import com.am.adastra.service.InterestService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
* 基于协同用户的兴趣推荐
* */
@RestController
@RequestMapping("/interest")
public class InterestController {
    @Autowired
    private UserService userService;

    @Autowired
    private InterestService interestService;

    @GetMapping("/ing")
    public List<Integer> getInterest(HttpServletRequest request){
        //1.获取当前用户id
        User user = userService.isLogin(request.getSession());
        Integer userId = user.getId();


        //2.获取当前用户的关看历史和收藏夹和获取所有人的观看历史和收藏夹记录，来得到用户喜欢看的视频和可能喜欢看的视频
        interestService.list(userId);

        return null;
    }

}
