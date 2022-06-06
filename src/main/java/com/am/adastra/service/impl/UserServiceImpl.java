package com.am.adastra.service.impl;

import com.am.adastra.controller.UserController;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDB;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.UserService;
import com.am.adastra.util.ClassExamine;
import com.am.adastra.util.POJOUtils;
import com.am.adastra.util.Result;
import com.am.adastra.util.State;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 11:25
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<User> register(User user) {
        Result<User> result = new Result<>();
        UserDB userDB = POJOUtils.userToDB(user);
        UserDB getUser = userMapper.getByUsername(userDB.getUsername());
        if (getUser != null) {
            result.setFail("用户名已存在！", State.ERR_USERNAME_EXISTED);
            return result;
        }
        userDB.setPassword(DigestUtils.md5Hex(userDB.getPassword()));
        userMapper.addDB(userDB);
        result.setSuccess(POJOUtils.DBToUser(userDB));
        return result;
    }

    @Override
    public Result<User> login(User user) {
        Result<User> result = new Result<>();
        UserDB getUser = userMapper.getByUsername(user.getUsername());
        if (getUser == null) {
            result.setFail("用户不存在！", State.ERR_NO_USER);
            return result;
        }
        if (!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            result.setFail("密码错误！", State.ERR_PASSWORD);
            return result;
        }
        result.setSuccess(POJOUtils.DBToUser(getUser));
        return result;
    }

    @Override
    public Result<User> update(User user) throws Exception {
        Result<User> result = new Result<>();
        UserDB getUser = userMapper.getById(user.getId());
        if (getUser == null) {
            result.setFail("用户不存在！", State.ERR_NO_USER);
            return result;
        }
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }
        ClassExamine.objectOverlap(user, getUser);
        userMapper.update(user);
        result.setSuccess("修改成功！", user);
        return result;
    }

    @Override
    public Result<User> isLogin(HttpSession session) {
        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz  "+session);
        Result<User> result = new Result<>();
        User sessionUser = (User) session.getAttribute(UserController.SESSION_NAME);
        if (sessionUser == null) {
            result.setFail("用户未登录！");
            return result;
        }
        UserDB getUser = userMapper.getByUsername(sessionUser.getUsername());
        System.out.println("getUser : "+getUser);
        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
            result.setFail("用户信息无效！");
            return result;
        }
        result.setSuccess("用户已登录！", POJOUtils.DBToUser(getUser));
        return result;
    }
}
