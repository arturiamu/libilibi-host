package com.am.libilibi.service.impl;

import com.am.libilibi.controller.LBUserAPI;
import com.am.libilibi.entity.LBUser;
import com.am.libilibi.mapper.LBUserMapper;
import com.am.libilibi.service.LBUserService;
import com.am.libilibi.utils.ClassExamine;
import com.am.libilibi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/9 19:23
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Component
public class LBUserServiceImpl implements LBUserService {

    @Autowired
    private LBUserMapper lbUserMapper;

    @Override
    public Result<LBUser> register(LBUser lbUser) {
        Result<LBUser> result = new Result<>();
        LBUser getUser = lbUserMapper.getByUsername(lbUser.getUsername());
        if (getUser != null) {
            result.setResultFailed("该用户名已存在！");
            return result;
        }
        lbUserMapper.add(lbUser);
        result.setResultSuccess("注册用户成功！", lbUser);
        return result;
    }

    @Override
    public Result<LBUser> login(LBUser lbUser) {
        Result<LBUser> result = new Result<>();
        LBUser getUser = lbUserMapper.getByUsername(lbUser.getUsername());
        if (getUser == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        if (!getUser.getPassword().equals(lbUser.getPassword())) {
            result.setResultFailed("用户名或者密码错误！");
            return result;
        }
        result.setResultSuccess("登录成功！", getUser);
        return result;
    }

    @Override
    public Result<LBUser> update(LBUser lbUser) throws Exception {
        Result<LBUser> result = new Result<>();
        LBUser getUser = lbUserMapper.getById(lbUser.getId());
        if (getUser == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        ClassExamine.objectOverlap(lbUser, getUser);
        lbUserMapper.update(lbUser);
        result.setResultSuccess("修改用户成功！", lbUser);
        return result;
    }

    @Override
    public Result<LBUser> isLogin(HttpSession session) {
        Result<LBUser> result = new Result<>();
        LBUser sessionUser = (LBUser) session.getAttribute(LBUserAPI.SESSION_NAME);
        if (sessionUser == null) {
            result.setResultFailed("用户未登录！");
            return result;
        }
        LBUser getUser = lbUserMapper.getById(sessionUser.getId());
        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
            result.setResultFailed("用户信息无效！");
            return result;
        }
        result.setResultSuccess("用户已登录！", getUser);
        return result;
    }
}
