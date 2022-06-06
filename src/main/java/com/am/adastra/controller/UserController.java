package com.am.adastra.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.controller.param.ValidationRules;
import com.am.adastra.entity.Item;
import com.am.adastra.entity.RegisterParm;
import com.am.adastra.entity.User;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.am.adastra.util.SMSUtil;
import com.am.adastra.util.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 10:34
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
@RequestMapping("/user")
public class UserController {
    public static final String VERIFICATION_CODE_SESSION = "verificationCodeSession";
    public static final String USER_INFO_SESSION = "userInfoSession";

    @Autowired
    private UserService userService;
    @Autowired
    private SMSUtil smsUtil;

    @PostMapping("/registerSMS")
    public Result<Void> registerSMS(@RequestBody String account, HttpServletRequest request) {
        Result<Void> result = new Result<>();
        JSONObject jsonObject = JSON.parseObject(account);
        String phone = jsonObject.getString("account");
        if (smsUtil.sendSMS(phone, request)) {
            result.setSuccess("获取验证码成功！", null);
        } else {
            result.setFail("系统繁忙，请稍后重试！", State.ERR_REG_INFO);
        }
        return result;
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody @Validated(ValidationRules.register.class) RegisterParm rp, BindingResult errors, HttpServletRequest request) {
        Result<User> result = new Result<>();
        if (errors.hasErrors()) {
            result.setFail(errors.getFieldError().getDefaultMessage(), State.ERR_REG_INFO);
            return result;
        }
        if (rp.getVerCode().equals(request.getSession().getAttribute(VERIFICATION_CODE_SESSION))) {
            User getUser = userService.register(rp.getUser());
            result.setSuccess(getUser);
        } else {
            result.setFail("验证码错误", State.ERR_REG_INFO);
        }
        return result;
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody @Validated(ValidationRules.login.class) User user, BindingResult errors, HttpServletRequest request) {
        Result<User> result = new Result<>();
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setFail(errors.getFieldError().getDefaultMessage(), State.ERR_USER_INFO);
            return result;
        }
        User getUser = userService.login(user);
        request.getSession().setAttribute(USER_INFO_SESSION, getUser);
        result.setSuccess(getUser);
        return result;
    }

    @GetMapping("/isLogin")
    public Result<User> isLogin(HttpServletRequest request) {
        User getUser = userService.isLogin(request.getSession());
        Result<User> result = new Result<>();
        result.setSuccess(getUser);
        return result;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public Result<User> update(@RequestBody User user, HttpServletRequest request) {
        Result<User> result = new Result<>();
        User sessionUser = (User) request.getSession().getAttribute(USER_INFO_SESSION);
        if (!sessionUser.getId().equals(user.getId())) {
            result.setFail("当前登录用户和被修改用户不一致，操作终止！", State.ERR_USER_INFO);
            return result;
        }
        User getUser = userService.update(user);
        result.setSuccess(getUser);
        return result;
    }

    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result<>();
        result.setSuccess("用户退出登录成功！", null);
        request.getSession().setAttribute(USER_INFO_SESSION, null);
        return result;
    }
}