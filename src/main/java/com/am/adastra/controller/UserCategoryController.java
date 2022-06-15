package com.am.adastra.controller;

import com.am.adastra.entity.User;

import com.am.adastra.entity.dto.UserCategoryAddDTO;
import com.am.adastra.entity.vo.UserCategorySimpleVO;
import com.am.adastra.service.UserCategoryService;
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
用户收藏夹分类的controller
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class UserCategoryController {
    @Autowired(required = false)
    private UserCategoryService userCategoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result<UserCategorySimpleVO> add(@RequestBody @Valid UserCategoryAddDTO userCategory, BindingResult errors, HttpServletRequest request){
        System.out.println("收藏夹名称---------->" + userCategory.getCategoryName());
        Result<UserCategorySimpleVO> result = new Result<>();

        if (errors.hasErrors()){
            result.setSuccess(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage(),null);
            return result;
        }

        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Long userId = user.getId();
        log.info("用户id:" + userId);
        userCategory.setUid(userId);

        //调用逻辑层将信息添加到用户的收藏夹中
        result = userCategoryService.add(userCategory);
        return result;
    }

    @GetMapping("/selectByCategory")
    public Result<List<UserCategorySimpleVO>> selectById(HttpServletRequest request){
        User user = userService.isLogin(request.getSession());
        Long userId = user.getId();

        log.info("用户ID： "+userId);

        return userCategoryService.selectById(userId);
    }
}
