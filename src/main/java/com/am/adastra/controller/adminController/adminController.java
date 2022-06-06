package com.am.adastra.controller.adminController;


import com.am.adastra.entity.Admin;
import com.am.adastra.util.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/*
* 管理员C
* */
@RestController
@RequestMapping("/admin")
public class adminController {

    /*
    * 管理员登录
    * */
    @PostMapping("/login")
    public Result<Admin> login(@Valid Admin admin, BindingResult errors, HttpServletRequest request){
        

        return null;
    }

}
