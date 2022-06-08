package com.am.adastra.service.impl;

import com.am.adastra.entity.UserHistory;
import com.am.adastra.mapper.UserHistoryMapper;
import com.am.adastra.pojo.DTO.UserHistoryAddDTO;
import com.am.adastra.service.UserHistoryService;
import com.am.adastra.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public class UserHistoryServiceImpl implements UserHistoryService {
    @Autowired(required = false)
    private UserHistoryMapper userHistoryMapper;

    /*
     *实现用户浏览后   添加历史浏览记录的方法
     */
    @Override
    public Result<UserHistory> add(UserHistoryAddDTO userHistory) {
        //设置添加时间为当前时间
        userHistory.setTime(new Date());
        //设置当前信息状态为正常状态  normal
        userHistory.setState("normal");

        Result<UserHistory> result = new Result<>();

        //调用mapper中的add方法添加数据到数据库中
        userHistoryMapper.add(userHistory);
        result.setSuccess("添加成功！", null);
        return result;
    }

    @Override
    public Result<List<UserHistory>> select(Integer userId) {
        Result<List<UserHistory>> result = new Result<>();

        List<UserHistory> list = userHistoryMapper.selectById(userId);
        System.out.println(userId);
        System.out.println(list);
        result.setSuccess("查询成功！", list);

        return result;
    }
}
