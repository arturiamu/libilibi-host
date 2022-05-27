package com.am.adastra.mapper;

import com.am.adastra.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Insert("insert into user(username,password,phone) value(#{username},#{password},#{phone})")
    int add(User user);

    @Update("update user set username=#{username},password=#{password},phone=#{phone} where id=#{id}")
    int update(User user);

    @Select("select id,username,password,phone from user where id=#{id}")
    User getById(int id);

    @Select("select id,username,password,phone from user where username=#{username}")
    User getByUsername(String username);
}
