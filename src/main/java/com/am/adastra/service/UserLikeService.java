package com.am.adastra.service;

import com.am.adastra.entity.dto.VideoOperateDTO;
import org.springframework.stereotype.Service;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/9 16:01
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Service
public interface UserLikeService {
    boolean addLike(VideoOperateDTO videoOperateDTO);

    boolean cancelLike(VideoOperateDTO videoOperateDTO);
}
