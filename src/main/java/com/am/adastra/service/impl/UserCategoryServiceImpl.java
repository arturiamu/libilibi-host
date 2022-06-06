package com.am.adastra.service.impl;


import com.am.adastra.entity.UserCategory;
import com.am.adastra.mapper.UserCategoryMapper;
import com.am.adastra.service.UserCategoryService;
import com.am.adastra.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UserCategoryServiceImpl implements UserCategoryService {
    @Autowired(required = false)
    private UserCategoryMapper mapper;

    //1.通过用户id 添加收藏夹分类 判断是否存在
    @Override
    public Result<UserCategory> add(UserCategory lbUserCategory) {
        Result<UserCategory> result = new Result<>();
        System.out.println("用户id：" + lbUserCategory.getUserId());
        System.out.println("收藏夹分类：" + lbUserCategory.getCategoryName());
        UserCategory userCategory = mapper.selectByUserId(lbUserCategory.getUserId(), lbUserCategory.getCategoryName());
        if (userCategory != null){
            System.out.println("重复添加  :::: " + userCategory);
            result.setSuccess("收藏夹分类已经存在",userCategory);
            return result;
        }else{
            mapper.add(lbUserCategory);
            result.setSuccess("添加成功",null);
        }
        return result;
    }


    @Override
    public Result<List<UserCategory>> selectById(Integer userId){
        Result<List<UserCategory>> result = new Result<>();

        List<UserCategory> list = mapper.selectById(userId);

        System.out.println("查询出来的信息："+list);
        result.setSuccess("查询成功", list);

        return result;
    }
}
