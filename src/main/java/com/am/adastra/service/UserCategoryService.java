package com.am.adastra.service;

import com.am.adastra.entity.UserCategory;
import com.am.adastra.util.Result;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserCategoryService {
    Result<UserCategory> add(UserCategory lbUserCategory);

    Result<List<UserCategory>> selectById(Integer userId);
}
