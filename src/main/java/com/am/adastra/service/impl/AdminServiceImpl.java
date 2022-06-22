package com.am.adastra.service.impl;

import com.am.adastra.controller.Admin.AdminController;
import com.am.adastra.entity.Admin;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.vo.AdminVO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.ex.LoginException;
import com.am.adastra.ex.SystemException;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.mapper.AdminMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    /*
     * 处理管理员登录
     * */
    @Override
    public AdminVO login(Admin admin) {
        AdminVO adminUser = null;
        try{//当数据库查出多条数据的时候，就捕获异常抛出
            adminUser = adminMapper.getByUsername(admin.getUsername());
        }catch (Exception e){
            throw new SystemException("用户名重复，请联系管理员");
        }

        //2.管理员存在就判断密码是否正确
        if (adminUser == null) {
            //说明用户名不正确
            throw new LoginException("用户名不存在");
        } else {
            if (admin.getPassword().equals(adminUser.getPassword())) {
                return adminUser;
            }else {
                throw new LoginException("密码错误");
            }
        }
    }

    /*
    * 判断用户是否登录
    * */
    @Override
    public Admin isLogin(HttpSession session) {
        Admin sessionUser = (Admin) session.getAttribute(AdminController.USER_INFO_SESSION);
        if (sessionUser == null) {
            throw new UserNotLoginException("用户未登录");
        }
        return sessionUser;
    }



    /**
     * 分页查询用户信息
     * @param cur  第几页
     * @param pageSize  每页有多少条信息
     * @param username
     * @return
     */
    @Override
    public List<UserVO> selectUser(int cur, int pageSize, String username) {
        log.info("分页查询用户信息  selectUser() ---> ");
        log.info("第几页"+cur);
        log.info("每页有多少"+pageSize);

        cur = cur <=1 ? 0 : cur-1;
        //调用userMapper层查询数据
        List<UserVO> userList = userMapper.selectPage(cur*pageSize,pageSize,username);

        log.info("分页查询到的数据 -->" + userList);
        return userList;
    }

    @Override
    public int updataUser(UserDBO userDBO) {
        String password = userDBO.getPassword();
        userDBO.setPassword(DigestUtils.md5Hex(password));
        int i = adminMapper.updateUser(userDBO);
        return i;
    }

    @Override
    public Integer selectTotal() {
        Integer total = adminMapper.selectTotal();
        return total;
    }

    /**
     * 改变用户状态
     * @param uid
     */
    @Override
    public void changeState(Long uid) {
        int ans = userMapper.changeState(uid);
        if (ans != 1){
            //说明修改失败
            throw new SystemException("修改失败,请稍后再试");
        }
    }


}
