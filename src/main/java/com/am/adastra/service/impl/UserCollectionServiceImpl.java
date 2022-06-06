package com.am.adastra.service.impl;

import com.am.adastra.entity.UserCollection;
import com.am.adastra.mapper.UserCollectionMapper;
import com.am.adastra.service.UserCollectionService;
import com.am.adastra.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class UserCollectionServiceImpl implements UserCollectionService {
    @Autowired(required = false)
    private UserCollectionMapper mapper;

    //添加用户收藏夹到数据库中
    @Override
    public Result<UserCollection> add(UserCollection userCollection) {
        //设置添加时间为当前时间
        userCollection.setCollectionTime(new Date());

        Result<UserCollection> result = new Result<>();
        //1.先判断该用户  收藏夹中是否存在该视频（通过视频id和用户id查询）   存在就不添加
        System.out.println("用户ID ： " + userCollection.getUserId() );
        System.out.println("视频ID ： " + userCollection.getCollectionVideoId() );
        UserCollection userNewCollection = mapper.selectByUserId(userCollection.getUserId(),userCollection.getCollectionVideoId());
        if (userNewCollection != null){
            System.out.println("重复添加"+userCollection);
            result.setSuccess("视频已存在收藏夹",userCollection);
            return result;
        }else {
            //2.收藏夹中不存在此视频  调用mapper中的方法添加进入
            mapper.add(userCollection);
            result.setSuccess("添加成功",null);
        }
        return result;
    }

    @Override
    public Result<List<UserCollection>> selectByCollection(Integer userId, String category) {
        return null;
    }
}
