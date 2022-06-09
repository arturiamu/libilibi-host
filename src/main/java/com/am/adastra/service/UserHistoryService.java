package com.am.adastra.service;

import com.am.adastra.entity.dto.VideoOperateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserHistoryService {
    boolean add(VideoOperateDTO videoOperateDTO);
}
