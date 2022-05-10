package com.am.libilibi.service;

import com.am.libilibi.entity.LBUser;
import com.am.libilibi.utils.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/9 19:20
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Service
public interface LBUserService {

    Result<LBUser> register(LBUser lbUser);

    Result<LBUser> login(LBUser lbUser);

    Result<LBUser> update(LBUser lbUser) throws Exception;

    Result<LBUser> isLogin(HttpSession session);

}
