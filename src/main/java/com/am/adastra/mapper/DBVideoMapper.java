package com.am.adastra.mapper;

import com.am.adastra.entity.DBVideo;
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
public interface DBVideoMapper {
    @Select("select * from videos")
    List<DBVideo> getAll();

    @Select("select * from videos where aid=#{aid}")
    DBVideo getByAId(String aid);

    @Select("select * from videos where tid in ${subTypes}")
    List<DBVideo> getByTIds(String typeList);

    @Select("select * from videos where tid=#{tid}")
    List<DBVideo> getByTId(String tid);

    @Select("select * from videos where pid=${pid}")
    List<DBVideo> getByPId(String pid);
}
