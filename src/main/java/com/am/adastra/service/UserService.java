package com.am.adastra.service;

import com.am.adastra.entity.User;
import com.am.adastra.util.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 11:23
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Service
public interface UserService {
    Result<User> register(User user);

    Result<User> login(User user);

    Result<User> update(User user) throws Exception;

    Result<User> isLogin(HttpSession session);
}
