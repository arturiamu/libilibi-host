package com.am.libilibi.service.impl;


import com.am.libilibi.entity.LBUser;
import com.am.libilibi.entity.LBUserHistory;
import com.am.libilibi.mapper.LBUserHistoryMapper;
import com.am.libilibi.mapper.LBUserMapper;
import com.am.libilibi.service.LBUserHistoryService;
import com.am.libilibi.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class LBUserHistoryServiceImpl implements LBUserHistoryService {

    @Autowired(required = false)
    private LBUserHistoryMapper lbUserHistoryMapper;

    /*
    *实现用户浏览后   添加历史浏览记录的方法
     */
    @Override
    public Result<LBUserHistory> add(LBUserHistory lbUserHistory) {
        //设置添加时间为当前时间
        lbUserHistory.setTime(new Date());

        Result<LBUserHistory> result = new Result<>();

        //调用mapper中的add方法添加数据到数据库中
        lbUserHistoryMapper.add(lbUserHistory);
        result.setResultSuccess("添加成功！", null);
        return result;
    }

    /*
    * 通过用户id查询用户的历史浏览记录
    * */
    @Override
    public Result<List<LBUserHistory>> select(Integer userId) {
        Result<List<LBUserHistory>> result = new Result<>();

        List<LBUserHistory> list = lbUserHistoryMapper.select(userId);
        System.out.println(userId);
        System.out.println(list);
        result.setResultSuccess("查询成功！", lbUserHistoryMapper.select(userId));

        return result;
    }


}
