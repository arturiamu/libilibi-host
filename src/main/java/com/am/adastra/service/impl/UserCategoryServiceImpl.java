package com.am.adastra.service.impl;



import com.am.adastra.entity.dto.UserCategoryAddDTO;
import com.am.adastra.entity.vo.UserCategorySimpleVO;
import com.am.adastra.ex.RepeatException;
import com.am.adastra.mapper.UserCategoryMapper;
import com.am.adastra.service.UserCategoryService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* 用户的收藏夹分类的业务逻辑层
* */
@Slf4j
@Component
public class UserCategoryServiceImpl implements UserCategoryService {
    @Autowired(required = false)
    private UserCategoryMapper userCategoryMapper;

    /*
    * 新建一个收藏夹
    * */
    @Override
    public Result<UserCategorySimpleVO> add(UserCategoryAddDTO userCategoryAddDTO) {
        Result<UserCategorySimpleVO> result = new Result<>();
        log.info("用户id：" + userCategoryAddDTO.getUid());
        log.info("收藏夹分类：" + userCategoryAddDTO.getCategoryName());
        UserCategorySimpleVO userCategory = userCategoryMapper.selectByCategory(userCategoryAddDTO.getUid(), userCategoryAddDTO.getCategoryName());
        if (userCategory != null){
            log.info("重复添加" + userCategory);
//            result.setResultSuccess("收藏夹分类已经存在",userCategory);
            throw new RepeatException("收藏夹已经存在,重复添加");
        }else{
            userCategoryMapper.add(userCategoryAddDTO);
            result.setSuccess("添加成功",null);
        }
        return result;
    }

    @Override
    public Result<List<UserCategorySimpleVO>> selectById(Integer userId) {
        Result<List<UserCategorySimpleVO>> result = new Result<>();

        List<UserCategorySimpleVO> list = userCategoryMapper.selectById(userId);

        log.info("查询出来的信息："+list);
        result.setSuccess("查询成功", list);

        return result;
    }
}
