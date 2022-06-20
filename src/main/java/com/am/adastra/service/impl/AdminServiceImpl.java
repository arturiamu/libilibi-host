package com.am.adastra.service.impl;

import com.am.adastra.controller.Admin.AdminController;
import com.am.adastra.controller.UserController;
import com.am.adastra.entity.Admin;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.ex.LoginException;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.mapper.AdminMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Resource
    private UserMapper userMapper;

    /*
     * 处理管理员登录
     * */
    @Override
    public Admin login(Admin admin) {
        Admin adminUser = adminMapper.getByUsername(admin.getUsername());
        //2.管理员存在就判断密码是否正确
        if (adminUser == null) {
            //说明用户名不正确
            throw new LoginException("用户名不存在");
        } else {
            if (admin.getPassword().equals(adminUser.getPassword())) {
                return adminUser;
            }else {
                throw new LoginException("密码错误");
            }
        }
    }

    /*
    * 判断用户是否登录
    * */
    @Override
    public Admin isLogin(HttpSession session) {
        Admin sessionUser = (Admin) session.getAttribute(AdminController.USER_INFO_SESSION);
        if (sessionUser == null) {
            throw new UserNotLoginException("用户未登录");
        }
        return sessionUser;
    }



    /**
     * 分页查询用户信息
     * @param cur  第几页
     * @param pageSize  每页有多少条信息
     * @return
     */
    @Override
    public List<User> selectUser(int cur, int pageSize) {
        log.info("分页查询用户信息  selectUser() ---> ");

        //调用userMapper层查询数据
        List<User> userList = userMapper.selectPage(cur*pageSize,pageSize);

        log.info("分页查询到的数据 -->" + userList);
        return userList;
    }

    @Override
    public int updataUser(UserDBO userDBO) {
        String password = userDBO.getPassword();
        userDBO.setPassword(DigestUtils.md5Hex(password));
        return adminMapper.updataUser(userDBO);
    }


}
