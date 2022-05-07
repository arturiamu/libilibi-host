package com.am.libilibi.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.am.libilibi.entity.GeneralVideo;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/5 21:53
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class JsonUtils {

    public static List<GeneralVideo> jsStrToGeneralVideo(String json){
        JSONObject jsonObject = JSON.parseObject(json);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray archives = data.getJSONArray("archives");
        List<GeneralVideo> gvs = JSONObject.parseArray(archives.toJSONString(), GeneralVideo.class);
        return gvs;
    }
}
