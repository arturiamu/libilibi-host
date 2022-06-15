package com.am.adastra.service.impl;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserHistorySimpleVO;
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
    public List<Video> getAll(Long uid) {
        return userHistoryMapper.getAll(uid);
    }

    @Override
    public List<Video> getLimitByDate(Long uid, Date date) {
        return userHistoryMapper.getLimitByDate(uid, date);
    }

    @Override
    public List<UserHistorySimpleVO> getLimit(Long uid, Integer ps) {
        if (ps > 100) {
            ps = 100;
        }
        return userHistoryMapper.getLimit(uid, ps);
    }

    @Override
    public boolean add(VideoOperateDTO videoOperateDTO) {
        return userHistoryMapper.add(videoOperateDTO) == 1;
    }

    @Override
    public boolean del(Long id) {
        return userHistoryMapper.del(id) == 1;
    }

    @Override
    public boolean clear(Long uid) {
        return userHistoryMapper.clear(uid) >= 0;
    }

    @Override
    public boolean fakeDel(Long id) {
        return userHistoryMapper.fakeDel(id) >= 0;
    }

    @Override
    public boolean fakeClear(Long uid) {
        return userHistoryMapper.fakeClear(uid) >= 0;
    }
}
