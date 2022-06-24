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
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 修改管理员的个人信息
     * @param request
     * @return
     */
    @PostMapping("/updateAdmin")
    public Result<Void> updateAdmin(@RequestBody AdminDTO AdminDTO, HttpServletRequest request){

        //1.获取当前管理员的id
        //2.调用adminServer修改管理员信息


        return null;
    }

    /*
    * 获取所有用户的基本信息
    * 分页查询
    * */
    @GetMapping("/selectUser/{cur}/{pageSize}")
    public Result<Map<String,Object>> selectUser(@RequestBody @PathVariable int cur, @PathVariable int pageSize, String username, HttpServletRequest request){
        log.info("分页查询");
        Result<Map<String,Object>> result = new Result<>();
        Map<String,Object> map =new  HashMap<>();
        //1.先判断当前用户是否登录
//        Admin getAdmin = adminService.isLogin(request.getSession());
        log.info("参数用户名 === > "+username);
        if (username != null) username = "%"+username+"%";
        //2.分页查询，获取部分用户数据
        List<UserVO> userList = adminService.selectUser(cur, pageSize,username);
        //3.获取用户总数量
        Integer total = adminService.selectTotal();
        map.put("data",userList);
        map.put("total",total);
        result.setSuccess(map);
        return result;
    }


    /*
    * 管理员修改用户信息
    * */
    @PostMapping ("/updateUser")
    public Result<User> updateUser(@RequestBody UserDBO userDBO) {
        Result<User> result = new Result<>();
        log.info(userDBO.toString());
//        UserDBO userDBO = POJOUtils.userToDB(user);
        log.info(userDBO.toString());

        int i = adminService.updateUser(userDBO);
        if (i>0){
            result.setMessage("用户信息修改成功");
            return result;
        }
        log.info("用户信息修改失败");
        result.setMessage("用户信息修改失败");
        return result;
    }

    /**
     * 改变用户的状态，正常设置为禁用，禁用设置为正常
     * @param uid
     * @return
     */
    @GetMapping("/changeState/{uid}")
    public Result<Void> changeState(@PathVariable Long uid){
        Result<Void> result = new Result<>();
        log.info("用户id为 ---> " + uid);
        adminService.changeState(uid);
        result.setSuccess("修改成功",null);
        return result;
    }

    /**
     * 管理员下载所有用户信息
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/export")
    public Result<Void> export(HttpServletResponse response) throws Exception{
        Result<Void> result = new Result<>();
        //1.从数据库查出所有用户数据
        List<UserVO> userList = userService.list();
        //2.操作内存，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //3.自定义标题别名
        writer.addHeaderAlias("username","用户名");

        //4.一次性写list内的对象到excel中，使用默认样式，强制输出标题
        writer.write(userList,true);

        // 5.设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

        //6.操作成功
        result.setSuccess();
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
















