package com.am.adastra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/9 14:28
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
public class ModeController {
    @GetMapping("/player/{video}")
    public void history(@PathVariable String video) throws UnsupportedEncodingException {
        System.out.println(video);
        System.out.println(URLDecoder.decode(video));
        System.out.println(URLDecoder.decode(video, "utf8"));
    }
}
