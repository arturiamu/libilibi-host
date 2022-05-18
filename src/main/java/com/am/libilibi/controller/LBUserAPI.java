package com.am.libilibi.controller;

import com.am.libilibi.entity.LBUser;
import com.am.libilibi.service.LBUserService;
import com.am.libilibi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/9 19:45
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
@RequestMapping("user")

public class LBUserAPI {
    public static final String SESSION_NAME = "userInfo";

    @Qualifier("LBUserServiceImpl")
    @Autowired
    private LBUserService lbUserService;

    @PostMapping("/register")
    public Result<LBUser> register(@RequestBody LBUser user, BindingResult errors, HttpServletRequest request) {
        Result<LBUser> result = new Result<>();
        if (errors.hasErrors()) {
            result.setResultFailed(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            return result;
        }
        result = lbUserService.register(user);
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    @PostMapping("/login")
    public Result<LBUser> login(@RequestBody LBUser user, BindingResult errors, HttpServletRequest request) {
        Result<LBUser> result = new Result<>();
        if (errors.hasErrors()) {
            result.setResultFailed(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            return result;
        }
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            result.setResultFailed("请输入完整信息...");
        }
        result = lbUserService.login(user);
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    @GetMapping("/islogin")
    public Result<LBUser> isLogin(HttpServletRequest request) {
        return lbUserService.isLogin(request.getSession());
    }

    @PutMapping("/update")
    public Result<LBUser> update(@RequestBody LBUser user, HttpServletRequest request) throws Exception {
        Result<LBUser> result = new Result<>();
        HttpSession session = request.getSession();
        LBUser sessionUser = (LBUser) session.getAttribute(SESSION_NAME);
        if (sessionUser.getId() != user.getId()) {
            result.setResultFailed("当前登录用户和被修改用户不一致！");
            return result;
        }
        result = lbUserService.update(user);
        if (result.isSuccess()) {
            session.setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        Result result = new Result();
        request.getSession().setAttribute(SESSION_NAME, null);
        result.setResultSuccess("用户退出登录成功！", null);
        return result;
    }
}
