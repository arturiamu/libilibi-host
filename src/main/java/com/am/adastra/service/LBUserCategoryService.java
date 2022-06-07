package com.am.adastra.service;

import com.am.libilibi.entity.LBUserCategory;
import com.am.libilibi.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LBUserCategoryService {
    Result<LBUserCategory> add(LBUserCategory lbUserCategory);

    Result<List<LBUserCategory>> selectById(Integer userId);
}
