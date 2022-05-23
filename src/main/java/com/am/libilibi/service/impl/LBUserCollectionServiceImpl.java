package com.am.libilibi.service.impl;

import com.am.libilibi.entity.LBUserCollection;
import com.am.libilibi.entity.LBUserHistory;
import com.am.libilibi.mapper.LBUserCollectionMapper;
import com.am.libilibi.service.LBUserCollectionService;
import com.am.libilibi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class LBUserCollectionServiceImpl implements LBUserCollectionService {

    @Autowired(required = false)
    private LBUserCollectionMapper mapper;

    //添加用户收藏夹到数据库中
    @Override
    public Result<LBUserCollection> add(LBUserCollection lbUserCollection) {
        //设置添加时间为当前时间
        lbUserCollection.setCollectionTime(new Date());

        Result<LBUserCollection> result = new Result<>();
        //1.先判断该用户  收藏夹中是否存在该视频（通过视频id和用户id查询）   存在就不添加
        System.out.println("用户ID ： " + lbUserCollection.getUserId() );
        System.out.println("视频ID ： " + lbUserCollection.getCollectionVideoId() );
        LBUserCollection userCollection = mapper.selectByUserId(lbUserCollection.getUserId(),lbUserCollection.getCollectionVideoId());
        if (userCollection != null){
            System.out.println("重复添加"+userCollection);
            result.setResultSuccess("视频已存在收藏夹",userCollection);
            return result;
        }else {
            //2.收藏夹中不存在此视频  调用mapper中的方法添加进入
            mapper.add(lbUserCollection);
            result.setResultSuccess("添加成功",null);
        }
        return result;
    }


    /*
    * 通过用户传递过来的分类信息查询他的收藏夹中属于这一分类的所有视频信息
    * 通过session对象获取当前用户的用户id  再通过用户id和分类信息一起寻找视频信息
    * */
    @Override
    public Result<List<LBUserCollection>> selectByCollection(Integer userId,String category) {

        Result<List<LBUserCollection>> result = new Result<>();

        //调用mapper查询数据
        List<LBUserCollection> list = mapper.selectByCollection(userId,category);

        System.out.println("查询出来的信息： "+list);

        result.setResultSuccess("查询成功",list);

        return result;
    }


}
