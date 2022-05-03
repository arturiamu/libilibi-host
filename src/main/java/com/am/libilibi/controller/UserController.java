package com.am.libilibi.controller;

import com.am.libilibi.entity.User;
import com.am.libilibi.mapper.UserMapper;
import com.am.libilibi.utils.ReturnData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/1 17:03
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ReturnData login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return new ReturnData("请输入有效的用户名和密码", -1, null);
        }
        User user = userMapper.getUserByName(username);
        if (user == null) {
            return new ReturnData("用户不存在", -1, null);
        } else {
            if (user.getPassword().equals(password)) {
                return new ReturnData("登录成功", 1, user);
            } else {
                return new ReturnData("密码错误", 0, null);
            }
        }
    }
}
