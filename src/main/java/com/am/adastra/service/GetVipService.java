package com.am.adastra.service;

import com.am.adastra.entity.UserVip;
import org.springframework.stereotype.Service;

/**
 * @PackagePathcom.am.adastra.service
 * @Classname GetVipService
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/6/15 17:52
 * @Created by Volcan
 */
@Service
public interface GetVipService {
    UserVip selectById(long id);

    void addVip(UserVip userVip);

    void updataVip(UserVip userVip);
}
