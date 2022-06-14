package com.am.adastra.controller;

import com.am.adastra.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/advise")
public class AdviseController {


    /**
     * 接收意见反馈的post请求
     */

    public void acceptAdvice(@RequestBody @Valid User user, HttpServletRequest request){
        //1.获取用户信息

        //2.将用户输入的advise 保存在session中

    }

}
