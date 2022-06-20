package com.am.adastra.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/17 11:10
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
@RestController
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/view/{img}")
    public void view(@PathVariable String img, HttpServletRequest request, HttpServletResponse response) {
        log.info(img);
    }
}
