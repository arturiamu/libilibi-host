package com.am.adastra;

import com.alibaba.fastjson.JSONObject;
import com.am.adastra.entity.Video;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.util.SMSUtil;
import com.am.adastra.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;
import java.util.regex.Pattern;

@AutoConfigureMockMvc
@SpringBootTest(classes = AdAstraApplication.class)
class AdAstraApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private SMSUtil smsUtil;


    @Test
    public void mail() {
        String to = "1743089727@qq.com";
        emailUtil.sendRegisterMail(to, null);
    }

    @Test
    public void reTest() {
        Pattern pattern = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
        String phone = "15911245016";
        System.out.println(pattern.matcher(phone).matches());
    }

    @Test
    public void sms() {
        System.out.println(smsUtil.sendSMS("123", null));
        System.out.println(smsUtil.sendSMS("abc", null));
        System.out.println(smsUtil.sendSMS("15911245016", null));
    }

    @Test
    public void reMailTest() {
        Pattern pattern = Pattern.compile("^\\s*\\w+(?:\\.?[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        String mail = "174308972@qq.com";
        System.out.println(pattern.matcher(mail).matches());
    }

    @Autowired
    public UserMapper userMapper;

    @Test
    void getMD5() {
        String s = "123456admin";
        System.out.println(DigestUtils.md5Hex(s));
    }

    @Test
    void getJson() {
        String[] item = new String[]{};
    }


    @Test
    void testGetPid() {
        Integer pid = 155;
        Map entries = redisTemplate.opsForHash().entries("video:811566062");
        System.out.println(entries);
    }


    @Test
    void getAllVideo() {
        List list = new LinkedList();
        long satrt = System.currentTimeMillis();
        System.out.println(satrt);
        Set keys = redisTemplate.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key);
            Map entries = redisTemplate.opsForHash().entries(key);
            Video video = mapToObject(entries, Video.class);
            System.out.println(video);
        }
//        System.out.println(list);
//        long end=System.currentTimeMillis();
//        System.out.println("所用时间："+(end-satrt));
    }


    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        String jsonStr = JSONObject.toJSONString(map);
        return JSONObject.parseObject(jsonStr, clazz);
    }
}
