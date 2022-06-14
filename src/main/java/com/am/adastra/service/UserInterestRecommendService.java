package com.am.adastra.service;


import com.am.adastra.entity.Video;
import com.am.adastra.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInterestRecommendService {

    Result<List<Video>> list(Long userId,int number);

}
