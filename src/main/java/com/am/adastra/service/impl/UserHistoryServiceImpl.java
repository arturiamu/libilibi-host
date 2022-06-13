package com.am.adastra.service.impl;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.mapper.UserHistoryMapper;
import com.am.adastra.service.UserHistoryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 13:43
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Component
public class UserHistoryServiceImpl implements UserHistoryService {
    @Resource
    UserHistoryMapper userHistoryMapper;

    @Override
    public List<Video> getAll(Integer user_id) {
        return userHistoryMapper.getAll(user_id);
    }

    @Override
    public List<Video> getLimitByDate(Integer user_id, Date date) {
        return userHistoryMapper.getLimitByDate(user_id, date);
    }

    @Override
    public List<Video> getLimit(Integer user_id, Integer ps) {
        return userHistoryMapper.getLimit(user_id, ps);
    }

    @Override
    public boolean add(VideoOperateDTO videoOperateDTO) {
        return userHistoryMapper.add(videoOperateDTO);
    }
}
