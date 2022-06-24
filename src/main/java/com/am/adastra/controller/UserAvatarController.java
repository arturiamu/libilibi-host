package com.am.adastra.controller;

import com.am.adastra.service.UserAvatarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 21:21
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
@RequestMapping("/avatar")
public class UserAvatarController {
    @Resource
    UserAvatarService userAvatarService;
}
