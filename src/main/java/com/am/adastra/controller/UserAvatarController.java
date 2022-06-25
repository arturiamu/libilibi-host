package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.service.UserAvatarService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.am.adastra.util.oss.OssUtils;
import org.springframework.web.bind.annotation.*;
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
@CrossOrigin
public class UserAvatarController {

    @Resource
    UserAvatarService userAvatarService;
    @Resource
    UserService userService;

    @GetMapping("/getAvatar")
    public Result<String> getAvatar(HttpServletRequest request) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        Result<String> result = new Result<>();
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        String url = userAvatarService.getByUid(sessionUser.getId());
        result.setSuccess("获取成功",url);
        return result;
    }


    @PostMapping("/updateAvatar")
    public Result<Void> updateAvatar(HttpServletRequest request,MultipartFile file) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        Result<Void> result = new Result<>();
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        userAvatarService.updateAvatar(sessionUser.getId(),new OssUtils().uploadFileAvatar(file));
        result.setSuccess();
        return result;
    }

    @PostMapping("/addAvatar")
    public Result<String> addAvatar(HttpServletRequest request,MultipartFile file) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        Result<String> result = new Result<>();
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        String url = new OssUtils().uploadFileAvatar(file);
        userAvatarService.addAvatar(sessionUser.getId(),new OssUtils().uploadFileAvatar(file));
        result.setSuccess("ImageUrl",url);
        return result;
    }

    @PostMapping("/ossfile")
    public Result<String> addAvatar(MultipartFile file) {
        String url = new OssUtils().uploadFileAvatar(file);
        Result<String> result = new Result<>();
        result.setSuccess("ImageUrl",url);
        return result;
    }
}
