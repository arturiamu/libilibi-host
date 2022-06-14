package com.am.adastra.mapper;


import com.am.adastra.entity.dto.UserCategoryAddDTO;
import com.am.adastra.entity.vo.UserCategorySimpleVO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCategoryMapper {
    /*
    通过收藏夹名字和用户id 在收藏夹分类中查找 判断该用户是否已经创建了这个收藏夹
    UserCategorySimpleVO selectByUserId(Integer userId, String categoryName);


    /*
    在数据库中添加新的收藏夹分类
     */
    int add(UserCategoryAddDTO userCategoryAddDTO);


    /*
    通过用户id和收藏夹名字查询出收藏夹分类
     */
    UserCategorySimpleVO selectByCategory(Integer uid,String CategoryName);

    /*
    *  通过用户id查询出该用户所有的收藏夹分类
    * */
    List<UserCategorySimpleVO> selectById(Integer uid);
}
