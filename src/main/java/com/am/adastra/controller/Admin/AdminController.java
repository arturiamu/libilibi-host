package com.am.adastra.controller.Admin;


import com.am.adastra.entity.Admin;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.service.AdminService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.POJOUtils;
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
 * 管理员C
 * */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    public static final String USER_INFO_SESSION = "userInfoSession";
//    public static final String SESSION_NAME = "userInfo";
    @Autowired
    AdminService adminService;

    /*
     * 管理员登录
     * */
    @PostMapping("/reg")
    public Result<Admin> login(@RequestBody @Valid Admin admin, BindingResult errors, HttpServletRequest request) {
        log.info("管理员的登录信息 : {}", admin);
        Result<Admin> result = new Result<>();
        if (errors.hasErrors()) {
            log.info("用户信息输入错误");
            result.setSuccess(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage(), null);
            return result;
        }
        Admin getAdmin = adminService.login(admin);

        result.setSuccess(getAdmin);

        request.getSession().setAttribute(USER_INFO_SESSION, result.getData());

        return result;
    }

    /*
    * 判断当前用户是否登录
    * */
    @GetMapping("/isLogin")
    public Result<Admin> isLogin(HttpServletRequest request) {
        log.info("判断用户是否登录 --->  ");
        Admin getAdmin = adminService.isLogin(request.getSession());
        Result<Admin> result = new Result<>();
        result.setSuccess(getAdmin);
        return result;
    }

    /*
    * 退出登录
    * */
    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        log.info("用户退出登录");
        Result<Void> result = new Result<>();
        result.setSuccess("用户退出登录成功！", null);
        request.getSession().setAttribute(USER_INFO_SESSION, null);
        return result;
    }

    /*
    * 获取所有用户的基本信息
    * 分页查询
    * */
    @GetMapping("/selectUser/{cur}/{pageSize}")
    public Result<List<User>> selectUser(@RequestBody @PathVariable int cur, @PathVariable int pageSize, HttpServletRequest request){
        log.info("分页查询");
        Result<List<User>> result = new Result<>();
        //1.先判断当前用户是否登录
        Admin getAdmin = adminService.isLogin(request.getSession());
        //2.分页查询，获取所有部分用户数据
        List<User> userList = adminService.selectUser(cur, pageSize);
        result.setSuccess(userList);
        return result;
    }


    /*
    * 管理员修改用户信息
    * */
    @PostMapping ("/updataUser")
    public Result<User> updataUser(@RequestBody User user) {
        Result<User> result = new Result<>();
        log.info(user.toString());

        UserDBO userDBO = POJOUtils.userToDB(user);
        log.info(userDBO.toString());

        int i = adminService.updataUser(userDBO);
        if (i>0){
            result.setMessage("用户信息修改成功");
            return result;
        }
        log.info("用户信息修改失败");
        result.setMessage("用户信息修改失败");
        return result;
    }


    /*
    * 根据传来的数量，查询从今天开始，近几天的用户人数
    * 返回的内容包括每天的用户人数，和当天的日期
    * */




    /*
    *分页查询所有视频
    * */





}
















