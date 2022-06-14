package com.am.adastra.service;


import com.am.adastra.entity.dto.UserCategoryAddDTO;
import com.am.adastra.entity.vo.UserCategorySimpleVO;
import com.am.adastra.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 用户收藏夹的分类
* */
@Service
public interface UserCategoryService {
    Result<UserCategorySimpleVO> add(UserCategoryAddDTO userCategoryAddDTO);

    Result<List<UserCategorySimpleVO>> selectById(Long userId);
}
