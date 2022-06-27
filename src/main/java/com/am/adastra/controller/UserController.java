package com.am.adastra.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.entity.dto.UpdatePwdDTO;
import com.am.adastra.entity.param.ValidationRules;
import com.am.adastra.entity.dto.UserRegisterDTO;
import com.am.adastra.entity.User;
import com.am.adastra.ex.IllegalOperationException;
import com.am.adastra.ex.SystemException;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.UserService;
import com.am.adastra.util.EmailUtil;
import com.am.adastra.util.IPUtil;
import com.am.adastra.util.Result;
import com.am.adastra.util.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
//@Api(tags = "用户模块")
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=utf-8")
public class UserController {
    public static final String VERIFICATION_CODE_SESSION = "verificationCodeSession";
    public static final String USER_INFO_SESSION = "userInfoSession";
    public static final Pattern patternMail = Pattern.compile("^\\s*\\w+(?:\\.?[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
    public static final Pattern patternPhone = Pattern.compile("^1[3|4|5|8|9][0-9]\\d{8}$");
    public static final String AD_ASTRA = "ad-astra 官方团队";
    public static final String WELCOME = "hi，欢迎加入ad astra，让我们开始愉快的探索之旅吧~";

    @Resource
    private UserService userService;
    @Resource
    private SMSUtil smsUtil;
    @Resource
    private EmailUtil emailUtil;

    //    @ApiOperation("发送验证码")
//    @ApiOperationSupport(order = 0)
    @PostMapping("/verifyCode")
    public Result<Void> verifyCode(@RequestBody @NotBlank String account, HttpServletRequest request) {
        log.info("发送验证码：{}", account);
        Result<Void> result = new Result<>();
        JSONObject jsonObject = JSON.parseObject(account);
        String acc = jsonObject.getString("account");
        if (patternMail.matcher(acc).matches()) {
            if (emailUtil.sendRegisterMail(acc, request)) {
                log.info("获取邮箱验证码成功");
                result.setSuccess("获取邮箱验证码成功！", null);
            } else {
                throw new SystemException("系统繁忙，请稍后重试");
            }
        } else if (patternPhone.matcher(acc).matches()) {
            if (smsUtil.sendSMS(acc, request)) {
                log.info("获取手机验证码成功");
                result.setSuccess("获取手机验证码成功！", null);
            } else {
                throw new SystemException("系统繁忙，请稍后重试");
            }
        } else {
            log.info("账号不合法");
            result.setFail("账号不合法");
        }
        return result;
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody @Validated(ValidationRules.register.class) UserRegisterDTO rp, BindingResult errors, HttpServletRequest request) {
        log.info("用户注册: {}", rp);
        Result<User> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (rp.getVerCode().equals(request.getSession().getAttribute(VERIFICATION_CODE_SESSION))) {
            User getUser = userService.register(rp.getUser());
            result.setSuccess("注册成功，已为您自动登录", getUser);
            request.getSession().setAttribute(USER_INFO_SESSION, getUser);
        } else {
            throw new ValidException("验证码错误");
        }
        return result;
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody @Validated(ValidationRules.login.class) User user, BindingResult errors, HttpServletRequest request) {
        log.info("用户登录 ： {}", user);
        Result<User> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        String ip = IPUtil.getIP(request);
        log.info(ip);
        User getUser = userService.login(user, ip);
        request.getSession().setAttribute(USER_INFO_SESSION, getUser);
        result.setSuccess(getUser);
        return result;
    }

    @PostMapping("/updatePwd")
    public Result<User> updatePwd(@RequestBody @Validated UpdatePwdDTO up, BindingResult errors, HttpServletRequest request) {
        log.info("修改密码 ： {}", up);
        Result<User> result = new Result<>();
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }
        if (up.getVerCode().equals(request.getSession().getAttribute(VERIFICATION_CODE_SESSION))) {
            User getUser = userService.updatePwd(up.getPassword(), up.getAccount());
            result.setSuccess(getUser);
            request.getSession().setAttribute(USER_INFO_SESSION, null);
        } else {
            throw new ValidException("验证码错误");
        }
        return result;
    }

    @GetMapping("/isLogin")
    public Result<User> isLogin(HttpServletRequest request) {
        User getUser = userService.isLogin(request.getSession());
        Result<User> result = new Result<>();
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

    @PostMapping("/verify")
    public Result<Void> verify(@RequestBody @NotBlank String code, HttpServletRequest request) {
        log.info("验证 ： {}", code);
        JSONObject jsonObject = JSON.parseObject(code);
        String acc = jsonObject.getString("code");
        Result<Void> result = new Result<>();
        if (acc.equals(request.getSession().getAttribute(VERIFICATION_CODE_SESSION))) {
            result.setSuccess();
        } else {
            result.setFail("验证码错误");
        }
        return result;
    }


    @PostMapping("/update")
    public Result<User> update(@RequestBody User user, HttpServletRequest request) {
        if (userService.isLogin(request.getSession()) == null) {
            throw new UserNotLoginException("用户未登录");
        }
        log.info("修改信息：{}", user);
        Result<User> result = new Result<>();
        User sessionUser = (User) request.getSession().getAttribute(USER_INFO_SESSION);
        log.info("修改信息：{}  -->  {}", sessionUser, user);
        User getUser = userService.updateDBO(sessionUser, user);
        result.setSuccess(getUser);
        return result;
    }

}
