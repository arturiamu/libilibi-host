package com.am.adastra.service;

import com.am.adastra.entity.dto.VideoOperateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserHistoryService {
    List<VideoOperateDTO> getAll(Integer user_id);

    boolean add(VideoOperateDTO videoOperateDTO);
}
