package com.am.adastra.service.impl;

import com.am.adastra.mapper.AdviseMapper;
import com.am.adastra.entity.dto.AdviseDTO;
import com.am.adastra.service.AdviseService;
import com.am.adastra.util.Result;
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
    public Result<AdviseDTO> add(AdviseDTO advise) {
        //设置添加时间为当前时间
        advise.setAdviseTime(new Date());
        Result<AdviseDTO> result = new Result<>();
        //调用mapper中的add方法将数据添加到数据库中
        adviseMapper.add(advise);
        result.setSuccess("添加成功", null);
        return result;
    }

    @Override
    public Result<List<AdviseDTO>> selectById(Integer userId) {
        Result<List<AdviseDTO>> result = new Result<>();

        //调用mapper查询数据
        List<AdviseDTO> list = adviseMapper.selectById(userId);
        System.out.println("查询出来的信息： " + list);
        System.out.println(list);
        result.setSuccess("查询成功", list);
        return result;
    }
}
