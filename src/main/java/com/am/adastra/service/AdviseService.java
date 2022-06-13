package com.am.adastra.service;

import com.am.adastra.entity.dto.AdviseDTO;
import com.am.adastra.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 10:25
 * @Description:
 */
@Service
public interface AdviseService {

    int add(AdviseDTO advise);

    List<AdviseDTO> selectById(Integer userId);
}
