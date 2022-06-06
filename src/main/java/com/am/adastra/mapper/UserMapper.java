package com.am.adastra.mapper;

import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDB;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
    int add(User user);

    int addDB(UserDB user);

    int update(User user);

    UserDB getById(Integer id);

    UserDB getByUsername(String username);

    /*
     * 返回所有用户信息
     * */
    @Select("select * from user")
    List<User> list();
}
