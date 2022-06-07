package com.am.adastra.util;

import com.am.adastra.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 马强
 * @Description
 * @create 2022-06-07 13:30
 */
public class VideosUtilsRedis {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setVide(Video vide){
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","aid",vide.getAid());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","pid",vide.getPid());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","tid",vide.getTid());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","tname",vide.getTname());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","pic",vide.getPic());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","title",vide.getTitle());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","desc",vide.getDesc());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","view",vide.getView());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","danmaku",vide.getDanmaku());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","reply",vide.getReply());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","favorite",vide.getFavorite());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","coin",vide.getCoin());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","share",vide.getShare());
        redisTemplate.opsForHash().put("video:"+vide.getAid()+"","like",vide.getLike());
    }


}
