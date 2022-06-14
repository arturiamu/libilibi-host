package com.am.adastra.service;


import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import com.am.adastra.util.Result;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserCollectionService {

    Result<Void> add(VideoOperateDTO videoOperateDTO);

    Result<List<UserCollectionSimpleVO>> selectByCollection(Long userId, String category);
}
