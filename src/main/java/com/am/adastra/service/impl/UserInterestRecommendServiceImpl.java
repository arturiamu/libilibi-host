package com.am.adastra.service.impl;

import com.am.adastra.entity.Video;
import com.am.adastra.service.UserInterestRecommendService;
import com.am.adastra.util.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInterestRecommendServiceImpl implements UserInterestRecommendService {
    @Override
    public Result<List<Video>> list(Long userId) {


        return null;
    }
}
