package com.am.adastra.service;

import com.am.adastra.entity.UserHistory;
import com.am.adastra.pojo.DTO.UserHistoryAddDTO;
import com.am.adastra.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserHistoryService {
    Result<UserHistory> add(UserHistoryAddDTO UserHistory);

    Result<List<UserHistory>> select(Integer userId);
}
