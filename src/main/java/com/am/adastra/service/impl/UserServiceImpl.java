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
import com.am.adastra.util.GetIpInfo;
import com.am.adastra.util.POJOUtils;
import com.am.adastra.app.VideoPool;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

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
    UserAvatarService userAvatarService;

    @Resource
    private UserCategoryService userCategoryService;

    @Resource
    private UserMessageService userMessageService;

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
    public User login(User user, String ip) {
        UserDBO getUser = userMapper.getDBOByAccount(user.getAccount());
        if (getUser == null) {
            throw new LoginException("该账号不存在");
        }
        if (!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
            throw new LoginException("密码错误");
        }
        if ( ! "normal".equals(getUser.getState())){
            throw new LoginException("该账户已冻结，请联系管理员");
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
        log.info("new:{}", new_);
        log.info("old:{}", old);
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
    @Override
    public List<Map<String,Object>> ipList() {
        List<UserVO> userVOList = userMapper.list();
        List<Map<String,Object>> litMap = new ArrayList<>();
        for(UserVO user : userVOList){
            log.info(user.getUsername());
            Long userId = user.getId();
            UserLoginLogVO userLoginLogVOList = userMapper.ipList(userId);
            if (userLoginLogVOList == null) continue;
//            https://www.ip138.com/iplookup.asp?ip=116.249.112.73&action=2
            String ip = userLoginLogVOList.getIp();
            String city = GetIpInfo.getCity(ip);

            Integer city1 = city.indexOf("市");
            Integer city2 = city.indexOf("省");
            String cityMunicipal = city.substring(city2+1,city1);
            log.info(cityMunicipal);

            /*
            * 先将所有城市的人员信息放到map中，然后再遍历一遍，将他们放入list<Map<String,Integer>>中
            * */
            Map<String,Integer> map = new HashMap<>();
            if (!map.containsKey(cityMunicipal)){
                map.put(cityMunicipal,1);
            }else {
                map.put(cityMunicipal,map.get(cityMunicipal)+1);
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String mapKey = entry.getKey();
                Integer mapValue = entry.getValue();
                Map<String,Object> cityMap = new HashMap<>();
                cityMap.put("name",mapKey);
                cityMap.put("value",mapValue);
                litMap.add(cityMap);
            }
        }
        return litMap;
    }

}
