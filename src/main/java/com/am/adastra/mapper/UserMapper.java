package com.am.adastra.mapper;

import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDB;
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
    @Insert("insert into users(username,password,account,items) value(#{username},#{password},#{account},#{jsItems})")
    int add(User user);

    @Insert("insert into users(username,password,account,items) value(#{username},#{password},#{account},#{jsItems})")
    int addDB(UserDB user);

    @Update("update users set username=#{username},password=#{password},account=#{account} where id=#{id}")
    int update(User user);

    @Select("select id,username,password,account,items as jsStr,state from users where id=#{id}")
    User getById(int id);

    @Select("select id,username,password,account,items as jsItems,state from users where id=#{id}")
    UserDB getByID(int id);

    @Select("select id,username,password,account,items as jsStr,state from users where username=#{username}")
    User getByUsername(String username);
}
