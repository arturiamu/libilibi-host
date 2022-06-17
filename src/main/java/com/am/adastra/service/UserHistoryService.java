package com.am.adastra.service;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserHistorySimpleVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface UserHistoryService {
    List<Video> getAll(Long uid);

    List<Video> getLimitByDate(Integer uid, Date date);

    List<UserHistorySimpleVO> getLimit(Long uid, Integer ps);

    boolean add(VideoOperateDTO videoOperateDTO);

    /**
     * 预热类别数据的缓存
     * 将用户的收藏记录预加载到缓存中
     */
    void preloadCache();
}
