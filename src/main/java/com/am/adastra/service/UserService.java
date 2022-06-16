package com.am.adastra.service;

import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
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
    User register(User user);

    User login(User user);

    User update(User user);

    User isLogin(HttpSession session);

    User updatePwd(String password,String account);

    UserDBO getDBOById(Long id);


}
