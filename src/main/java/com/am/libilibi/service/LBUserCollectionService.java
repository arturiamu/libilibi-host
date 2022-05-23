package com.am.libilibi.service;


import com.am.libilibi.entity.LBUserCollection;
import com.am.libilibi.entity.LBUserHistory;
import com.am.libilibi.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LBUserCollectionService {

    Result<LBUserCollection> add(LBUserCollection lbUserCollection);

    Result<List<LBUserCollection>> selectByCollection(Integer userId,String category);
}
