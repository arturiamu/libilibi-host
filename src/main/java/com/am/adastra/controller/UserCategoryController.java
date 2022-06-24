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
    public Result<Void> add(@RequestBody @Valid UserCategoryAddDTO userCategory, BindingResult errors, HttpServletRequest request) {
        log.info("收藏夹名称---------->{}", userCategory.getCategoryName());
        Result<Void> result = new Result<>();
        if (errors.hasErrors()) {
            result.setSuccess(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage(), null);
            return result;
        }

        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Long userId = user.getId();
        log.info("用户id:" + userId);
        userCategory.setUid(userId);

        if (userCategoryService.add(userCategory)) {
            result.setSuccess();
        }
        return result;
    }

    @GetMapping("/selectByCategory")
    public Result<List<UserCategorySimpleVO>> selectById(HttpServletRequest request) {
        Result<List<UserCategorySimpleVO>> result = new Result<>();
        User user = userService.isLogin(request.getSession());
        Long userId = user.getId();
        log.info("用户ID： " + userId);
        result.setSuccess(userCategoryService.selectById(userId));
        return result;
    }

    @GetMapping("/clear/{categoryName}")
    public Result<Void> clear(HttpServletRequest request, @PathVariable String categoryName) {
        Result<Void> result = new Result<>();
        User user = userService.isLogin(request.getSession());
        userCategoryService.clear(user.getId(), categoryName);
        result.setSuccess();
        return result;
    }

    @GetMapping("/del/{categoryName}")
    public Result<Void> del(HttpServletRequest request, @PathVariable String categoryName) {
        Result<Void> result = new Result<>();
        User user = userService.isLogin(request.getSession());
        userCategoryService.del(user.getId(), categoryName);
        result.setSuccess();
        return result;
    }
}
