package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.service.UserAvatarService;
import com.am.adastra.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 21:21
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
@RequestMapping("/avatar")
public class UserAvatarController {
    @Resource
    UserAvatarService userAvatarService;
    @Resource
    UserService userService;



    public void getAvatar(HttpServletRequest request) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        userAvatarService.getByUid(sessionUser.getId());
    }
}
