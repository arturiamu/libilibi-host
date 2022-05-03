package com.am.libilibi.mapper;

import com.am.libilibi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/1 17:03
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
@Repository
public interface UserMapper {
    public User getUserByName(@Param("username") String username);
}
