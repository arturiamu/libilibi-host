package com.am.libilibi.controller;

import com.am.libilibi.entity.LBUser;
import com.am.libilibi.entity.LBUserCollection;
import com.am.libilibi.entity.LBUserHistory;
import com.am.libilibi.service.LBUserCollectionService;
import com.am.libilibi.service.LBUserHistoryService;
import com.am.libilibi.service.LBUserService;
import com.am.libilibi.utils.Result;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
public class LBUserCollectionAPI {
    @Qualifier("LBUserServiceImpl")
    @Autowired
    private LBUserService lbUserService;


    @Qualifier("LBUserCollectionServiceImpl")
    @Autowired(required = false)
    private LBUserCollectionService lbUserCollectionService;


   /*
        用户添加视频到收藏夹中
   */

    @PostMapping("/add")
    public Result<LBUserCollection> add(@RequestBody @Valid LBUserCollection userCollection, BindingResult errors, HttpServletRequest request){
        //先判断传递的信息是否有误
        System.out.println("用户收藏============"+userCollection);
        Result<LBUserCollection> result = new Result<>();

        //前端传递过来的格式出错就直接返回
        if (errors.hasErrors()) {
            result.setResultFailed(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            return result;
        }

        //调用逻辑层将信息添加到用户的收藏夹中
        System.out.println("用户的添加信息  ：  "+userCollection);
        result = lbUserCollectionService.add(userCollection);
        return result;

    }

    /*
    * 通过用户分类的查看用户的收藏
    * */
    @GetMapping("/selectByCollection")
    public Result<List<LBUserCollection>> selectByCollection(String category,HttpServletRequest request){

//        1.获取当前用户的用户 id
        Result<LBUser> user = lbUserService.isLogin(request.getSession());
        Integer userId = user.getData().getId();

        System.out.println("用户ID： "+userId);
        System.out.println("用户分类：  "+category);

        //2.调用逻辑层获取到此用户的视频分类信息  将用户id和分类信息传递过去
        return lbUserCollectionService.selectByCollection(userId,category);
    }


}
