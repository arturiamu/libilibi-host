package com.am.libilibi.controller;


import com.am.libilibi.entity.LBUser;
import com.am.libilibi.entity.LBUserHistory;
import com.am.libilibi.service.LBUserHistoryService;
import com.am.libilibi.service.LBUserService;
import com.am.libilibi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/*
* 用户浏览历史的controller
* */
@RestController
@RequestMapping("/history")
public class LBUserHistoryAPI {
    public static final String SESSION_NAME = "userHistory";

    @Qualifier("LBUserHistoryServiceImpl")
    @Autowired(required = false)
    private LBUserHistoryService lbUserHistoryService;


    //添加用户的历史浏览信息
    @PostMapping("/add")
    public Result<LBUserHistory> add(@RequestBody @Valid LBUserHistory userHistory, BindingResult errors, HttpServletRequest request){
        System.out.println("用户历史============"+userHistory);
        Result<LBUserHistory> result = new Result<>();

        //前端传递过来的格式出错就直接返回
        if (errors.hasErrors()) {
            result.setResultFailed(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            return result;
        }

        //调用业务层逻辑将当前信息添加到数据库中
        return lbUserHistoryService.add(userHistory);

  /*      //不懂
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }

        return result;*/

    }


    //通过用户id查询
    // 返回所有的用户历史信息（按照时间排序）
    @GetMapping("/select")
    public Result<List<LBUserHistory>> select(Integer userId){

        //调用业务层查询所有的用户历史信息
        return lbUserHistoryService.select(userId);
    }




}
