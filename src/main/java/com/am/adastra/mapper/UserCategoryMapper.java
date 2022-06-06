package com.am.adastra.mapper;


import com.am.adastra.entity.UserCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCategoryMapper {
    /*x
      通过收藏夹名字和用户id 在收藏夹分类中查找 判断该用户是否已经创建了这个收藏夹
     */
    @Select("select * from category where user_id=#{userId} and category_name=#{categoryName}")
    @Result(property = "userId",column = "user_id")
    @Result(property = "categoryName",column = "category_name")
    UserCategory selectByUserId(Integer userId, String categoryName);


    /*
    在数据库中添加新的收藏夹分类
     */
    @Insert("insert into category values(null,#{categoryName},#{userId},#{remarks})")
    void add(UserCategory lbUserCategory);


    /*
    通过用户id和收藏夹名字查询出收藏夹分类
     */
    @Select("select * from category where user_id=#{userId}")
    @Result(property = "userId",column = "user_id")
    @Result(property = "categoryName",column = "category_name")
    List<UserCategory> selectById(Integer userId);
}
