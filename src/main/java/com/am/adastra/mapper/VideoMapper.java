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

    List<Video> getAll();

    Video getByAId(int aid);

    List<Video> getByTIds(String typeList);

    List<Video> getByTId(int tid);

    List<Video> getByPId(int pid);
}
