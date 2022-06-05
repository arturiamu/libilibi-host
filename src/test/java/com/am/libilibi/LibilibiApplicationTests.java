package com.am.libilibi;

import com.am.adastra.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;


@SpringBootTest
@SpringBootConfiguration
@MapperScan("com.am.adastra.mapper")
class LibilibiApplicationTests {

    @Autowired
    public UserMapper userMapper;

    @Test
    void json(){
        userMapper.getById(1);
    }

    @Test
    public void reTest() {
        Pattern pattern = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
        String phone = "15911245016";
        System.out.println(pattern.matcher(phone).matches());
    }

    @Test
    void getMD5() {
        String s = "123456admin";
        System.out.println(DigestUtils.md5Hex(s));
    }

    @Test
    void getJson() {
        String[] item = new String[]{};
    }
}
