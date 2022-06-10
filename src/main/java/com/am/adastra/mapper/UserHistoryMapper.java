package com.am.adastra.mapper;

import com.am.adastra.entity.dto.VideoOperateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 13:44
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
public interface UserHistoryMapper {
    List<VideoOperateDTO> getAll(Integer user_id);

    boolean add(VideoOperateDTO videoOperateDTO);
}
