package com.am.adastra.mapper;

import com.am.adastra.entity.Advise;
import com.am.adastra.pojo.DTO.AdviseAddDTO;
import com.am.adastra.util.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 10:16
 * @Description:
 */
@Mapper
public interface AdviseMapper {
    //添加用户建议
    int add(AdviseAddDTO advise);
    //查询用户建议
    List<Advise> selectById(Integer userId);

}
