package com.am.adastra.service.impl;

import com.am.adastra.entity.UserCollection;
import com.am.adastra.ex.UserCollectionRepeatException;
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
        //设置当前信息状态为正常状态  normal
        userCollection.setState("normal");

        Result<UserCollection> result = new Result<>();
        //1.先判断该用户  收藏夹中是否存在该视频（通过视频id和用户id查询）   存在就不添加
        System.out.println("用户ID ： " + userCollection.getUserId() );
        System.out.println("视频ID ： " + userCollection.getVideoId() );
        UserCollection userNewCollection = mapper.selectByUserId(userCollection.getUserId(),userCollection.getVideoId());
        if (userNewCollection != null){
            System.out.println("重复添加"+userCollection);
            throw new UserCollectionRepeatException("重复添加,视频已存在收藏夹");
        }else {
            //2.收藏夹中不存在此视频  调用mapper中的方法添加进入
            mapper.add(userCollection);
            result.setSuccess("添加成功",null);
        }
        return result;
    }

    /*
    * 通过用户id和收藏夹名字查询出该用户该收藏夹中的所有视频
    * */
    @Override
    public Result<List<UserCollection>> selectByCategory(Integer userId, String category) {
        Result<List<UserCollection>> result = new Result<>();

        //调用mapper查询数据
        List<UserCollection> list = mapper.selectByCategory(userId,category);

        System.out.println("查询出来的信息： "+list);

        result.setSuccess("查询成功",list);

        return result;
    }

    /*
     * 查看当前用户的收藏夹分类
     * */
    @Override
    public Result<List<String>> selectAllCollection(Integer userId) {
        Result<List<String>> result = new Result<>();

        List<String> list = mapper.selectAllCollection(userId);
        result.setSuccess(list);
        return result;
    }
}
