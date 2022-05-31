package com.am.adastra.mapper;

import com.am.adastra.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/30 16:39
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
public interface ItemMapper {
    @Select("SELECT * FROM items;")
    List<Item> getAll();

    @Select("SELECT * FROM items WHERE ID=#{id};")
    Item getById(int id);

    @Select("SELECT * FROM items WHERE NAME=#{name};")
    Item getByName(String name);

    @Select("SELECT * FROM items WHERE URI=#{uri};")
    Item getByUri(String uri);
}
