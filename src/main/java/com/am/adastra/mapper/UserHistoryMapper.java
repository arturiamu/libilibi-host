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
    List<Video> getAll(Long uid);

    List<Video> getLimitByDate(Long uid, Date date);

    List<UserHistorySimpleVO> getLimit(Long uid, Integer ps);

    List<UserHistorySimpleVO> getLimitAdmin(Long uid, Integer ps);

    int add(VideoOperateDTO videoOperateDTO);

    int del(Long id);  // 真实删除

    int clear(Long uid);  //   真实清空

    int fakeDel(Long id);  // 真实删除

    int fakeClear(Long uid);  //   真实清空


}
