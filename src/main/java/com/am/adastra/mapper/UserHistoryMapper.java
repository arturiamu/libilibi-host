package com.am.adastra.mapper;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserHistorySimpleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
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
    List<Video> getAll(Integer user_id);

    List<Video> getLimitByDate(Integer user_id, Date date);

    List<UserHistorySimpleVO> getLimit(Integer user_id, Integer ps);

    boolean add(VideoOperateDTO videoOperateDTO);
}
