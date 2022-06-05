package com.am.adastra.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 马强
 * @Description
 * @create 2022-06-02 16:34
 */
public class JedisOp {
    private final Jedis resource = JedisUtils.getJedisPool().getResource();

    public void setJson(String key, String value) {
        resource.set(key, value);
    }

    public String getJson(String key) {
        return resource.get(key);
    }

    public void setJson(String key, String value, int timeOut) {
        resource.setex(key, timeOut, value);
    }

}
