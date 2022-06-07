package com.am.adastra.controller;

import com.am.libilibi.entity.LBUser;
import com.am.libilibi.entity.LBUserCategory;
import com.am.libilibi.service.LBUserCategoryService;
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
用户收藏夹分类的controller
 */
@RestController
@RequestMapping("/category")
public class LBUserCategoryAPI {
    @Qualifier("LBUserCategoryServiceImpl")
    @Autowired(required = false)
    private LBUserCategoryService lbUserCategoryService;

    @Qualifier("LBUserServiceImpl")
    @Autowired
    private LBUserService lbUserService;


    @PostMapping("/add")
    public Result<LBUserCategory> add(@RequestBody @Valid LBUserCategory userCategory, BindingResult errors, HttpServletRequest request){
        System.out.println("收藏夹名称---------->" + userCategory.getCategoryName());
        Result<LBUserCategory> result = new Result<>();

        if (errors.hasErrors()){
            result.setResultFailed(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            return result;
        }

        //  获取当前用户的用户 id
        Result<LBUser> user = lbUserService.isLogin(request.getSession());
        Integer userId = user.getData().getId();
        System.out.println("用户id:" + userId);
        userCategory.setUserId(userId);

        //调用逻辑层将信息添加到用户的收藏夹中
        result = lbUserCategoryService.add(userCategory);
        return result;


    }
    @GetMapping("/selectByCategory")
    public Result<List<LBUserCategory>> selectById(HttpServletRequest request){
        Result<LBUser> user = lbUserService.isLogin(request.getSession());
        Integer userId = user.getData().getId();

        System.out.println("用户ID： "+userId);


        return lbUserCategoryService.selectById(userId);
    }
}
