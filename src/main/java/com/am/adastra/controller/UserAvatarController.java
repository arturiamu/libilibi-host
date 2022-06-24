package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.service.UserAvatarService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.oss.OssUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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



    @GetMapping("/getAvatar")
    public void getAvatar(HttpServletRequest request) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        userAvatarService.getByUid(sessionUser.getId());
    }


    @PostMapping("/updateAvatar")
    public void updateAvatar(HttpServletRequest request,MultipartFile file) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        userAvatarService.updateAvatar(sessionUser.getId(),new OssUtils().uploadFileAvatar(file));
    }
}
