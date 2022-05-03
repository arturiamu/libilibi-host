package com.am.libilibi.mapper;

import com.am.libilibi.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/4/29 15:32
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
@Repository
public interface VideoMapper {
    public List<Video> refresh(int s,int e);
}
