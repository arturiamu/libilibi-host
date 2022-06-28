package com.am.adastra.controller;

import com.am.adastra.entity.Admin;
import com.am.adastra.entity.User;
import com.am.adastra.entity.dto.MessageDTO;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.AdminService;
import com.am.adastra.service.UserMessageService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "消息模块")
public class UserMessageController {

    @Resource
    private AdminService adminService;

    @Resource
    private UserService userService;

    @Resource
    private UserMessageService userMessageService;

    @ApiOperation("获取用户所有消息")
    @ApiOperationSupport(order = 0)
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

    @ApiOperation("删除一条消息")
    @ApiOperationSupport(order = 5)
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

    @ApiOperation("删除全部消息")
    @ApiOperationSupport(order = 15)
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

    @ApiOperation("标记已读一条消息")
    @ApiOperationSupport(order = 20)
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

    @ApiOperation("已读全部消息")
    @ApiOperationSupport(order = 25)
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


    @ApiOperation("发送消息")
    @ApiOperationSupport(order = 25)
    @PostMapping("/send")
    public Result<Void> send(HttpServletRequest request, @RequestBody @Validated MessageDTO messageDTO, BindingResult errors) {
        log.info("发送消息：");
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }

        if (messageDTO.getIsAdmin() != null){
            Admin adminServiceLogin = adminService.isLogin(request.getSession());
            if (adminServiceLogin == null) {
                throw new UserNotLoginException("请先登录");
            }

            messageDTO.setSendUserId(adminServiceLogin.getId());
            messageDTO.setSendUserName(adminServiceLogin.getUsername());
            userMessageService.sendMessage(messageDTO);
            result.setSuccess();
            return result;
        }else {
            User userServiceLogin = userService.isLogin(request.getSession());
            if (userServiceLogin == null) {
                throw new UserNotLoginException("请先登录");
            }
            messageDTO.setSendUserId(userServiceLogin.getId());
            messageDTO.setSendUserName(userServiceLogin.getUsername());
            userMessageService.sendMessage(messageDTO);
            result.setSuccess();
            return result;
        }


    }
}
