package com.am.adastra.mapper;

import com.am.adastra.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/25 15:20
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
public interface VideoMapper {
    @Select("select * from videos")
    List<Video> getAll();

    @Select("select * from videos where aid=#{aid}")
    Video getByAId(int aid);

    @Select("select * from videos where tid in ${subTypes}")
    List<Video> getByTIds(String typeList);

    @Select("select * from videos where tid=#{tid}")
    List<Video> getByTId(int tid);

    @Select("select * from videos where pid=${pid}")
    List<Video> getByPId(int pid);
}
