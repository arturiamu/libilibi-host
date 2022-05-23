package com.am.libilibi.mapper;

import com.am.libilibi.entity.LBUserCollection;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface LBUserCollectionMapper {


    /*
    * 通过视频id和用户id   在收藏夹中查找   判断该用户是否收藏了这个视频
    * */
    @Select("select * from collection where user_id=#{userId} and collection_video_id=#{collectionVideoId}")
    //这里的名称和数据库中的名称不一样，因此我们需要利用Result来声明不一样的变量
    @Result(property = "collectionVideoId", column = "collection_video_id")
    @Result(property = "collectionTime", column = "collection_time")
    @Result(property = "userId", column = "user_id")
    LBUserCollection selectByUserId(Integer userId,Integer collectionVideoId);


    /*
    * 在数据库中添加新的收藏记录
    * */
    @Insert("insert into collection values(null,#{userId},#{collectionVideoId},#{collectionTime},#{category})")
    void add(LBUserCollection lbUserCollection);

    /*
    * 通过用户id和分类信息查询出用户的收藏信息
    * */
    @Select("select * from collection where user_id=#{userId} and category=#{category}")
    //这里的名称和数据库中的名称不一样，因此我们需要利用Result来声明不一样的变量
    @Result(property = "collectionVideoId", column = "collection_video_id")
    @Result(property = "collectionTime", column = "collection_time")
    @Result(property = "userId", column = "user_id")
    List<LBUserCollection> selectByCollection(Integer userId, String category);


}
