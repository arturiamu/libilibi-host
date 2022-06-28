package com.am.adastra.mapper;

import com.am.adastra.entity.UserVip;
import org.apache.ibatis.annotations.Mapper;

/**
 * @PackagePathcom.am.adastra.mapper
 * @Classname UserVipMapper
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/6/15 21:43
 * @Created by Volcan
 */
@Mapper
public interface UserVipMapper {

    /*根据用户ID查询userVip表中是否有重复用户*/
    UserVip selectById(long id);

    void addVip(UserVip userVip);

    void updataVip(UserVip userVip);
}
