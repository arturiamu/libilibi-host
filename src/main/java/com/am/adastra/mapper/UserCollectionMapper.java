package com.am.adastra.mapper;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserCollectionMapper {


    /*
     * 通过视频id和用户id   在收藏夹中查找   判断该用户是否收藏了这个视频
     * */
    UserCollectionSimpleVO selectByUserId(Integer uid, Integer aid);


    /*
     * 在数据库中添加新的收藏记录
     * */
    int add(VideoOperateDTO lbUserCollection);

    /*
     * 通过用户id和分类信息查询出用户的收藏信息
     * */
    List<UserCollectionSimpleVO> selectByCollection(Integer uid, String categoryName);



    /*
     * 通过用户id查询出用户所有的收藏信息
     * */
    List<UserCollectionSimpleVO> selectById(Integer uid);

}
