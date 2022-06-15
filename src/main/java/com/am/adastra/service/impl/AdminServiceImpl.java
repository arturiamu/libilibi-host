package com.am.adastra.service.impl;

import com.am.adastra.controller.Admin.AdminController;
import com.am.adastra.controller.UserController;
import com.am.adastra.entity.Admin;
import com.am.adastra.entity.User;
import com.am.adastra.ex.LoginException;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.mapper.AdminMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Autowired
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
    public User isLogin(HttpSession session) {
        User sessionUser = (User) session.getAttribute(AdminController.USER_INFO_SESSION);
        if (sessionUser == null) {
            throw new UserNotLoginException("用户未登录");
        }
        return sessionUser;
    }

    /*
    * 分页查询用户信息
    * */
    @Override
    public List<User> selectUser(int cur, int pageSize) {
        return null;
    }


}
