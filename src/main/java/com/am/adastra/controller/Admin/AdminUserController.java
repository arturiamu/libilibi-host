package com.am.adastra.controller.Admin;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.vo.MessageVO;
import com.am.adastra.entity.vo.UserLoginLogVO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.AdminService;
import com.am.adastra.service.UserMessageService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminUserController {

    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    UserMessageService userMessageService;
    @Autowired
    UserMapper userMapper;



    /**
     * 获取所有用户的基本信息
     *      * 分页查询
     * @param cur
     * @param pageSize
     * @param username
     * @param request
     * @return
     */
    @GetMapping("/selectUser/{cur}/{pageSize}")
    public Result<Map<String,Object>> selectUser(@RequestBody @PathVariable int cur, @PathVariable int pageSize, String username, HttpServletRequest request){
        log.info("分页查询");
        Result<Map<String,Object>> result = new Result<>();
        Map<String,Object> map =new HashMap<>();
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


    /**
     * 管理员修改用户信息
     * @param userDBO
     * @return
     */
    @PostMapping("/updateUser")
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


    /**
     * 获取所有用户的登录日志
     */
    @GetMapping("/allUserLog")
    public Result<List<UserLoginLogVO>> allUserLog(){
        log.info("获取所有用户登录信息");
        Result<List<UserLoginLogVO>> result = new Result<>();
        //1.从数据库中得到所有的信息
        List<UserLoginLogVO> userLoginLogVOList = userService.loginList();
        result.setSuccess(userLoginLogVOList);
        return result;
    }

    /**
     * 获取指定uid用户的登录日志
     *
     */
    @GetMapping("/UserLog/{uid}")
    public Result<List<UserLoginLogVO>> UserLog(@PathVariable Long uid){
        Result<List<UserLoginLogVO>> result = new Result<>();
        log.info("用户id为 ---> " + uid);
        //1.从数据库中得到所有的信息
        List<UserLoginLogVO> userLoginLogVOList = userService.loginListByUid(uid);
        result.setSuccess(userLoginLogVOList);
        return result;
    }

    /**
     * 获取所有管理员发送信息
     */
    @GetMapping("/getMessage")
    public Result<List<MessageVO>> getMessage(Integer isAdmin){
        Result<List<MessageVO>> result = new Result<>();
        //1.从数据库中获取信息
        List<MessageVO> messageList = userMessageService.getAllMessage(isAdmin);
        result.setSuccess(messageList);
        return result;
    }

    /**
     * 通过用户id得到用户的完整信息
     * @param uid
     * @return
     */
    @GetMapping("/getDBOById/{uid}")
    public Result<UserDBO> getDBOById(@PathVariable Long uid){
        Result<UserDBO> result = new Result<>();
        UserDBO dboById = userService.getDBOById(uid);
        result.setSuccess(dboById);
        return result;
    }

    /**
     * 获取用户最新一次的ip
     */
    @GetMapping("/getNewestIp/{uid}")
    public Result<List<UserLoginLogVO>> getNewestIp(@PathVariable Long uid){
        Result<List<UserLoginLogVO>> result = new Result<>();
        log.info("用户id为 ---> " + uid);
        List<UserLoginLogVO> ipList = userService.ipList(uid);
        result.setSuccess(ipList);
        return result;
    }
    /**
     * 发送通知给全体成员
     */

    /**
     * 发送消息给部分成员
     */


}
