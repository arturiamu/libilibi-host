package com.am.adastra.service;

import com.am.adastra.util.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface InterestService {
    Result<ArrayList<Integer>> list(Integer userId);
}
