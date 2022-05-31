package com.am.adastra.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.controller.param.ValidationRules;
import com.am.adastra.entity.RegisterParm;
import com.am.adastra.entity.User;
import com.am.adastra.mapper.UserMapper;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static final String USER_SESSION = "user_session";
    public static final String CODE_SESSION = "code_session";

    public static final Pattern PHONE_RE = Pattern.compile("^((13[0-9])|(14[0|5|6|7|9])|(15[0-3])|(15[5-9])|(16[6|7])|(17[2|3|5|6|7|8])|(18[0-9])|(19[1|8|9]))\\d{8}$");

    @Autowired
    private UserService userService;

    @PostMapping("/registerSMS")
    public Result<String> registerSMS(@RequestBody String account, HttpServletRequest request) {
        Result<String> result = new Result<>();
        JSONObject jsonObject = JSON.parseObject(account);
        String phoneNum = jsonObject.getString("account");
        Matcher m = PHONE_RE.matcher(phoneNum);
        if (m.matches()) {
            String res = SMSUtil.sendSMS(request, phoneNum);
            if (res.equals("success")) {
                result.setSuccess(request.getSession().getAttribute(CODE_SESSION).toString());
            } else {
                result.setFail("系统繁忙，请稍后重试！", State.ERR_REG_INFO);
            }
            return result;
        }
        result.setFail("手机号码格式有误！", State.ERR_REG_INFO);
        return result;
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody @Validated(ValidationRules.register.class) RegisterParm rp, BindingResult errors, HttpServletRequest request) {
        Result<User> result = new Result<>();
        System.out.println(rp);
        if (errors.hasErrors()) {
            result.setFail(errors.getFieldError().getDefaultMessage(), State.ERR_REG_INFO);
            return result;
        }
        String verCode = rp.getVerCode();
        User getUser = rp.getUser();
        String session = request.getSession().getAttribute(CODE_SESSION).toString();  // {"createTime":1653878079279,"Code":"060770"}
        if (session == null) {
            result.setFail("非法请求！", State.ERR_REG_INFO);
            return result;
        }
        JSONObject jsonObject = JSON.parseObject(session);  // {"createTime":1653878079279,"Code":"060770"}
        long timestamp = Long.parseLong(jsonObject.getString("createTime"));
        String sessionCode = jsonObject.getString("Code");
        System.out.println(rp.getVerCode());
        System.out.println(sessionCode);
        if ((System.currentTimeMillis() - timestamp) > 1000 * 60 * 5) {
            result.setFail("验证码过期", State.ERR_REG_INFO);
            return result;
        }
        if (verCode.equals(sessionCode)) {
            result = userService.register(getUser);
            if (result.isSuccess()) {
                request.getSession().setAttribute(USER_SESSION, result.getData());
                result.setSuccess(getUser);
                return result;
            }
            result.setFail("注册失败", State.ERR_REG_INFO);
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
            request.getSession().setAttribute(USER_SESSION, result.getData());
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
        User sessionUser = (User) session.getAttribute(USER_SESSION);
        if (sessionUser.getId() != user.getId()) {
            result.setFail("当前登录用户和被修改用户不一致，操作终止！", State.ERR_USER_INFO);
            return result;
        }
        result = userService.update(user);
        if (result.isSuccess()) {
            session.setAttribute(USER_SESSION, result.getData());
        }
        return result;
    }

    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result();
        request.getSession().setAttribute(USER_SESSION, null);
        result.setSuccess("用户退出登录成功！", null);
        return result;
    }
}
