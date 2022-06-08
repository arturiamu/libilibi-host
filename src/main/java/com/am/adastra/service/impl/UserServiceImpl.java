package com.am.adastra.service.impl;

import com.am.adastra.controller.ItemController;
import com.am.adastra.controller.UserController;
import com.am.adastra.entity.Item;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.ex.*;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.UserService;
import com.am.adastra.util.POJOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(User user) {
        UserDBO getUser = userMapper.getDBOByUsername(user.getUsername());
        if (getUser != null) {
            throw new UsernameDuplicateException();
        }
        getUser = userMapper.getDBOByAccount(user.getAccount());
        if (getUser != null) {
            throw new AccountRegisteredException();
        }
        if (user.getItems() == null || user.getItems().length == 0) {
            log.info("default items");
            user.setItems(ItemController.defaultItems.toArray(new Item[0]));
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userMapper.addDBO(POJOUtils.userToDB(user));
        return user;
    }

    @Override
    public User login(User user) {
        UserDBO getUser = userMapper.getDBOByAccount(user.getAccount());
//        UserDBO getUser = userMapper.getDBOByUsername(user.getUsername());
        if (getUser == null) {
            throw new UsernameDoesNotExistException();
        }
        if (!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            throw new PasswordNotMatchException();
        }
        return POJOUtils.DBToUser(getUser);
    }

    @Override
    public User update(User user) {
        UserDBO getUser = userMapper.getDBOById(user.getId());
        return null;
    }

    @Override
    public User isLogin(HttpSession session) {
        User sessionUser = (User) session.getAttribute(UserController.USER_INFO_SESSION);
        if (sessionUser == null) {
            throw new UserNotLoginException();
        }
        UserDBO getUserDB = userMapper.getDBOById(sessionUser.getId());
        if (getUserDB == null || !getUserDB.getPassword().equals(sessionUser.getPassword())) {
            throw new InvalidUserInformationException();
        }
        return POJOUtils.DBToUser(getUserDB);
    }

//    @Override
//    public Result<User> update(User user) throws Exception {
//        Result<User> result = new Result<>();
//        UserDBO getUser = userMapper.getById(user.getId());
//        if (getUser == null) {
//            result.setFail("用户不存在！", State.ERR_NO_USER);
//            return result;
//        }
//        if (!StringUtils.isEmpty(user.getPassword())) {
//            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
//        }
//        ClassExamine.objectOverlap(user, getUser);
//        userMapper.update(user);
//        result.setSuccess("修改成功！", user);
//        return result;
//    }
}
