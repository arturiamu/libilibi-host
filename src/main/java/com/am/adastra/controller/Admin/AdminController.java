package com.am.adastra.controller.Admin;



import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.am.adastra.entity.Admin;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.dto.AdminDTO;
import com.am.adastra.entity.vo.AdminVO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.service.AdminService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.POJOUtils;
import com.am.adastra.util.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
 * 管理员C
 * */
@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员模块")
public class AdminController {
    public static final String USER_INFO_SESSION = "userInfoSession";
//    public static final String SESSION_NAME = "userInfo";
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    /*
     * 管理员登录
     * */
    @ApiOperation("管理员登录")
    @ApiOperationSupport(order = 0)
    @PostMapping("/login")
    public Result<AdminVO> login(@RequestBody @Valid Admin admin, BindingResult errors, HttpServletRequest request) {
        log.info("管理员的登录信息 : {}", admin);
        Result<AdminVO> result = new Result<>();
        if (errors.hasErrors()) {
            log.info("用户信息输入错误");
            result.setSuccess(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage(), null);
            return result;
        }
        AdminVO getAdmin = adminService.login(admin);

        // 赋值同名属性
        BeanUtils.copyProperties(getAdmin,admin);
        result.setSuccess(getAdmin);

        request.getSession().setAttribute(USER_INFO_SESSION, admin);

        return result;
    }

    /*
    * 判断当前管理员是否登录
    * */
    @ApiOperation("判断管理员是否登录")
    @ApiOperationSupport(order = 5)
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
    @ApiOperation("退出登录")
    @ApiOperationSupport(order = 15)
    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        log.info("用户退出登录");
        Result<Void> result = new Result<>();
        result.setSuccess("用户退出登录成功！", null);
        request.getSession().setAttribute(USER_INFO_SESSION, null);
        return result;
    }

    /**
     * 修改管理员的自己的个人信息
     * @param request
     * @return
     */
    @ApiOperation("更新管理员信息")
    @ApiOperationSupport(order = 20)
    @PostMapping("/updateAdmin")
    public Result<Void> updateAdmin(@RequestBody AdminDTO adminDTO, HttpServletRequest request){

        Result<Void> result = new Result<>();

        log.info("修改的信息" +adminDTO );

        //1.获取当前管理员的id
        Admin admin = adminService.isLogin(request.getSession());
        Long adminId = admin.getId();
        //2.调用adminServer修改管理员信息
        adminDTO.setId(adminId);
        adminService.updateAdmin(adminDTO);

        result.setSuccess("修改完成");

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
















