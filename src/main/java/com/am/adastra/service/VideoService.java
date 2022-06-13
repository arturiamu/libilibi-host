package com.am.adastra.service;

import com.am.adastra.entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 13:01
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Service
public interface VideoService {
    List<Video> getAll();

    Video getByAId(Integer aid);

    List<Video> getByTIds(String typeList);

    List<Video> getByTId(Integer tid);

    List<Video> getByPId(Integer pid);

    int like(Integer aid);

    int unlike(Integer aid);

    int collect(Integer aid);

    int unCollect(Integer aid);

    int share(Integer aid);
}
