package com.am.adastra.service.impl;

import com.am.adastra.entity.Video;
import com.am.adastra.mapper.VideoMapper;
import com.am.adastra.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 13:01
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Component
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<Video> getAll() {
        return videoMapper.getAll();
    }

    @Override
    public Video getByAId(Integer aid) {
        return videoMapper.getByAId(aid);
    }

    @Override
    public List<Video> getByTIds(String typeList) {
        return videoMapper.getByTIds(typeList);
    }

    @Override
    public List<Video> getByTId(Integer tid) {
        return videoMapper.getByTId(tid);
    }

    @Override
    public List<Video> getByPId(Integer pid) {
        return videoMapper.getByPId(pid);
    }

    @Override
    public int like(Integer aid) {
        return videoMapper.like(aid);
    }

    @Override
    public int unlike(Integer aid) {
        return videoMapper.unlike(aid);
    }

    @Override
    public int collect(Integer aid) {
        return videoMapper.collect(aid);
    }

    @Override
    public int unCollect(Integer aid) {
        return videoMapper.unCollect(aid);
    }

    @Override
    public int share(Integer aid) {
        return videoMapper.share(aid);
    }
}
