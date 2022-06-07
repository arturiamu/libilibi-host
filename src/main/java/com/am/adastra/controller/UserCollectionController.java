package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.UserCollection;
import com.am.adastra.service.UserCollectionService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/*
 * 用户收藏夹的controller
 * */
@RestController
@RequestMapping("/collection")
public class UserCollectionController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserCollectionService userCollectionService;


     /*
        用户添加视频到收藏夹中
   */
    @PostMapping("/add")
    public Result<UserCollection> add(@RequestBody @Valid UserCollection userCollection, BindingResult errors, HttpServletRequest request){
        //先判断传递的信息是否有误
        System.out.println("用户收藏============"+userCollection);
        Result<UserCollection> result = new Result<>();

        //前端传递过来的格式出错就直接返回
        if (errors.hasErrors()) {
            result.setSuccess(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage(),null);
            return result;
        }

        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Integer userId = user.getId();
        userCollection.setUserId(userId);

        //调用逻辑层将信息添加到用户的收藏夹中
        result = userCollectionService.add(userCollection);
        return result;

    }


    /*
     * 通过用户分类的查看用户的收藏
     * */
    @GetMapping("/selectByCollection")
    public Result<List<UserCollection>> selectByCollection(String category, HttpServletRequest request){

//        1.获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Integer userId = user.getId();

        System.out.println("用户ID： "+userId);
        System.out.println("用户分类：  "+category);

        //2.调用逻辑层获取到此用户的视频分类信息  将用户id和分类信息传递过去
        return userCollectionService.selectByCollection(userId,category);
    }

}