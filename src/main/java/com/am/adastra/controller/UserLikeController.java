package com.am.adastra.controller;

import com.am.adastra.app.VideoPool;
import com.am.adastra.entity.User;
import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.UserLikeService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.BinarySearch;
import com.am.adastra.util.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/9 21:41
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
@RestController
@Api(tags = "点赞模块")
@RequestMapping("/like")
public class UserLikeController {
    @Autowired
    UserLikeService userLikeService;

    @Autowired
    UserService userService;

    @ApiOperation("添加点赞记录")
    @ApiOperationSupport(order = 0)
    @PostMapping("/add")
    public Result<Void> addLike(@RequestBody @Valid VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
        log.info("add like {}", videoOperateDTO);
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        videoOperateDTO.setUid(sessionUser.getId());
        userLikeService.addLike(videoOperateDTO);
        int indexPid = VideoPool.indexPid(videoOperateDTO.getPid());
        Video video = BinarySearch.GetVideo(indexPid, Long.valueOf(videoOperateDTO.getAid()));
        video.setLike(video.getLike() + 1);
        result.setSuccess("添加成功", null);
        return result;
    }

    @ApiOperation("取消点赞记录")
    @ApiOperationSupport(order = 5)
    @PostMapping("/cancel")
    public Result<Void> cancelLike(@RequestBody @Valid VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
        log.info("unlike {}", videoOperateDTO);
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        videoOperateDTO.setUid(sessionUser.getId());
        userLikeService.cancelLike(videoOperateDTO);
        int indexPid = VideoPool.indexPid(videoOperateDTO.getPid());
        Video video = BinarySearch.GetVideo(indexPid, Long.valueOf(videoOperateDTO.getAid()));
        video.setLike(video.getLike() - 1);
        result.setSuccess("取消成功", null);
        return result;
    }

    @ApiOperation("判断是否点赞视频")
    @ApiOperationSupport(order = 10)
    @PostMapping("/isLike")
    public Result<Void> isLike(@RequestBody @Valid VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
        log.info("isLike {}", videoOperateDTO);
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        videoOperateDTO.setUid(sessionUser.getId());
        if (userLikeService.isLikeVideo(videoOperateDTO)) {
            result.setSuccess("like", null);
        } else {
            result.setSuccess("unlike", null);
        }
        return result;
    }
}
