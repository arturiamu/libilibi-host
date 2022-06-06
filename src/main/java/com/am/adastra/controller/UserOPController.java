package com.am.adastra.controller;

import com.am.adastra.entity.UserOPO;
import com.am.adastra.entity.param.ValidationRules;
import com.am.adastra.util.Result;
import com.am.adastra.util.State;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/6 20:39
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
@RequestMapping("/operate")
public class UserOPController {

    @PostMapping("/play")
    public void play(@RequestBody @Validated(ValidationRules.userOP.class) UserOPO userOPO, BindingResult errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return;
        }
        System.out.println(1);
    }
}
