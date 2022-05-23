package com.am.libilibi.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/19 9:25
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class JsonBirdUtils {
    private static final String PROXY_V1 = "https://bird.ioliu.cn/v1?url=";

    public static String JBReq(String url) throws Exception {
        System.out.println(PROXY_V1 + url);
        return HttpUtils.httpRequest(PROXY_V1 + url, "GET", null, null);
    }
}
