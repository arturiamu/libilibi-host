package com.am.adastra.mapper;

import com.am.adastra.entity.dto.VideoOperateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/9 20:54
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
public interface UserLikeMapper {
    int addLike(VideoOperateDTO videoOperateDTO);

    List<VideoOperateDTO> listByUserId(Integer uid);

    VideoOperateDTO isLikeVideo(VideoOperateDTO videoOperateDTO);

    int cancelLike(VideoOperateDTO videoOperateDTO);
}
