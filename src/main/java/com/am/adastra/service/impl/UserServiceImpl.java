package com.am.adastra.service.impl;

import com.am.adastra.controller.ItemController;
import com.am.adastra.controller.UserController;
import com.am.adastra.entity.Item;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.dto.UserCategoryAddDTO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.ex.*;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.UserCategoryService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.POJOUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserCategoryService userCategoryService;

    @Override
    public User register(User user) {
        log.info("user register:{}", user);
        UserDBO getUser = userMapper.getDBOByUsername(user.getUsername());
        if (getUser != null) {
            throw new RegisterException("用户名已存在");
        }
        getUser = userMapper.getDBOByAccount(user.getAccount());
        if (getUser != null) {
            throw new RegisterException("该号码已经注册");
        }
        if (user.getItems() == null || user.getItems().length == 0) {
            log.info("default items");
            user.setItems(ItemController.defaultItems.toArray(new Item[0]));
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        if (userMapper.addDBO(POJOUtils.userToDB(user)) == 1) {
            UserDBO userDBO = userMapper.getDBOByUsername(user.getUsername());
            UserCategoryAddDTO userCategoryAddDTO = new UserCategoryAddDTO();
            userCategoryAddDTO.setCategoryName("默认收藏夹");
            userCategoryAddDTO.setUid(userDBO.getId());
            userCategoryService.add(userCategoryAddDTO);
            log.info("新用户注册：{}",userDBO);
            return POJOUtils.DBToUser(userDBO);
        }
        throw new SystemException("系统繁忙，请稍后重试");
    }

    @Override
    public User login(User user) {
        UserDBO getUser = userMapper.getDBOByAccount(user.getAccount());
        if (getUser == null) {
            throw new LoginException("该账号不存在");
        }
        if (!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            throw new LoginException("密码错误");
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
            throw new UserNotLoginException("用户未登录");
        }
        UserDBO getUserDB = userMapper.getDBOById(sessionUser.getId());
        if (getUserDB == null) {
            throw new IllegalOperationException("非法操作");
        }
        return POJOUtils.DBToUser(getUserDB);
    }

    @Override
    public User updatePwd(String password, String account) {
        UserDBO getUser = userMapper.getDBOByAccount(account);
        if (getUser == null) {
            throw new IllegalOperationException("目标用户不存在");
        }
        String pass = DigestUtils.md5Hex(password);
        if (userMapper.updatePwd(pass, getUser.getId()) == 1) {
            userMapper.passwordBack(pass, getUser.getId());
            getUser.setPassword(pass);
            return POJOUtils.DBToUser(getUser);
        } else {
            throw new SystemException("系统繁忙，请稍后重试");
        }
    }

    @Override
    public UserDBO getDBOById(Long id) {
        UserDBO dboById = userMapper.getDBOById(id);
        return dboById;
    }

    @Override
    public List<UserVO> list() {
        return userMapper.list();
    }


}
