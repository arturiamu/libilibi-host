package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.UserHistoryService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 13:53
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
@RestController
@RequestMapping("/history")
public class UserHistoryController {
    @Autowired
    UserHistoryService userHistoryService;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result<Void> addLike(@RequestBody @Valid VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
        log.info("add history {}", videoOperateDTO);
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        log.info("sessionUser: {}", sessionUser);
        videoOperateDTO.setUser_id(sessionUser.getId());
        log.info("videoOperateDTO: {}", videoOperateDTO);
        userHistoryService.add(videoOperateDTO);
        result.setSuccess("添加成功", null);
        return result;
    }

    @PostMapping("/getAll")
    public Result<List<VideoOperateDTO>> getAll(HttpServletRequest request) {
        Result<List<VideoOperateDTO>> result = new Result<>();
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        List<VideoOperateDTO> all = userHistoryService.getAll(sessionUser.getId());
        result.setSuccess("添加成功", all);
        return result;
    }
}
