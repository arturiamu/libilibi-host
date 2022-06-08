package com.am.adastra.service;

import com.am.adastra.entity.UserCollection;
import com.am.adastra.util.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserCollectionService {

    Result<UserCollection> add(UserCollection UserCollection);

    Result<List<UserCollection>> selectByCategory(Integer userId, String category);

    Result<List<String>> selectAllCollection(Integer userId);

}
