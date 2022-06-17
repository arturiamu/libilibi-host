package com.am.adastra.controller;


import com.am.adastra.entity.Admin;
import com.am.adastra.service.AdminService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

/*
 * 管理员C
 * */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    public static final String SESSION_NAME = "userInfo";
    @Resource
    AdminService adminService;

    /*
     * 管理员登录
     * */
    @PostMapping("/reg")
    public Result<Admin> login(@RequestBody @Valid Admin admin, BindingResult errors, HttpServletRequest request) {
        log.info("管理员的登录信息 : {}", admin);
        Result<Admin> result = new Result<>();
        if (errors.hasErrors()) {
            log.info("用户信息输入错误");
            result.setSuccess(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage(), null);
            return result;
        }
        Admin getAdmin = adminService.login(admin);
        result.setSuccess(getAdmin);
        request.getSession().setAttribute(SESSION_NAME, result.getData());
        return result;
    }

}
