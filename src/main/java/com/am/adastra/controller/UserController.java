package com.am.adastra.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.controller.param.ValidationRules;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

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
    public static final String SESSION_NAME = "userInfo";
    private static final HashMap<Long, String> verMap = new HashMap<>();

    @Autowired
    private UserService userService;

    @PostMapping("/registerSMS")
    public Result<Long> registerSMS(@RequestBody String account, HttpServletRequest request, HttpServletResponse response) {
        Result<Long> result = new Result<>();
        JSONObject jsonObject = JSON.parseObject(account);
        String phone = jsonObject.getString("account");
        String res = SMSUtil.sendSMS(phone);
        if (res == null) {
            result.setFail("系统繁忙，请稍后重试！", State.ERR_REG_INFO);
        } else {
            long time = System.currentTimeMillis();
            result.setSuccess(time);
            verMap.put(time, res);
        }
        return result;
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody @Validated(ValidationRules.register.class) RegisterParm rp, BindingResult errors, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println(rp);
        Result<User> result = new Result<>();
        if (errors.hasErrors()) {
            System.out.println(1);
            result.setFail(errors.getFieldError().getDefaultMessage(), State.ERR_REG_INFO);
            return result;
        }
        System.out.println(rp);
        Long getVerId = rp.getTokenId();
        if (getVerId == null) {
            result.setFail("请先获取验证码", State.ERR_REG_INFO);
            return result;
        }
        Long ctime = System.currentTimeMillis();
        if ((ctime - getVerId) > 1000 * 60 * 5) {
            result.setFail("验证码失效", State.ERR_REG_INFO);
            return result;
        }
        if (verMap.get(getVerId).equals(rp.getVerCode())) {
            result = userService.register(rp.getUser());
        } else {
            result.setFail("验证码错误", State.ERR_REG_INFO);
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
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    @GetMapping("/isLogin")
    public Result<User> isLogin(HttpServletRequest request) {
        return userService.isLogin(request.getSession());
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/update")
//    public Result<User> update(@RequestBody User user, HttpServletRequest request) throws Exception {
//        Result<User> result = new Result<>();
//        HttpSession session = request.getSession();
//        if (sessionUser.getId() != user.getId()) {
//            result.setFail("当前登录用户和被修改用户不一致，操作终止！", State.ERR_USER_INFO);
//            return result;
//        }
//        result = userService.update(user);
//        return result;
//    }

    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result();
        result.setSuccess("用户退出登录成功！", null);
        return result;
    }
}
