package com.am.adastra;

import com.am.adastra.mapper.UserMapper;
import com.am.adastra.util.SMSUtil;
import com.am.adastra.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

@SpringBootTest(classes = AdAstraApplication.class)
class AdAstraApplicationTests {

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
}
