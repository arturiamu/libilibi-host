package com.am.adastra.service.impl;

import com.am.adastra.entity.Admin;
import com.am.adastra.ex.LoginException;
import com.am.adastra.mapper.AdminMapper;
import com.am.adastra.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)
    private AdminMapper adminMapper;

    /*
     * 处理管理员登录
     * */
    @Override
    public Admin login(Admin admin) {
        Admin adminUser = adminMapper.getByUsername(admin.getAdminName());
        //2.管理员存在就判断密码是否正确
        if (adminUser == null) {
            //说明用户名不正确
            throw new LoginException("");
        } else {
            if (admin.getPassword().equals(adminUser.getPassword())) {
                return adminUser;
            }
        }
        return null;
    }


}
