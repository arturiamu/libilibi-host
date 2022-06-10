package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.UserLikeService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
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
@RequestMapping("/like")
public class UserLikeController {
    @Autowired
    UserLikeService userLikeService;

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result<Void> addLike(@RequestBody @Valid VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
        log.info("add like {}",videoOperateDTO);
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        videoOperateDTO.setUser_id(sessionUser.getId());
        userLikeService.addLike(videoOperateDTO);
        result.setSuccess("添加成功", null);
        return result;
    }

    @PostMapping("/cancel")
    public Result<Void> cancelLike(@RequestBody @Valid VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
        log.info("unlike {}",videoOperateDTO);
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        videoOperateDTO.setUser_id(sessionUser.getId());
        userLikeService.cancelLike(videoOperateDTO);
        result.setSuccess("取消成功", null);
        return result;
    }

    @PostMapping("/isLike")
    public Result<Void> isLike(@RequestBody @Valid VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
        log.info("isLike {}",videoOperateDTO);
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        videoOperateDTO.setUser_id(sessionUser.getId());
        if (userLikeService.isLikeVideo(videoOperateDTO)) {
            result.setSuccess("like", null);
        } else {
            result.setSuccess("unlike", null);
        }
        return result;
    }
}
