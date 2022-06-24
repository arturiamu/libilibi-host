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

    List<Video> getLimitByDate(Long uid, Date date);

    List<UserHistorySimpleVO> getLimit(Long uid, Integer ps);

    boolean add(VideoOperateDTO videoOperateDTO);

    boolean del(Long id);  // 真实删除

    boolean clear(Long uid);  //   真实清空

    boolean fakeDel(Long id);  // 真实删除

    boolean fakeClear(Long uid);  //   真实清空

    /**
     * 预热类别数据的缓存
     * 将用户的收藏记录预加载到缓存中
     */
    void preloadCache();
}
