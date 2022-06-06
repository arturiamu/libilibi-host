package com.am.adastra.service.impl;

import com.am.adastra.entity.Admin;
import com.am.adastra.mapper.AdminMapper;
import com.am.adastra.service.AdminService;
import com.am.adastra.util.Result;
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
    public Result<Admin> login(Admin admin) {
        Result<Admin> result = new Result<>();
        //1.通过用户名查看管理员是否存在
        Admin adminUser = adminMapper.getByUsername(admin.getAdminName());
        //2.管理员存在就判断密码是否正确
        if (adminUser == null){
            //说明用户名不正确
            result.setSuccess("用户名错误",null);
        }else {
            //3.密码正确就返回登录信息
            if (admin.getPassword().equals(adminUser.getPassword())){
                result.setSuccess("登录成功",adminUser);
            }
        }

        return result;
    }


}
