package com.am.adastra.util;

import com.alibaba.fastjson.JSONObject;
import com.am.adastra.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author 马强
 * @Description
 * @create 2022-06-07 13:30
 */
@Component
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

    public void setVideAll(Video vide){
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("aid",vide.getAid());
       maps.put("pid",vide.getPid());
        maps.put("tid",vide.getTid());
        maps.put("tname",vide.getTname());
        maps.put("pic",vide.getPic());
        maps.put("title",vide.getTitle());
        maps.put("desc",vide.getDesc());
        maps.put("view",vide.getView());
        maps.put("danmaku",vide.getDanmaku());
        maps.put("reply",vide.getReply());
        maps.put("favorite",vide.getFavorite());
        maps.put("coin",vide.getCoin());
        maps.put("share",vide.getShare());
        maps.put("like",vide.getLike());
        redisTemplate.opsForHash().putAll("video:"+vide.getAid()+"",maps);
    }

    public List<Video> getAllVideo(){
        List<Video> list= new LinkedList();
        Set keys = redisTemplate.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String key=iterator.next();
            Map entries = redisTemplate.opsForHash().entries(key);
            Video video = mapToObject(entries, Video.class);
            list.add(video);
        }
        return  list;
    }



    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        String jsonStr = JSONObject.toJSONString(map);
        return JSONObject.parseObject(jsonStr, clazz);
    }


}
