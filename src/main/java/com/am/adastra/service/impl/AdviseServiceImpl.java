package com.am.adastra.service.impl;

import com.am.adastra.entity.Advise;
import com.am.adastra.mapper.AdviseMapper;
import com.am.adastra.pojo.DTO.AdviseAddDTO;
import com.am.adastra.service.AdviseService;
import com.am.adastra.util.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 10:33
 * @Description:
 */
@Component
public class AdviseServiceImpl implements AdviseService {
    @Autowired
    private AdviseMapper adviseMapper;

    @Override
    public Result<Advise> add(AdviseAddDTO advise) {
        //设置添加时间为当前时间
        advise.setAdviseTime(new Date());

        Result<Advise> result = new Result<>();

        //调用mapper中的add方法将数据添加到数据库中
        adviseMapper.add(advise);
        result.setSuccess("添加成功",null);

        return result;
    }

    @Override
    public Result<List<Advise>> selectById(Integer userId) {
        Result<List<Advise>> result = new Result<>();

        //调用mapper查询数据
        List<Advise> list = adviseMapper.selectById(userId);
        System.out.println("查询出来的信息： "+list);
        System.out.println(list);
        result.setSuccess("查询成功",list);
        return result;
    }
}
