package com.am.adastra.service.impl;

import com.am.adastra.controller.ItemController;
import com.am.adastra.controller.UserController;
import com.am.adastra.entity.Item;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.dto.MessageDTO;
import com.am.adastra.entity.dto.UserCategoryAddDTO;
import com.am.adastra.entity.vo.UserLoginLogVO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.ex.*;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.UserAvatarService;
import com.am.adastra.service.UserCategoryService;
import com.am.adastra.service.UserMessageService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.ClassExamine;
import com.am.adastra.util.POJOUtils;
import com.am.adastra.util.VideoPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

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
    VideoPool videoPool;

    @Resource
    private UserCategoryService userCategoryService;

    @Resource
    private UserMessageService userMessageService;

    @Resource
    private UserAvatarService userAvatarService;

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
            log.info("新用户注册：{}", userDBO);

            int idx = new Random().nextInt(VideoPool.DEFAULT_AVATAR.size());
            userAvatarService.addAvatar(userDBO.getId(), VideoPool.DEFAULT_AVATAR.get(idx));
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setSendUserName(UserController.AD_ASTRA);
            messageDTO.setText(UserController.WELCOME);
            messageDTO.setTargetUserId(userDBO.getId());
            messageDTO.setSendUserId(0L);
            userMessageService.sendMessage(messageDTO);

            return POJOUtils.DBToUser(userDBO);
        }
        throw new SystemException("系统繁忙，请稍后重试");
    }

    @Override
    public User login(User user,String ip) {
        UserDBO getUser = userMapper.getDBOByAccount(user.getAccount());
        if (getUser == null) {
            throw new LoginException("该账号不存在");
        }
        if (!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            throw new LoginException("密码错误");
        }
        UserLoginLogVO userLoginLogVO = new UserLoginLogVO();
        userLoginLogVO.setUid(getUser.getId());
        userLoginLogVO.setIp(ip);

        this.addLoginLog(userLoginLogVO);
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
    public User updateDBO(User old, User new_) {
        old.setUsername(new_.getUsername());
        old.setItems(new_.getItems());
        int i = userMapper.updateDBO(POJOUtils.userToDB(old));
        if (i == 1) {
            return old;
        }
        throw new SystemException("系统繁忙，请稍后再试");
    }

    @Override
    public UserDBO getDBOById(Long id) {
        return userMapper.getDBOById(id);
    }

    @Override
    public List<UserVO> list() {
        return userMapper.list();
    }

    @Override
    public List<UserLoginLogVO> loginList() {
        return userMapper.loginList();
    }

    @Override
    public List<UserLoginLogVO> loginListByUid(Long uid) {
        return userMapper.loginListByUid(uid);
    }

    @Override
    public int addLoginLog(UserLoginLogVO userLoginLogVO) {
        return userMapper.addLoginLog(userLoginLogVO);
    }

}
