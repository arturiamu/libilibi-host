package com.am.libilibi.service;

import com.am.libilibi.entity.LBUser;
import com.am.libilibi.entity.LBUserHistory;
import com.am.libilibi.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LBUserHistoryService {

    Result<LBUserHistory> add(LBUserHistory lbUserHistory);

    Result<List<LBUserHistory>> select(Integer userId);
}
