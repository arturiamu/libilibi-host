package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.dto.MessageDTO;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.service.UserMessageService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 16:10
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class UserMessageController {
    @Resource
    private UserService userService;

    @Resource
    private UserMessageService userMessageService;

    @GetMapping("/getAll")
    public Result<List<MessageDTO>> getAll(HttpServletRequest request) {
        log.info("获取消息");
        User sessionUser = userService.isLogin(request.getSession());
        if (sessionUser == null) {
            throw new UserNotLoginException("请先登录");
        }
        Result<List<MessageDTO>> result = new Result<>();
        List<MessageDTO> all = userMessageService.getAll(sessionUser.getId());
        result.setSuccess(all);
        return result;
    }

    @GetMapping("/del/{id}")
    public Result<Void> del(HttpServletRequest request, @PathVariable Long id) {
        log.info("删除消息：");
        User sessionUser = userService.isLogin(request.getSession());
        if (sessionUser == null) {
            throw new UserNotLoginException("请先登录");
        }
        Result<Void> result = new Result<>();
        userMessageService.fakeDel(id);
        result.setSuccess();
        return result;
    }

    @GetMapping("/delAll")
    public Result<Void> del(HttpServletRequest request) {
        log.info("删除全部消息：");
        User sessionUser = userService.isLogin(request.getSession());
        if (sessionUser == null) {
            throw new UserNotLoginException("请先登录");
        }
        Result<Void> result = new Result<>();
        userMessageService.fakeDelAll(sessionUser.getId());
        result.setSuccess();
        return result;
    }

    @GetMapping("/read/{id}")
    public Result<Void> read(HttpServletRequest request, @PathVariable Long id) {
        log.info("已读消息：");
        User sessionUser = userService.isLogin(request.getSession());
        if (sessionUser == null) {
            throw new UserNotLoginException("请先登录");
        }
        Result<Void> result = new Result<>();
        userMessageService.fakeRead(id);
        result.setSuccess();
        return result;
    }

    @GetMapping("/readAll")
    public Result<Void> readAll(HttpServletRequest request) {
        log.info("已读全部消息：");
        User sessionUser = userService.isLogin(request.getSession());
        if (sessionUser == null) {
            throw new UserNotLoginException("请先登录");
        }
        Result<Void> result = new Result<>();
        userMessageService.fakeReadAll(sessionUser.getId());
        result.setSuccess();
        return result;
    }
}
