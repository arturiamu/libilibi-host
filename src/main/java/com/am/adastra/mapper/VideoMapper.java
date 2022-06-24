package com.am.adastra.mapper;

import com.am.adastra.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

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

    Video getByAId(Integer aid);

    List<Video> search(String keyword, int st, int ps);

    List<Video> getByTIds(String typeList);

    List<Video> getByTId(Integer tid);

    List<Video> getByPId(Integer pid);

    int like(Integer aid);

    int unlike(Integer aid);

    int collect(Integer aid);

    int unCollect(Integer aid);

    int share(Integer aid);
}
