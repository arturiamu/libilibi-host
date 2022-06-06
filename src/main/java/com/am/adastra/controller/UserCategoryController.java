package com.am.adastra.controller;


import com.am.adastra.entity.User;
import com.am.adastra.entity.UserCategory;
import com.am.adastra.service.UserCategoryService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
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
public class UserCategoryController {

    @Autowired
    private UserCategoryService lbUserCategoryService;


    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public Result<UserCategory> add(@RequestBody @Valid UserCategory userCategory, BindingResult errors, HttpServletRequest request){
        System.out.println("收藏夹名称---------->" + userCategory.getCategoryName());
        Result<UserCategory> result = new Result<>();

        if (errors.hasErrors()){
            result.setFail(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            return result;
        }

        //  获取当前用户的用户 id
        System.out.println("aaaaaaaaaa" + request.getSession().toString());
        User sessionUser = (User) request.getSession().getAttribute(UserController.SESSION_NAME);
        System.out.println(sessionUser);
//        Result<User> user =   userService.isLogin(request.getSession());
//        System.out.println("BBBBBBBBBBBBB "+user);
//        Integer userId = user.getData().getId();
        Integer userId = sessionUser.getId();
        System.out.println("用户id:" + userId);
        userCategory.setUserId(userId);

        //调用逻辑层将信息添加到用户的收藏夹中
        result = lbUserCategoryService.add(userCategory);
        System.out.println();
        System.out.println("hhhhhhhhhhh"+result);
        return result;


    }
    @GetMapping("/selectCategory")
    public Result<List<UserCategory>> selectById(HttpServletRequest request){
        Result<User> user = userService.isLogin(request.getSession());
        Integer userId = user.getData().getId();

        System.out.println("用户ID： "+userId);


        return lbUserCategoryService.selectById(userId);
    }
}
