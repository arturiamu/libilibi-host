package com.am.adastra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/5 15:59
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/addSession")
    public void addSession(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
    }

    @GetMapping("/get")
    public void getTest(HttpServletRequest request) {
        System.out.println(request.getSession().getId());
    }
}
