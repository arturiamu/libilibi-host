package com.am.adastra.controller;


import com.am.adastra.entity.User;
import com.am.adastra.entity.UserHistory;
import com.am.adastra.pojo.DTO.UserHistoryAddDTO;
import com.am.adastra.service.UserHistoryService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/*
 * 用户浏览历史的controller
 * */
@Slf4j
@RestController
@RequestMapping("/history")
public class UserHistoryController {
    public static final String SESSION_NAME = "userHistory";
    @Autowired
    private UserService userService;
    @Autowired
    private UserHistoryService userHistoryService;

    //添加用户的历史浏览信息
    @PostMapping("/add")
    public Result<UserHistory> add(@RequestBody @Valid UserHistoryAddDTO userHistory, BindingResult errors, HttpServletRequest request){
        log.info("用户新增历史:{}",userHistory);
        Result<UserHistory> result = new Result<>();

        //前端传递过来的格式出错就直接返回
        if (errors.hasErrors()) {
            result.setSuccess(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage(),null);
            return result;
        }

        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Integer userId = user.getId();
        userHistory.setUserId(userId);

        //调用业务层逻辑将当前信息添加到数据库中
        return userHistoryService.add(userHistory);

    }

    //通过用户id查询
    // 返回所有的用户历史信息（按照时间排序）
    @GetMapping("/select")
    public Result<List<UserHistory>> select(HttpServletRequest request){
        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Integer userId = user.getId();

        log.info("查询观看历史的用户ID是:{}",userId);
        //调用业务层查询所有的用户历史信息
        return userHistoryService.select(userId);
    }


}
