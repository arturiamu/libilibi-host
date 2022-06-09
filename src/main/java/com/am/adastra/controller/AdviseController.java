package com.am.adastra.controller;

import com.am.adastra.entity.Advise;
import com.am.adastra.entity.User;
import com.am.adastra.pojo.DTO.AdviseAddDTO;
import com.am.adastra.service.AdviseService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 7:53
 * @Description:
 */
@RestController
@RequestMapping("/advise")
public class AdviseController {

    @Autowired
    private AdviseService adviseService;
    @Autowired
    private UserService userService;

    //添加用户的建议
    @PostMapping("/add")
    public Result<Advise> advise(@RequestBody AdviseAddDTO advise, HttpServletRequest request) {
        //首先输出用户的建议
        System.out.println("用户建议============"+advise);
        Result<Advise> result = new Result<>();

        //判断是否有用户信息
        if (request.getSession().getAttribute(UserController.USER_INFO_SESSION) != null){
            System.out.println("用户建议============"+advise);
            //不为空的话就获取当前用户的用户id
            User user = userService.isLogin(request.getSession());
            Integer userId = user.getId();
            advise.setUserId(userId);

            //调用逻辑层用户建议添加到数据库中
            return adviseService.add(advise);
        }else{
            System.out.println("......................");
            return adviseService.add(advise);
        }
    }

    //通过id来查询
    @GetMapping("/select")
    public Result<List<Advise>> select(HttpServletRequest request){
        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Integer userId = user.getId();

        System.out.println("查询观看历史的用户ID是 ： " + userId);
        //调用业务层查询所有的用户历史信息
        return adviseService.selectById(userId);
    }

}
