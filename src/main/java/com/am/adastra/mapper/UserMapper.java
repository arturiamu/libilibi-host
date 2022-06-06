package com.am.adastra.mapper;

import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 11:20
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
public interface UserMapper {

    int addDBO(UserDBO user);

    int updateDBO(User user);

    UserDBO getDBOById(int id);

    UserDBO getDBOByAccount(String account);

    UserDBO getDBOByUsername(String username);
}
