package com.am.adastra.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 马强
 * @Description
 * @create 2022-06-02 10:20
 */
public class JedisUtils {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig, "47.113.222.20", 6379, 10000, "123");
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

}
