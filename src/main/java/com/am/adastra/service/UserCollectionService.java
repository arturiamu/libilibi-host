package com.am.adastra.service;


import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserCollectionService {

    void add(VideoOperateDTO videoOperateDTO);

    List<UserCollectionSimpleVO> selectByCollection(Long userId, String category);

    Map<String ,List<UserCollectionSimpleVO>> selectCategory(Long userId);
}
