package com.am.adastra.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.entity.param.ValidationRules;
import com.am.adastra.entity.dto.UserRegisterDTO;
import com.am.adastra.entity.User;
import com.am.adastra.ex.IllegalOperationException;
import com.am.adastra.ex.SystemException;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.UserService;
import com.am.adastra.util.EmailUtil;
import com.am.adastra.util.Result;
import com.am.adastra.util.SMSUtil;
import com.am.adastra.util.State;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.regex.Pattern;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 10:34
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
    public static final String VERIFICATION_CODE_SESSION = "verificationCodeSession";
    public static final String USER_INFO_SESSION = "userInfoSession";
    public static final Pattern patternMail = Pattern.compile("^\\s*\\w+(?:\\.?[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
    public static final Pattern patternPhone = Pattern.compile("^1[3|4|5|8|9][0-9]\\d{8}$");

    @Autowired
    private UserService userService;
    @Autowired
    private SMSUtil smsUtil;
    @Autowired
    private EmailUtil emailUtil;

    @ApiOperation("发送注册验证码")
    @ApiOperationSupport(order = 0)
    @PostMapping("/registerVerify")
    public Result<Void> registerVerify(@RequestBody @NotBlank String account, HttpServletRequest request) {
        Result<Void> result = new Result<>();
        JSONObject jsonObject = JSON.parseObject(account);
        String acc = jsonObject.getString("account");
        if (patternMail.matcher(acc).matches()) {
            if (emailUtil.sendRegisterMail(acc, request)) {
                log.info("发送邮箱验证成功");
                result.setSuccess("获取验证码成功！", null);
            } else {
                throw new SystemException("系统繁忙，请稍后重试");
            }
        } else if (patternPhone.matcher(acc).matches()) {
            if (smsUtil.sendSMS(acc, request)) {
                log.info("发送邮箱验证成功");
                result.setSuccess("获取验证码成功！", null);
            } else {
                throw new SystemException("系统繁忙，请稍后重试");
            }
        } else {
            log.info("账号不合法");
            result.setFail("账号不合法！");
        }
        return result;
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody @Validated(ValidationRules.register.class) UserRegisterDTO rp, BindingResult errors, HttpServletRequest request) {
        Result<User> result = new Result<>();
        System.out.println(rp);
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (rp.getVerCode().equals(request.getSession().getAttribute(VERIFICATION_CODE_SESSION))) {
            User getUser = userService.register(rp.getUser());
            result.setSuccess(getUser);
        } else {
            throw new ValidException("验证码错误");
        }
        return result;
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody @Validated(ValidationRules.login.class) User user, BindingResult errors, HttpServletRequest request) {
        log.info("登陆的用户信息 ： {}", user);
        Result<User> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
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
            throw new IllegalOperationException("系统繁忙，请稍后重试");
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
