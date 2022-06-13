package com.am.adastra.service;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface UserHistoryService {
    List<Video> getAll(Integer user_id);

    List<Video> getLimitByDate(Integer user_id, Date date);

    List<Video> getLimit(Integer user_id, Integer ps);

    boolean add(VideoOperateDTO videoOperateDTO);
}
