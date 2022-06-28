package com.am.adastra.service.impl;

import com.am.adastra.entity.UserVip;
import com.am.adastra.mapper.UserVipMapper;
import com.am.adastra.service.GetVipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackagePathcom.am.adastra.service.impl
 * @Classname GetVipServiceImpl
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/6/15 17:55
 * @Created by Volcan
 */
@Slf4j
@Component
public class GetVipServiceImpl implements GetVipService {

    @Autowired
    UserVipMapper userVipMapper;

    @Override
    public UserVip selectById(long id) {
        UserVip userVip = userVipMapper.selectById(id);
        return  userVip;
    }

    @Override
    public void addVip(UserVip userVip){
        userVipMapper.addVip(userVip);
    }

    @Override
    public void updataVip(UserVip userVip) {
        userVipMapper.updataVip(userVip);
    }
}
