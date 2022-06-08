package com.am.adastra.mapper;

import com.am.adastra.entity.Item;
import org.apache.ibatis.annotations.Mapper;

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
    List<Item> getAll();

    Item getById(int id);

    Item getByName(String name);

    Item getByUri(String uri);
}
