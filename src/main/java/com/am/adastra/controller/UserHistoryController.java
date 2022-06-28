package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserHistorySimpleVO;
import com.am.adastra.ex.SystemException;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.UserHistoryService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
@Api("历史记录模块")
public class UserHistoryController {
    @Resource
    UserHistoryService userHistoryService;
    @Resource
    UserService userService;

    @ApiOperation("添加历史记录")
    @ApiOperationSupport(order = 0)
    @PostMapping("/add")
    public Result<Void> addHistory(@RequestBody @Valid VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
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
        videoOperateDTO.setUid(sessionUser.getId());
        log.info("videoOperateDTO: {}", videoOperateDTO);
        userHistoryService.add(videoOperateDTO);
        result.setSuccess("添加成功", null);
        return result;
    }

    @ApiOperation("获取用户历史记录")
    @ApiOperationSupport(order = 5)
    @GetMapping("/get/{ps}")
    public Result<List<UserHistorySimpleVO>> getAll(@PathVariable Integer ps, HttpServletRequest request) {
        log.info("get history: {}", ps);
        Result<List<UserHistorySimpleVO>> result = new Result<>();
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        log.info("sessionUser: {}", sessionUser);
        List<UserHistorySimpleVO> all = userHistoryService.getLimit(sessionUser.getId(), ps);
        log.info("result:{}", all.size());
        result.setSuccess("获取历史记录成功", all);
        return result;
    }

    @ApiOperation("删除历史记录")
    @ApiOperationSupport(order = 10)
    @GetMapping("/del/{id}")
    public Result<Void> del(@PathVariable @NotBlank Long id, HttpServletRequest request) {
        log.info("del history: {}", id);
        Result<Void> result = new Result<>();
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        log.info("sessionUser: {}", sessionUser);
        if (userHistoryService.fakeDel(id)) {
            result.setSuccess("获取历史记录成功", null);
        } else {
            throw new SystemException("系统繁忙，请稍后重试");
        }
        return result;
    }

    @ApiOperation("清空历史记录")
    @ApiOperationSupport(order = 15)
    @GetMapping("/del/all")
    public Result<Void> delAll(HttpServletRequest request) {
        log.info("clear history");
        Result<Void> result = new Result<>();
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        User sessionUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        log.info("sessionUser: {}", sessionUser);
        if (userHistoryService.fakeClear(sessionUser.getId())) {
            result.setSuccess("获取历史记录成功", null);
        } else {
            throw new SystemException("系统繁忙，请稍后重试");
        }
        return result;
    }
}
