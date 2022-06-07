package com.am.adastra.service.impl;

import com.am.libilibi.entity.LBUserCategory;
import com.am.libilibi.mapper.LBUserCategoryMapper;
import com.am.libilibi.service.LBUserCategoryService;
import com.am.libilibi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LBUserCategoryServiceImpl implements LBUserCategoryService {
    @Autowired(required = false)
    private LBUserCategoryMapper mapper;

    //1.通过用户id 添加收藏夹分类 判断是否存在
    @Override
    public Result<LBUserCategory> add(LBUserCategory lbUserCategory) {
        Result<LBUserCategory> result = new Result<>();
        System.out.println("用户id：" + lbUserCategory.getUserId());
        System.out.println("收藏夹分类：" + lbUserCategory.getCategoryName());
        LBUserCategory userCategory = mapper.selectByUserId(lbUserCategory.getUserId(), lbUserCategory.getCategoryName());
        if (userCategory != null){
            System.out.println("重复添加" + userCategory);
            result.setResultSuccess("收藏夹分类已经存在",userCategory);
            return result;
        }else{
            mapper.add(lbUserCategory);
            result.setResultSuccess("添加成功",null);
        }
        return result;
    }


    @Override
    public Result<List<LBUserCategory>> selectById(Integer userId){
        Result<List<LBUserCategory>> result = new Result<>();

        List<LBUserCategory> list = mapper.selectById(userId);

        System.out.println("查询出来的信息："+list);
        result.setResultSuccess("查询成功", list);

        return result;
    }
}
