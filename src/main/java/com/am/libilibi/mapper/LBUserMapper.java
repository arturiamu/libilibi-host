package com.am.libilibi.mapper;

import com.am.libilibi.entity.LBUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/9 19:07
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */

@Mapper
public interface LBUserMapper {
    @Insert("insert into user values(null,#{username},#{password})")
    int add(LBUser lbUser);

    @Update("update user set password=#{password} where id = #{id}")
    int update(LBUser lbUser);

    @Select("select * from user where id = #{id}")
    LBUser getById(int id);

    @Select("select * from user where username = #{username}")
    LBUser getByUsername(String username);
}
