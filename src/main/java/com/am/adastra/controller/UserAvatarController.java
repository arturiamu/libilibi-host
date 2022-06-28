package com.am.adastra.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.entity.User;
import com.am.adastra.entity.Video;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.service.UserAvatarService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.am.adastra.util.oss.OssUtils;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "头像模块")
@RestController
@RequestMapping("/avatar")
@CrossOrigin
public class UserAvatarController {

    @Resource
    UserAvatarService userAvatarService;
    @Resource
    UserService userService;

    @ApiOperation("获取用户头像")
    @ApiOperationSupport(order = 0)
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


    @ApiOperation("更新用户头像")
    @ApiOperationSupport(order = 5)
    @PostMapping("/updateAvatar")
    public Result<Void> updateAvatar(HttpServletRequest request,@RequestBody String url) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        Result<Void> result = new Result<>();
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        JSONObject jsonObject = JSON.parseObject(url);
        String string = jsonObject.getString("url");
        userAvatarService.updateAvatar(sessionUser.getId(),string);
        result.setSuccess();
        return result;
    }

    @ApiOperation("添加用户头像")
    @ApiOperationSupport(order = 10)
    @PostMapping("/addAvatar")
    public Result<Video> addAvatar(HttpServletRequest request, @RequestBody String url) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        Result<Video> result = new Result<>();
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        userAvatarService.addAvatar(sessionUser.getId(),url);
        result.setSuccess();
        return result;
    }

    @ApiOperation("持久化用户头像到oss")
    @ApiOperationSupport(order = 15)
    @PostMapping("/ossfile")
    public Result<String> addAvatar(MultipartFile file) {
        String url = new OssUtils().uploadFileAvatar(file);
        Result<String> result = new Result<>();
        result.setSuccess("ImageUrl",url);
        return result;
    }
}
