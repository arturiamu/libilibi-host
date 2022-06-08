package com.am.adastra.mapper;

import com.am.adastra.entity.UserCollection;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCollectionMapper {
    /*
     * 通过视频id和用户id   在收藏夹中查找判断该用户是否收藏了这个视频
     * */
    UserCollection selectByUserId(Integer userId, Integer videoId);


    /*
     * 在数据库中添加新的收藏记录
     * */
    void add(UserCollection lbUserCollection);

    /*
     * 通过用户id和分类信息查询出用户的收藏信息
     * */
/*    @Select("select * from collection where user_id=#{userId} and category=#{category}")
    //这里的名称和数据库中的名称不一样，因此我们需要利用Result来声明不一样的变量
    @Result(property = "collectionVideoId", column = "collection_video_id")
    @Result(property = "collectionTime", column = "collection_time")
    @Result(property = "userId", column = "user_id")*/
    List<UserCollection> selectByCategory(Integer userId, String category);



    /*
     * 通过用户id查询出用户所有的收藏信息
     * */
 /*   @Select("select * from collection where user_id=#{userId}")
    //这里的名称和数据库中的名称不一样，因此我们需要利用Result来声明不一样的变量
    @Result(property = "collectionVideoId", column = "collection_video_id")
    @Result(property = "collectionTime", column = "collection_time")
    @Result(property = "userId", column = "user_id")*/
    List<UserCollection> selectById(Integer userId);



/*    * 查询该用户所有的收藏夹分类
    * */
    List<String> selectAllCollection(Integer userId);
}
