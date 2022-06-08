package com.am.adastra.mapper;

import com.am.adastra.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/*
* 管理员的mapper
* */
@Mapper
public interface AdminMapper {

    /*
    * 通过用户名查询用户信息
    * */
    Admin getByUsername(String username);



}
