package com.am.adastra.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.controller.param.ValidationRules;
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
import javax.servlet.http.HttpSession;

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

    public static final String USER_NAME = "user_session";
    public static final String CODE_NAME = "code_session";

    @Autowired
    private UserService userService;

    @PostMapping("/registerSMS")
    public void registerSMS(@RequestBody String phone, HttpServletRequest request) {
        JSONObject jsonObject = JSON.parseObject(phone);
        String phoneNum = jsonObject.getString("phone");
        System.out.println(SMSUtil.sendSMS(request, phoneNum));
    }

    @PostMapping("/registerSMSTest")
    public void registerSMSTest(HttpServletRequest request, HttpSession session) {
        System.out.println(request.getSession().getAttribute(UserController.CODE_NAME));
        System.out.println(session.getAttribute(UserController.CODE_NAME));
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody @Validated(ValidationRules.register.class) User user, BindingResult errors, HttpServletRequest request) {
        Result<User> result;
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setFail(errors.getFieldError().getDefaultMessage(), State.ERR_REG_INFO);
            return result;
        }

        result = userService.register(user);
        if (result.isSuccess()) {
            request.getSession().setAttribute(USER_NAME, result.getData());
        }
        return result;
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody @Validated(ValidationRules.login.class) User user, BindingResult errors, HttpServletRequest request) {
        Result<User> result;
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setFail(errors.getFieldError().getDefaultMessage(), State.ERR_USER_INFO);
            return result;
        }
        result = userService.login(user);
        if (result.isSuccess()) {
            request.getSession().setAttribute(USER_NAME, result.getData());
        }
        return result;
    }

    @GetMapping("/isLogin")
    public Result<User> isLogin(HttpServletRequest request) {
        return userService.isLogin(request.getSession());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public Result<User> update(@RequestBody User user, HttpServletRequest request) throws Exception {
        Result<User> result = new Result<>();
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute(USER_NAME);
        if (sessionUser.getId() != user.getId()) {
            result.setFail("当前登录用户和被修改用户不一致，操作终止！", State.ERR_USER_INFO);
            return result;
        }
        result = userService.update(user);
        if (result.isSuccess()) {
            session.setAttribute(USER_NAME, result.getData());
        }
        return result;
    }

    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result();
        request.getSession().setAttribute(USER_NAME, null);
        result.setSuccess("用户退出登录成功！", null);
        return result;
    }
}
